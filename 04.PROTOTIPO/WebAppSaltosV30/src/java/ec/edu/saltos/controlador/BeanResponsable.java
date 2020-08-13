/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOResponsable;
import ec.edu.saltos.modelo.Responsable;
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
public class BeanResponsable implements Serializable{

    private List<Responsable> listaResponsables;
    private Responsable responsable;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanResponsable() {
        responsable=new Responsable();
    }
    
    @PostConstruct
    public void init(){
        obtenerResponsables();
    }
    
    public void obtenerResponsables(){
        DAOResponsable daoresponsable=new DAOResponsable();
        listaResponsables=daoresponsable.obtenerResponsables();
    }
    
    public void agregarResponsable() {
        DAOResponsable daoresponsable=new DAOResponsable();
        try{
            if(daoresponsable.guardar(responsable)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarResponsable() {
        DAOResponsable daoresponsable=new DAOResponsable();
        try{
            if(daoresponsable.editar(responsable)){
                //limpiarResponsable();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarResponsable() {
        DAOResponsable daoresponsable=new DAOResponsable();
        try{
            if(daoresponsable.eliminar(responsable)){
                //limpiarResponsable();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarResponsable() {
        responsable = new Responsable();
    }

    public List<Responsable> getListaResponsables() {
        obtenerResponsables();
        return listaResponsables;
    }

    public void setListaResponsables(List<Responsable> listaResponsables) {
        this.listaResponsables = listaResponsables;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
    
    
}
