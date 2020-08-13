/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOPedido;
import ec.edu.saltos.modelo.Pedido;
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
public class BeanPedido implements Serializable{

    private List<Pedido> listaPedidos;
    private Pedido pedido;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanPedido() {
        pedido=new Pedido();
    }
    
    @PostConstruct
    public void init(){
        obtenerPedidos();
    }
    
    public void obtenerPedidos(){
        DAOPedido daopedido=new DAOPedido();
        listaPedidos=daopedido.obtenerPedidos();
    }
    
    public void agregarPedido() {
        DAOPedido daopedido=new DAOPedido();
        try{
            if(daopedido.guardar(pedido)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarPedido() {
        DAOPedido daopedido=new DAOPedido();
        try{
            if(daopedido.editar(pedido)){
                //limpiarPedido();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarPedido() {
        DAOPedido daopedido=new DAOPedido();
        try{
            if(daopedido.eliminar(pedido)){
                //limpiarPedido();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarPedido() {
        pedido = new Pedido();
    }

    public List<Pedido> getListaPedidos() {
        obtenerPedidos();
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
}
