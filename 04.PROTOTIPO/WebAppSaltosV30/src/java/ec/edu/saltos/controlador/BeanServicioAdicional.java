/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOResponsable;
import ec.edu.saltos.dao.DAOServicioAdicional;
import ec.edu.saltos.modelo.Responsable;
import ec.edu.saltos.modelo.ServicioAdicional;
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
public class BeanServicioAdicional implements Serializable{

    private List<ServicioAdicional> listaServicioAdicionales;
    private ServicioAdicional servicioAdicional;
    
    private List<Responsable> listaResponsables;
    private Responsable responsableSeleccionado; 
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanServicioAdicional() {
        servicioAdicional=new ServicioAdicional();
        responsableSeleccionado=new Responsable();
    }
    
    @PostConstruct
    public void init(){
        obtenerServicioAdicionals();
        obtenerResponsables();
    }
    
    
    public void obtenerResponsables(){
        DAOResponsable dao=new DAOResponsable();
        listaResponsables=dao.obtenerResponsables();
    }
    
    public void obtenerServicioAdicionals(){
        DAOServicioAdicional daoservicioAdicional=new DAOServicioAdicional();
        listaServicioAdicionales=daoservicioAdicional.obtenerServicioAdicionals();
    }
    
    public void agregarServicioAdicional() {
        DAOServicioAdicional daoservicioAdicional=new DAOServicioAdicional();
        try{
            if(daoservicioAdicional.guardar(servicioAdicional)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarServicioAdicional() {
        DAOServicioAdicional daoservicioAdicional=new DAOServicioAdicional();
        try{
            if(daoservicioAdicional.editar(servicioAdicional)){
                //limpiarServicioAdicional();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarServicioAdicional() {
        DAOServicioAdicional daoservicioAdicional=new DAOServicioAdicional();
        try{
            if(daoservicioAdicional.eliminar(servicioAdicional)){
                //limpiarServicioAdicional();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarServicioAdicional() {
        servicioAdicional = new ServicioAdicional();
    }

    public List<ServicioAdicional> getListaServicioAdicionals() {
        obtenerServicioAdicionals();
        return listaServicioAdicionales;
    }

    public void setListaServicioAdicionals(List<ServicioAdicional> listaServicioAdicionales) {
        this.listaServicioAdicionales = listaServicioAdicionales;
    }

    public ServicioAdicional getServicioAdicional() {
        return servicioAdicional;
    }

    public void setServicioAdicional(ServicioAdicional servicioAdicional) {
        this.servicioAdicional = servicioAdicional;
    }

    public List<ServicioAdicional> getListaServicioAdicionales() {
        return listaServicioAdicionales;
    }

    public void setListaServicioAdicionales(List<ServicioAdicional> listaServicioAdicionales) {
        this.listaServicioAdicionales = listaServicioAdicionales;
    }

    public List<Responsable> getListaResponsables() {
        return listaResponsables;
    }

    public void setListaResponsables(List<Responsable> listaResponsables) {
        this.listaResponsables = listaResponsables;
    }

    public Responsable getResponsableSeleccionado() {
        return responsableSeleccionado;
    }

    public void setResponsableSeleccionado(Responsable responsableSeleccionado) {
        this.responsableSeleccionado = responsableSeleccionado;
    }
    
    
    
}
