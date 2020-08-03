package ec.edu.monster.config.util;

//import ec.fin.emagic.acm_utilidades.AES256;
//import ec.fin.emagic.acm_utilidades.FechaUtiles;
import ec.edu.monster.config.ControlSesion;
//import ec.fin.emagic.dao.DAOEntidad;
//import ec.fin.emagic.dao.DAOUsuarioAcceso;
//import ec.fin.emagic.modelo.Bitacora;
//import ec.fin.emagic.modelo.Entidad;
//import ec.fin.emagic.modelo.UsuarioAcceso;
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
        /*
        if (cs.obtenerEstadoSesionUsuario()) {
            UsuarioAcceso usuario = new DAOUsuarioAcceso().obtenerUsuarioAccesoPorIDUsuario(Integer.parseInt(cs.obtenerIdUsuarioSesionActiva()));
            Entidad entidadSeleccionada = new DAOEntidad().obtenerEntidadPorId(cs.obtenerRucEntidadSesionActiva());

            Logger.getLogger(_clazz.getName())
                    .log(Level.INFO, "El usuario {0}, de la entidad {1}, ip {2}, ha ingresado al módulo:{3} .",
                            new Object[]{
                                usuario.getUsuario().getUsrIdentificacion(),
                                entidadSeleccionada.getRucEntidad(), ipIngreso,
                                _clazz.getName()});
        } else {
            Logger.getLogger(_clazz.getName())
                    .log(Level.INFO, "Usuario sin sesión, intenta acceder desde la ip {0}", ipIngreso);
        }*/

    }

    public static void logSesion(Class _clazz) {
        ControlSesion cs = new ControlSesion();
        String ipIngreso = SOUtiles.obtenerIP();
        /*
        if (cs.obtenerEstadoSesionUsuario()) {
            UsuarioAcceso usuario = new DAOUsuarioAcceso().obtenerUsuarioAccesoPorIDUsuario(Integer.parseInt(cs.obtenerIdUsuarioSesionActiva()));
            Entidad entidadSeleccionada = new DAOEntidad().obtenerEntidadPorId(cs.obtenerRucEntidadSesionActiva());

            Logger.getLogger(_clazz.getName())
                    .log(Level.INFO, "Verificando sesión del usuario {0}, de la entidad {1}, ip {2} ",
                            new Object[]{
                                usuario.getUsuario().getUsrIdentificacion(),
                                entidadSeleccionada.getRucEntidad(), ipIngreso,
                                _clazz.getName()});
        } else {
            Logger.getLogger(_clazz.getName())
                    .log(Level.INFO, "Ha finalizado la sesión del usuario con ip {0}", ipIngreso);
        }
        */
    }
    /*
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
        registro.setFechaAlta(FechaUtiles.ahora());
        registro.setFechaMod(FechaUtiles.ahora());
        registro.setIp(ipIngreso);
        registro.setTipoOperacion(tipoOperacion);
        registro.setModuloAfectado(moduloAfectado);
        registro.setDescripcion(descripcion);
        registro.setEstatus(tipoLoggeo);
        registro.setCheckup(AES256.toAES256(_cadenaSha.toString()));
        registro.setSo(sistemaOperativo);
        registro.setNavegador(navegadorUsuario);

        return registro;
    }*/
}
