package ec.edu.saltos.config;

/**
 *
 * @author guffenix
 */
public enum DirectorioConfig {

//    RUTA EN EL SISTEMA
    //RUTA_BASE("/home/guffenix/resources-risknadim/", 0), //ambiente local - dev1
    RUTA_BASE("D:\\Documentos/DOCS/LOCAL/8 SEMESTRE/APLICACIONES DISTRIBUIDAS/PARCIAL 3/PROYECTO/distri/distribuidas_2p_proyecto_saltos/04.PROTOTIPO/Recursos/", 0), //ambiente local - dev1
    //    RUTA_BASE("/home/kalex/sig/", 0), //ambiente local
    //    RUTA_BASE("/kalex/", 0), // linux cloud
    //    RUTA_BASE("c:\\kalex/", 0), // windows cloud
    //    DIRECTORIOS
    DIRECTORIO_REPORTE_GENERADO(RUTA_BASE.url + "reporte/", 0),
    DIRECTORIO_LOG(RUTA_BASE.url + "log/", 0),
    DIRECTORIO_CARGA_TEMPLATE(RUTA_BASE.url + "template/", 0),
    DIRECTORIO_CARGA_COMUN(RUTA_BASE.url + "upload/", 0),
    DIRECTORIO_FOTOS_PERFIL(RUTA_BASE.url + "fotos/", 0);

    private final String url;
    private final Integer valor;

    DirectorioConfig(String _url, Integer _valor) {
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
