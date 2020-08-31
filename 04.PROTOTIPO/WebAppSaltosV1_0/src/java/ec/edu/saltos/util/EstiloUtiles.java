package ec.edu.saltos.util;

import ec.edu.saltos.config.EstadosConfig;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author guffenix
 */
@ManagedBean
@ViewScoped
public class EstiloUtiles {

    public String toStylePersona(String _tipo) {
        String result;
        switch (_tipo) {
            case "PEC":
                result = "text-green";
                break;
            case "PED":
                result = "text-azul-claro";
                break;
            case "PEA":
                result = "text-rojo";
                break;
            case "PEE":
                result = "text-naranja";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }
    
    public String toValuePersona(String _tipo) {
        String result;
        switch (_tipo) {
            case "PEC":
                result = EstadosConfig.PERSONA_EST_ACTIVADO.getDescripcion();
                break;
            case "PED":
                result = EstadosConfig.PERSONA_EST_DESACTIVADO.getDescripcion();
                break;
            case "PEA":
                result = EstadosConfig.PERSONA_EST_ARCHIVADO.getDescripcion();
                break;
            case "PEE":
                result = EstadosConfig.PERSONA_EST_ESPERA.getDescripcion();
                break;
            default:
                result = "DESCONOCIDO";
        }
        return result;
    }
    
    public String toValueFechaFormato(Date _fecha,String _formato) {
        String result;
        if(_fecha!=null){
            switch (_formato) {
                case "si":
                    result = FechaUtil.fechaSimple(_fecha);
                    break;
                case "cm":
                    result = FechaUtil.fechaCompuestoMes(_fecha);
                    break;
                case "cdm":
                    result = FechaUtil.fechaCompuestoDiaMes(_fecha);
                    break;
                default:
                    result = FechaUtil.fechaSinFormato(_fecha);
            }
        }else{
            result="-";
        }
        return result;
    }
    
    public String toValueHoraFormato(Date _hora,String _formato) {
        String result;
        switch (_formato) {
            case "simple":
                result = FechaUtil.horaSimple(_hora);
                break;
            case "am_pm":
                result = FechaUtil.horaAMPM(_hora);
                break;
            default:
                result = FechaUtil.fechaSinFormato(_hora);
        }
        return result;
    }
    
    public String toStyleOpcion(String _tipo) {
        String result;
        switch (_tipo) {
            case "ORG":
                result = "text-rojo";
                break;
            case "MOD":
                result = "text-azul-claro";
                break;
            case "APP":
                result = "text-green";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }
    
    public String toValueOpcion(String _tipo) {
        String result;
        switch (_tipo) {
            case "ORG":
                result = EstadosConfig.OPCION_ORIGEN.getDescripcion();
                break;
            case "MOD":
                result = EstadosConfig.OPCION_CATEGORIA.getDescripcion();
                break;
            case "APP":
                result = EstadosConfig.OPCION_PLANTILLA.getDescripcion();
                break;
            default:
                result = "DESCONOCIDO";
        }
        return result;
    }
    
    public String toStylePerfil(String _tipo) {
        String result;
        switch (_tipo) {
            case "PTA":
                result = "text-rojo";
                break;
            case "PTU":
                result = "text-azul-claro";
                break;
            case "PAC":
                result = "text-green";
                break;
            case "PDE":
                result = "text-azul-claro";
                break;
            case "PAR":
                result = "text-rojo";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }
    
    public String toValuePerfil(String _tipo) {
        String result;
        switch (_tipo) {
            case "PTA":
                result = "ADMINISTRADOR";
                break;
            case "PTU":
                result = "USUARIO";
                break;
            case "PAC":
                result = "Activado";
                break;
            case "PDE":
                result = "Desactivado";
                break;
            case "PAR":
                result = "Archivado";
                break;
            default:
                result = "DESCONOCIDO";
        }
        return result;
    }
    
    public String toStyleUsuario(String _tipo) {
        String result;
        switch (_tipo) {
            case "UAA":
                result = "text-green";
                break;
            case "UAD":
                result = "text-azul-claro";
                break;
            case "UAR":
                result = "text-rojo";
                break;
            case "UAE":
                result = "text-naranja";
                break;
            default:
                result = "text-plomo-raton";
        }
        return result;
    }
    
    public String toValueUsuario(String _tipo) {
        String result;
        switch (_tipo) {
            case "UAA":
                result = EstadosConfig.USR_ACC_ACTIVADO.getDescripcion();
                break;
            case "UAD":
                result = EstadosConfig.USR_ACC_DESACTIVADO.getDescripcion();
                break;
            case "UAR":
                result = EstadosConfig.USR_ACC_ARCHIVADO.getDescripcion();
                break;
            case "UAE":
                result = EstadosConfig.USR_ACC_ESPERA.getDescripcion();
                break;
            default:
                result = "DESCONOCIDO";
        }
        return result;
    }
    
    /////////////////////////////////////////////////
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
