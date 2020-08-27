/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.presentacion;


import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.config.DocumentoConfig;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Opcion;
import ec.edu.saltos.modelo.OpcionPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOOpcion;
import ec.edu.saltos.persistencia.DAOOpcionPerfil;
import ec.edu.saltos.persistencia.DAOPerfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;


/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanMenu implements Serializable{
    
    private UsuarioAcceso usuarioAcceso;
    private OpcionPerfil permiso;
    private Perfil perfilCorrespondiente;
    
    private AsignarPerfil perfilAccesado;
    
    private MenuModel model;
    private boolean mostrarMenu;
    

    public BeanMenu() throws Exception {
        mostrarMenu = false;
        ControlSesion sesion = new ControlSesion();
        
        //listaOpciones=new ArrayList<>();
        usuarioAcceso=new UsuarioAcceso();
        perfilCorrespondiente=new Perfil();
        
        if (sesion.obtenerEstadoSesionUsuario()){
            model = new DefaultMenuModel();
            perfilCorrespondiente=new DAOPerfil().obtenerPorId(Integer.parseInt(sesion.obtenerIdPerfilSesionActiva()));
            if (perfilCorrespondiente != null) {
                //TODO - activar cuando sean ACT
                if (perfilCorrespondiente.getPerfilEstatus().equals(EstadosConfig.PERFIL_EST_ACTIVADO.getCodigo())) {
                    Opcion raiz = new DAOOpcion().obtenerOrigen();
                    if (raiz != null) {
                        mostrarMenu = true;
                        crearMenuDinamico(model, raiz.getIdOpcion(), Integer.parseInt(sesion.obtenerIdPerfilSesionActiva()));
                    } else {
                        //LOG.info("Sistema sin aplicaciones. Contactese con el Administrador del sistema.");
                    }

                }
            }
        }
        
    }
    
    public List<Opcion> listarOpcionesPermitidos(Integer _idRecursoPadre,Integer _idPerfil){
        List<Opcion> listaOpciones=new ArrayList<>();
        List<OpcionPerfil> listaOpcionPerfil;
        
        DAOOpcion daoOpcion=new DAOOpcion();
        DAOOpcionPerfil daoOpcionesPerfil=new DAOOpcionPerfil();
        Opcion opcP=new Opcion(); 
        
        listaOpcionPerfil=daoOpcionesPerfil.opcionesPorPerfil(_idPerfil);
        
        opcP=daoOpcion.obtenerPorId(_idRecursoPadre);
        for(OpcionPerfil op1:listaOpcionPerfil){
            if(op1.getOpcion().getIdOpcion()==opcP.getIdOpcion()){
                listaOpcionPerfil.remove(opcP);
            }
        }

        for(OpcionPerfil op:listaOpcionPerfil){
            Opcion opcion=daoOpcion.obtenerOpcionesSudo(op.getOpcion().getIdOpcion(),_idRecursoPadre);
            if(opcion!=null){
                listaOpciones.add(opcion);
            }
        }
        
        return listaOpciones;
    }
    
    public void crearMenuDinamico(Object menuYgdrasil, Integer _idRecursoPadre, Integer _idPerfil) throws Exception {

        List<Opcion> recursos =listarOpcionesPermitidos(_idRecursoPadre, _idPerfil);
        
        recursos.stream().forEach(rx -> {
            if (rx.getOpcEstatus().equals(EstadosConfig.OPCION_CATEGORIA.getCodigo())) {
                DefaultSubMenu itemMenu = new DefaultSubMenu(rx.getOpcNombre());
                itemMenu.setIcon(rx.getOpcIcono());
                if (menuYgdrasil instanceof DefaultMenuModel) {
                    ((DefaultMenuModel) menuYgdrasil).getElements().add(itemMenu);
                    try {
                        crearMenuDinamico(itemMenu, rx.getIdOpcion(), _idPerfil);
                    } catch (Exception ex) {
                        Logger.getLogger(BeanMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    ((DefaultSubMenu) menuYgdrasil).getElements().add(itemMenu);
                    try {
                        crearMenuDinamico(itemMenu, rx.getIdOpcion(), _idPerfil);
                    } catch (Exception ex) {
                        Logger.getLogger(BeanMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
            else if (rx.getOpcEstatus().equals(EstadosConfig.OPCION_PLANTILLA.getCodigo())) {
                DefaultMenuItem item = new DefaultMenuItem(rx.getOpcNombre());
                item.setOutcome(rx.getOpcPagina().concat(DocumentoConfig.DOT.getValor()).concat(DocumentoConfig.EXTENSION_XHTML.getValor()));
                item.setIcon(rx.getOpcIcono());
                if (menuYgdrasil instanceof DefaultMenuModel) {
                    ((DefaultMenuModel) menuYgdrasil).getElements().add(item);
                } else {
                    ((DefaultSubMenu) menuYgdrasil).getElements().add(item);
                }
            }
        });
    }
    
    @PostConstruct
    public void init(){

    }

    public UsuarioAcceso getUsuarioAcceso() {
        return usuarioAcceso;
    }

    public void setUsuario(UsuarioAcceso usuarioAcceso) {
        this.usuarioAcceso = usuarioAcceso;
    }

    public OpcionPerfil getPermiso() {
        return permiso;
    }

    public void setPermiso(OpcionPerfil permiso) {
        this.permiso = permiso;
    }

    /*
    public List<OpcionPerfil> getListaOpcionPerfil() {
        return listaOpcionPerfil;
    }

    public void setListaOpcionPerfil(List<OpcionPerfil> listaOpcionPerfil) {
        this.listaOpcionPerfil = listaOpcionPerfil;
    }

    public List<Opcion> getListaOpciones() {
        return listaOpciones;
    }

    public void setListaOpciones(List<Opcion> listaOpciones) {
        this.listaOpciones = listaOpciones;
    }
    */

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public AsignarPerfil getPerfilAccesado() {
        return perfilAccesado;
    }

    public void setPerfilAccesado(AsignarPerfil perfilAccesado) {
        this.perfilAccesado = perfilAccesado;
    }

    public Perfil getPerfilCorrespondiente() {
        return perfilCorrespondiente;
    }

    public void setPerfilCorrespondiente(Perfil perfilCorrespondiente) {
        this.perfilCorrespondiente = perfilCorrespondiente;
    }
    
    
    
}
