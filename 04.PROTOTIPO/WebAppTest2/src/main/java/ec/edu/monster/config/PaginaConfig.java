package ec.edu.monster.config;

/**
 *
 * @author guffenix
 */
public enum PaginaConfig {

    //    PAGINAS RESOLVER
    DIRECTORIO_ADM("/adm/", 0),
    DIRECTORIO_SUPERADM("/super/", 0),
    DIRECCION_CAMBIO_CLAVE("seguridad/cambio-clave.xhtml?cli=", 0),
    PAGINA_VISTA_CADUCADA("/publico/vista-caducada.xhtml", 0),
    PAGINA_ERROR_500("/publico/error-app.xhtml", 500),
    PAGINA_ERROR_404("/publico/404.xhtml", 404),
    PAGINA_ACCESO_DENEGADO("/publico/acceso-denegado.xhtml", 0),
    PAGINA_SOPORTE("/soporte/soporte.xhtml", 0),
    PAGINA_CONFIGURACION_CUENTA("/usr/config-cuenta.xhtml", 0),
    PAGINA_CONFIGURACION_CORREO("/adm/admin-correo.xhtml", 0),
    PAGINA_CONFIGURACION_CLAVE("/usr/config-clave.xhtml", 0),
    PAGINA_INFORMACION_USUARIO("/usr/info-usuario.xhtml", 0),
    PAGINA_TERMINOS_PRIVACIDAD("/usr/terminos-privacidad.xhtml", 0),
    PAGINA_REGISTRO_ACTIVIDAD("/usr/registro-actividad.xhtml", 0),
    PAGINA_RECUPERAR_CLAVE("/seguridad/recuperacion-clave.xhtml", 0),
    PAGINA_INICIO("/usr/inicio.xhtml", 0),
    PAGINA_LOGIN("/login.xhtml", 0);

    private final String url;
    private final Integer valor;

    PaginaConfig(String _url, Integer _valor) {
        this.url = _url;
        this.valor = _valor;
    }

    public String getUrl() {
        return url;
    }

    public Integer getValor() {
        return valor;
    }

}
