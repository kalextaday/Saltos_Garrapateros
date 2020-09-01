/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Empresa;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOAsignarPerfil;
import ec.edu.saltos.persistencia.DAOEmpresa;
import ec.edu.saltos.persistencia.DAOOpcionPerfil;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.validaciones.Cedula;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author kalex
 */
public class Test {
    public static void main(String[] args){
        /*
        DAOOpcionPerfil dao=new DAOOpcionPerfil();
        if(dao.eliminarNOpcionesPerfil(2)){
            System.out.println("eliminados correctamente");
        }else{
            System.out.println("no se eliminaron");
        }
        */
        List<Empresa> lista;
        DAOEmpresa daopersona=new DAOEmpresa();
        
        lista=daopersona.obtenerTodos();
        
        System.out.println("inicio");
        for(Empresa p:lista){
            System.out.println("--> "+p.getEmpRazonSocial());
        }
        
    }
    
    
}
