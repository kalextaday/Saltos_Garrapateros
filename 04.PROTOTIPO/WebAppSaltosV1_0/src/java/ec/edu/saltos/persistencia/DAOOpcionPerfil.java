/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.OpcionPerfil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author kalex
 */
public class DAOOpcionPerfil extends DAOGenerico<OpcionPerfil, Integer>{

    public DAOOpcionPerfil() {
        super(OpcionPerfil.class);
    }
    
    public List<OpcionPerfil> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(OpcionPerfil _opcionPerfil){
        return super.save(_opcionPerfil);
    }
    
    public OpcionPerfil obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(OpcionPerfil _opcionPerfil) {
        return super.update(_opcionPerfil);
    }
    
    public Boolean eliminar(OpcionPerfil _opcionPerfil){
        return super.delete(_opcionPerfil);
    }
    
    public List<OpcionPerfil> opcionesPorPerfil(int _idPerfil) {
        List<OpcionPerfil> a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM OpcionPerfil where id_perfil="+_idPerfil;
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
    
    public Boolean eliminarNOpcionesPerfil(Integer _idPerfil) {
        Integer elementos = 0;
        Boolean result = false;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "DELETE FROM OpcionPerfil where id_perfil="+_idPerfil;
            elementos = sesion.createQuery(hql).executeUpdate();
            t.commit();
            result = true;
        }catch(Exception e) {
            t.rollback();
        }finally{
            if (sesion != null) {
                sesion.close();
            }
        }
        return result;
    }
    
    public Boolean eliminarPermisos(Integer _idPerfil) {
        List<OpcionPerfil> elementos = opcionesPorPerfil(_idPerfil);
        elementos.forEach(el -> {
            super.delete(el);
        });
        return true;
    }
    
}
