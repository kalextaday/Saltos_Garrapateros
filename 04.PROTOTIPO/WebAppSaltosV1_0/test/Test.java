/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.persistencia.DAOOpcionPerfil;
import ec.edu.saltos.validaciones.Cedula;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author kalex
 */
public class Test {
    public static void main(String[] args){
        DAOOpcionPerfil dao=new DAOOpcionPerfil();
        if(dao.eliminarNOpcionesPerfil(2)){
            System.out.println("eliminados correctamente");
        }else{
            System.out.println("no se eliminaron");
        }
        
    }
    
    
}
