/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.seguridad;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOAsignarPerfil;
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
    
    private List<AsignarPerfil> todasAsignacionesPerfil;
    
    private String formatoFecha;
    private StringBuilder builder; 

    
    /**
     * Creates a new instance of BeanPersona
     */
    public BeanAsignarPerfiles() {
        super(FacesContext.getCurrentInstance().getExternalContext());
    }
    
    @PostConstruct
    public void init(){
        formatoFecha="si";
        //obtenerTodosUsuarios();
        obtenerPerfiles();
        initUsuarios();
    }
    
    public void initUsuarios(){
        usuariosOrigen = new ArrayList<>();
        usuariosSeleccionados = new ArrayList<>();
        usuariosGlobal = new DualListModel<UsuarioAcceso>(usuariosOrigen, usuariosSeleccionados);
    }
    
    public void setPickUsuarios(){
        usuariosSeleccionados = new ArrayList<>();
        usuariosGlobal = new DualListModel<UsuarioAcceso>(usuariosOrigen, usuariosSeleccionados);
    }
    
    public void obtenerTodosUsuarios(){
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        try{
            usuariosOrigen=dao.obtenerTodos();
        }catch(Exception e){
            LOG.info("Excepcion al obtener a todos:"+e);
        }
    }
    
    public void obtenerTodaAsignacionPerfil(){
        DAOAsignarPerfil dao=new DAOAsignarPerfil();
        try{
            todasAsignacionesPerfil=dao.obtenerTodos();
        }catch(Exception e){
            LOG.info("Excepcion al obtener a todas las asignaciones por perfil:"+e);
        }
    }
    
    public void obtenerUsersPermitidosParaAsignar(){
        DAOAsignarPerfil daoap=new DAOAsignarPerfil();
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        
        obtenerTodaAsignacionPerfil();
        usuariosOrigen=daoap.obtenerUsuariosSinEstePerfil(perfilSeleccionado.getIdPerfil());
        
        try{
            for(int i=0;i<usuariosOrigen.size();i++){
                for(int j=0;j<todasAsignacionesPerfil.size();j++){
                    if(usuariosOrigen.get(i).getIdUsuarioAcceso()==todasAsignacionesPerfil.get(j).getUsuarioAcceso().getIdUsuarioAcceso()){
                        if(todasAsignacionesPerfil.get(j).getPerfil().getIdPerfil()==perfilSeleccionado.getIdPerfil()){
                            usuariosOrigen.remove(usuariosOrigen.get(i));
                            i=0;
                        }
                    }
                }

            }
        }catch(Exception e){
            LOG.info("Excepcion al obtener a usuarios permitidos:"+e);
        }
    }
    
    public void obtenerPerfiles(){
        DAOPerfil dao=new DAOPerfil();
        try{
            listaPerfiles=dao.obtenerTodos();
        }catch(Exception e){
            LOG.info("Excepcion al obtener a todos: "+e);
        }
        
    }
    
    public void actualizarPermisosPerfil(SelectEvent event) {
        perfilSeleccionado=(Perfil)event.getObject();
        //obtenerUsersPermitidosParaAsignar();
        obtenerTodosUsuarios();
        setPickUsuarios();
    }
    /*
    public void asignarPerfil(){
        LOG.info("Listo para Asignar");
        
        DAOAsignarPerfil dao=new DAOAsignarPerfil();

        usuariosSeleccionados=usuariosGlobal.getTarget();

        try{
            for(UsuarioAcceso u:usuariosSeleccionados){
                AsignarPerfil asigPer=new AsignarPerfil();
                asigPer.setUsuarioAcceso(u);
                asigPer.setPerfil(perfilSeleccionado);
                asigPer.setAsiPerFechaCreacion(FechaUtil.ahoraSinFormato());

                if(dao.guardar(asigPer)){
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Al usuario: "
                            +u.getUsrAccesoNombre()+" se le asigno correctamente el perfil.");
                }else{
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error:", "Al usuario: "
                            +u.getUsrAccesoNombre()+" se le asigno correctamente el perfil.");
                }
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Error para Asignar{0}", e);
        }finally{
            initUsuarios();
            PrimeUtiles.primeExecute("PF('wv-asignarPerfil').hide();");
        }
    }*/
    
    public void asignarPerfil(){
        LOG.info("Listo para Asignar");
        
        DAOAsignarPerfil dao=new DAOAsignarPerfil();

        usuariosSeleccionados=usuariosGlobal.getTarget();

        try{
            for(UsuarioAcceso u:usuariosSeleccionados){
                AsignarPerfil asignarPerfil=dao.autenticarPerfil(u.getIdUsuarioAcceso(), perfilSeleccionado.getIdPerfil());
                if(asignarPerfil!=null){
                    dao.eliminar(asignarPerfil);
                }
                AsignarPerfil asigPer=new AsignarPerfil();
                asigPer.setUsuarioAcceso(u);
                asigPer.setPerfil(perfilSeleccionado);
                asigPer.setAsiPerFechaCreacion(FechaUtil.ahoraSinFormato());

                if(dao.guardar(asigPer)){
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Al usuario: <b>"
                            +u.getUsrAccesoNombre()+"</b> se le asigno correctamente el perfil.");
                }else{
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error:", "Al usuario: "
                            +u.getUsrAccesoNombre()+"</b> se le asigno correctamente el perfil.");
                }
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Error para Asignar{0}", e);
        }finally{
            initUsuarios();
            PrimeUtiles.primeExecute("PF('wv-asignarPerfil').hide();");
        }
    }
    
    public void retirarPerfil(){
        LOG.info("Listo para retirar");
        
        DAOAsignarPerfil dao=new DAOAsignarPerfil();

        usuariosSeleccionados=usuariosGlobal.getTarget();

        try{
            for(UsuarioAcceso u:usuariosSeleccionados){
                AsignarPerfil asignarPerfil=dao.autenticarPerfil(u.getIdUsuarioAcceso(), perfilSeleccionado.getIdPerfil());
                if(asignarPerfil!=null){
                    if(dao.eliminar(asignarPerfil)){
                        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Al usuario: <b>"
                                +u.getUsrAccesoNombre()+"</b> se le retiro correctamente el perfil.");
                    }else{
                        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error:", "Al usuario: <b>"
                                +u.getUsrAccesoNombre()+"</b> se le retiro correctamente el perfil.");
                    }
                }else{
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "El usuario: <b>"
                                +u.getUsrAccesoNombre()+"</b> no tenia este perfil.");
                }
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Error para Asignar{0}", e);
        }finally{
            initUsuarios();
            PrimeUtiles.primeExecute("PF('wv-retirarPerfil').hide();");
        }
    }
    
    public void onTransfer(TransferEvent event) {
        builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((UsuarioAcceso) item).getUsrAccesoNombre()).append("<br />");
        }
        
        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Usuarios Transferidos", builder.toString());
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
    
    public void preparaAsignarPerfil() {
        PrimeUtiles.primeExecute("PF('wv-asignarPerfil').show();");
    }
    
    public void preparaRetirarPerfil() {
        LOG.info("Preparando Eliminar el perfil de los usuarios ");
        PrimeUtiles.primeExecute("PF('wv-retirarPerfil').show();");

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

    public StringBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(StringBuilder builder) {
        this.builder = builder;
    }

    
    
    
}
