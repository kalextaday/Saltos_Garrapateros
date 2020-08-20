/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.UsuarioAcceso;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author kalex
 */
public class DAOUsuarioAcceso extends DAOGenerico<UsuarioAcceso, Integer>{

    public DAOUsuarioAcceso() {
        super(UsuarioAcceso.class);
    }
    
    public List<UsuarioAcceso> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(UsuarioAcceso _usuarioAcceso){
        return super.save(_usuarioAcceso);
    }
    
    public UsuarioAcceso obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(UsuarioAcceso _usuarioAcceso) {
        return super.update(_usuarioAcceso);
    }
    
    public Boolean eliminar(UsuarioAcceso _usuarioAcceso){
        return super.delete(_usuarioAcceso);
    }
    
    public UsuarioAcceso autenticar(String _username,String _password) {
        UsuarioAcceso a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM UsuarioAcceso where usr_acceso_clave='"+_password+"' and usr_acceso_nombre='"+_username+"'";
            a = (UsuarioAcceso)sesion.createQuery(hql).uniqueResult();
            t.commit();
            
        }catch(Exception e) {
            t.rollback();
        }finally{
            if (sesion != null) {
                sesion.close();
            }
        }
        return a;
    }
}
