/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.app;


import ec.edu.saltos.controlador.seguridad.*;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.Empresa;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.persistencia.DAOEmpresa;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.util.FechaUtil;
import ec.edu.saltos.util.PrimeUtiles;
import ec.edu.saltos.validaciones.Cedula;
import java.io.Serializable;
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
public class BeanEmpresa extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanEmpresa.class.getName());
    
    private List<Persona> listaPersonas;
    private List<Empresa> listaEmpresas;
    
    private Persona personaSeleccionada;
    
    private Empresa empresaSeleccionada;
    
    private String formatoFecha;
    
    private String ciudad;
    private String sector;
    private String calle;
    private String numCasa;
    private boolean estatus;
    
    /**
     * Creates a new instance of BeanPersona
     */
    public BeanEmpresa() {
        super(FacesContext.getCurrentInstance().getExternalContext());
    }
    
    @PostConstruct
    public void init(){
        formatoFecha="si";
        obtenerEmpresas();
    }
    
    public void obtenerPersonas(){
        DAOPersona daopersona=new DAOPersona();
        try{
            listaPersonas=daopersona.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerEmpresas(){
        DAOEmpresa dao=new DAOEmpresa();
        try{
            listaEmpresas=dao.obtenerTodos();
            if(!listaEmpresas.isEmpty()){
                empresaSeleccionada=listaEmpresas.get(0);
            }else{
                empresaSeleccionada=new Empresa();
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void agregarPersona() {
        DAOPersona daopersona=new DAOPersona();
        //personaSeleccionada.setPerDireccion(ciudad+","+sector+","+calle+","+numCasa);
        personaSeleccionada.setPerFoto("imagenes/app/personas/fotos/");
        personaSeleccionada.setPerFechaCreacion(FechaUtil.ahoraSinFormato());
        personaSeleccionada.setPerFechaMod(FechaUtil.ahoraSinFormato());
        personaSeleccionada.setPerEstatus(EstadosConfig.PERSONA_EST_ACTIVADO.getCodigo());
        try{
            if(daopersona.guardar(personaSeleccionada)){
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Se registro correctamente.");
                limpiarEmpresa();
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al registrar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al agregar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-crear').hide();");
        }
    }
    
    public void agregarEmpresa() {
        DAOEmpresa dao=new DAOEmpresa();
        
        empresaSeleccionada.setEmpFechaCreacion(FechaUtil.ahoraSinFormato());
        empresaSeleccionada.setEmpFechaMod(FechaUtil.ahoraSinFormato());
        try{
            if(dao.guardar(empresaSeleccionada)){
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Se registro correctamente.");
                limpiarEmpresa();
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al registrar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al agregar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-crear').hide();");
        }
    }

    public void modificarPersona() {
        DAOPersona daopersona=new DAOPersona();
        personaSeleccionada.setPerFechaMod(FechaUtil.ahoraSinFormato());
        try{
            if(daopersona.editar(personaSeleccionada)){
                limpiarEmpresa();
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
    
    public void archivarPersona(){
        DAOPersona daopersona=new DAOPersona();
        personaSeleccionada.setPerEstatus(EstadosConfig.PERSONA_EST_ARCHIVADO.getCodigo());
        personaSeleccionada.setPerFechaMod(FechaUtil.ahoraSinFormato());
        try{
            if(daopersona.editar(personaSeleccionada)){
                limpiarEmpresa();
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info: Se Archivo correctamente ", "Recuerda, Ya no podras usar el registro");
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al archivar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al archivar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-archivar').hide();");
        }
    }

    public void eliminarPersona() {
        DAOPersona daopersona=new DAOPersona();
        
        try{
            if(daopersona.eliminar(personaSeleccionada)){
                limpiarEmpresa();
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

    public Empresa onRowSelect(SelectEvent event) {
        setPersonaSeleccionada((Persona) event.getObject());
        LOG.log(Level.INFO, "Persona {0} listo para usar a: ", empresaSeleccionada.getEmpRazonSocial());
        return empresaSeleccionada;
    }

    public void onRowUnselect(UnselectEvent event) {
    }

    public Empresa preparaCrear() {
        LOG.log(Level.INFO, "Preparando nueva persona");

        empresaSeleccionada = new Empresa();
        PrimeUtiles.primeExecute("PF('wv-crear').show();");
        return empresaSeleccionada;

    }

    public Persona preparaActualizar() {
        LOG.log(Level.INFO, "Persona {0} lista para actualizar.", empresaSeleccionada.getEmpRazonSocial());
        
        PrimeUtiles.primeExecute("PF('wv-actualizar').show();");
        return personaSeleccionada;
    }

    public void preparaArchivar() {
        LOG.log(Level.INFO, "Persona {0} lista para archivar.", personaSeleccionada.getPerNombres());
        
        PrimeUtiles.primeExecute("PF('wv-archivar').show();");
    }
    
    public void preparaEliminar() {

        LOG.log(Level.INFO, "Persona {0} lista para eliminar.", personaSeleccionada.getPerNombres());
        PrimeUtiles.primeExecute("PF('wv-eliminar').show();");
    }
    
    public void validarCedula() {
        FacesContext context = FacesContext.getCurrentInstance();

        if(Cedula.validarCedulaEcuatoriana(empresaSeleccionada.getEmpRuc())){
            context.addMessage("ideUsr", new FacesMessage(FacesMessage.SEVERITY_INFO,"RUC Correcto",""));
        }else{
            context.addMessage("ideUsr", new FacesMessage(FacesMessage.SEVERITY_ERROR,"RUC Incorrecto",""));
        }
        
    }
    
    public void limpiarEmpresa() {
        empresaSeleccionada = new Empresa();
        ciudad="";
        sector="";
        calle="";
        numCasa="";
    }

    public List<Persona> getListaPersonas() {
        obtenerPersonas();
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public Empresa getEmpresaSeleccionada() {
        return empresaSeleccionada;
    }

    public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
        this.empresaSeleccionada = empresaSeleccionada;
    }

    
    
}
