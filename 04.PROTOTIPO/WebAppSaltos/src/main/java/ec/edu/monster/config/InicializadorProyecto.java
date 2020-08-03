/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.config;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * import java.net.URL; import org.apache.logging.log4j.LogManager; import
 * org.apache.logging.log4j.core.LoggerContext;
 */
/**
 * Web application lifecycle listener.
 *
 * @author DavidLeonardo
 */
public class InicializadorProyecto implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getLogger(InicializadorProyecto.class.getName()).log(Level.INFO, "INICIANDO CONTEXTO GESTION DE RIESGOS");
        try {
            Logger.getLogger(InicializadorProyecto.class.getName()).log(Level.INFO, "Iniciando {0}", EstadosConfig.AMBIENTE.getDescripcion());
            HibernateFactory.cargarPropiedades();
            Logger.getLogger(InicializadorProyecto.class.getName()).log(Level.INFO, "CONTEXTO GESTION DE RIESGOS INICIADO CORRECTAMENTE.");
        } catch (Exception ex) {
            Logger.getLogger(InicializadorProyecto.class.getName()).log(Level.SEVERE, "Error: ".concat(ex.getMessage()));
        }
    }

    //Este metodo se ejecuta cuando se destruye el contexo, o cuando se detiene la aplicación.
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Logger.getLogger(InicializadorProyecto.class.getName()).log(Level.INFO, "CONTEXTO DESTRUIDO GESTION DE RIESGOS");

    }

}
