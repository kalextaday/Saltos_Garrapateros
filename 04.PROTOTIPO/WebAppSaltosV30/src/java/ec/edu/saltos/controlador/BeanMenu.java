/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.dao.DAOPerfil;
import ec.edu.saltos.dao.DAOPermiso;
import ec.edu.saltos.dao.DAORecurso;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Permiso;
import ec.edu.saltos.modelo.Recurso;
import ec.edu.saltos.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    
    private Usuario usuario;
    private Perfil perfil;
    private Permiso permiso;
    private List<Permiso> listaPermisos;
    private List<Recurso> listaRecursos;
    
    private MenuModel model;
    

    public BeanMenu() {
        listaRecursos=new ArrayList<>();
        usuario=new Usuario();
        perfil=new Perfil();
        usuario=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
    
    @PostConstruct
    public void init(){
        model=new DefaultMenuModel();
        listarRecursosPermitidos();
        obtenerPerfil();
        validarMenu();
    }
    
    public void obtenerPerfil(){
        DAOPerfil daoperfil=new DAOPerfil();
        perfil=daoperfil.obtenerPorId(usuario.getPerfil().getIdPerfil());
    }
    
    public void listarRecursosPermitidos(){

        DAORecurso daorecurso=new DAORecurso();
        DAOPermiso daopermiso=new DAOPermiso();
        
        listaPermisos=daopermiso.permisosPerfil(usuario.getPerfil().getIdPerfil());
        
        listaPermisos.forEach((p) -> {
            listaRecursos.add(daorecurso.obtenerPorId(p.getRecurso().getIdRecurso()));
        });
    }
    
    public void establecerRecursosAdmin(){
        FacesContext context=FacesContext.getCurrentInstance();
        
        DefaultSubMenu primerSubmenu = DefaultSubMenu.builder()
                    .label("Administración")
                    .build();


        for(Recurso r:listaRecursos){
            if(r.getEstadorec().equals(EstadosConfig.PERFIL_ADMINISTRADOR.getCodigo())){
                DefaultMenuItem item = DefaultMenuItem.builder()
                    .value(r.getNombrerec())
                    .url(context.getExternalContext().getRequestContextPath()+r.getPaginarec())
                    .icon("pi pi-home")
                    .build();
                
                primerSubmenu.getElements().add(item);
            }
        }
        model.getElements().add(primerSubmenu);
        
    }
    
    public void establecerRecursosUser(){
        FacesContext context=FacesContext.getCurrentInstance();
        DefaultSubMenu segundoSubmenu = DefaultSubMenu.builder()
                    .label("Usuario")
                    .build();
        
        for(Recurso r:listaRecursos){
            if(r.getEstadorec().equals(EstadosConfig.PERFIL_USUARIO.getCodigo())){
                DefaultMenuItem item = DefaultMenuItem.builder()
                    .value(r.getNombrerec())
                    .url(context.getExternalContext().getRequestContextPath()+r.getPaginarec())
                    .icon("pi pi-user")
                    .build();
                segundoSubmenu.getElements().add(item);
            }
        }
        model.getElements().add(segundoSubmenu);
    }
    
    public void establecerRecursosInvi(){
        FacesContext context=FacesContext.getCurrentInstance();
        DefaultSubMenu tercerSubmenu = DefaultSubMenu.builder()
                    .label("Invitado")
                    .build();
        
        for(Recurso r:listaRecursos){
            if(r.getEstadorec().equals(EstadosConfig.PERFIL_INVITADO.getCodigo())){
                DefaultMenuItem item = DefaultMenuItem.builder()
                    .value(r.getNombrerec())
                    .url(context.getExternalContext().getRequestContextPath()+r.getPaginarec())
                    .icon("pi pi-eye")
                    .build();
                tercerSubmenu.getElements().add(item);
            }
        }
        model.getElements().add(tercerSubmenu);
    }
    
    public void validarMenu(){
        if(perfil.getEstadoper2().equals(EstadosConfig.PERFIL_ADMINISTRADOR.getCodigo())){
            establecerRecursosAdmin();
            establecerRecursosUser();
        }else if(perfil.getEstadoper2().equals(EstadosConfig.PERFIL_USUARIO.getCodigo())){
            establecerRecursosUser();
        }else if(perfil.getEstadoper2().equals(EstadosConfig.PERFIL_INVITADO.getCodigo())){
            establecerRecursosInvi();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public List<Permiso> getListaPermisos() {
        return listaPermisos;
    }

    public void setListaPermisos(List<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
    
}
