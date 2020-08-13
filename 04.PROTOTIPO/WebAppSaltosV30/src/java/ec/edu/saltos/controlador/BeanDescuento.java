/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAODescuento;
import ec.edu.saltos.modelo.Descuento;
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
public class BeanDescuento implements Serializable{

    private List<Descuento> listaDescuentos;
    private Descuento descuento;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanDescuento() {
        descuento=new Descuento();
    }
    
    @PostConstruct
    public void init(){
        obtenerDescuentos();
    }
    
    public void obtenerDescuentos(){
        DAODescuento daodescuento=new DAODescuento();
        listaDescuentos=daodescuento.obtenerDescuentos();
    }
    
    public void agregarDescuento() {
        DAODescuento daodescuento=new DAODescuento();
        try{
            if(daodescuento.guardar(descuento)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarDescuento() {
        DAODescuento daodescuento=new DAODescuento();
        try{
            if(daodescuento.editar(descuento)){
                //limpiarDescuento();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarDescuento() {
        DAODescuento daodescuento=new DAODescuento();
        try{
            if(daodescuento.eliminar(descuento)){
                //limpiarDescuento();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarDescuento() {
        descuento = new Descuento();
    }

    public List<Descuento> getListaDescuentos() {
        obtenerDescuentos();
        return listaDescuentos;
    }

    public void setListaDescuentos(List<Descuento> listaDescuentos) {
        this.listaDescuentos = listaDescuentos;
    }

    public Descuento getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }
    
    
}
