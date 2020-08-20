/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.app;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.util.FechaUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanPersona implements Serializable{

    private List<Persona> listaPersonas;
    private Persona persona;
    
    private List<Persona> personasSeleccionados;
    
    private String ciudad;
    private String sector;
    private String calle;
    private String numCasa;
    private boolean estatus;
    
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanPersona() {
        persona=new Persona();
    }
    
    @PostConstruct
    public void init(){
        obtenerPersonas();
    }
    
    public void obtenerPersonas(){
        DAOPersona daopersona=new DAOPersona();
        listaPersonas=daopersona.obtenerTodos();
    }
    
    public void agregarPersona() {
        DAOPersona daopersona=new DAOPersona();
        persona.setUsrDireccion(ciudad+","+sector+","+calle+","+numCasa);
        persona.setUsrFoto("imagenes/app/personas/fotos/");
        persona.setUsrFechaCreacion(FechaUtil.obtenerFechaActualSinFormato());
        persona.setUsrFechaMod(FechaUtil.obtenerFechaActualSinFormato());
        
        try{
            if(daopersona.guardar(persona)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
                limpiarPersona();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }finally{
            
        }
    }

    public void modificarPersona() {
        DAOPersona daopersona=new DAOPersona();
        try{
            if(daopersona.editar(persona)){
                limpiarPersona();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarPersona() {
        DAOPersona daopersona=new DAOPersona();
        try{
            if(daopersona.eliminar(persona)){
                limpiarPersona();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarPersona() {
        persona = new Persona();
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonasSeleccionados() {
        return personasSeleccionados;
    }

    public void setPersonasSeleccionados(List<Persona> personasSeleccionados) {
        this.personasSeleccionados = personasSeleccionados;
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
    
    
    
    
}
