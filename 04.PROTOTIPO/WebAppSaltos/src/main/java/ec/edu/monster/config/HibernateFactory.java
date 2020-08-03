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
                    .addResource("ec/fin/emagic/modelo/AsignarEntidad.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/AsignarPerfil.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BalanceBalc.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BalanceDato.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BalanceIntermedio.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Balance.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BalancePresupuesto.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkBalance.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkBce.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkCanton.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkCaptacion.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkCosede.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkCredito.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Benchmark.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkServicios.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkSistemico.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkTarjeta.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/BenchmarkVolumenCredito.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Bitacora.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Buro.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CaptacionesB45.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CatalogoCanton.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CatalogoCatastro.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CatalogoDato.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CatalogoEventos.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CatalogoFeriado.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Catalogo.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CatalogoJerarquia.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/ConfigClave.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/ConfigEmail.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Configuracion.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Contrato.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CosteoA6.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CosteoAsignacion.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CosteoDriverProcesos.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CosteoEsfuerzo.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Costeo.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CosteoMasaSalarial.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/CosteoTabla108.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Descargas.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Entidad.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/EventosRiesgos.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/EventosRiesgosReales.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/GapPortafolio.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/GapSupuesto.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/MapaProcesos.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Oficina.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Pago.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Perfil.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Permiso.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/PlanEjecucion.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/PlanFinanciero.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/PlanPlanificacion.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/PlanPresupuesto.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Plantilla.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/RdmRdlDividendo.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/RdmRdlR38.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Recurso.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/SepsSeguros.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/StressSupuestos.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaC01.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaC02.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaC03.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaD01.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaR01.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaR02.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaR03.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaR04.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaR05.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaR20.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaR21.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaR22.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TablaS01.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TipoBalance.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TipoToken.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/TransaccionRegistrada.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/UsuarioAcceso.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/UsuarioClave.hbm.xml")
                    .addResource("ec/fin/emagic/modelo/Usuario.hbm.xml");
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
