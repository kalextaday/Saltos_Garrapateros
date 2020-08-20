/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.config.PaginaConfig;
import ec.edu.saltos.modelo.AsignarPerfil;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanSesion implements Serializable{

    public void verificarSesion(){
        try{
            FacesContext context=FacesContext.getCurrentInstance();
            AsignarPerfil asignarPerfil;
            asignarPerfil=(AsignarPerfil) context.getExternalContext().getSessionMap().get("asignarPerfil");
            
            if(asignarPerfil==null){
                //context.getExternalContext().redirect("./../error-sesion.xhtml");
                context.getExternalContext().redirect(context.getExternalContext()
                        .getRequestContextPath()+PaginaConfig.PAGINA_VISTA_CADUCADA.getUrl());
            }
        }catch(IOException e){
            System.out.println("excepcion: "+e);
        }
    }
    
    public void cerrarSesion(){
        try{
            FacesContext context=FacesContext.getCurrentInstance();
            
            context.getExternalContext().invalidateSession();
            
            //FacesContext.getCurrentInstance().getExternalContext().redirect("./../login.xhtml");
            context.getExternalContext().redirect(context.getExternalContext()
                    .getRequestContextPath()+PaginaConfig.PAGINA_LOGIN.getUrl());
        }catch(IOException e){
            System.out.println("excepcion: "+e);
        }
    }
}
