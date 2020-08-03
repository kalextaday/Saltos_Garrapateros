package ec.edu.monster.config;

/**
 *
 * @author guffenix
 */
public enum OperacionConfig {
//OPERACIONES PARA BITACORA
    MODULO_INGRESO("Módulo Login", "INGR", 0),
    MODULO_SEGURIDAD("Módulo Seguridad", "SGRD", 0),
    MODULO_ADMINISTRACION_OFICINA("Administración Oficinas", "ADOF", 0),
    MODULO_ADMINISTRACION_PERFIL("Administración Perfil", "ADPF", 0),
    MODULO_ADMINISTRACION_USUARIO("Administración Usuario", "ADUS", 0),
    MODULO_ADMINISTRACION_PERMISOS("Asignación de Permisos", "ASPA", 0),
    OPERACION_CAMBIO_CLAVE("Cambio Clave", "CHPW", 0),
    OPERACION_ASIGNACION_PERMISOS("Asignación Permisos", "ASPR", 0),
    OPERACION_CREACION_USUARIO("Creación Usuario", "CUSR", 0),
    OPERACION_ACTUALIZACION_USUARIO("Actualización Usuario", "UUSR", 0),
    OPERACION_ASIGNAR_PERFIL_USUARIO("Asignar Perfil a Usuario", "ASGU", 0),
    OPERACION_REENVIAR_CLAVE_USUARIO("Reenviar Clave Usuario", "RCUS", 0),
    OPERACION_ARCHIVAR_USUARIO("Archivar Usuario", "AUSR", 0),
    OPERACION_DESHABILITAR_USUARIO("Deshabilitar Usuario", "DUSR", 0),
    OPERACION_HABILITAR_USUARIO("Habilitar Usuario", "HUSR", 0),
    OPERACION_DESBLOQUEAR_USUARIO("Desbloquear Usuario", "DBUS", 0),
    OPERACION_BLOQUEAR_USUARIO("Bloquear Usuario", "BUSR", 0),
    OPERACION_CREACION_PERFIL("Creación Perfil", "CPFL", 0),
    OPERACION_ELIMINACION_PERFIL("Eliminación Perfil", "DPFL", 0),
    OPERACION_ACTUALIZACION_PERFIL("Actualización Perfil", "UPFL", 0),
    OPERACION_CREACION_OFICINA("Creación Oficina", "COFI", 0),
    OPERACION_ELIMINACION_OFICINA("Eliminación Oficina", "DOFI", 0),
    OPERACION_ACTUALIZACION_OFICINA("Actualización Oficina", "UOFI", 0),
    OPERACION_LOGIN("Inicio de sesión", "LGIN", 0);

    private final String descripcion;
    private final String codigo;
    private final Integer id;

    OperacionConfig(String _descripcion, String _codigo, Integer _id) {
        this.descripcion = _descripcion;
        this.codigo = _codigo;
        this.id = _id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getId() {
        return id;
    }

}
