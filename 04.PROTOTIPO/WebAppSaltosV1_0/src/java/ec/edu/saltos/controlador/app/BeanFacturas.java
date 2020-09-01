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
import ec.edu.saltos.modelo.Empresa;
import ec.edu.saltos.modelo.FormaPago;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOCabeceraFactura;
import ec.edu.saltos.persistencia.DAOEmpresa;
import ec.edu.saltos.persistencia.DAOFormaPago;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
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
public class BeanFacturas extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanFacturas.class.getName());
    
    private List<CabeceraFactura> listaFacturas;
    private List<UsuarioAcceso> listaUsuariosClientes;
    private List<Empresa> listaEmpresas;
    private List<FormaPago> listaFormasPago;
    
    private Empresa empresaSeleccionada;
    private FormaPago formaPagoSeleccionada;
    private CabeceraFactura facturaSeleccionada;
    private Perfil perfilGenerador;
    private UsuarioAcceso usuarioGenerador;
    private UsuarioAcceso usuarioClienteSeleccionado;
    
    private String formatoFecha;

    private boolean estatus;
    
    /**
     * Creates a new instance of BeanPersona
     */
    public BeanFacturas() {
        super(FacesContext.getCurrentInstance().getExternalContext());
        ControlSesion sesion = new ControlSesion();
        perfilGenerador=new Perfil();
        usuarioGenerador=new UsuarioAcceso();
        usuarioClienteSeleccionado=new UsuarioAcceso();
        facturaSeleccionada=new CabeceraFactura();
        empresaSeleccionada=new Empresa();
        formaPagoSeleccionada=new FormaPago();
        listaFacturas=new ArrayList<>();
        listaUsuariosClientes=new ArrayList<>();
        listaFormasPago=new ArrayList<>();
        listaEmpresas=new ArrayList<>();
        
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
        listaFacturas=new ArrayList<>();
        obtenerFacturas();
        obtenerFormasPago();
        obtenerInfoEmpresa();
        obtenerUsuarioCliente();
    }
    
    public void obtenerFormasPago(){
        DAOFormaPago dao=new DAOFormaPago();
        try{
            listaFormasPago=dao.obtenerTodos();
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
    
    public void obtenerUsuarioCliente(){
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        try{
            listaUsuariosClientes=dao.obtenerTodos();
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
    
    public void actualizarEmpresa() {
        if (facturaSeleccionada != null && empresaSeleccionada != null) {
            facturaSeleccionada.setEmpresa(empresaSeleccionada);
        }
    }
    
    public void actualizarCliente() {
        if (facturaSeleccionada != null && usuarioClienteSeleccionado != null) {
            facturaSeleccionada.setUsuarioAccesoByIdUsuarioCliente(usuarioClienteSeleccionado);
        }
    }
    
    public void actualizarFormaPago() {
        if (facturaSeleccionada != null && formaPagoSeleccionada != null) {
            facturaSeleccionada.setFormaPago(formaPagoSeleccionada);
        }
    }
    
    public void limpiarFactura() {
        facturaSeleccionada = new CabeceraFactura();
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

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public List<FormaPago> getListaFormasPago() {
        return listaFormasPago;
    }

    public void setListaFormasPago(List<FormaPago> listaFormasPago) {
        this.listaFormasPago = listaFormasPago;
    }

    public Empresa getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }

    public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
    }

    public FormaPago getFormaPagoSeleccionada() {
        return formaPagoSeleccionada;
    }

    public void setFormaPagoSeleccionada(FormaPago formaPagoSeleccionada) {
        this.formaPagoSeleccionada = formaPagoSeleccionada;
    }

    
    
    
}
