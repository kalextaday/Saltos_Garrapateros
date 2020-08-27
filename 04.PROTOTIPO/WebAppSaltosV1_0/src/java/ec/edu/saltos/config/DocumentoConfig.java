package ec.edu.saltos.config;

/**
 *
 * @author guffenix
 */
public enum DocumentoConfig {

    SEPARADOR_TAB("\t"),
    EXTENSION_TXT("txt"),
    EXTENSION_XHTML("xhtml"),
    EXTENSION_XLSX("xlsx"),
    EXTENSION_PNG("png"),
    EXTENSION_JPG("jpg"),
    EXTENSION_JPEG("jpeg"),
    DOT("."),
    TARGET_LINE(1),
    NO_TARGET_LINE(2);

    private final String valor;
    private final Integer codigo;

    DocumentoConfig(String _valor) {
        this.valor = _valor;
        this.codigo = 0;
    }

    DocumentoConfig(Integer _codigo) {
        this.valor = "";
        this.codigo = _codigo;
    }

    public String getValor() {
        return valor;
    }

    public Integer getCodigo() {
        return codigo;
    }

}
