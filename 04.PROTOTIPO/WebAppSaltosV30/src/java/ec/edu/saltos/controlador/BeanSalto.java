/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOSalto;
import ec.edu.saltos.modelo.Salto;
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
public class BeanSalto implements Serializable{

    private List<Salto> listaSaltos;
    private Salto salto;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanSalto() {
        salto=new Salto();
    }
    
    @PostConstruct
    public void init(){
        obtenerSaltos();
    }
    
    public void obtenerSaltos(){
        DAOSalto daosalto=new DAOSalto();
        listaSaltos=daosalto.obtenerSaltos();
    }
    
    public void agregarSalto() {
        DAOSalto daosalto=new DAOSalto();
        try{
            if(daosalto.guardar(salto)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarSalto() {
        DAOSalto daosalto=new DAOSalto();
        try{
            if(daosalto.editar(salto)){
                limpiarSalto();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarSalto() {
        DAOSalto daosalto=new DAOSalto();
        try{
            if(daosalto.eliminar(salto)){
                limpiarSalto();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarSalto() {
        salto = new Salto();
    }

    public List<Salto> getListaSaltos() {
        obtenerSaltos();
        return listaSaltos;
    }

    public void setListaSaltos(List<Salto> listaSaltos) {
        this.listaSaltos = listaSaltos;
    }

    public Salto getSalto() {
        return salto;
    }

    public void setSalto(Salto salto) {
        this.salto = salto;
    }
    
    
}
