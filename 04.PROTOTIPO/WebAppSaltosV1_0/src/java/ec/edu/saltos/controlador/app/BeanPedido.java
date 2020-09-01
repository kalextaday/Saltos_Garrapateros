/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.app;


import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.controlador.seguridad.*;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.CabeceraFactura;
import ec.edu.saltos.modelo.Descuento;
import ec.edu.saltos.modelo.Empresa;
import ec.edu.saltos.modelo.FormaPago;
import ec.edu.saltos.modelo.Paracaidista;
import ec.edu.saltos.modelo.Pedido;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.modelo.Salto;
import ec.edu.saltos.modelo.ServicioAdicional;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.modelo.Vuelo;
import ec.edu.saltos.persistencia.DAOCabeceraFactura;
import ec.edu.saltos.persistencia.DAODescuento;
import ec.edu.saltos.persistencia.DAOEmpresa;
import ec.edu.saltos.persistencia.DAOFormaPago;
import ec.edu.saltos.persistencia.DAOParacaidista;
import ec.edu.saltos.persistencia.DAOPedido;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.persistencia.DAOSalto;
import ec.edu.saltos.persistencia.DAOServicioAdicional;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import ec.edu.saltos.persistencia.DAOVuelo;
import ec.edu.saltos.util.FechaUtil;
import ec.edu.saltos.util.PrimeUtiles;
import ec.edu.saltos.validaciones.Cedula;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanPedido extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanPedido.class.getName());
    
    private List<CabeceraFactura> listaFacturas;
    private List<UsuarioAcceso> listaUsuariosClientes;
    private List<FormaPago> listaFormasPago;
    private List<Empresa> listaEmpresas;
    private FormaPago formaPagoSeleccionada;
    private CabeceraFactura facturaSeleccionada;
    private Perfil perfilGenerador;
    private UsuarioAcceso usuarioGenerador;
    private UsuarioAcceso usuarioClienteSeleccionado;
    private Empresa empresaSeleccionada;
    
    private List<Pedido> listaPedidos;
    private List<Paracaidista> listaParacaidistas;
    private List<Descuento> listaDescuentos;
    private List<ServicioAdicional> listaServiciosAdicionales;
    private List<Salto> listaSaltos;
    private List<Vuelo> listaVuelos;
    private Pedido pedidoSeleccionado;
    private Paracaidista paracaidistaSeleccionado;
    private Descuento descuentoSeleccionado;
    private ServicioAdicional servAdicionalSeleccionado;
    private Salto saltoSeleccionado;
    private Vuelo vueloSeleccionado;
    
    private String formatoFecha;
    private String formatoHora;

    private boolean estatus;
    
    /**
     * Creates a new instance of BeanPersona
     */
    public BeanPedido() {
        super(FacesContext.getCurrentInstance().getExternalContext());
        ControlSesion sesion = new ControlSesion();
        perfilGenerador=new Perfil();
        usuarioGenerador=new UsuarioAcceso();
        usuarioClienteSeleccionado=new UsuarioAcceso();
        facturaSeleccionada=new CabeceraFactura();
        formaPagoSeleccionada=new FormaPago();
        empresaSeleccionada=new Empresa();
        pedidoSeleccionado=new Pedido();
        
        listaEmpresas=new ArrayList<>();
        listaFacturas=new ArrayList<>();
        listaUsuariosClientes=new ArrayList<>();
        listaFormasPago=new ArrayList<>();
        listaPedidos=new ArrayList<>();
        listaParacaidistas=new ArrayList<>();
        listaDescuentos=new ArrayList<>();
        listaServiciosAdicionales=new ArrayList<>();
        listaSaltos=new ArrayList<>();
        listaVuelos=new ArrayList<>();
        
        if (sesion.obtenerEstadoSesionUsuario()){
            perfilGenerador=new DAOPerfil().obtenerPorId(Integer.parseInt(sesion.obtenerIdPerfilSesionActiva()));
            if (perfilGenerador != null) {
                if (perfilGenerador.getPerfilEstatus().equals(EstadosConfig.PERFIL_EST_ACTIVADO.getCodigo())){
                    usuarioGenerador=new DAOUsuarioAcceso().obtenerPorId(Integer.parseInt(sesion.obtenerIdUsuarioAccesoSesionActiva()));
                }
            }
        }
    }
    
    @PostConstruct
    public void init(){
        formatoFecha="cm";
        formatoHora="am_pm";
        
        obtenerPedidos();
        obtenerFormasPago();
        obtenerInfoEmpresa();
        obtenerUsuarioCliente();
        obtenerParacaidistas();
        obtenerDescuentos();
        obtenerServiciosAdicionales();
        obtenerSaltos();
        obtenerVuelos();
        obtenerFacturas();
    }
    
    public void obtenerFormasPago(){
        DAOFormaPago dao=new DAOFormaPago();
        try{
            listaFormasPago=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerUsuarioCliente(){
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        try{
            listaUsuariosClientes=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerInfoEmpresa(){
        DAOEmpresa dao=new DAOEmpresa();
        try{
            listaEmpresas=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerParacaidistas(){
        DAOParacaidista dao=new DAOParacaidista();
        try{
            listaParacaidistas=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerDescuentos(){
        DAODescuento dao=new DAODescuento();
        try{
            listaDescuentos=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerServiciosAdicionales(){
        DAOServicioAdicional dao=new DAOServicioAdicional();
        try{
            listaServiciosAdicionales=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerSaltos(){
        DAOSalto dao=new DAOSalto();
        try{
            listaSaltos=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerVuelos(){
        DAOVuelo dao=new DAOVuelo();
        try{
            listaVuelos=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerPedidos(){
        DAOPedido dao=new DAOPedido();
        try{
            listaPedidos=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerFacturas(){
        DAOCabeceraFactura dao=new DAOCabeceraFactura();
        try{
            listaFacturas=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void agregarPedido() {
        DAOPedido dao=new DAOPedido();
        
        pedidoSeleccionado.setEstatusFacturacion(false);
        pedidoSeleccionado.setFechaPedido(FechaUtil.ahoraSinFormato());
        
        try{
            if(dao.guardar(pedidoSeleccionado)){
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Se registro correctamente.");
                limpiarPedido();
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al registrar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al agregar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-crear').hide();");
        }
    }
    
    public void agregarFactura() {
        DAOCabeceraFactura dao=new DAOCabeceraFactura();
        CabeceraFactura ultimaFactura=new CabeceraFactura();
        ultimaFactura=dao.obtenerUltimoRegistro();
        long ultimoNumeroFactura=Long.parseLong(ultimaFactura.getFacNumero());
        ultimoNumeroFactura++;
        
        facturaSeleccionada.setUsuarioAccesoByIdUsuarioGeneradorFac(usuarioGenerador);
        facturaSeleccionada.setFacNumero(String.valueOf(ultimoNumeroFactura));
        facturaSeleccionada.setFacSubtotal(BigDecimal.ZERO);
        facturaSeleccionada.setFacTotalDescuento(BigDecimal.ZERO);
        facturaSeleccionada.setFacValorTotal(BigDecimal.ZERO);
        facturaSeleccionada.setFacFechaEmision(FechaUtil.ahoraSinFormato());
        facturaSeleccionada.setFacFechaCaducidad(FechaUtil.ahoraSinFormato());
        
        try{
            if(dao.guardar(facturaSeleccionada)){
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Se registro correctamente.");
                limpiarFactura();
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al registrar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al agregar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-crear').hide();");
        }
    }

    public void modificarFactura() {
        DAOCabeceraFactura dao=new DAOCabeceraFactura();
        //facturaSeleccionada.setPerFechaMod(FechaUtil.ahoraSinFormato());
        try{
            if(dao.editar(facturaSeleccionada)){
                limpiarFactura();
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info: ","Se actualizo correctamente");
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al actualizar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al modificar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-actualizar').hide();");
        }
    }

    public void eliminarFactura() {
        DAOCabeceraFactura dao=new DAOCabeceraFactura();
        
        try{
            if(dao.eliminar(facturaSeleccionada)){
                limpiarFactura();
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info: ", "Se elimino correctamente");
                
                LOG.log(Level.INFO, "Persona Eliminada Correctamente");
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "Hubo un error al eliminar");
                
                LOG.log(Level.INFO, "No se pudo eliminar la persona");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al eliminar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-eliminar').hide();");
        }
        
    }

    public CabeceraFactura onRowSelect(SelectEvent event) {
        setFacturaSeleccionada((CabeceraFactura) event.getObject());
        LOG.log(Level.INFO, "Persona {0} listo para usar a: ", facturaSeleccionada.getFacNumero());
        return facturaSeleccionada;
    }

    public void onRowUnselect(UnselectEvent event) {
    }

    public CabeceraFactura preparaCrear() {
        LOG.log(Level.INFO, "Preparando nueva persona");

        facturaSeleccionada = new CabeceraFactura();
        PrimeUtiles.primeExecute("PF('wv-crear').show();");
        return facturaSeleccionada;

    }

    public CabeceraFactura preparaActualizar() {
        LOG.log(Level.INFO, "Persona {0} lista para actualizar.", facturaSeleccionada.getFacNumero());
        
        PrimeUtiles.primeExecute("PF('wv-actualizar').show();");
        return facturaSeleccionada;
    }

    public void preparaArchivar() {
        LOG.log(Level.INFO, "Persona {0} lista para archivar.", facturaSeleccionada.getFacNumero());
        
        PrimeUtiles.primeExecute("PF('wv-archivar').show();");
    }
    
    public void preparaEliminar() {

        LOG.log(Level.INFO, "Persona {0} lista para eliminar.", facturaSeleccionada.getFacNumero());
        PrimeUtiles.primeExecute("PF('wv-eliminar').show();");
    }
    
    public void actualizarCliente() {
        if (facturaSeleccionada != null && usuarioClienteSeleccionado != null) {
            facturaSeleccionada.setUsuarioAccesoByIdUsuarioCliente(usuarioClienteSeleccionado);
        }
    }
    
    public void actualizarSalto() {
        if (pedidoSeleccionado != null && saltoSeleccionado != null) {
            pedidoSeleccionado.setSalto(saltoSeleccionado);
        }
    }
    
    public void actualizarParacaidista() {
        if (pedidoSeleccionado != null && paracaidistaSeleccionado != null) {
            pedidoSeleccionado.setParacaidista(paracaidistaSeleccionado);
        }
    }
    
    public void actualizarDescuento() {
        if (pedidoSeleccionado != null && descuentoSeleccionado != null) {
            pedidoSeleccionado.setDescuento(descuentoSeleccionado);
        }
    }
    
    public void actualizarVuelo() {
        if (pedidoSeleccionado != null && vueloSeleccionado != null) {
            pedidoSeleccionado.setVuelo(vueloSeleccionado);
        }
    }
    
    public void actualizarFormaPago() {
        if (facturaSeleccionada != null && formaPagoSeleccionada != null) {
            facturaSeleccionada.setFormaPago(formaPagoSeleccionada);
        }
    }
    
    public void actualizarEmpresa() {
        if (facturaSeleccionada != null && empresaSeleccionada != null) {
            facturaSeleccionada.setEmpresa(empresaSeleccionada);
        }
    }
    
    public void limpiarFactura() {
        facturaSeleccionada = new CabeceraFactura();
    }
    
    public void limpiarPedido() {
        pedidoSeleccionado = new Pedido();
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public List<CabeceraFactura> getListaFacturas() {
        obtenerFacturas();
        return listaFacturas;
    }

    public void setListaFacturas(List<CabeceraFactura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public List<UsuarioAcceso> getListaUsuariosClientes() {
        return listaUsuariosClientes;
    }

    public void setListaUsuariosClientes(List<UsuarioAcceso> listaUsuariosClientes) {
        this.listaUsuariosClientes = listaUsuariosClientes;
    }

    public CabeceraFactura getFacturaSeleccionada() {
        return facturaSeleccionada;
    }

    public void setFacturaSeleccionada(CabeceraFactura facturaSeleccionada) {
        this.facturaSeleccionada = facturaSeleccionada;
    }

    public Perfil getPerfilGenerador() {
        return perfilGenerador;
    }

    public void setPerfilGenerador(Perfil perfilGenerador) {
        this.perfilGenerador = perfilGenerador;
    }

    public UsuarioAcceso getUsuarioGenerador() {
        return usuarioGenerador;
    }

    public void setUsuarioGenerador(UsuarioAcceso usuarioGenerador) {
        this.usuarioGenerador = usuarioGenerador;
    }

    public UsuarioAcceso getUsuarioClienteSeleccionado() {
        return usuarioClienteSeleccionado;
    }

    public void setUsuarioClienteSeleccionado(UsuarioAcceso usuarioClienteSeleccionado) {
        this.usuarioClienteSeleccionado = usuarioClienteSeleccionado;
    }

    public List<FormaPago> getListaFormasPago() {
        return listaFormasPago;
    }

    public void setListaFormasPago(List<FormaPago> listaFormasPago) {
        this.listaFormasPago = listaFormasPago;
    }

    public FormaPago getFormaPagoSeleccionada() {
        return formaPagoSeleccionada;
    }

    public void setFormaPagoSeleccionada(FormaPago formaPagoSeleccionada) {
        this.formaPagoSeleccionada = formaPagoSeleccionada;
    }

    public Empresa getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }

    public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public List<Paracaidista> getListaParacaidistas() {
        return listaParacaidistas;
    }

    public void setListaParacaidistas(List<Paracaidista> listaParacaidistas) {
        this.listaParacaidistas = listaParacaidistas;
    }

    public List<Descuento> getListaDescuentos() {
        return listaDescuentos;
    }

    public void setListaDescuentos(List<Descuento> listaDescuentos) {
        this.listaDescuentos = listaDescuentos;
    }

    public List<ServicioAdicional> getListaServiciosAdicionales() {
        return listaServiciosAdicionales;
    }

    public void setListaServiciosAdicionales(List<ServicioAdicional> listaServiciosAdicionales) {
        this.listaServiciosAdicionales = listaServiciosAdicionales;
    }

    public List<Salto> getListaSaltos() {
        return listaSaltos;
    }

    public void setListaSaltos(List<Salto> listaSaltos) {
        this.listaSaltos = listaSaltos;
    }

    public List<Vuelo> getListaVuelos() {
        return listaVuelos;
    }

    public void setListaVuelos(List<Vuelo> listaVuelos) {
        this.listaVuelos = listaVuelos;
    }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public Paracaidista getParacaidistaSeleccionado() {
        return paracaidistaSeleccionado;
    }

    public void setParacaidistaSeleccionado(Paracaidista paracaidistaSeleccionado) {
        this.paracaidistaSeleccionado = paracaidistaSeleccionado;
    }

    public Descuento getDescuentoSeleccionado() {
        return descuentoSeleccionado;
    }

    public void setDescuentoSeleccionado(Descuento descuentoSeleccionado) {
        this.descuentoSeleccionado = descuentoSeleccionado;
    }

    public ServicioAdicional getServAdicionalSeleccionado() {
        return servAdicionalSeleccionado;
    }

    public void setServAdicionalSeleccionado(ServicioAdicional servAdicionalSeleccionado) {
        this.servAdicionalSeleccionado = servAdicionalSeleccionado;
    }

    public Salto getSaltoSeleccionado() {
        return saltoSeleccionado;
    }

    public void setSaltoSeleccionado(Salto saltoSeleccionado) {
        this.saltoSeleccionado = saltoSeleccionado;
    }

    public Vuelo getVueloSeleccionado() {
        return vueloSeleccionado;
    }

    public void setVueloSeleccionado(Vuelo vueloSeleccionado) {
        this.vueloSeleccionado = vueloSeleccionado;
    }

    public String getFormatoHora() {
        return formatoHora;
    }

    public void setFormatoHora(String formatoHora) {
        this.formatoHora = formatoHora;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    
    
    
}
