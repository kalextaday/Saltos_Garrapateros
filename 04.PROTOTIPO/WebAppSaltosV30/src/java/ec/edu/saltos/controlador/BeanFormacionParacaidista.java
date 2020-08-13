/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOFormacionParacaidista;
import ec.edu.saltos.modelo.FormacionParacaidista;
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
public class BeanFormacionParacaidista implements Serializable{

    private List<FormacionParacaidista> listaFormacionParacaidistas;
    private FormacionParacaidista formacionParacaidista;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanFormacionParacaidista() {
        formacionParacaidista=new FormacionParacaidista();
    }
    
    @PostConstruct
    public void init(){
        obtenerFormacionParacaidistas();
    }
    
    public void obtenerFormacionParacaidistas(){
        DAOFormacionParacaidista daoformacionParacaidista=new DAOFormacionParacaidista();
        listaFormacionParacaidistas=daoformacionParacaidista.obtenerFormacionParacaidistas();
    }
    
    public void agregarFormacionParacaidista() {
        DAOFormacionParacaidista daoformacionParacaidista=new DAOFormacionParacaidista();
        try{
            if(daoformacionParacaidista.guardar(formacionParacaidista)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarFormacionParacaidista() {
        DAOFormacionParacaidista daoformacionParacaidista=new DAOFormacionParacaidista();
        try{
            if(daoformacionParacaidista.editar(formacionParacaidista)){
                //limpiarFormacionParacaidista();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarFormacionParacaidista() {
        DAOFormacionParacaidista daoformacionParacaidista=new DAOFormacionParacaidista();
        try{
            if(daoformacionParacaidista.eliminar(formacionParacaidista)){
                //limpiarFormacionParacaidista();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarFormacionParacaidista() {
        formacionParacaidista = new FormacionParacaidista();
    }

    public List<FormacionParacaidista> getListaFormacionParacaidistas() {
        obtenerFormacionParacaidistas();
        return listaFormacionParacaidistas;
    }

    public void setListaFormacionParacaidistas(List<FormacionParacaidista> listaFormacionParacaidistas) {
        this.listaFormacionParacaidistas = listaFormacionParacaidistas;
    }

    public FormacionParacaidista getFormacionParacaidista() {
        return formacionParacaidista;
    }

    public void setFormacionParacaidista(FormacionParacaidista formacionParacaidista) {
        this.formacionParacaidista = formacionParacaidista;
    }
    
    
}
