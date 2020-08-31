/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.ConfigClave;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author kalex
 */
public class DAOConfigClave extends DAOGenerico<ConfigClave, Integer>{

    public DAOConfigClave() {
        super(ConfigClave.class);
    }
    
    public List<ConfigClave> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(ConfigClave _configClave){
        return super.save(_configClave);
    }
    
    public ConfigClave obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(ConfigClave _configClave) {
        return super.update(_configClave);
    }
    
    public Boolean eliminar(ConfigClave _configClave){
        return super.delete(_configClave);
    }
    
    public ConfigClave obtenerUltimoRegistro() {
        ConfigClave a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM ConfigClave order by id_config_clave DESC";
            a = (ConfigClave)sesion.createQuery(hql).setMaxResults(1).uniqueResult();
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
