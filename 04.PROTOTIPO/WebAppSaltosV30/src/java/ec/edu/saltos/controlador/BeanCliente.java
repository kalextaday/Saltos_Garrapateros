/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOCliente;
import ec.edu.saltos.modelo.Cliente;
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
public class BeanCliente implements Serializable{

    private List<Cliente> listaClientes;
    private Cliente cliente;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanCliente() {
        cliente=new Cliente();
    }
    
    @PostConstruct
    public void init(){
        obtenerClientes();
    }
    
    public void obtenerClientes(){
        DAOCliente daocliente=new DAOCliente();
        listaClientes=daocliente.obtenerClientes();
    }
    
    public void agregarCliente() {
        DAOCliente daocliente=new DAOCliente();
        try{
            if(daocliente.guardar(cliente)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarCliente() {
        DAOCliente daocliente=new DAOCliente();
        try{
            if(daocliente.editar(cliente)){
                //limpiarCliente();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarCliente() {
        DAOCliente daocliente=new DAOCliente();
        try{
            if(daocliente.eliminar(cliente)){
                //limpiarCliente();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarCliente() {
        cliente = new Cliente();
    }

    public List<Cliente> getListaClientes() {
        obtenerClientes();
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
