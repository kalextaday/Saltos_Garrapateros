package ec.edu.monster.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

/**
 *
 * @author JIMM
 */
public class HibernateFactory {

    private static SessionFactory sessionFactory;

    private static final Logger LOGGER = Logger.getLogger(HibernateFactory.class.getName());

    /**
     * Construye una nueva Singleton SessionFactory
     *
     * @return
     * @throws HibernateException
     */
    public static SessionFactory construirSessionFactory() throws HibernateException {
        if (sessionFactory != null) {
            cerrarFactory();
        }
        return configurarSessionFactory();
    }

    /**
     * Crea una SessionFactory, si esta no ha sido creada.
     *
     * @return
     */
    public static SessionFactory construirSiNecesita() {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        try {
            return configurarSessionFactory();
        } catch (HibernateException e) {
            LOGGER.log(Level.SEVERE, "Error:".concat(e.getMessage()));
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session abrirSession() throws HibernateException {
        construirSiNecesita();
        return sessionFactory.openSession();
    }

    public static void cerrarFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException exception) {
                LOGGER.log(Level.SEVERE, "No se pudo cerrar SessionFactory:".concat(exception.getMessage()));
            }
        }
    }

    public static void cerrar(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException exception) {
                LOGGER.log(Level.SEVERE, "No se pudo cerrar Session:".concat(exception.getMessage()));
            }
        }
    }

    public static void rollback(Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (HibernateException exception) {
            LOGGER.log(Level.SEVERE, "No se pudo hacer rollback Transaction:".concat(exception.getMessage()));
        }
    }

    /**
     *
     * @return @throws HibernateException
     */
    private static SessionFactory configurarSessionFactory() throws HibernateException {

        sessionFactory = new Configuration().configure(EstadosConfig.ARCHIVO_HIBERNATE.getDescripcion()).buildSessionFactory();
//        sessionFactory = cargarPropiedades();
        return sessionFactory;
    }

    public static SessionFactory cargarPropiedades() {

//        SessionFactory sessionFactory = null;
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(Ultra.CIFRADO.getNombre());

        Properties properties = new EncryptableProperties(encryptor);
        InputStream in = null;
        try {
            in = HibernateFactory.class.getClassLoader().getResourceAsStream(Ultra.ARCHIVO_PROPIEDADES.getNombre());
            properties.load(in);
            Configuration configuration = new Configuration();

            properties.setProperty("hibernate.hikari.dataSourceClassName", "com.microsoft.sqlserver.jdbc.SQLServerDataSource");
            properties.setProperty("hibernate.hikari.dataSource.url", properties.getProperty("uri." + EstadosConfig.AMBIENTE.getCodigo()));
            //properties.setProperty("hibernate.hikari.dataSource.databaseName", properties.getProperty("db." + EstadosConfig.AMBIENTE.getCodigo()));
            properties.setProperty("hibernate.hikari.dataSource.user", properties.getProperty("us." + EstadosConfig.AMBIENTE.getCodigo()));
            properties.setProperty("hibernate.hikari.dataSource.password", properties.getProperty("cl." + EstadosConfig.AMBIENTE.getCodigo()));
            configuration.configure(EstadosConfig.ARCHIVO_HIBERNATE.getDescripcion()).addProperties(properties);
            configuration
                    .addResource("ec/edu/monster/modelo/Aeronave.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Vuelo.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Cliente.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Paracaidista.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Permiso.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Perfil.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Recurso.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Usuario.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Salto.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Descuento.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Piloto.hbm.xml")
                    .addResource("ec/edu/monster/modelo/ServicioAdicional.hbm.xml")
                    .addResource("ec/edu/monster/modelo/FormacionParacaidista.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Responsable.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Empleado.hbm.xml")
                    .addResource("ec/edu/monster/modelo/Pedido.hbm.xml")
                    .addResource("ec/edu/monster/modelo/DetalleVuelo.hbm.xml");
            StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(configuration.getProperties());
            StandardServiceRegistry standardServiceRegistry = sb.build();
            sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);

        } catch (IOException e) {
            Logger.getLogger(HibernateFactory.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(HibernateFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return sessionFactory;
    }
}
