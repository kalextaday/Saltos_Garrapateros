/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.app;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import ec.edu.saltos.util.FechaUtil;
import ec.edu.saltos.util.PrimeUtiles;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanPerfil implements Serializable{

    private AsignarPerfil perfilAccesado;
    private UsuarioAcceso usuarioAcceso;
    
    private List<Perfil> listaPerfiles;
    private Perfil perfilSeleccionado;
    private Perfil perfilPanel;
    
    
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanPerfil() {
        perfilAccesado=new AsignarPerfil();
    }
    
    @PostConstruct
    public void init(){
        obtenerMiUsuarioAcc();
        obtenerPerfiles();
        initPerfil();
        
    }
    
    public void obtenerMiUsuarioAcc() {
        perfilAccesado=new AsignarPerfil();
        perfilAccesado=(AsignarPerfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("asignarPerfil");
        
        DAOUsuarioAcceso dao=new DAOUsuarioAcceso();
        usuarioAcceso=dao.obtenerPorId(perfilAccesado.getUsuarioAcceso().getIdUsuarioAcceso());
    }
    
    public void initPerfil() {
        perfilPanel = new Perfil();
    }
    
    public void obtenerPerfiles(){
        DAOPerfil dao=new DAOPerfil();
        listaPerfiles=dao.obtenerTodos();
    }
    
    public void agregarPerfil() {
        DAOPerfil dao=new DAOPerfil();
        perfilSeleccionado.setPerfilEstatus(EstadosConfig.ACTIVADO.getCodigo());
        try{
            if(dao.guardar(perfilSeleccionado)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
                limpiarPerfil();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-crearPerfil').hide();");
        }
    }

    public void modificarPerfil() {
        DAOPerfil dao=new DAOPerfil();
        try{
            if(dao.editar(perfilSeleccionado)){
                limpiarPerfil();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-actualizarPerfil').hide();");
        }
    }

    public void eliminarPerfil() {
        DAOPerfil dao=new DAOPerfil();
        try{
            if(dao.eliminar(perfilSeleccionado)){
                limpiarPerfil();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void onRowSelect(SelectEvent event) {
        setPerfilSeleccionado((Perfil) event.getObject());
        setPerfilPanel((Perfil) event.getObject());
        PrimeUtiles.primeUpdate("form:panelPerfil");

    }
    

    public void onRowUnselect(UnselectEvent event) {
    }
    
    public Perfil preparaCrear() {
        perfilSeleccionado = new Perfil();
        PrimeUtiles.primeExecute("PF('wv-crearPerfil').show();");
        return perfilSeleccionado;

    }
    
    public Perfil preparaActualizar() {
        PrimeUtiles.primeExecute("PF('wv-actualizarPerfil').show();");
        return perfilSeleccionado;

    }

    public void preparaEliminar() {

        //LOG.log(Level.INFO, "Preparando perfil {0} para eliminar", perfilSeleccionado.getPerfilNomre());
        PrimeUtiles.primeExecute("PF('wv-eliminarPerfil').show();");
    }
    
    public void preparaDeshabilitar() {

        //LOG.log(Level.INFO, "Preparando perfil {0} para deshabilitar, esperando confirmaci\u00f3n del administrador...", perfilSeleccionado.getPerfilNomre());
        PrimeUtiles.primeExecute("PF('wv-deshabilitarPerfil').show();");
    }
    
    public void preparaHabilitar() {

        //LOG.log(Level.INFO, "Preparando perfil {0} para habilitar, esperando confirmaci\u00f3n del administrador...", perfilSeleccionado.getPerfilNomre());
        PrimeUtiles.primeExecute("PF('wv-habilitarPerfil').show();");
    }
    
    public void limpiarPerfil() {
        perfilSeleccionado = new Perfil();
    }

    public AsignarPerfil getPerfilAccesado() {
        return perfilAccesado;
    }

    public void setPerfilAccesado(AsignarPerfil perfilAccesado) {
        this.perfilAccesado = perfilAccesado;
    }

    public UsuarioAcceso getUsuarioAcceso() {
        return usuarioAcceso;
    }

    public void setUsuarioAcceso(UsuarioAcceso usuarioAcceso) {
        this.usuarioAcceso = usuarioAcceso;
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

    public Perfil getPerfilPanel() {
        return perfilPanel;
    }

    public void setPerfilPanel(Perfil perfilPanel) {
        this.perfilPanel = perfilPanel;
    }
    
}
