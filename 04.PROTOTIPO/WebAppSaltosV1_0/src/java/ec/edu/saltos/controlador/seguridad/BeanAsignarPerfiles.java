/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.seguridad;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import ec.edu.saltos.util.FechaUtil;
import ec.edu.saltos.util.PrimeUtiles;
import ec.edu.saltos.validaciones.Cedula;
import java.io.Serializable;
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
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanAsignarPerfiles extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanAsignarPerfiles.class.getName());
    
    private List<UsuarioAcceso> usuariosOrigen;
    private List<Perfil> listaPerfiles;
    
    private DualListModel<UsuarioAcceso> usuariosGlobal;
    
    private List<UsuarioAcceso> usuariosSeleccionados;
    private Perfil perfilSeleccionado;
    
    private String formatoFecha;

    
    /**
     * Creates a new instance of BeanPersona
     */
    public BeanAsignarPerfiles() {
        super(FacesContext.getCurrentInstance().getExternalContext());
    }
    
    @PostConstruct
    public void init(){
        formatoFecha="si";
        obtenerUsuarios();
        obtenerPerfiles();
        initUsuarios();
    }
    
    public void initUsuarios(){
        usuariosSeleccionados = new ArrayList<>();
        usuariosGlobal = new DualListModel<UsuarioAcceso>(usuariosOrigen, usuariosSeleccionados);
    }
    
    public void obtenerUsuarios(){
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        try{
            usuariosOrigen=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerPerfiles(){
        DAOPerfil dao=new DAOPerfil();
        try{
            listaPerfiles=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
        
    }
    
    public void actualizarPermisosPerfil(SelectEvent event) {
        perfilSeleccionado=(Perfil)event.getObject();
    }
    
    public void preparaAsignarPerfil() {
        PrimeUtiles.primeExecute("PF('wv-usr-perfiles').show();");
    }
    
    /*
    public void agregarPersona() {
        DAOPersona daopersona=new DAOPersona();
        
        personaSeleccionada.setPerFoto("imagenes/app/personas/fotos/");
        personaSeleccionada.setPerFechaCreacion(FechaUtil.ahoraSinFormato());
        personaSeleccionada.setPerFechaMod(FechaUtil.ahoraSinFormato());
        personaSeleccionada.setPerEstatus(EstadosConfig.PERSONA_EST_ACTIVADO.getCodigo());
        try{
            if(daopersona.guardar(personaSeleccionada)){
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Se registro correctamente.");
                limpiarPersona();
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
                limpiarPersona();
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
                limpiarPersona();
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
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info: ", "Se elimino correctamente");
                
                LOG.log(Level.INFO, "Persona Eliminada Correctamente");
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Eror: ", "Hubo un error al eliminar");
                
                LOG.log(Level.INFO, "No se pudo eliminar la persona");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al eliminar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-eliminar').hide();");
        }
        
    }

    public Persona preparaCrear() {
        LOG.log(Level.INFO, "Preparando nueva persona");

        personaSeleccionada = new Persona();
        PrimeUtiles.primeExecute("PF('wv-crear').show();");
        return personaSeleccionada;

    }

    public Persona preparaActualizar() {
        LOG.log(Level.INFO, "Persona {0} lista para actualizar.", personaSeleccionada.getPerNombres());
        
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
    */
    
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((UsuarioAcceso) item).getUsrAccesoNombre()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Usuarios Transferidos");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
     
    public void onSelect(SelectEvent<UsuarioAcceso> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Seleccionado", event.getObject().getUsrAccesoNombre()));
    }
     
    public void onUnselect(UnselectEvent<UsuarioAcceso> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario deseleccionado", event.getObject().getUsrAccesoNombre()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Lista Reordenada", null));
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public Perfil getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public DualListModel<UsuarioAcceso> getUsuariosGlobal() {
        return usuariosGlobal;
    }

    public void setUsuariosGlobal(DualListModel<UsuarioAcceso> usuariosGlobal) {
        this.usuariosGlobal = usuariosGlobal;
    }

    public List<UsuarioAcceso> getUsuariosSeleccionados() {
        return usuariosSeleccionados;
    }

    public void setUsuariosSeleccionados(List<UsuarioAcceso> usuariosSeleccionados) {
        this.usuariosSeleccionados = usuariosSeleccionados;
    }

    public List<UsuarioAcceso> getUsuariosOrigen() {
        return usuariosOrigen;
    }

    public void setUsuariosOrigen(List<UsuarioAcceso> usuariosOrigen) {
        this.usuariosOrigen = usuariosOrigen;
    }

    
    
    
}
