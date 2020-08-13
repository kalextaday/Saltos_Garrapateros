/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOEmpleado;
import ec.edu.saltos.dao.DAOPerfil;
import ec.edu.saltos.dao.DAOUsuario;
import ec.edu.saltos.modelo.Empleado;
import ec.edu.saltos.modelo.Perfil;
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
public class BeanUsuario implements Serializable{

    private List<Usuario> listaUsuarios;
    private Usuario usuario;
    
    private Empleado empleado;
    private List<Empleado> listaEmpleados;
    
    private Perfil perfil;
    private List<Perfil> listaPerfiles;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanUsuario() {
        usuario=new Usuario();
        empleado=new Empleado();
        perfil=new Perfil();
    }
    
    @PostConstruct
    public void init(){
        obtenerUsuarios();
        obtenerEmpleados();
        obtenerPerfiles();
    }
    
    public void obtenerUsuarios(){
        DAOUsuario daousuario=new DAOUsuario();
        listaUsuarios=daousuario.obtenerUsuarios();
    }
    
    public void obtenerEmpleados(){
        DAOEmpleado daoempleado=new DAOEmpleado();
        listaEmpleados=daoempleado.obtenerEmpleados();
    }
    
    public void obtenerPerfiles(){
        DAOPerfil daoperfil=new DAOPerfil();
        listaPerfiles=daoperfil.obtenerPerfils();
    }
    
    public void agregarUsuario() {
        DAOUsuario daousuario=new DAOUsuario();
        
        usuario.setEmpleado(empleado);
        usuario.setPerfil(perfil);
        usuario.setClaveusu("12345");
        try{
            if(daousuario.guardar(usuario)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarUsuario() {
        DAOUsuario daousuario=new DAOUsuario();
        usuario.setEmpleado(empleado);
        usuario.setPerfil(perfil);
        try{
            if(daousuario.editar(usuario)){
                //limpiarUsuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarUsuario() {
        DAOUsuario daousuario=new DAOUsuario();
        try{
            if(daousuario.eliminar(usuario)){
                //limpiarUsuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarUsuario() {
        usuario = new Usuario();
    }

    public List<Usuario> getListaUsuarios() {
        obtenerUsuarios();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getListaEmpleados() {
        obtenerEmpleados();
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getListaPerfiles() {
        obtenerPerfiles();
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }
    
    
}
