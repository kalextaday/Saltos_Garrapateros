/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOPerfil;
import ec.edu.saltos.modelo.Perfil;
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
public class BeanPerfil implements Serializable{

    private List<Perfil> listaPerfiles;
    private Perfil perfil;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanPerfil() {
        perfil=new Perfil();
    }
    
    @PostConstruct
    public void init(){
        obtenerPerfils();
    }
    
    public void obtenerPerfils(){
        DAOPerfil daoperfil=new DAOPerfil();
        listaPerfiles=daoperfil.obtenerPerfils();
    }
    
    public void agregarPerfil() {
        DAOPerfil daoperfil=new DAOPerfil();
        try{
            if(daoperfil.guardar(perfil)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarPerfil() {
        DAOPerfil daoperfil=new DAOPerfil();
        try{
            if(daoperfil.editar(perfil)){
                //limpiarPerfil();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarPerfil() {
        DAOPerfil daoperfil=new DAOPerfil();
        try{
            if(daoperfil.eliminar(perfil)){
                //limpiarPerfil();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarPerfil() {
        perfil = new Perfil();
    }

    public List<Perfil> getListaPerfils() {
        obtenerPerfils();
        return listaPerfiles;
    }

    public void setListaPerfils(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    
}
