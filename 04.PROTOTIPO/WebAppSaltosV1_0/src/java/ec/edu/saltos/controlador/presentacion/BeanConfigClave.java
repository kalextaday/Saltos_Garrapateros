/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.presentacion;


import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.ConfigClave;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOConfigClave;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import ec.edu.saltos.util.FechaUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
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
public class BeanConfigClave extends FiltroAcceso implements Serializable{
    
    static final Logger LOG = Logger.getLogger(BeanConfigClave.class.getName());
    
    private AsignarPerfil perfilAccesado;
    
    private UsuarioAcceso usuarioAcceso;
    private ConfigClave configClave;
    
    private boolean estatusAlfa;
    private boolean estatusMayus;
    private boolean estatusMinus;
    private boolean estatusCharEspecial;
    
    
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanConfigClave() {
        super(FacesContext.getCurrentInstance().getExternalContext());
        usuarioAcceso=new UsuarioAcceso();
        configClave=new ConfigClave();
        perfilAccesado=new AsignarPerfil();
        perfilAccesado=(AsignarPerfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("asignarPerfil");

    }
    
    @PostConstruct
    public void init(){
        obtenerUsuarioAcceso();
        obtenerConfigClave();
    }
    
    public void obtenerUsuarioAcceso(){
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        usuarioAcceso=dao.obtenerPorId(perfilAccesado.getUsuarioAcceso().getIdUsuarioAcceso());
    }
    
    public void obtenerConfigClave(){
        DAOConfigClave dao=new DAOConfigClave();
        configClave=dao.obtenerPorId(usuarioAcceso.getConfigClave().getIdConfigClave());
    }

    public void modificarClave() {
        DAOConfigClave dao=new DAOConfigClave();
        configClave.setClaveFechaMod(FechaUtil.ahoraSinFormato());
        try{
            if(dao.editar(configClave)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }
    
    public ConfigClave getConfigClave() {
        return configClave;
    }

    public void setConfigClave(ConfigClave configClave) {
        this.configClave = configClave;
    }
    
    
    
    
}
