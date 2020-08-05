package ec.edu.monster.dao;

import ec.edu.monster.dao.*;
import ec.edu.monster.filtro.*;
import ec.edu.monster.config.HibernateFactory;
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

    private static final Logger LOG = Logger.getLogger(DAOGenerico.class.getName());

    Session session;

    private Transaction tx;

    private final Class<T> clase;
    private String dataBase;

    public DAOGenerico(Class<T> clase) {
        this.clase = clase;
    }

    public DAOGenerico(Class<T> clase, String dataBase) {
        this.clase = clase;
        this.dataBase = dataBase;
    }

    protected T save(T entidad) {

        try {
            startOperation();

            if (session != null) {
                session.save(entidad);
                tx.commit();
            }
        } catch (HibernateException e) {
            handleException(e);
            entidad = null;
        } finally {
            HibernateFactory.cerrar(session);
        }
        return entidad;
    }
/*
    protected boolean saveLotes(List<T> entidad) {

        Boolean result = false;
        try {
            startOperation();
            int batchSize = 25;

            if (session != null) {
                int[] i = {0};
                
                entidad.stream().forEach(t -> {
                    session.save(t);
                    if (i[0] % 1000 == 0) {
                        tx.commit();
                    }
                    if (i[0] > 0 && i[0] % batchSize == 0) {
                        session.flush();
                        session.clear();
                    }
                    i[0]++;
                });
                entidad.stream().forEach((T t) -> {
                    session.save(t);
                    if (i[0] % 1000 == 0) {
                        tx.commit();
                    }
                    if (i[0] > 0 && i[0] % batchSize == 0) {
                        session.flush();
                        session.clear();
                    }
                    i[0]++;
                });
                tx.commit();
                result = true;
            }
        } catch (HibernateException e) {
            handleException(e);
            entidad = null;
        } finally {
            HibernateFactory.cerrar(session);
        }

        return result;
    }
*/
    protected boolean saveOrUpdate(T entidad) {
        boolean guardado = false;
        try {
            startOperation();
            if (session != null) {
                session.saveOrUpdate(entidad);
                tx.commit();
                guardado = true;
            }
        } catch (HibernateException e) {
            handleException(e);
            entidad = null;
        } finally {
            HibernateFactory.cerrar(session);
        }
        return guardado;
    }

    protected T merge(T entidad) {

        try {
            startOperation();
            if (session != null) {
                session.merge(entidad);
                tx.commit();
            }
        } catch (HibernateException e) {
            handleException(e);
            entidad = null;
        } finally {
            HibernateFactory.cerrar(session);
        }
        return entidad;
    }

    protected boolean delete(T entidad) {
        boolean borrado = false;
        try {
            startOperation();
            if (session != null) {
                session.delete(entidad);
                tx.commit();
                borrado = true;
            }
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.cerrar(session);
        }
        return borrado;
    }
/*
    protected boolean deleteLotes(List<T> entidad) {

        Boolean result = false;
        try {
            startOperation();

            if (session != null) {
                int[] i = {0};
                entidad.stream().forEach(t -> {

                    session.delete(t);
                    if (i[0] % 1000 == 0) {
                        tx.commit();
                    }
                    if (i[0] % 20 == 0) {
                        session.flush();
                        session.clear();
                    }
                    i[0]++;
                });
                tx.commit();
                result = true;
            }
        } catch (HibernateException e) {
            handleException(e);
            result = false;
            entidad = null;
        } finally {
            HibernateFactory.cerrar(session);
        }

        return result;
    }
*/
    protected T find(ID id) {
        T entidad = null;
        try {
            startOperation();
            if (session != null) {
                entidad = (T) session.get(clase, id);
                tx.commit();
            }
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.cerrar(session);
        }
        return entidad;
    }

    protected List<T> findAll() {
        List<T> lista = null;
        try {
            startOperation();
            if (session != null) {
                lista = session.createQuery("from " + clase.getName())
                        .list();
                tx.commit();
            }

        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.cerrar(session);
        }
        return lista;
    }

    protected List<T> findRange(int minimo, int maximo) {
        List<T> lista = null;
        try {
            startOperation();
            if (session != null) {
                lista = session.createQuery("from " + clase.getName())
                        .setFirstResult(minimo)
                        .setMaxResults(maximo)
                        .list();
                tx.commit();
            }

        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.cerrar(session);
        }
        return lista;
    }

    protected void handleException(HibernateException e) {
        HibernateFactory.rollback(tx);
        LOG.log(Level.SEVERE, "Se hará un rollback, Clase:{0} Error:{1}", new Object[]{clase.getCanonicalName(), e.getMessage()});

    }

    protected void startOperation() throws HibernateException {
        session = HibernateFactory.abrirSession();
        if (session != null) {
            tx = session.beginTransaction();
        } else {
            LOG.log(Level.SEVERE, null, "No existe conexión a la base de datos");
        }
    }

    public String getDataBase() {
        return dataBase;
    }

}
