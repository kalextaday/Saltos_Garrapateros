/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAORecurso;
import ec.edu.saltos.modelo.Recurso;
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
public class BeanRecurso implements Serializable{

    private List<Recurso> listaRecursos;
    private Recurso recurso;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanRecurso() {
        recurso=new Recurso();
    }
    
    @PostConstruct
    public void init(){
        obtenerRecursos();
    }
    
    public void obtenerRecursos(){
        DAORecurso daorecurso=new DAORecurso();
        listaRecursos=daorecurso.obtenerRecursos();
    }
    
    public void agregarRecurso() {
        DAORecurso daorecurso=new DAORecurso();
        try{
            if(daorecurso.guardar(recurso)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarRecurso() {
        DAORecurso daorecurso=new DAORecurso();
        try{
            if(daorecurso.editar(recurso)){
                //limpiarRecurso();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarRecurso() {
        DAORecurso daorecurso=new DAORecurso();
        try{
            if(daorecurso.eliminar(recurso)){
                //limpiarRecurso();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarRecurso() {
        recurso = new Recurso();
    }

    public List<Recurso> getListaRecursos() {
        obtenerRecursos();
        return listaRecursos;
    }

    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }
    
    
}
