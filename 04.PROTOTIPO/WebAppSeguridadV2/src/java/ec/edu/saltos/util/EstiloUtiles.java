package ec.edu.saltos.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author guffenix
 */
@ManagedBean
@ViewScoped
public class EstiloUtiles {

    public String toTipoAplicacion(String _tipo) {
        String result;
        switch (_tipo) {
            case "ORG":
                result = "Módulos";
                break;
            case "SPR":
                result = "Super Administración";
                break;
            case "ADM":
                result = "Administración";
                break;
            case "APP":
                result = "Cliente Reportería";
                break;
            default:
                result = "";
        }
        return result;
    }

    public String toTipo(String _tipo) {
        String result;
        switch (_tipo) {
            case "6":
                result = "Iggdrasil";
                break;
            case "1":
                result = "Plantilla";
                break;
            case "0":
                result = "Categoría";
                break;
            default:
                result = "";
        }
        return result;
    }

    public String toEstiloAplicacion(String _tipo) {
        String result;
        switch (_tipo) {
            case "ORG":
                result = "text-plomo-raton";
                break;
            case "SPR":
                result = "text-rojo";
                break;
            case "ADM":
                result = "text-azul";
                break;
            case "APP":
                result = "text-azul-claro";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }

    public String toEstilo(String _tipo) {
        String result;
        switch (_tipo) {
            case "6":
                result = "text-rojo";
                break;
            case "1":
                result = "text-green";
                break;
            case "0":
                result = "text-azul";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }

    public String toEstiloEstatus(String _tipo) {
        String result;
        switch (_tipo) {
            case "ARC":
                result = "text-naranja";
                break;
            case "BLO":
                result = "text-rojo";
                break;
            case "ACT":
                result = "text-green";
                break;
            case "PRI":
                result = "text-naranja";
                break;
            case "DHA":
                result = "text-rojo";
                break;
            case "HAB":
                result = "text-green";
                break;
            case "DAR":
                result = "text-azul-claro";
                break;
            case "CAM":
                result = "text-naranja";
                break;
            case "PAC":
                result = "text-green";
                break;
            case "DES":
                result = "text-rojo";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }

    public String toValorEstatus(String _tipo) {
        String result;
        switch (_tipo) {
            case "ARC":
                result = "ARCHIVADO";
                break;
            case "BLO":
                result = "BLOQUEADO";
                break;
            case "ACT":
                result = "ACTIVO";
                break;
            case "PRI":
                result = "NUNCA HA CAMBIADO";
                break;
            case "DHA":
                result = "DESHABILITADO";
                break;
            case "HAB":
                result = "HABILITADO";
                break;
            case "DAR":
                result = "ACTIVO";
                break;
            case "CAM":
                result = "REENVIO CLAVE";
                break;
            case "PAC":
                result = "HABILITADO";
                break;
            case "DES":
                result = "DESACTIVADO";
                break;
            default:
                result = "DESCONOCIDO";
        }
        return result;
    }

    public String toStylePerfil(String _tipo) {
        String result;
        switch (_tipo) {
            case "ORG":
                result = "text-plomo-raton";
                break;
            case "SPR":
                result = "text-rojo";
                break;
            case "ADM":
                result = "text-azul";
                break;
            case "REP":
                result = "text-azul-claro";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }

    public String toValuePerfil(String _tipo) {
        String result;
        switch (_tipo) {
            case "ORG":
                result = "EMAGIC";
                break;
            case "SPR":
                result = "SUPER ADMINISTRADOR";
                break;
            case "ADM":
                result = "ADMINISTRADOR";
                break;
            case "USR":
                result = "USUARIO";
                break;
            default:
                result = "DESCONOCIDO";
        }
        return result;
    }

    public String toStatusPerfil(String _tipo) {
        String result;
        switch (_tipo) {

            case "DES":
                result = "text-rojo";
                break;
            case "PAC":
                result = "text-azul";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }

    public String toTextStatusPerfil(String _tipo) {
        String result;
        switch (_tipo) {

            case "DES":
                result = "Desactivado";
                break;
            case "PAC":
                result = "Activo";
                break;
            default:
                result = "Sin Estado";
        }
        return result;
    }
}
