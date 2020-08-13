/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Recurso;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author kalex
 */
public class DAORecurso extends DAOGenerico<Recurso, Integer>{
    public DAORecurso() {
        super(Recurso.class);
    }
    
    public List<Recurso> obtenerRecursos() {
        return super.findAll();
    }
    
    public Boolean guardar(Recurso recurso){
        return super.save(recurso);
    }
    
    public Recurso obtenerPorId(Integer _idRecurso) {
        return super.find(_idRecurso);
    }

    public Boolean editar(Recurso _recurso) {
        return super.update(_recurso);
    }
    
    public Boolean eliminar(Recurso _recurso){
        return super.delete(_recurso);
    }
    
    public List<Recurso> permisosRecursos(int _idRecurso) {
        List<Recurso> a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM Recurso where id_recurso="+_idRecurso;
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
