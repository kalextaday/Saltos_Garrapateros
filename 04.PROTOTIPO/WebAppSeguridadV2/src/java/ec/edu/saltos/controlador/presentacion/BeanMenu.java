/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.presentacion;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Opcion;
import ec.edu.saltos.modelo.OpcionPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOOpcion;
import ec.edu.saltos.persistencia.DAOOpcionPerfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private List<OpcionPerfil> listaOpcionPerfil;
    private List<Opcion> listaOpciones;
    
    private AsignarPerfil perfilAccesado;
    
    private MenuModel model;
    

    public BeanMenu() {
        listaOpciones=new ArrayList<>();
        usuarioAcceso=new UsuarioAcceso();
        perfilCorrespondiente=new Perfil();
        perfilAccesado=new AsignarPerfil();
        perfilAccesado=(AsignarPerfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("asignarPerfil");
        perfilCorrespondiente=perfilAccesado.getPerfil();
    }
    
    @PostConstruct
    public void init(){
        model=new DefaultMenuModel();
        listarOpcionsPermitidos();
        validarMenu();
    }
    
    
    public void listarOpcionsPermitidos(){
        
        DAOOpcion daorecurso=new DAOOpcion();
        DAOOpcionPerfil daoOpcionesPerfil=new DAOOpcionPerfil();
        
        listaOpcionPerfil=daoOpcionesPerfil.opcionesPerfil(perfilAccesado.getPerfil().getIdPerfil());
        
        listaOpcionPerfil.forEach((p) -> {
            listaOpciones.add(daorecurso.obtenerPorId(p.getOpcion().getIdOpcion()));
        });
    }
    
    public void establecerOpcionsAdmin(){
        FacesContext context=FacesContext.getCurrentInstance();
        
        DefaultSubMenu primerSubmenu = DefaultSubMenu.builder()
                    .label("Administración")
                    .build();


        for(Opcion r:listaOpciones){
            if(r.getRecEstatus().equals(EstadosConfig.PERFIL_ADMINISTRADOR.getCodigo())){
                DefaultMenuItem item = DefaultMenuItem.builder()
                    .value(r.getRecNombre())
                    .url(context.getExternalContext().getRequestContextPath()+r.getRecPagina())
                    .icon("pi pi-home")
                    .build();
                
                primerSubmenu.getElements().add(item);
            }
        }
        model.getElements().add(primerSubmenu);
        
    }
    
    public void establecerOpcionsUser(){
        FacesContext context=FacesContext.getCurrentInstance();
        DefaultSubMenu segundoSubmenu = DefaultSubMenu.builder()
                    .label("Usuario")
                    .build();
        
        for(Opcion r:listaOpciones){
            if(r.getRecEstatus().equals(EstadosConfig.PERFIL_USUARIO.getCodigo())){
                DefaultMenuItem item = DefaultMenuItem.builder()
                    .value(r.getRecNombre())
                    .url(context.getExternalContext().getRequestContextPath()+r.getRecPagina())
                    .icon("pi pi-user")
                    .build();
                segundoSubmenu.getElements().add(item);
            }
        }
        model.getElements().add(segundoSubmenu);
    }
    
    public void establecerOpcionsInvi(){
        FacesContext context=FacesContext.getCurrentInstance();
        DefaultSubMenu tercerSubmenu = DefaultSubMenu.builder()
                    .label("Invitado")
                    .build();
        
        for(Opcion r:listaOpciones){
            if(r.getRecEstatus().equals(EstadosConfig.PERFIL_INVITADO.getCodigo())){
                DefaultMenuItem item = DefaultMenuItem.builder()
                    .value(r.getRecNombre())
                    .url(context.getExternalContext().getRequestContextPath()+r.getRecPagina())
                    .icon("pi pi-eye")
                    .build();
                tercerSubmenu.getElements().add(item);
            }
        }
        model.getElements().add(tercerSubmenu);
    }
    
    public void validarMenu(){
        if(perfilCorrespondiente.getPerfilEstatus2().equals(EstadosConfig.PERFIL_ADMINISTRADOR.getCodigo())){
            establecerOpcionsAdmin();
            establecerOpcionsUser();
        }else if(perfilCorrespondiente.getPerfilEstatus2().equals(EstadosConfig.PERFIL_USUARIO.getCodigo())){
            establecerOpcionsUser();
        }else if(perfilCorrespondiente.getPerfilEstatus2().equals(EstadosConfig.PERFIL_INVITADO.getCodigo())){
            establecerOpcionsInvi();
        }
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
