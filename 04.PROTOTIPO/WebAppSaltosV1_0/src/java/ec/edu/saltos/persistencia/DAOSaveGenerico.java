package ec.edu.saltos.persistencia;

import ec.edu.saltos.config.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author guffenix
 */
public class DAOSaveGenerico extends DAOGenerico<Object, Integer> {

    public DAOSaveGenerico(Class<Object> clase) {
        super(clase);
    }

    
    public static boolean guardarLotes(List<Object> listaObjetos) {
        boolean guardado = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (listaObjetos != null && !listaObjetos.isEmpty()) {

            Transaction t = session.beginTransaction();
            try {
                listaObjetos.forEach((Object objeto) -> {
                    session.saveOrUpdate(objeto);
                });
                t.commit();
                guardado = true;
            } catch (Exception ex) {
                guardado = false;
                t.rollback();
                Logger.getLogger(DAOSaveGenerico.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return guardado;
    }
    
    
    /*
    public static boolean guardarLotes(List<Object> listaObjetos) { 
        boolean guardado = false;
        Session session = null;
        if (listaObjetos != null && !listaObjetos.isEmpty()){
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                //sesion.save(entidad);
                listaObjetos.forEach((Object objeto) -> {
                    session.saveOrUpdate(objeto);
                });
                session.getTransaction().commit();
                guardado=true;
            } catch (Exception e) {
                guardado = false;
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        
        
        return guardado;
    }
    */
}
