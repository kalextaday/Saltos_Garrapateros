/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;



import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.config.PaginaConfig;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOAsignarPerfil;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import ec.edu.saltos.util.PrimeUtiles;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanLogin extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanLogin.class.getName());
    
    private UsuarioAcceso usuarioAcceso;
    private AsignarPerfil perfilAccesado;
    private Perfil perfilSeleccionado;
    
    private List<Perfil> listaPerfiles;
    private List<AsignarPerfil> misPerfiles;
    
    
    private String nombreUsuario;
    private String claveUsuario;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanLogin() {
        super(FacesContext.getCurrentInstance().getExternalContext());
        usuarioAcceso=new UsuarioAcceso();
        perfilSeleccionado=new Perfil();
    }
    
    @PostConstruct
    public void init(){
        
    }
    
    public void obtenerPerfilesRegistrados(){
        DAOPerfil dao=new DAOPerfil();
        listaPerfiles=dao.obtenerTodos();
    }
    
    public void validarUsuarioAcceso(){

        DAOUsuarioAcceso daousuario=new DAOUsuarioAcceso();

        try{
            usuarioAcceso=daousuario.autenticar(nombreUsuario,claveUsuario);
            if(usuarioAcceso!=null){
                DAOAsignarPerfil daoAsignarPerfil=new DAOAsignarPerfil();
                misPerfiles=daoAsignarPerfil.obtenerPerfilesPorUsuarioAcceso(usuarioAcceso.getIdUsuarioAcceso());
                
                mostrarDialogSeleccionarPerfil();

            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","Datos incorrectos"));
            }
        }catch(Exception e){
            
        }
    }
    
    public void mostrarDialogSeleccionarPerfil() {
        PrimeUtiles.primeExecute("PF('wv-seleccionarPerfil').show();");
    }
    
    public void validarPerfil() throws Exception {
        PrimeUtiles.primeExecute("PF('wv-seleccionarPerfil').hide();");
        System.out.println("Perfil selecionado: " + perfilSeleccionado.getPerfilNombre());
        validarUsuarioPerfil();
    }
    
    public void validarUsuarioPerfil() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        
        try {
            DAOAsignarPerfil daoAsignarPerfil=new DAOAsignarPerfil();
            
            perfilAccesado=daoAsignarPerfil.autenticarPerfil(usuarioAcceso.getIdUsuarioAcceso(),perfilSeleccionado.getIdPerfil());
            
            if(perfilAccesado!=null){
                new ControlSesion().iniciarSesion(String.valueOf(usuarioAcceso.getPersona().getIdPersona())
                                                ,String.valueOf(perfilSeleccionado.getIdPerfil())
                                                ,usuarioAcceso.getUsrAccesoNombre()
                                                ,String.valueOf(usuarioAcceso.getIdUsuarioAcceso()));
                
                FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + PaginaConfig.PAGINA_INICIO.getUrl());
            }

            if (usuarioAcceso.getPersona().getPerEstatus().equals(EstadosConfig.PERSONA_EST_ARCHIVADO.getCodigo())) {
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_WARN, "Su usuario se encuentra ARCHIVADO, ya no se podrá utilizar nuevamente.");
            } else {
                LOG.log(Level.INFO, "Seguridad de Nivel III completa");
                if (usuarioAcceso.getPersona().getPerEstatus().equals(EstadosConfig.PERSONA_EST_DESACTIVADO.getCodigo())) {
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Su usuario se encuentra DESHABILITADO, comuníquese con el Administrador del Sistema, para solicitar HABILITAR su usuario.");
                } else {
                    
                }
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

    }

    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public UsuarioAcceso getUsuarioAcceso() {
        return usuarioAcceso;
    }

    public void setUsuarioAcceso(UsuarioAcceso usuarioAcceso) {
        this.usuarioAcceso = usuarioAcceso;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public AsignarPerfil getPerfilAccesado() {
        return perfilAccesado;
    }

    public void setPerfilAccesado(AsignarPerfil perfilAccesado) {
        this.perfilAccesado = perfilAccesado;
    }

    public Perfil getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public List<AsignarPerfil> getMisPerfiles() {
        return misPerfiles;
    }

    public void setMisPerfiles(List<AsignarPerfil> misPerfiles) {
        this.misPerfiles = misPerfiles;
    }
    
    
    
    
}
