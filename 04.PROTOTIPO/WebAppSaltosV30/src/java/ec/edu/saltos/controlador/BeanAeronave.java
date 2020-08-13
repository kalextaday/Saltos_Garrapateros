/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.modelo.Aeronave;
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
public class BeanAeronave implements Serializable{

    private List<Aeronave> listaAeronaves;
    private Aeronave aeronave;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanAeronave() {
        aeronave=new Aeronave();
    }
    
    @PostConstruct
    public void init(){
        obtenerAeronaves();
    }
    
    public void obtenerAeronaves(){
        DAOAeronave daoaeronave=new DAOAeronave();
        listaAeronaves=daoaeronave.obtenerAeronaves();
    }
    
    public void agregarAeronave() {
        DAOAeronave daoaeronave=new DAOAeronave();
        try{
            if(daoaeronave.guardar(aeronave)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarAeronave() {
        DAOAeronave daoaeronave=new DAOAeronave();
        try{
            if(daoaeronave.editar(aeronave)){
                limpiarAeronave();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarAeronave() {
        DAOAeronave daoaeronave=new DAOAeronave();
        try{
            if(daoaeronave.eliminar(aeronave)){
                limpiarAeronave();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarAeronave() {
        aeronave = new Aeronave();
    }

    public List<Aeronave> getListaAeronaves() {
        obtenerAeronaves();
        return listaAeronaves;
    }

    public void setListaAeronaves(List<Aeronave> listaAeronaves) {
        this.listaAeronaves = listaAeronaves;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
    }
    
    
}
