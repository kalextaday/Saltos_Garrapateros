/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.CabeceraFactura;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author kalex
 */
public class DAOCabeceraFactura extends DAOGenerico<CabeceraFactura, Integer>{

    public DAOCabeceraFactura() {
        super(CabeceraFactura.class);
    }
    
    public List<CabeceraFactura> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(CabeceraFactura _cabeceraFactura){
        return super.save(_cabeceraFactura);
    }
    
    public CabeceraFactura obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(CabeceraFactura _cabeceraFactura) {
        return super.update(_cabeceraFactura);
    }
    
    public Boolean eliminar(CabeceraFactura _cabeceraFactura){
        return super.delete(_cabeceraFactura);
    }
    
    public CabeceraFactura obtenerUltimoRegistro() {
        CabeceraFactura a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM CabeceraFactura order by id_factura DESC";
            a = (CabeceraFactura)sesion.createQuery(hql).setMaxResults(1).uniqueResult();
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
