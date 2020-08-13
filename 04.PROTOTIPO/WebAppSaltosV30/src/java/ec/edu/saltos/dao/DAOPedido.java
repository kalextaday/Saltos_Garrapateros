/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Pedido;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author kalex
 */
public class DAOPedido extends DAOGenerico<Pedido, Integer>{
    public DAOPedido() {
        super(Pedido.class);
    }
    
    public List<Pedido> obtenerPedidos() {
        return super.findAll();
    }
    
    public Boolean guardar(Pedido pedido){
        return super.save(pedido);
    }
    
    public Pedido obtenerPorId(Integer _idPedido) {
        return super.find(_idPedido);
    }

    public Boolean editar(Pedido _pedido) {
        return super.update(_pedido);
    }
    
    public Boolean eliminar(Pedido _pedido){
        return super.delete(_pedido);
    }
    
    public Pedido obtenerUltimo(){
        Pedido entidad = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM Pedido order by id_pedido DESC LIMIT 1";
            entidad = (Pedido)sesion.createQuery(hql).uniqueResult();
            t.commit();
        }catch(Exception e) {
            t.rollback();
        }finally{
            if (sesion != null) {
                sesion.close();
            }
        }
        return entidad;
    }
}
