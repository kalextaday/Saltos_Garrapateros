/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Permiso;
import ec.edu.saltos.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOPermiso extends DAOGenerico<Permiso, Integer>{
    public DAOPermiso() {
        super(Permiso.class);
    }
    
    public List<Permiso> obtenerPermisos() {
        return super.findAll();
    }
    
    public Boolean guardar(Permiso permiso){
        return super.save(permiso);
    }
    
    public Permiso obtenerPorId(Integer _idPermiso) {
        return super.find(_idPermiso);
    }

    public Boolean editar(Permiso _permiso) {
        return super.update(_permiso);
    }
    
    public Boolean eliminar(Permiso _permiso){
        return super.delete(_permiso);
    }
    
    public List<Permiso> permisosPerfil(int _idPerfil) {
        List<Permiso> a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM Permiso where perfil_id="+_idPerfil;
            a = sesion.createQuery(hql).list();
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
