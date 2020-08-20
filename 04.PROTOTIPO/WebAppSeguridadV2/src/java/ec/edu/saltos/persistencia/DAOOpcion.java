/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Opcion;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author kalex
 */
public class DAOOpcion extends DAOGenerico<Opcion, Integer>{

    public DAOOpcion() {
        super(Opcion.class);
    }
    
    public List<Opcion> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Opcion _opcion){
        return super.save(_opcion);
    }
    
    public Opcion obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(Opcion _opcion) {
        return super.update(_opcion);
    }
    
    public Boolean eliminar(Opcion _opcion){
        return super.delete(_opcion);
    }
    
    public Opcion obtenerOrigen() {
        Opcion a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM Opcion where rec_nombre='"+EstadosConfig.RECURSO_ORIGEN.getCodigo()+"'";
            a = (Opcion)sesion.createQuery(hql).uniqueResult();
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
    
    public List<Opcion> obtenerOpcionesSudo(int _idPadre) {
        List<Opcion> opciones = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM Opcion where id_opcion_padre="+_idPadre+" "
                        +"order by rec_index ASC";
            opciones =sesion.createQuery(hql).list();
            t.commit();
            
        }catch(Exception e) {
            t.rollback();
        }finally{
            if (sesion != null) {
                sesion.close();
            }
        }
        return opciones;
    }
}
