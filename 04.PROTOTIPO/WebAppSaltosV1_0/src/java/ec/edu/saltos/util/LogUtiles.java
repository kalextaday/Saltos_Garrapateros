package ec.edu.saltos.util;

import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.modelo.Bitacora;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guffenix
 */
public class LogUtiles {

    /*
    * @param <T>
     */
    public static void logIngreso(Class _clazz) {
        ControlSesion cs = new ControlSesion();
        String ipIngreso = SOUtiles.obtenerIP();
        if (cs.obtenerEstadoSesionUsuario()) {
            UsuarioAcceso usuario = new DAOUsuarioAcceso().obtenerPorId(Integer.parseInt(cs.obtenerIdUsuarioAccesoSesionActiva()));
            

            Logger.getLogger(_clazz.getName())
                    .log(Level.INFO, "El usuario {0}, de la entidad {1}, ip {2}, ha ingresado al módulo:{3} .",
                            new Object[]{
                                usuario.getPersona().getPerNombres(),
                                ipIngreso,
                                _clazz.getName()});
        } else {
            Logger.getLogger(_clazz.getName())
                    .log(Level.INFO, "Usuario sin sesión, intenta acceder desde la ip {0}", ipIngreso);
        }

    }

    public static void logSesion(Class _clazz) {
        ControlSesion cs = new ControlSesion();
        String ipIngreso = SOUtiles.obtenerIP();
        if (cs.obtenerEstadoSesionUsuario()) {
            UsuarioAcceso usuario = new DAOUsuarioAcceso().obtenerPorId(Integer.parseInt(cs.obtenerIdUsuarioAccesoSesionActiva()));
            Logger.getLogger(_clazz.getName())
                    .log(Level.INFO, "Verificando sesión del usuario {0}, de la entidad {1}, ip {2} ",
                            new Object[]{
                                usuario.getPersona().getPerNombres(),
                                _clazz.getName()});
        } else {
            Logger.getLogger(_clazz.getName())
                    .log(Level.INFO, "Ha finalizado la sesión del usuario con ip {0}", ipIngreso);
        }

    }

    public static Bitacora registroActividad(UsuarioAcceso usuario, String tipoOperacion, String moduloAfectado, String descripcion, String tipoLoggeo, String nombrePerfil) throws Exception {

        String ipIngreso = SOUtiles.obtenerIP();
        String navegadorUsuario = SOUtiles.navegador();
        String sistemaOperativo = SOUtiles.miSistemaOperativo();

        StringBuilder _cadenaSha = new StringBuilder();
        _cadenaSha.append(usuario.getIdUsuarioAcceso())
                .append(tipoLoggeo)
                .append(ipIngreso)
                .append(nombrePerfil)
                .append(navegadorUsuario)
                .append(sistemaOperativo);

        Bitacora registro = new Bitacora();
        registro.setUsuarioAcceso(usuario);
        registro.setBitFecha(FechaUtil.ahoraSinFormato());
        registro.setBitIp(ipIngreso);
        registro.setBitOperacion(tipoOperacion);
        registro.setBitOpcionAfectado(moduloAfectado);
        registro.setBitDescripcion(descripcion);
        registro.setBitSo(sistemaOperativo);
        registro.setBitNavegador(navegadorUsuario);

        return registro;
    }

}
