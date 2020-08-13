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
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanPermiso implements Serializable{

    private List<Permiso> listaPermisos;
    private Permiso permiso;
    
    private List<Recurso> listaRecursos;
    private List<Recurso> recursosSeleccionados;
    private Recurso recusoSeleccionado; 
    
    private List<Perfil> listaPerfiles;
    private Perfil perfilSeleccionado;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanPermiso() {
        permiso=new Permiso();
    }
    
    @PostConstruct
    public void init(){
        obtenerPermisosAsignados();
        obtenerTodosRecursos();
        obtenerPerfiles();
    }
    
    public void obtenerPerfiles(){
        DAOPerfil dao=new DAOPerfil();
        listaPerfiles=dao.obtenerPerfils();
    }
    
    public void obtenerTodosRecursos(){
        DAORecurso dao=new DAORecurso();
        listaRecursos=dao.obtenerRecursos();
    }
    
    public void obtenerPermisosAsignados(){
        DAOPermiso daopermiso=new DAOPermiso();
        listaPermisos=daopermiso.obtenerPermisos();
    }
    
    public void agregarPermiso() {
        DAOPermiso daopermiso=new DAOPermiso();
        try{
            if(daopermiso.guardar(permiso)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarPermiso() {
        DAOPermiso daopermiso=new DAOPermiso();
        try{
            if(daopermiso.editar(permiso)){
                //limpiarPermiso();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarPermiso() {
        DAOPermiso daopermiso=new DAOPermiso();
        try{
            if(daopermiso.eliminar(permiso)){
                //limpiarPermiso();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }
    
    public void asignarPermisos(){
        String nombrePerfil=perfilSeleccionado.getNombreper();
        String nombreRecurso="";
        DAOPermiso daopermiso=new DAOPermiso();
        List<Permiso> permisosExistentes=daopermiso.permisosPerfil(perfilSeleccionado.getIdPerfil());
        
        for(Permiso p:permisosExistentes){
            daopermiso.eliminar(p);
        }
        
        for(Recurso r:recursosSeleccionados){
            nombreRecurso=r.getNombrerec();
            
            Permiso per=new Permiso(); 
            per.setRecurso(r);
            per.setPerfil(perfilSeleccionado);
            per.setNombreper(nombrePerfil+" - "+nombreRecurso);
            per.setEstadoper(EstadosConfig.PERMISO_ACTIVO.getCodigo());
            
            daopermiso.guardar(per);
        }
    }
    
    public void limpiarPermiso() {
        permiso = new Permiso();
    }

    public List<Permiso> getListaPermisos() {
        obtenerPermisosAsignados();
        return listaPermisos;
    }

    public void setListaPermisos(List<Permiso> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public Recurso getRecusoSeleccionado() {
        return recusoSeleccionado;
    }

    public void setRecusoSeleccionado(Recurso recusoSeleccionado) {
        this.recusoSeleccionado = recusoSeleccionado;
    }

    public Perfil getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public List<Recurso> getRecursosSeleccionados() {
        return recursosSeleccionados;
    }

    public void setRecursosSeleccionados(List<Recurso> recursosSeleccionados) {
        this.recursosSeleccionados = recursosSeleccionados;
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }
    
    
    
}
