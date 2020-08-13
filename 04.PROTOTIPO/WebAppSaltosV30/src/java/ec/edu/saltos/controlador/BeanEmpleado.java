/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOEmpleado;
import ec.edu.saltos.modelo.Empleado;
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
public class BeanEmpleado implements Serializable{

    private List<Empleado> listaEmpleados;
    private Empleado empleado;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanEmpleado() {
        empleado=new Empleado();
    }
    
    @PostConstruct
    public void init(){
        obtenerEmpleados();
    }
    
    public void obtenerEmpleados(){
        DAOEmpleado daoempleado=new DAOEmpleado();
        listaEmpleados=daoempleado.obtenerEmpleados();
    }
    
    public void agregarEmpleado() {
        DAOEmpleado daoempleado=new DAOEmpleado();
        try{
            if(daoempleado.guardar(empleado)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarEmpleado() {
        DAOEmpleado daoempleado=new DAOEmpleado();
        try{
            if(daoempleado.editar(empleado)){
                limpiarEmpleado();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarEmpleado() {
        DAOEmpleado daoempleado=new DAOEmpleado();
        try{
            if(daoempleado.eliminar(empleado)){
                limpiarEmpleado();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarEmpleado() {
        empleado = new Empleado();
    }

    public List<Empleado> getListaEmpleados() {
        obtenerEmpleados();
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
}
