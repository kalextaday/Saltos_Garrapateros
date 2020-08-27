package ec.edu.saltos.config;

/**
 *
 * @author guffenix
 */
public enum IconoConfig {

    PLANTILLA("fa fa-file-text", "Plantilla"),
    CATEGORIA("fa fa-bars", "Categoria"),
    UPLOAD("fa fa-cloud-upload", "Carga"),
    DOWNLOAD("fa fa-cloud-download", "Descarga"),
    ENGRANE("fa fa-cog", "Engane"),
    CERTIFICADO("fa fa-certificate", "Certificado"),
    BANK("fa fa-bank", "Banco"),
    GRAPHBAR("fa fa-bar-chart", "Grafico barra"),
    CONFIG("fa fa-sliders", "Configuración"),
    USUARIO("fa fa-user-o", "Usuario"),
    LISTA("fa fa-list", "Listado"),
    LISTA_NUMERO("fa fa-list-ol", "Listado numérico"),
    ENLACE("fa fa-link", "Enlace"),
    ENLACE_ROTO("fa fa-chain-broken", "Enlace Roto"),
    VISTO("fa fa-check-circle", "Visto (Circulo)"),
    DATA("fa fa-database", "Base de datos"),
    ENTIDAD("fa fa-university", "Entidad"),
    GENERAR("fa fa-cogs", "Engranes"),
    LLAVE("fa fa-key", "Llaves"),
    AGENTE_SECRETO("fa fa-user-secret", "Admin"),
    ESTRUCTURAS("fa fa-list-alt", "Estructuras"),
    BUG("fa fa-bug", "BUG"),
    IMAGEN("fa fa-image","Imagen"),
    USERS("fa fa-users","Usuarios"),
    OBSERVAR("fa fa-eye","Observar"),
    LAPIZ("fa fa-pencil-square-o", "Edición");

    private final String clase;
    private final String nombre;

    IconoConfig(String _clase, String _nombre) {
        this.clase = _clase;
        this.nombre = _nombre;
    }

    public String getClase() {
        return clase;
    }

    public String getNombre() {
        return nombre;
    }

}
