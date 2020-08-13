/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOVuelo;
import ec.edu.saltos.modelo.Vuelo;
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
public class BeanVuelo implements Serializable{

    private List<Vuelo> listaVuelos;
    private Vuelo vuelo;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanVuelo() {
        vuelo=new Vuelo();
    }
    
    @PostConstruct
    public void init(){
        obtenerVuelos();
    }
    
    public void obtenerVuelos(){
        DAOVuelo daovuelo=new DAOVuelo();
        listaVuelos=daovuelo.obtenerVuelos();
    }
    
    public void agregarVuelo() {
        DAOVuelo daovuelo=new DAOVuelo();
        try{
            if(daovuelo.guardar(vuelo)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarVuelo() {
        DAOVuelo daovuelo=new DAOVuelo();
        try{
            if(daovuelo.editar(vuelo)){
                //limpiarVuelo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarVuelo() {
        DAOVuelo daovuelo=new DAOVuelo();
        try{
            if(daovuelo.eliminar(vuelo)){
                //limpiarVuelo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarVuelo() {
        vuelo = new Vuelo();
    }

    public List<Vuelo> getListaVuelos() {
        obtenerVuelos();
        return listaVuelos;
    }

    public void setListaVuelos(List<Vuelo> listaVuelos) {
        this.listaVuelos = listaVuelos;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    
}
