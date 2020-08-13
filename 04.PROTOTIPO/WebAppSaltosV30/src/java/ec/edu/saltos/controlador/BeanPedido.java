/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOCliente;
import ec.edu.saltos.dao.DAODescuento;
import ec.edu.saltos.dao.DAODetalleVuelo;
import ec.edu.saltos.dao.DAOParacaidista;
import ec.edu.saltos.dao.DAOPedido;
import ec.edu.saltos.dao.DAOSalto;
import ec.edu.saltos.dao.DAOServicioAdicional;
import ec.edu.saltos.modelo.Cliente;
import ec.edu.saltos.modelo.Descuento;
import ec.edu.saltos.modelo.DetalleVuelo;
import ec.edu.saltos.modelo.Paracaidista;
import ec.edu.saltos.modelo.Pedido;
import ec.edu.saltos.modelo.Salto;
import ec.edu.saltos.modelo.ServicioAdicional;
import ec.edu.saltos.modelo.Vuelo;
import java.io.Serializable;
import java.math.BigDecimal;
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
    
    private List<Cliente> listaClientes;
    private Cliente clienteSeleccionado;
    
    private List<Salto> listaSaltos;
    private Salto saltoSeleccionado;
    
    private List<Paracaidista> listaParacaidista;
    private Paracaidista paracaidistaSeleccionado;
    
    private List<Descuento> listaDescuento;
    private Descuento descuentoSeleccionado;
    
    private List<ServicioAdicional> listaServicios;
    private ServicioAdicional serviciosSeleccionado;
    
    private List<Vuelo> listaVuelos;
    private Vuelo vueloSeleccionado; 
    
    private DetalleVuelo detVuelo;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanPedido() {
        pedido=new Pedido();
        clienteSeleccionado=new Cliente();
        saltoSeleccionado=new Salto();
        paracaidistaSeleccionado=new Paracaidista();
        descuentoSeleccionado=new Descuento();
        serviciosSeleccionado=new ServicioAdicional();
        vueloSeleccionado=new Vuelo();
        detVuelo=new DetalleVuelo();
    }
    
    @PostConstruct
    public void init(){
        obtenerPedidos();
        obtenerClientes();
        obtenerSaltos();
        obtenerPracaidistas();
        obtenerDescuentos();
        obtenerServicios();
    }
    
    public void obtenerClientes(){
        DAOCliente dao=new DAOCliente();
        listaClientes=dao.obtenerClientes();
    }
    
    public void obtenerSaltos(){
        DAOSalto dao=new DAOSalto();
        listaSaltos=dao.obtenerSaltos();
    }
    
    public void obtenerPracaidistas(){
        DAOParacaidista dao=new DAOParacaidista();
        listaParacaidista=dao.obtenerParacaidistas();
    }
    
    public void obtenerDescuentos(){
        DAODescuento dao=new DAODescuento();
        listaDescuento=dao.obtenerDescuentos();
    }
    
    public void obtenerServicios(){
        DAOServicioAdicional dao=new DAOServicioAdicional();
        listaServicios=dao.obtenerServicioAdicionals();
    }
    
    
    public void obtenerPedidos(){
        DAOPedido daopedido=new DAOPedido();
        listaPedidos=daopedido.obtenerPedidos();
    }
    
    public void agregarPedido() {
        DAOPedido daopedido=new DAOPedido();
        pedido.setCliente(clienteSeleccionado);
        pedido.setSalto(saltoSeleccionado);
        pedido.setParacaidista(paracaidistaSeleccionado);
        pedido.setDescuento(descuentoSeleccionado);
        pedido.setServicioAdicional(serviciosSeleccionado);
        try{
            if(daopedido.guardar(pedido)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
                agregarDetalleVuelo();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }
    
    public void agregarDetalleVuelo(){
        DAODetalleVuelo daoDet=new DAODetalleVuelo();
        DAOPedido daoPedido=new DAOPedido();
        
        Pedido ultimoPedido=new Pedido();
        ultimoPedido=daoPedido.obtenerUltimo();
                
        detVuelo.setPedido(ultimoPedido); //
        detVuelo.setVuelo(vueloSeleccionado);
        detVuelo.setCostototal(BigDecimal.ZERO);
        try{
            if(daoDet.guardar(detVuelo)){
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
        pedido.setCliente(clienteSeleccionado);
        pedido.setSalto(saltoSeleccionado);
        pedido.setParacaidista(paracaidistaSeleccionado);
        pedido.setDescuento(descuentoSeleccionado);
        pedido.setServicioAdicional(serviciosSeleccionado);
        try{
            if(daopedido.editar(pedido)){
                limpiarPedido();
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
                limpiarPedido();
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

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public List<Salto> getListaSaltos() {
        return listaSaltos;
    }

    public void setListaSaltos(List<Salto> listaSaltos) {
        this.listaSaltos = listaSaltos;
    }

    public Salto getSaltoSeleccionado() {
        return saltoSeleccionado;
    }

    public void setSaltoSeleccionado(Salto saltoSeleccionado) {
        this.saltoSeleccionado = saltoSeleccionado;
    }

    public List<Paracaidista> getListaParacaidista() {
        return listaParacaidista;
    }

    public void setListaParacaidista(List<Paracaidista> listaParacaidista) {
        this.listaParacaidista = listaParacaidista;
    }

    public Paracaidista getParacaidistaSeleccionado() {
        return paracaidistaSeleccionado;
    }

    public void setParacaidistaSeleccionado(Paracaidista paracaidistaSeleccionado) {
        this.paracaidistaSeleccionado = paracaidistaSeleccionado;
    }

    public List<Descuento> getListaDescuento() {
        return listaDescuento;
    }

    public void setListaDescuento(List<Descuento> listaDescuento) {
        this.listaDescuento = listaDescuento;
    }

    public Descuento getDescuentoSeleccionado() {
        return descuentoSeleccionado;
    }

    public void setDescuentoSeleccionado(Descuento descuentoSeleccionado) {
        this.descuentoSeleccionado = descuentoSeleccionado;
    }

    public List<ServicioAdicional> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<ServicioAdicional> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public ServicioAdicional getServiciosSeleccionado() {
        return serviciosSeleccionado;
    }

    public void setServiciosSeleccionado(ServicioAdicional serviciosSeleccionado) {
        this.serviciosSeleccionado = serviciosSeleccionado;
    }

    public List<Vuelo> getListaVuelos() {
        return listaVuelos;
    }

    public void setListaVuelos(List<Vuelo> listaVuelos) {
        this.listaVuelos = listaVuelos;
    }

    public Vuelo getVueloSeleccionado() {
        return vueloSeleccionado;
    }

    public void setVueloSeleccionado(Vuelo vueloSeleccionado) {
        this.vueloSeleccionado = vueloSeleccionado;
    }

    public DetalleVuelo getDetVuelo() {
        return detVuelo;
    }

    public void setDetVuelo(DetalleVuelo detVuelo) {
        this.detVuelo = detVuelo;
    }
    
    
    
}
