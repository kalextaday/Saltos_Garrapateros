/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.seguridad;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOAsignarPerfil;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import ec.edu.saltos.util.FechaUtil;
import ec.edu.saltos.util.PrimeUtiles;
import ec.edu.saltos.validaciones.Cedula;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanVerUsuariosPorPerfil extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanVerUsuariosPorPerfil.class.getName());
    
    private List<AsignarPerfil> listaAsignacionesPerfil;
    private List<UsuarioAcceso> listaUsuariosAcceso;
    private List<Perfil> listaPerfiles;
    
    private Perfil perfilSeleccionado;
    private AsignarPerfil usuarioPerfilSeleccionado;

    private String formatoFecha;
    private StringBuilder builder; 

    
    /**
     * Creates a new instance of BeanPersona
     */
    public BeanVerUsuariosPorPerfil() {
        super(FacesContext.getCurrentInstance().getExternalContext());
    }
    
    @PostConstruct
    public void init(){
        formatoFecha="si";
        obtenerPerfiles();
    }
    
    public void obtenerTodasAsignacionesPorPerfil(){
        DAOAsignarPerfil dao=new DAOAsignarPerfil();
        try{
            listaAsignacionesPerfil=dao.obtenerAsignacionesPorPerfil(perfilSeleccionado.getIdPerfil());
        }catch(Exception e){
            LOG.info("Excepcion al obtener a todos:"+e);
        }
    }
    
    public void obtenerPerfiles(){
        DAOPerfil dao=new DAOPerfil();
        try{
            listaPerfiles=dao.obtenerTodos();
        }catch(Exception e){
            LOG.info("Excepcion al obtener a todos: "+e);
        }
    }
    
    public AsignarPerfil onRowSelect(SelectEvent event) {
        setUsuarioPerfilSeleccionado((AsignarPerfil) event.getObject());
        LOG.log(Level.INFO, "UsuarioPerfil {0} listo para usar a: ", usuarioPerfilSeleccionado.getIdAsignarPerfil());
        return usuarioPerfilSeleccionado;
    }

    public void onRowUnselect(UnselectEvent event) {
    }
    
    public void actualizarPermisosPerfil(SelectEvent event) {
        perfilSeleccionado=(Perfil)event.getObject();
        obtenerTodasAsignacionesPorPerfil();
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public Perfil getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    public List<AsignarPerfil> getListaAsignacionesPerfil() {
        return listaAsignacionesPerfil;
    }

    public void setListaAsignacionesPerfil(List<AsignarPerfil> listaAsignacionesPerfil) {
        this.listaAsignacionesPerfil = listaAsignacionesPerfil;
    }

    public List<UsuarioAcceso> getListaUsuariosAcceso() {
        return listaUsuariosAcceso;
    }

    public void setListaUsuariosAcceso(List<UsuarioAcceso> listaUsuariosAcceso) {
        this.listaUsuariosAcceso = listaUsuariosAcceso;
    }

    public AsignarPerfil getUsuarioPerfilSeleccionado() {
        return usuarioPerfilSeleccionado;
    }

    public void setUsuarioPerfilSeleccionado(AsignarPerfil usuarioPerfilSeleccionado) {
        this.usuarioPerfilSeleccionado = usuarioPerfilSeleccionado;
    }

    
    
    
}
