package ec.edu.monster.controlador;


/*
import ec.edu.monster.dao.DAOAsignacionEntidadUsuario;
import ec.edu.monster.dao.DAOAsignacionPerfilUsuario;
import ec.edu.monster.dao.DAOConfigClave;
import ec.edu.monster.dao.DAOContrasenia;
import ec.edu.monster.dao.DAOSaveGenerico;
import ec.edu.monster.dao.DAOUsuarioAcceso;
import ec.edu.monster.modelo.AsignarEntidad;
import ec.edu.monster.modelo.AsignarPerfil;
import ec.edu.monster.modelo.Bitacora;
import ec.edu.monster.modelo.ConfigClave;
import ec.edu.monster.modelo.Entidad;
import ec.edu.monster.modelo.Perfil;
import ec.edu.monster.modelo.Usuario;
import ec.edu.monster.modelo.UsuarioAcceso;
import ec.edu.monster.modelo.UsuarioClave;*/
import ec.edu.monster.utilidades.Validaciones;
import ec.edu.monster.filtro.ControlSesion;
import ec.edu.monster.config.EstadosConfig;
import ec.edu.monster.config.HibernateFactory;
import ec.edu.monster.config.PaginaConfig;
import ec.edu.monster.config.OperacionConfig;
import ec.edu.monster.dao.DAOAeronave;
import ec.edu.monster.utilidades.AES256;
import ec.edu.monster.utilidades.FechaUtiles;
import ec.edu.monster.utilidades.PrimeUtiles;
import ec.edu.monster.utilidades.SOUtiles;
import ec.edu.monster.utilidades.Utilidades;
import ec.edu.monster.utilidades.LogUtiles;
import ec.edu.monster.filtro.FiltroAcceso;
import ec.edu.monster.modelo.Aeronave;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;

/**
 *
 * @author guffenix
 */
@ManagedBean
@ViewScoped
public class BeanLoginAdmin extends FiltroAcceso implements Serializable {

    private String nombreUsuario;
    private String claveUsuario;
    private String codigoPIN;
    private Boolean validacionDosPasos;

    private final String ipIngreso;
    private final DAOAeronave daoAeronave;
    /*
    private Entidad entidadSeleccionada;
    private List<AsignarEntidad> listaEntidadesUsuario;
    private List<AsignarPerfil> misPerfiles;
    private Perfil perfilSeleccionado;

    private final DAOUsuarioAcceso daoUsuarioAcceso;

    private ConfigClave configClave;*/

    private static final Logger LOG = Logger.getLogger(BeanLoginAdmin.class.getName());

    public BeanLoginAdmin() {
        super(FacesContext.getCurrentInstance().getExternalContext());
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        ipIngreso = SOUtiles.obtenerIP();
        daoAeronave=new DAOAeronave();
        Aeronave aeronave=daoAeronave.obtenerAeronavePorId(1);
        /*
        daoUsuarioAcceso = new DAOUsuarioAcceso();

        configClave = new ConfigClave();*/

        LOG.log(Level.INFO, "[fecha:{0}] Se ha accedido al ambiente {1} con el navegador:{2} desde la ip: {3}", new Object[]{FechaUtiles.ahora(), url, SOUtiles.navegador(), ipIngreso});
    }

    private boolean validarConexiones() {
        boolean estado = true;
        Session session = HibernateFactory.abrirSession();
        if (session != null) {
            HibernateFactory.cerrar(session);
        } else {
            estado = false;
        }
        return estado;
    }

    public void validarIngreso() {
        if (nombreUsuario != null && claveUsuario != null && !nombreUsuario.equals("") && !claveUsuario.equals("")) {
            try {
                /*
                UsuarioAcceso usuario = daoUsuarioAcceso.obtenerUsuarioAccesoPorNombre(nombreUsuario);

                if (usuario != null) {
                    listaEntidadesUsuario = new DAOAsignacionEntidadUsuario().obtenerEntidadPorUsuario(usuario.getUsuario().getIdUsuario());
                    if (listaEntidadesUsuario != null && !listaEntidadesUsuario.isEmpty() && entidadSeleccionada == null) {
                        if (listaEntidadesUsuario.size() == 1) {
                            entidadSeleccionada = listaEntidadesUsuario.get(0).getEntidad();
                        }

                    }
                    obtenerInfoConfigClave();
                    UsuarioClave ultimaContrasenia = null;
                    List<UsuarioClave> contrasenias = new DAOContrasenia().obtenerUltimasContraseniasPorUsuarioAcceso(usuario.getIdUsuarioAcceso(), configClave.getHistorialClave());
                    if (contrasenias != null && contrasenias.size() > 0) {
                        ultimaContrasenia = contrasenias.get(0);
                    }
                    if (ultimaContrasenia != null) {
                        if (claveUsuario.length() < configClave.getClaveCharMin()) {
                            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, null, "La clave debe tener al menos " + configClave.getClaveCharMin() + " caracteres.");
                        } else if (claveUsuario.length() > configClave.getClaveCharMax()) {
                            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, null, "La clave NO debe tener más de " + configClave.getClaveCharMax() + " caracteres.");
                        } else {
                            if (AES256.verificarHash(claveUsuario, ultimaContrasenia.getUsrClaveContenido())) {

                                if (listaEntidadesUsuario.size() > 1 && entidadSeleccionada == null) {
                                    mostrarDialogSeleccionarEntidad();
                                } else {
                                    validarUsuario();
                                }
                            } else {
                                validarUsuario();
                            }
                        }
                    } else {
                        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "La clave ingresada no coincide con la del usuario. Si es la primera vez que ingresa al sistema, su clave se encuentra en su correo.");
                        LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intenta acceder al sistema por primera vez pero no coincide con la clave enviada al correo.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                    }
                } else {
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Usuario / clave incorrectos.");

                }*/
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        } else if (nombreUsuario == null && claveUsuario == null) {
            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Por favor ingrese su nombre de usuario y clave para continuar.");
        } else if (nombreUsuario.equals("") && claveUsuario.equals("")) {
            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Por favor ingrese su nombre de usuario y clave para continuar.");
        } else if (nombreUsuario.equals("")) {
            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Por favor ingrese su nombre de usuario para continuar.");
        } else if (claveUsuario.equals("")) {
            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Por favor ingrese su clave para continuar.");
        }
    }

    public void obtenerInfoConfigClave() {
        /*
        DAOConfigClave daoConfigClave = new DAOConfigClave();
        ConfigClave ultimaConfiguracion = new ConfigClave();
        List<ConfigClave> configuracion = daoConfigClave.obtenerPorEntidad(entidadSeleccionada.getRucEntidad());

        try {
            if (configuracion != null && configuracion.size() > 0) {
                ultimaConfiguracion = configuracion.get(0);
            } else {
                ultimaConfiguracion = obtenerConfigPorDefecto();
                crearConfigClave(ultimaConfiguracion);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        }

        configClave = ultimaConfiguracion;
        */
    }

    /*
    public void crearConfigClave(ConfigClave _nuevaConfig) {
        DAOConfigClave daoConfigClave = new DAOConfigClave();
        daoConfigClave.crear(_nuevaConfig);
    }

    public ConfigClave obtenerConfigPorDefecto() {
        ConfigClave configDefecto = new ConfigClave(entidadSeleccionada);
        return configDefecto;
    }
*/
    public void validarPerfil() throws Exception {
        PrimeUtiles.primeExecute("PF('wv-seleccionarPerfil').hide();");
        LOG.log(Level.INFO, "Perfil selecionado: {0}", "aqui va el nombre del perfil");
        validarUsuario();
    }

    public void validarEntidad() throws Exception {
        PrimeUtiles.primeExecute("PF('wv-seleccionarEntidad').hide();");
        validarUsuario();
    }

    public void mostrarDialogSeleccionarPerfil() {
        PrimeUtiles.primeExecute("PF('wv-seleccionarPerfil').show();");
    }

    public void mostrarDialogSeleccionarEntidad() {
        PrimeUtiles.primeExecute("PF('wv-seleccionarEntidad').show();");
    }

    public void validarUsuario() {

        try {
            /*
            UsuarioAcceso usuario = daoUsuarioAcceso.obtenerUsuarioAccesoPorNombre(nombreUsuario);
            String tipoLoggeo = "";
            String nombrePerfil = "";

            if (usuario.getUsuario().getUsrEstatus3().equals(EstadosConfig.ARCHIVADO.getCodigo())) {
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_WARN, "Su usuario se encuentra ARCHIVADO, ya no se podrá utilizar nuevamente.");
                LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intentó acceder al sistema pero tiene un estado de ARCHIVADO.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
            } else {
                LOG.log(Level.INFO, "Seguridad de Nivel III completa");
                if (usuario.getUsuario().getUsrEstatus2().equals(EstadosConfig.DESHABILITADO.getCodigo())) {
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Su usuario se encuentra DESHABILITADO, comuníquese con el Administrador del Sistema, para solicitar HABILITAR su usuario.");
                    LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intentó acceder al sistema pero tiene un estado de DESHABILITADO.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                } else {
                    LOG.log(Level.INFO, "Seguridad de Nivel II completa");
                    seguridadNivel1(tipoLoggeo, nombrePerfil, usuario);
                }
            }
            */
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

    }

    /*
    private void seguridadNivel1(String tipoLoggeo, String nombrePerfil, UsuarioAcceso usuario) throws Exception {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        if (usuario.getUsuario().getUsrEstatus().equals(EstadosConfig.BLOQUEADO.getCodigo())) {
            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Su usuario se encuentra bloqueado, comuníquese con el Administrador del Sistema, para solicitar DESBLOQUEAR su usuario.");
            LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, quiso acceder al sistema pero tiene un estado de bloqueado.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
        } else if (usuario.getUsuario().getUsrEstatus().equals(EstadosConfig.PRIMER_LOGUEO.getCodigo())) {

            
            UsuarioClave ultimaContrasenia;
            List<UsuarioClave> contrasenias = new DAOContrasenia().obtenerUltimasContraseniasPorUsuarioAcceso(usuario.getIdUsuarioAcceso(), configClave.getHistorialClave());
            if (contrasenias != null && contrasenias.size() > 0) {
                ultimaContrasenia = contrasenias.get(0);
                if (AES256.verificarHash(claveUsuario, ultimaContrasenia.getUsrClaveContenido())) {

                    String contrasena = Utilidades.generarcodigoenvioclave(configClave);
                    String contrasena2 = Utilidades.generarcodigoenvioclave(configClave);

                    String dominio = Utilidades.obtenerDominioUrl();
                    String minClave = String.valueOf(configClave.getClaveCharMin()) + "?";

                    String url = dominio + "verificacion?cli=" + minClave + contrasena + "1" + usuario.getIdUsuarioAcceso() + contrasena2;
                    FacesContext.getCurrentInstance().getExternalContext().redirect(url);

                    LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, se ha loggeado por primera vez en el sistema.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});

                } else {
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "La clave ingresada no coincide con la del usuario. Si es la primera vez que ingresa al sistema, su clave se encuentra en su correo registrado.");
                    LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intenta acceder al sistema por primera vez pero no coincide su clave.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                }
            } else {
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "No se ha encontrado la clave del usuario.");
                LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intenta acceder al sistema por primera pero no tiene una clave asignada.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
            }
            
        } else if (usuario.getUsuario().getUsrEstatus().equals(EstadosConfig.CLAVE_REENVIADA.getCodigo())) {

            UsuarioClave ultimaContrasenia;
            List<UsuarioClave> contrasenias = new DAOContrasenia().obtenerUltimasContraseniasPorUsuarioAcceso(usuario.getIdUsuarioAcceso(), configClave.getHistorialClave());
            if (contrasenias != null && contrasenias.size() > 0) {
                ultimaContrasenia = contrasenias.get(0);
                if (AES256.verificarHash(claveUsuario, ultimaContrasenia.getUsrClaveContenido())) {

                    String contrasena = Utilidades.generarcodigoenvioclave(configClave);
                    String contrasena2 = Utilidades.generarcodigoenvioclave(configClave);

                    String dominio = Utilidades.obtenerDominioUrl();
                    String minClave = String.valueOf(configClave.getClaveCharMin()) + "?";
                    String url = dominio + "verificacion?cli=" + minClave + contrasena + "1" + usuario.getIdUsuarioAcceso() + contrasena2;
                    FacesContext.getCurrentInstance().getExternalContext().redirect(url);

                    LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, se ha loggeado después de pedir cambio de clave.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});

                } else {
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "La clave ingresada no coincide con la clave del usuario. Revise su bandeja de entrada para validar sus credenciales.");
                    LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intenta acceder al sistema pero su clave no coincide con la enviada al correo.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                }
            } else {
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "No se ha encontrado la clave del usuario.");
                LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intenta acceder al sistema pero no tiene una clave asignada.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
            }
        } else if (usuario.getUsuario().getUsrEstatus().equals(EstadosConfig.ACTUALIZADO.getCodigo())) {
            UsuarioClave ultimaContrasenia = null;
            List<UsuarioClave> contrasenias = new DAOContrasenia().obtenerUltimasContraseniasPorUsuarioAcceso(usuario.getIdUsuarioAcceso(), configClave.getHistorialClave());
            //List<UsuarioClave> contrasenias = new DAOContrasenia().obtenerUltimasContraseniasPorUsuarioAcceso(usuario.getIdUsuarioAcceso(), EstadosConfig.HISTORIAL_CLAVES.getId());
            if (contrasenias != null && contrasenias.size() > 0) {
                ultimaContrasenia = contrasenias.get(0);
            }
            if (ultimaContrasenia != null) {
                if (AES256.verificarHash(claveUsuario, ultimaContrasenia.getUsrClaveContenido())) {
                    //if (Validaciones.obtenerDiasDesdeFechaIngresadaHastaFechaActual(ultimaContrasenia.getUsrClaveFecha()) >= EstadosConfig.CADUCIDAD_CLAVE.getId())
                    if (Validaciones.obtenerDiasDesdeFechaIngresadaHastaFechaActual(ultimaContrasenia.getUsrClaveFecha()) >= configClave.getClaveTiempoCaducar()) {
                        String contrasena = Utilidades.generarcodigoenvioclave(configClave);
                        String contrasena2 = Utilidades.generarcodigoenvioclave(configClave);

                        String dominio = Utilidades.obtenerDominioUrl();
                        String minClave = String.valueOf(configClave.getClaveCharMin()) + "?";
                        String usrOfuscado = minClave + contrasena + "3" + usuario.getIdUsuarioAcceso() + contrasena2;

                        tipoLoggeo = EstadosConfig.CLAVE_CADUCADA.getCodigo();
                        LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, ha intentado acceder al sistema pero su clave ha caducado.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});

                        String url = dominio + "verificacion?cli=" + usrOfuscado;
                        FacesContext.getCurrentInstance().getExternalContext().redirect(url);

                    } else {
                        DAOAsignacionPerfilUsuario daoAsignacion = new DAOAsignacionPerfilUsuario();
                        List<AsignarPerfil> asignaciones = daoAsignacion.obtenerAsignacionPerfilUsuariosPorUsuarioAcceso(usuario.getIdUsuarioAcceso());
                        if (asignaciones != null && asignaciones.size() > 0) {

                            if (asignaciones.size() == 1 && perfilSeleccionado == null) {
                                perfilSeleccionado = asignaciones.get(0).getPerfil();
                            }

                            if (asignaciones.size() > 1 && perfilSeleccionado == null) {
                                misPerfiles = asignaciones;
                                mostrarDialogSeleccionarPerfil();
                            } else {

                                Perfil perfil = perfilSeleccionado;

                                nombrePerfil = perfil.getPerfilNomre();
                                if (perfil.getPerfilEstatus2().equals(EstadosConfig.PERFIL_ADMINISTRADOR.getCodigo()) || perfil.getPerfilEstatus2().equals(EstadosConfig.PERFIL_MASTER_ADMINISTRADOR.getCodigo())) {

                                    if (perfil.getPerfilEstatus().equals(EstadosConfig.PERFIL_ACTIVO.getCodigo())) {
                                        Usuario u = usuario.getUsuario();
                                        if (validarConexiones()) {
                                            deshabilitarBtn();

                                            new ControlSesion().iniciarSesion(String.valueOf(perfil.getIdPerfil()), entidadSeleccionada.getRucEntidad(), String.valueOf(u.getIdUsuario()), u.getUsrNombre(), u.getUsrIdentificacion(), "1");
                                            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + PaginaConfig.PAGINA_INICIO.getUrl());
                                            tipoLoggeo = EstadosConfig.LOGIN_EXITOSO.getCodigo();
                                            LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, se ha loggeado con exito.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                                        } else {
                                            LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, ha puesto todos sus datos bien pero no puede entrar porque no hay conexion a las bases de datos.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                                            PrimeUtiles.mostrarMensajeDialog(FacesMessage.SEVERITY_ERROR, "No se puede utilizar el sistema en este momento, por favor contacte con el Administrador del Sistema.");
                                        }

                                    } else {
                                        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Su perfil está desactivado.");
                                        tipoLoggeo = EstadosConfig.LOGIN_DESACTIVADO.getCodigo();
                                        perfilSeleccionado = null;
                                        LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, ha intentado acceder pero su perfil esta deshabilitado.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                                    }

                                } else {
                                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Su perfil NO es ADMINISTRADOR.");
                                    tipoLoggeo = EstadosConfig.LOGIN_DESACTIVADO.getCodigo();
                                    perfilSeleccionado = null;
                                    LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, ha intentado acceder pero su perfil corresponde a un Cliente de Reportería.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                                }

                            }

                        } else {
                            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_WARN, "El usuario ingresado NO tiene un perfil asignado.");
                            tipoLoggeo = EstadosConfig.LOGIN_SIN_PERFIL.getCodigo();
                            LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, quiso acceder al sistema pero no tiene perfil asignado.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                        }
                    }

                } else {
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Usuario / clave incorrectos.");
                    tipoLoggeo = EstadosConfig.CLAVE_ERRONEA.getCodigo();
                    LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intenta acceder al sistema pero inserto una clave erronea.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
                }
            } else {
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "La clave ingresada no coincide con la del usuario. Si es la primera vez que ingresa al sistema, su clave es su número de identificación.");
                LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intenta acceder al sistema por primera vez pero no escribio su identificacion como clave.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
            }
            registrarAcceso(usuario, tipoLoggeo, ipIngreso, nombrePerfil);
        } else {
            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "La cuenta se encuentra obsoleta.");
            LOG.log(Level.INFO, "El usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, intenta acceder al sistema pero tiene su cuenta obsoleta (sin estado válido).", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ipIngreso});
        }
    }*/

    /*
    private void registrarAcceso(UsuarioAcceso usuario, String tipoLoggeo, String ip, String nombrePerfil) throws Exception {

        Bitacora registroActividadUsuario;
        List<Object> listaActividad = new ArrayList<>();

        Integer intFallidos = usuario.getUsrAccesoIntentosfallidos();

        if (tipoLoggeo.equals(EstadosConfig.LOGIN_EXITOSO.getCodigo()) && intFallidos >= 0) {

            if (daoUsuarioAcceso.actualizarNumeroIntentosFallidosUsuarioAcceso(usuario.getIdUsuarioAcceso(), 0)) {
                LOG.log(Level.INFO, "Se ha asignado a 0 los intentos fallidos para el usuario con identificacion: {0}, de la entidad: {1}", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad()});
                registroActividadUsuario = LogUtiles.registroActividad(usuario, OperacionConfig.OPERACION_LOGIN.getCodigo(), OperacionConfig.MODULO_INGRESO.getCodigo(), OperacionConfig.MODULO_INGRESO.getDescripcion() + " - " + EstadosConfig.LOGIN_EXITOSO.getDescripcion() + "  - " + OperacionConfig.OPERACION_LOGIN.getDescripcion(), tipoLoggeo, nombrePerfil);
                listaActividad.add(registroActividadUsuario);
            }
        } else if (tipoLoggeo.equals(EstadosConfig.CLAVE_ERRONEA.getCodigo())) {
            intFallidos++;

            if (daoUsuarioAcceso.actualizarNumeroIntentosFallidosUsuarioAcceso(usuario.getIdUsuarioAcceso(), intFallidos)) {
                LOG.log(Level.INFO, "Se ha asignado a {0} los intentos fallidos para el usuario con identificacion: {1}, de la entidad: {2}", new Object[]{intFallidos, usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad()});
                registroActividadUsuario = LogUtiles.registroActividad(usuario, OperacionConfig.OPERACION_LOGIN.getCodigo(), OperacionConfig.MODULO_INGRESO.getCodigo(), OperacionConfig.MODULO_INGRESO.getDescripcion() + " - " + EstadosConfig.CLAVE_ERRONEA.getDescripcion() + "  - " + OperacionConfig.OPERACION_LOGIN.getDescripcion(), tipoLoggeo, nombrePerfil);
                listaActividad.add(registroActividadUsuario);
            }
            //if (intFallidos >= EstadosConfig.INTENTOS_FALLIDOS.getId())
            if (intFallidos >= configClave.getIntentosFallidos()) {

                List<Object> listaObjetos = new ArrayList<>();

                try {

                    usuario.setUsrAccesoEstatus(EstadosConfig.USUARIO_BLOQUEADO.getCodigo());
                    listaObjetos.add(usuario);
                    Usuario usr = usuario.getUsuario();
                    usr.setUsrEstatus(EstadosConfig.USUARIO_BLOQUEADO.getCodigo());
                    listaObjetos.add(usr);

                    if (DAOSaveGenerico.guardarLotes(listaObjetos)) {
                        daoUsuarioAcceso.actualizarNumeroIntentosFallidosUsuarioAcceso(usuario.getIdUsuarioAcceso(), intFallidos);
                        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ha excedido el número maximo de intentos su Usuario ha sido BLOQUEADO. Por favor cominiquese con el Admnistrador del sistema.");
                        LOG.log(Level.INFO, "Se ha cambiado a estado bloqueado al usuario con identificacion: {0}, de la entidad: {1}, ip: {2}, por haber excedido el numero maximo permitido de intentos fallidos para ingreso de clave.", new Object[]{usuario.getUsuario().getUsrIdentificacion(), entidadSeleccionada.getRucEntidad(), ip});
                        registroActividadUsuario = LogUtiles.registroActividad(usuario, OperacionConfig.OPERACION_LOGIN.getCodigo(), OperacionConfig.MODULO_INGRESO.getCodigo(), OperacionConfig.MODULO_INGRESO.getDescripcion() + " - " + EstadosConfig.USUARIO_BLOQUEADO.getDescripcion() + " - " + intFallidos + " Intentos fallidos" + " - " + OperacionConfig.OPERACION_LOGIN.getDescripcion(), tipoLoggeo, nombrePerfil);
                        listaActividad.add(registroActividadUsuario);
                    } else {
                        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Por favor revise su conexión a internet e intente de nuevo.");
                        LOG.log(Level.WARNING, "Por favor revise su conexión a internet e intente de nuevo.");

                    }

                } catch (Exception ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!listaActividad.isEmpty()) {
            if (DAOSaveGenerico.guardarLotes(listaActividad)) {
                LOG.log(Level.INFO, "Registro de actividad agregado correctamente.");
            }
        } else {
            LOG.log(Level.WARNING, "No se ha podido registrar el acceso");
        }
    }*/

    public void deshabilitarBtn() {

        PrimeUtiles.primeExecute("PF('statusInicioSesion').disable();");
        PrimeUtiles.primeExecute("PF('wv-clave').disable();");
        PrimeUtiles.primeExecute("PF('wv-nombre').disable();");

    }

    public void reiniciarCampos() {
        this.nombreUsuario = null;
        this.claveUsuario = null;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getCodigoPIN() {
        return codigoPIN;
    }

    public void setCodigoPIN(String codigoPIN) {
        this.codigoPIN = codigoPIN;
    }

    public Boolean getValidacionDosPasos() {
        return validacionDosPasos;
    }

    public void setValidacionDosPasos(Boolean validacionDosPasos) {
        this.validacionDosPasos = validacionDosPasos;
    }

    /*
    public List<AsignarEntidad> getListaEntidadesUsuario() {
        return listaEntidadesUsuario;
    }

    public void setListaEntidadesUsuario(List<AsignarEntidad> listaEntidadesUsuario) {
        this.listaEntidadesUsuario = listaEntidadesUsuario;
    }

    public List<AsignarPerfil> getMisPerfiles() {
        return misPerfiles;
    }

    public void setMisPerfiles(List<AsignarPerfil> misPerfiles) {
        this.misPerfiles = misPerfiles;
    }

    public Perfil getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public Entidad getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    public void setEntidadSeleccionada(Entidad entidadSeleccionada) {
        this.entidadSeleccionada = entidadSeleccionada;
    }
*/
}
