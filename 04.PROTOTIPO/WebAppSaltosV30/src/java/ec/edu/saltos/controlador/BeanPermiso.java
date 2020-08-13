/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOPermiso;
import ec.edu.saltos.modelo.Permiso;
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
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanPermiso() {
        permiso=new Permiso();
    }
    
    @PostConstruct
    public void init(){
        obtenerPermisos();
    }
    
    public void obtenerPermisos(){
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
    
    public void limpiarPermiso() {
        permiso = new Permiso();
    }

    public List<Permiso> getListaPermisos() {
        obtenerPermisos();
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
    
    
}
