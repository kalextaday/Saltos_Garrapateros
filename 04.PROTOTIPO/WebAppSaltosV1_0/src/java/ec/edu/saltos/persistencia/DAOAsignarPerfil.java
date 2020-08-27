/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.AsignarPerfil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author kalex
 */
public class DAOAsignarPerfil extends DAOGenerico<AsignarPerfil, Integer>{

    public DAOAsignarPerfil() {
        super(AsignarPerfil.class);
    }
    
    public List<AsignarPerfil> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(AsignarPerfil _asignarPerfil){
        return super.save(_asignarPerfil);
    }
    
    public AsignarPerfil obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(AsignarPerfil _asignarPerfil) {
        return super.update(_asignarPerfil);
    }
    
    public Boolean eliminar(AsignarPerfil _asignarPerfil){
        return super.delete(_asignarPerfil);
    }
    
    public AsignarPerfil autenticarPerfil(int _idUsrAcceso,int _idPerfil) {
        AsignarPerfil a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM AsignarPerfil where id_usuario_acceso="+_idUsrAcceso+" and id_perfil="+_idPerfil;
            a = (AsignarPerfil)sesion.createQuery(hql).uniqueResult();
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
    
    public List<AsignarPerfil> obtenerPerfilesPorUsuarioAcceso(int _idUsuarioAcceso) {
        List<AsignarPerfil> a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM AsignarPerfil where id_usuario_acceso="+_idUsuarioAcceso;
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
