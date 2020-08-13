package ec.edu.saltos.dao;

import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Aeronave;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author guffenix
 * @param <T>
 * @param <ID>
 */
public abstract class DAOGenerico<T, ID extends Serializable> {

    //private static final Logger LOG = Logger.getLogger(DAOGenerico.class.getName());

    Session session;

    private Transaction tx;

    private final Class<T> clase;

    public DAOGenerico(Class<T> clase) {
        this.clase = clase;
    }

    protected boolean save(T entidad) { 
        boolean guardado = false;
        Session sesion = null;
        
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(entidad);
            sesion.getTransaction().commit();
            guardado=true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        
        
        return guardado;
    }

    protected boolean update(T entidad) {
        
        boolean guardado = false;

        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(entidad);
            sesion.getTransaction().commit();
            guardado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return guardado;
    }

    protected boolean delete(T entidad) {
        boolean borrado = false;
        
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(entidad);
            sesion.getTransaction().commit();
            borrado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return borrado;
    }

    protected T find(ID _id) {
        T entidad = null;
        Session sesion = null;
        Transaction t=null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM "+clase.getName()+ " where id="+_id;
            entidad = (T)sesion.createQuery(hql).uniqueResult();
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

    protected List<T> findAll() {
        List<T> lista = null;
        Session sesion = null;
        Transaction t=null;
        
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            t = sesion.beginTransaction();
            String hql = "FROM "+clase.getName();
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
    
    protected boolean saveLotes(List<T> entidad) {

        Boolean result = false;

        return result;
    }

    protected List<T> findRange(int minimo, int maximo) {
        
        List<T> lista = null;

        return lista;
    }
    
    protected boolean deleteLotes(List<T> entidad) {

        Boolean result = false;

        return result;
    }

    protected void startOperation() throws HibernateException {
        /*
        session = HibernateFactory.abrirSession();
        if (session != null) {
            tx = session.beginTransaction();
        } else {
            //LOG.log(Level.SEVERE, null, "No existe conexión a la base de datos");
        }*/
    }

}
