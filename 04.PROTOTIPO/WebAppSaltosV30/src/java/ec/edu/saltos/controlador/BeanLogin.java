/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.config.PaginaConfig;
import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAOUsuario;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.Usuario;
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
public class BeanLogin implements Serializable{

    
    private Usuario usuario;
    private String nombreUsuario;
    private String claveUsuario;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanLogin() {
    }
    
    @PostConstruct
    public void init(){
        usuario=new Usuario();
    }
    
    public String iniciarSesion(){
        String redireccion=null;
        DAOUsuario daousuario=new DAOUsuario();

        try{
            usuario=daousuario.autenticar(nombreUsuario,claveUsuario);
            if(usuario!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                
                //new ControlSesion().iniciarSesion(String.valueOf(usuario.getPerfil().getIdPerfil()), String.valueOf(usuario.getIdUsuario()), usuario.getNombreusuario(), String.valueOf(usuario.getEmpleado().getIdEmpleado()));

                
                redireccion=PaginaConfig.PAGINA_INICIO.getUrl();
                
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","Datos incorrectos"));
            }
        }catch(Exception e){
            
        }
        
        return redireccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
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
    
    
}
