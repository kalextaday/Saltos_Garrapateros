/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Persona;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author kalex
 */
public class DAOPersona extends DAOGenerico<Persona, Integer>{

    public DAOPersona() {
        super(Persona.class);
    }
    
    public List<Persona> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Persona _persona){
        return super.save(_persona);
    }
    
    public Persona obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(Persona _persona) {
        return super.update(_persona);
    }
    
    public Boolean eliminar(Persona _persona){
        return super.delete(_persona);
    }
    
    public Persona obtenerUltimoRegistro() {
        Persona a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM Persona order by id_persona DESC";
            a = (Persona)sesion.createQuery(hql).setMaxResults(1).uniqueResult();
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
