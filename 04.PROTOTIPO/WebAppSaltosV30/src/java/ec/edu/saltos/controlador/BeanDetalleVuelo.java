/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAODetalleVuelo;
import ec.edu.saltos.modelo.DetalleVuelo;
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
public class BeanDetalleVuelo implements Serializable{

    private List<DetalleVuelo> listaDetalleVuelos;
    private DetalleVuelo detalleVuelo;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanDetalleVuelo() {
        detalleVuelo=new DetalleVuelo();
    }
    
    @PostConstruct
    public void init(){
        obtenerDetalleVuelos();
    }
    
    public void obtenerDetalleVuelos(){
        DAODetalleVuelo daodetalleVuelo=new DAODetalleVuelo();
        listaDetalleVuelos=daodetalleVuelo.obtenerDetalleVuelos();
    }
    
    public void agregarDetalleVuelo() {
        DAODetalleVuelo daodetalleVuelo=new DAODetalleVuelo();
        try{
            if(daodetalleVuelo.guardar(detalleVuelo)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarDetalleVuelo() {
        DAODetalleVuelo daodetalleVuelo=new DAODetalleVuelo();
        try{
            if(daodetalleVuelo.editar(detalleVuelo)){
                //limpiarDetalleVuelo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarDetalleVuelo() {
        DAODetalleVuelo daodetalleVuelo=new DAODetalleVuelo();
        try{
            if(daodetalleVuelo.eliminar(detalleVuelo)){
                //limpiarDetalleVuelo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarDetalleVuelo() {
        detalleVuelo = new DetalleVuelo();
    }

    public List<DetalleVuelo> getListaDetalleVuelos() {
        obtenerDetalleVuelos();
        return listaDetalleVuelos;
    }

    public void setListaDetalleVuelos(List<DetalleVuelo> listaDetalleVuelos) {
        this.listaDetalleVuelos = listaDetalleVuelos;
    }

    public DetalleVuelo getDetalleVuelo() {
        return detalleVuelo;
    }

    public void setDetalleVuelo(DetalleVuelo detalleVuelo) {
        this.detalleVuelo = detalleVuelo;
    }
    
    
}
