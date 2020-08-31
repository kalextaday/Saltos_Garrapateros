/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.seguridad;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.ConfigClave;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOConfigClave;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
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
public class BeanAgregarUsuarioAcceso extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanAgregarUsuarioAcceso.class.getName());

    private List<UsuarioAcceso> listaUsuariosAcceso; 
    private List<Persona> listaPersonas;
    private Persona personaSeleccionada;
    private UsuarioAcceso usuarioSeleccionado;
    
    private String confirmacionClave;
    private String formatoFecha;
    
    private boolean estatus;
    
    
    
    /**
     * Creates a new instance of BeanPersona
     */
    public BeanAgregarUsuarioAcceso() {
        super(FacesContext.getCurrentInstance().getExternalContext());
    }
    
    @PostConstruct
    public void init(){
        formatoFecha="si";
        confirmacionClave="";
        usuarioSeleccionado=new UsuarioAcceso();
        obtenerUsuariosAcceso();
        obtenerPersonas();
    }
    
    public void obtenerUsuariosAcceso(){
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        try{
            listaUsuariosAcceso=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void obtenerPersonas(){
        DAOPersona dao=new DAOPersona();
        try{
            listaPersonas=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void agregarUsuarioAcceso() {
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        DAOConfigClave daoConfigClave=new DAOConfigClave();
        ConfigClave nuevaConfigClave;

        try{
            if(usuarioSeleccionado.getUsrAccesoClave().equals(confirmacionClave)){
                nuevaConfigClave=new ConfigClave(confirmacionClave);
                if(daoConfigClave.guardar(nuevaConfigClave)){
                    usuarioSeleccionado.setConfigClave(daoConfigClave.obtenerUltimoRegistro());
                    usuarioSeleccionado.setUsrAccesoIntentosFallidos(0);
                    usuarioSeleccionado.setUsrAccesoEstatus(EstadosConfig.USR_ACC_ESPERA.getCodigo());
                    usuarioSeleccionado.setUsrAccesoFechaCreacion(FechaUtil.ahoraSinFormato());
                    usuarioSeleccionado.setUsrAccesoFechaMod(FechaUtil.ahoraSinFormato());
                    if(dao.guardar(usuarioSeleccionado)){
                        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Se registro correctamente.");
                        limpiarUsuario();
                    }else{
                        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al registrar");
                    }
                }else{
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Al crear configuracion de la clave. Intente Nuevamente");
                }
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Al confirmar la contraseña");
            }
            
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al agregar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-crear').hide();");
        }
    }

    public void modificarUsuarioAcceso() {
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        usuarioSeleccionado.setUsrAccesoFechaMod(FechaUtil.ahoraSinFormato());
        try{
            if(dao.editar(usuarioSeleccionado)){
                limpiarUsuario();
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
    
    public void archivarUsuarioAcceso(){
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        ConfigClave configClave=usuarioSeleccionado.getConfigClave();
        
        usuarioSeleccionado.setConfigClave(configClave);
        usuarioSeleccionado.setUsrAccesoClave(EstadosConfig.CLAVE_VACIA.getCodigo());
        usuarioSeleccionado.setUsrAccesoIntentosFallidos(0);
        usuarioSeleccionado.setUsrAccesoEstatus(EstadosConfig.USR_ACC_ARCHIVADO.getCodigo());
        usuarioSeleccionado.setUsrAccesoFechaMod(FechaUtil.ahoraSinFormato());
        try{
            if(dao.editar(usuarioSeleccionado)){
                limpiarUsuario();
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

    public void eliminarUsuarioAcceso() {
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        
        try{
            if(dao.eliminar(usuarioSeleccionado)){
                limpiarUsuario();
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

    public UsuarioAcceso onRowSelect(SelectEvent event) {
        setUsuarioSeleccionado((UsuarioAcceso) event.getObject());
        LOG.log(Level.INFO, "Persona {0} listo para usar a: ", usuarioSeleccionado.getUsrAccesoNombre());
        return usuarioSeleccionado;
    }

    public void onRowUnselect(UnselectEvent event) {
    }
    
    public void actualizarPersona() {
        if (usuarioSeleccionado != null && personaSeleccionada != null) {
            usuarioSeleccionado.setPersona(personaSeleccionada);
        }
    }

    public UsuarioAcceso preparaCrear() {
        LOG.log(Level.INFO, "Preparando nueva persona");

        usuarioSeleccionado = new UsuarioAcceso();
        PrimeUtiles.primeExecute("PF('wv-crear').show();");
        return usuarioSeleccionado;

    }

    public UsuarioAcceso preparaActualizar() {
        LOG.log(Level.INFO, "Persona {0} lista para actualizar.", usuarioSeleccionado.getUsrAccesoNombre());
        
        PrimeUtiles.primeExecute("PF('wv-actualizar').show();");
        return usuarioSeleccionado;
    }

    public void preparaArchivar() {
        LOG.log(Level.INFO, "Persona {0} lista para archivar.", usuarioSeleccionado.getUsrAccesoNombre());
        
        PrimeUtiles.primeExecute("PF('wv-archivar').show();");
    }
    
    public void preparaEliminar() {

        LOG.log(Level.INFO, "Persona {0} lista para eliminar.", usuarioSeleccionado.getUsrAccesoNombre());
        PrimeUtiles.primeExecute("PF('wv-eliminar').show();");
    }
    
    public void limpiarUsuario() {
        usuarioSeleccionado = new UsuarioAcceso();
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

    public List<UsuarioAcceso> getListaUsuariosAcceso() {
        return listaUsuariosAcceso;
    }

    public void setListaUsuariosAcceso(List<UsuarioAcceso> listaUsuariosAcceso) {
        this.listaUsuariosAcceso = listaUsuariosAcceso;
    }

    public UsuarioAcceso getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioAcceso usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public String getConfirmacionClave() {
        return confirmacionClave;
    }

    public void setConfirmacionClave(String confirmacionClave) {
        this.confirmacionClave = confirmacionClave;
    }

}
