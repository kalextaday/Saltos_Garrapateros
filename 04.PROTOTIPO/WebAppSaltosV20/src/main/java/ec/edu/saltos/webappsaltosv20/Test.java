/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.webappsaltosv20;

import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.modelo.Aeronave;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author kalex
 */
public class Test {
    public static void main(String[] args){
        List<Aeronave> listaAeronaves;
        
        DAOAeronave daoaeronave=new DAOAeronave();
        
        Aeronave aer=daoaeronave.obtenerPorId(7);
        aer.setCapacidadtotalaer(200);
        if(daoaeronave.editar(aer)){
            System.out.println("se modifico exitosamente: "+aer.getNombreaer());
        }else{
            System.out.println("no se modifico");
        }
        
        Aeronave a2=new Aeronave();
        a2.setModeloaer("33");
        a2.setNombreaer("spacehx");
        a2.setCapacidadtotalaer(10);
        
        if(daoaeronave.guardar(a2)){
            System.out.println("Se guardo exitosamente");
        }else{
            System.out.println("No se guardo");
        }
        
        
        listaAeronaves=daoaeronave.obtenerAeronaves();
        
        listaAeronaves.forEach((a) -> {
            System.out.println(a.toString());
        });
        
        
        Aeronave aer3=listaAeronaves.get(0);
        if(daoaeronave.eliminar(aer3)){
            System.out.println("Se elimino exitosamente");
        }else{
            System.out.println("No se elimino");
        }
        
    }
    
    public static List<Aeronave> listarAeronaves() {
        List<Aeronave> lista = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM Aeronave";
            lista = sesion.createQuery(hql).list();
            t.commit();
        }catch(Exception e) {
            t.rollback();
        }finally{
            if (sesion != null) {
                sesion.close();
            }
        }
        return lista;
    }
    
    public static Aeronave buscarPorId(int _id) {
        Aeronave a = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM Aeronave where id="+_id;
            a = (Aeronave)sesion.createQuery(hql).uniqueResult();
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
    
    public static void agregar(Aeronave aeronave) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(aeronave);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public static void modificar(Aeronave aeronave) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(aeronave);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public static void eliminar(Aeronave aeronave) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(aeronave);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }
}
