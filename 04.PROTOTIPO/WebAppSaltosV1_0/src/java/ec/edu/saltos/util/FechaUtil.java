/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.util;

import ec.edu.saltos.config.EstadosConfig;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author kalex
 */
public final class FechaUtil {
    
    public static Date ahoraSinFormato(){
        return new Date();
    }
    
    public static String fechaSinFormato(Date _fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return formatoFecha.format(_fecha);
    }
    
    public static String fechaSimple(Date _fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat(EstadosConfig.APP_FORMAT_FECHA_SIMPLE.getCodigo());
        return formatoFecha.format(_fecha);
    }
    
    public static String fechaCompuestoMes(Date _fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat(EstadosConfig.APP_FORMAT_FECHA_COMPUESTO_MES.getCodigo());
        return formatoFecha.format(_fecha);
    }
    
    public static String fechaCompuestoDiaMes(Date _fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat(EstadosConfig.APP_FORMAT_FECHA_COMPUESTO_DIA_MES.getCodigo());
        return formatoFecha.format(_fecha);
    }
    
    public static String horaSimple(Date _fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat(EstadosConfig.APP_FORMAT_HORA_SIMPLE.getCodigo());
        return formatoFecha.format(_fecha);
    }
    
    public static String horaAMPM(Date _fecha){
        SimpleDateFormat formatoFecha=new SimpleDateFormat(EstadosConfig.APP_FORMAT_HORA_AM_PM.getCodigo());
        return formatoFecha.format(_fecha);
    }
    
    
}
