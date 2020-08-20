/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;



import ec.edu.saltos.config.PaginaConfig;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOAsignarPerfil;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
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
public class BeanLogin implements Serializable{

    
    private UsuarioAcceso usuarioAcceso;
    private AsignarPerfil perfilAccesado;
    private Perfil perfilSeleccionado;
    private List<Perfil> listaPerfiles;
    private String nombreUsuario;
    private String claveUsuario;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanLogin() {
        usuarioAcceso=new UsuarioAcceso();
        perfilAccesado=new AsignarPerfil();
    }
    
    @PostConstruct
    public void init(){
        
    }
    
    public void obtenerPerfilesRegistrados(){
        DAOPerfil dao=new DAOPerfil();
        listaPerfiles=dao.obtenerTodos();
    }
    
    public String iniciarSesion(){
        String redireccion=null;
        DAOUsuarioAcceso daousuario=new DAOUsuarioAcceso();

        try{
            usuarioAcceso=daousuario.autenticar(nombreUsuario,claveUsuario);
            if(usuarioAcceso!=null){
                
                //obtenerPerfilAcceso(usuarioAcceso.getIdUsuarioAcceso(),perfilSeleccionado.getIdPerfil());
                obtenerPerfilAcceso(usuarioAcceso.getIdUsuarioAcceso(),1);
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("asignarPerfil", perfilAccesado);
                
                redireccion=PaginaConfig.PAGINA_INICIO.getUrl();
                
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","Datos incorrectos"));
            }
        }catch(Exception e){
            
        }
        
        return redireccion;
    }
    
    public void obtenerPerfilAcceso(int idUsrAcceso,int idPerfil){
        DAOAsignarPerfil dao=new DAOAsignarPerfil();
        
        perfilAccesado=dao.autenticarPerfil(idUsrAcceso, idPerfil);
        
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
    
    
    
}
