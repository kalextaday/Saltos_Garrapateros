/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.config.util;

//import ec.bigdata.comprobanteelectronico.esquema.comprobantebase.ComprobanteElectronico;
import ec.edu.monster.config.EstadosConfig;
//import ec.fin.emagic.modelo.ConfigClave;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author david_crespin
 */
public class Utilidades {

    public static SimpleDateFormat ddMMyyyyDate = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat yyyyMMddDate = new SimpleDateFormat("yyyy/MM/dd");
    public static SimpleDateFormat ddMMyyyyHHmmssDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static SimpleDateFormat ddMMyyyyHHmmssSSDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
    public static SimpleDateFormat HHmmssDate = new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat mysqlFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat yyyyMMddDateConGuion = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat yyyyMMddDateConGuionBajo = new SimpleDateFormat("yyyy_MM_dd");
    public static SimpleDateFormat fechaFormatoLargo = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
    public static SimpleDateFormat dddeMMdeyyyyDate = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
    public static SimpleDateFormat DiadddeMMdeyyyyDate = new SimpleDateFormat("EEEE', 'd 'de' MMMM 'de' yyyy 'Hora' HH:mm", new Locale("es", "ES"));
    public static SimpleDateFormat ddMMyyyySinBarrasDate = new SimpleDateFormat("ddMMyyyy");

    public static String obtenerFormatoFechayyyyMMddhhmmss() {
        try {
            SimpleDateFormat formSD = new SimpleDateFormat("yyyyMMddhhmmss");
            formSD.setLenient(false);
            Date fecha = new Date();
            String fechaStr = formSD.format(fecha);
            return fechaStr;
        } catch (Exception ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.WARNING, "No se pudo formatear la fecha", ex);
            return null;
        }
    }
    /*
    public static String generarcodigoenvioclave(ConfigClave ultimaConfig) {
        double aux;
        String clave = "";
        String claveAux = "";
        Integer veces;
        String numeros = "0123456789";
        String todo = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        
        for (int i = 0; i < (ultimaConfig.getClaveCharMin() / 2); i++) {
            aux = (Math.random()) * numeros.length();
            clave = clave + numeros.charAt((int) aux);
        }
       
        for (int i = clave.length(); i <= (ultimaConfig.getClaveCharMin() / 2); i++) {
            aux = (Math.random()) * ultimaConfig.getClaveStrEspecial().length();
            
            clave = clave + ultimaConfig.getClaveStrEspecial().charAt((int) aux);
            
        }
        
        for (int i = clave.length(); i <= ultimaConfig.getClaveCharMin(); i++) {
            aux = (Math.random()) * todo.length();
            clave = clave + todo.charAt((int) aux);
        }

        veces = (int) (Math.random() * 8);
        for (int j = 0; j <= veces; j++) {
            for (int i = 0; i < clave.length(); i += 2) {
                claveAux += clave.charAt(i);
            }
            for (int i = 1; i < clave.length(); i += 2) {
                claveAux += clave.charAt(i);
            }

            clave = claveAux;
            claveAux = "";
        }
        return clave;

    }*/

    public static HashMap<String, String> obtenerCabeceras(Map<String, List<String>> headers) {
        HashMap<String, String> cabeceras = new HashMap<>();
        if (headers == null) {
            //create a new map of HTTP headers if there isn't already one.
            headers = new HashMap<>();
        } else {

            List userList = (List) headers.get("Username");
            List passList = (List) headers.get("Password");
            List macList = (List) headers.get("MAC");
            List hostNameList = (List) headers.get("Hostname");
            List authenticationPDEList = (List) headers.get("Reg-Authorization");
            List authenticationUsuarioList = (List) headers.get("User-Authorization");
            List authenticationAdmList = (List) headers.get("Adm-Authorization");
            List estadoJWTList = (List) headers.get("Estado JWT");

            List tipoTokenList = (List) headers.get("Tipo Token");
            if (userList != null && passList != null && macList != null && hostNameList != null) {
                String userName = "";
                String password = "";
                String mac = "";
                String hostName = "";
                String estadoJWT = "";
                String tipoToken = "";
                //get userName
                userName = userList.get(0).toString();

                //get password
                password = passList.get(0).toString();

                //get mac
                mac = macList.get(0).toString();

                //get host
                hostName = hostNameList.get(0).toString();

                //get estado JWT
                estadoJWT = estadoJWTList.get(0).toString();

                //get tipo token
                tipoToken = tipoTokenList.get(0).toString();
                if (!mac.equals("")) {
                    cabeceras.put("MAC", mac);
                }
                if (!userName.equals("")) {
                    cabeceras.put("Username", userName);
                }
                if (!password.equals("")) {
                    cabeceras.put("Password", password);
                }
                if (!hostName.equals("")) {
                    cabeceras.put("Hostname", hostName);
                }

                if (authenticationPDEList != null && !authenticationPDEList.equals("")) {
                    cabeceras.put("Authorization", authenticationPDEList.get(0).toString());
                } else if (authenticationUsuarioList != null && !authenticationUsuarioList.equals("")) {
                    cabeceras.put("Authorization", authenticationUsuarioList.get(0).toString());
                } else if (authenticationAdmList != null && !authenticationAdmList.equals("")) {
                    cabeceras.put("Authorization", authenticationAdmList.get(0).toString());
                }

                if (!estadoJWT.equals("")) {
                    cabeceras.put("Estado JWT", estadoJWT);
                }
                if (!tipoToken.equals("")) {
                    cabeceras.put("Tipo Token", tipoToken);
                }

            }

        }
        return cabeceras;
    }

    public static Document loadXMLString(String response) {
        Document document = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response));

            document = db.parse(is);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.INFO, "No existe el correo.");
        }
        return document;
    }

    public static String obtenerDominioUrl() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String[] partes = url.split("\\/");
        String finals = "";
        String pagina = partes[partes.length - 1];
        finals = url.replaceAll(pagina, "");
        return finals;
    }

    public static String obtenerClaveLongitud(int len) {

        // A strong password has Cap_chars, Lower_chars, 
        // numeric value and symbols. So we are using all of 
        // them to generate our password 
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
//        String symbols = "!@#$%^&*_=+-/.?<>)";

        String values = Capital_chars + Small_chars + numbers;// + symbols;

        // Using random method 
        Random rndm_method = new Random();

        char[] password = new char[len];

        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            password[i] = values.charAt(rndm_method.nextInt(values.length()));

        }

        return String.valueOf(password);
    }

    public static String obtenerOTP() {
        //int randomPin declared to store the otp 
        //since we using Math.random() hence we have to type cast it int 
        //because Math.random() returns decimal value 
        int randomPin = (int) (Math.random() * 9000) + 1000;
        String otp = String.valueOf(randomPin);
        return otp; //returning value of otp 
    }

    public static Date parsearCadenaFecha(String _cadenaFecha, SimpleDateFormat _formato) {
        Date fecha = null;
        SimpleDateFormat simpleDateFormat = _formato;
        try {
            fecha = simpleDateFormat.parse(_cadenaFecha);
        } catch (ParseException pe) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.WARNING, "No se pudo formatear la fecha", pe);
        }
        return fecha;
    }

    /**
     *
     * @param _cadenaFecha
     * @param _formatoFecha
     * @return
     */
    public static String obtenerFecha(Date _cadenaFecha, SimpleDateFormat _formatoFecha) {
        try {
            _formatoFecha.setLenient(false);
            return _formatoFecha.format(_cadenaFecha);
        } catch (Exception ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.WARNING, "No se pudo formatear la fecha", ex);
            return null;
        }
    }

    public static LinkedHashMap<String, LinkedHashMap<String, String>> obtTipoCompr() {
        LinkedHashMap<String, LinkedHashMap<String, String>> datosTipoCompr = new LinkedHashMap<>();

        LinkedHashMap<String, String> datos1 = new LinkedHashMap<>();
        datos1.put("01", "Factura");
        datos1.put("03", "Liquidación de compra de Bienes o Prestación de servicios");
        datos1.put("04", "Nota de crédito");
        datos1.put("05", "Nota de débito");
        datos1.put("11", "Pasajes expedidos por empresas de aviación");
        datos1.put("12", "Documentos emitidos por instituciones financieras");
        datos1.put("21", "Carta de Porte Aéreo");
        datos1.put("41", "Comprobante de venta emitido por reembolso");
        datos1.put("43", "Liquidación para Explotación y Exploración de Hidrocarburos");
        datos1.put("47", "Nota de Crédito por Reembolso Emitida por Intermediario");
        datos1.put("48", "Nota de Débito por Reembolso Emitida por Intermediario");

        LinkedHashMap<String, String> datos2 = new LinkedHashMap<>();
        datos2.put("01", "Factura");
        datos2.put("02", "Nota o boleta de venta");
        datos2.put("03", "Liquidación de compra de Bienes o Prestación de servicios");
        datos2.put("04", "Nota de crédito");
        datos2.put("05", "Nota de débito");
        datos2.put("11", "Pasajes expedidos por empresas de aviación");
        datos2.put("12", "Documentos emitidos por instituciones financieras");
        datos2.put("15", "Comprobante de venta emitido en el Exterior");
        datos2.put("19", "Comprobantes de Pago de Cuotas o Aportes");
        datos2.put("20", "Documentos por Servicios Administrativos emitidos por Inst. del Estado");
        datos2.put("21", "Carta de Porte Aéreo");
        datos2.put("41", "Comprobante de venta emitido por reembolso");
        datos2.put("43", "Liquidación para Explotación y Exploración de Hidrocarburos");
        datos2.put("47", "Nota de Crédito por Reembolso Emitida por Intermediario");
        datos2.put("48", "Nota de Débito por Reembolso Emitida por Intermediario");

        LinkedHashMap<String, String> datos3 = new LinkedHashMap<>();
        datos3.put("01", "Factura");
        datos3.put("03", "Liquidación de compra de Bienes o Prestación de servicios");
        datos3.put("04", "Nota de crédito");
        datos3.put("05", "Nota de débito");
        datos3.put("41", "Comprobante de venta emitido por reembolso");
        datos3.put("47", "Nota de Crédito por Reembolso Emitida por Intermediario");
        datos3.put("48", "Nota de Débito por Reembolso Emitida por Intermediario");

        LinkedHashMap<String, String> datos4 = new LinkedHashMap<>();
        datos4.put("01", "Factura");
        datos4.put("02", "Nota o boleta de venta");
        datos4.put("03", "Liquidación de compra de Bienes o Prestación de servicios");
        datos4.put("04", "Nota de crédito");
        datos4.put("05", "Nota de débito");
        datos4.put("15", "Comprobante de venta emitido en el Exterior");
        datos4.put("41", "Comprobante de venta emitido por reembolso");
        datos4.put("47", "Nota de Crédito por Reembolso Emitida por Intermediario");
        datos4.put("48", "Nota de Débito por Reembolso Emitida por Intermediario");

        LinkedHashMap<String, String> datos5 = new LinkedHashMap<>();
        datos5.put("01", "Factura");
        datos5.put("02", "Nota o boleta de venta");
        datos5.put("03", "Liquidación de compra de Bienes o Prestación de servicios");
        datos5.put("04", "Nota de crédito");
        datos5.put("05", "Nota de débito");
        datos5.put("11", "Pasajes expedidos por empresas de aviación");
        datos5.put("15", "Comprobante de venta emitido en el Exterior");
        datos5.put("41", "Comprobante de venta emitido por reembolso");

        LinkedHashMap<String, String> datos6 = new LinkedHashMap<>();
        datos6.put("01", "Factura");
        datos6.put("03", "Liquidación de compra de Bienes o Prestación de servicios");
        datos6.put("04", "Nota de crédito");
        datos6.put("05", "Nota de débito");
        datos6.put("41", "Comprobante de venta emitido por reembolso");
        datos6.put("43", "Liquidación para Explotación y Exploración de Hidrocarburos");
        datos6.put("47", "Nota de Crédito por Reembolso Emitida por Intermediario");
        datos6.put("48", "Nota de Débito por Reembolso Emitida por Intermediario");

        LinkedHashMap<String, String> datos7 = new LinkedHashMap<>();
        datos7.put("01", "Factura");
        datos7.put("02", "Nota o boleta de venta");
        datos7.put("03", "Liquidación de compra de Bienes o Prestación de servicios");
        datos7.put("04", "Nota de crédito");
        datos7.put("05", "Nota de débito");
        datos7.put("15", "Comprobante de venta emitido en el Exterior");
        datos7.put("41", "Comprobante de venta emitido por reembolso");
        datos7.put("43", "Liquidación para Explotación y Exploración de Hidrocarburos");
        datos7.put("47", "Nota de Crédito por Reembolso Emitida por Intermediario");
        datos7.put("48", "Nota de Débito por Reembolso Emitida por Intermediario");

        LinkedHashMap<String, String> datos8 = new LinkedHashMap<>();
        datos8.put("01", "Factura");
        datos8.put("02", "Nota o boleta de venta");
        datos8.put("03", "Liquidación de compra de Bienes o Prestación de servicios");
        datos8.put("04", "Nota de crédito");
        datos8.put("05", "Nota de débito");
        datos8.put("21", "Carta de Porte Aéreo");

        LinkedHashMap<String, String> datos9 = new LinkedHashMap<>();
        datos9.put("04", "Nota de crédito");
        datos9.put("05", "Nota de débito");
        datos9.put("45", "Liquidación por reclamos de aseguradoras");

        LinkedHashMap<String, String> datos10 = new LinkedHashMap<>();
        datos10.put("19", "Comprobantes de Pago de Cuotas o Aportes");

        LinkedHashMap<String, String> datos0 = new LinkedHashMap<>();
        datos0.put("01", "Factura");
        datos0.put("02", "Nota o boleta de venta");
        datos0.put("04", "Nota de crédito");
        datos0.put("05", "Nota de débito");
        datos0.put("42", "Documento agente de retención Presuntiva");

        datosTipoCompr.put("01", datos1);
        datosTipoCompr.put("02", datos2);
        datosTipoCompr.put("03", datos3);
        datosTipoCompr.put("04", datos4);
        datosTipoCompr.put("05", datos5);
        datosTipoCompr.put("06", datos6);
        datosTipoCompr.put("07", datos7);
        datosTipoCompr.put("08", datos8);
        datosTipoCompr.put("09", datos9);
        datosTipoCompr.put("10", datos10);
        datosTipoCompr.put("00", datos0);
        return datosTipoCompr;
    }

    public static LinkedHashMap<String, String> obtSustCompr() {
        LinkedHashMap<String, String> sustentoCompr = new LinkedHashMap<>();
        sustentoCompr.put("01", "Crédito Tributario para declaración de IVA (servicios y bienes distintos de inventarios y activos fijos)");
        sustentoCompr.put("02", "Costo o Gasto para declaración de IR (servicios y bienes distintos de inventarios y activos fijos)");
        sustentoCompr.put("03", "Activo Fijo - Crédito Tributario para declaración de IVA");
        sustentoCompr.put("04", "Activo Fijo - Costo o Gasto para declaración de IR");
        sustentoCompr.put("05", "Liquidación Gastos de Viaje, hospedaje y alimentación Gastos IR (a nombre de empleados y no de la empresa)");
        sustentoCompr.put("06", "Inventario - Crédito Tributario para declaración de IVA");
        sustentoCompr.put("07", "Inventario - Costo o Gasto para declaración de IR");
        sustentoCompr.put("08", "Valor pagado para solicitar Reembolso de Gasto (intermediario)");
        sustentoCompr.put("09", "Reembolso por Siniestros");
        sustentoCompr.put("10", "Distribución de Dividendos, Beneficios o Utilidades");
        sustentoCompr.put("00", "Casos especiales cuyo sustento no aplica en las opciones anteriores");
        return sustentoCompr;
    }

    public static String BorrarCaract(String s_cadena, String s_caracteres) {
        String nuevaCad = "";
        Character caracter = null;
        boolean valido = true;

        /* Va recorriendo la cadena s_cadena y copia a la cadena que va a regresar,
         sólo los caracteres que no estén en la cadena s_caracteres */
        for (int i = 0; i < s_cadena.length(); i++) {
            valido = true;
            for (int j = 0; j < s_caracteres.length(); j++) {
                caracter = s_caracteres.charAt(j);

                if (s_cadena.charAt(i) == caracter) {
                    valido = false;
                    break;
                }
            }
            if (valido) {
                nuevaCad += s_cadena.charAt(i);
            }
        }

        return nuevaCad;
    }

    public static String borrarEsp(String texto) {
        java.util.StringTokenizer tokStr = new java.util.StringTokenizer(texto);
        StringBuilder buffStrBldr = new StringBuilder();
        while (tokStr.hasMoreTokens()) {
            buffStrBldr.append(" ").append(tokStr.nextToken());
        }
        return buffStrBldr.toString().trim();
    }

    public static String borrarSaltosLinea(String cadena) {
        // Para el reemplazo usamos un string vacío 
        return cadena.replaceAll("[\n]", "");
    }

    public static String obtenerFechaHoraLargo() {
        DateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
        Calendar calendario = Calendar.getInstance();
        return formatoFecha.format(calendario.getTime());
    }

    public static String obtenerFechaHoraLargo(Date dia) {
        DateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
        return formatoFecha.format(dia);
    }

//    public static Date obtenerFechaEmisionComprobante(ComprobanteElectronico _comprobante) {
//        Date fecha = null;
//        String fecha_emision = null;
//        String tipo_comprobante = _comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc();
//        if (tipo_comprobante.equals("01")) {
//            fecha_emision = _comprobante.ConstruirFactura().getInformacionFactura().getFechaEmision();
//        } else if (tipo_comprobante.equals("04")) {
//            fecha_emision = _comprobante.ConstruirNotaCredito().getInformacionNotaCredito().getFechaEmision();
//        } else if (tipo_comprobante.equals("05")) {
//            fecha_emision = _comprobante.ConstruirNotaDebito().getInformacionNotaDebito().getFechaEmision();
//        } else if (tipo_comprobante.equals("06")) {
//            fecha_emision = _comprobante.ConstruirGuiaRemision().getInformacionGuiaRemision().getFechaIniTransporte();
//        } else if (tipo_comprobante.equals("07")) {
//            fecha_emision = _comprobante.ConstruirComprobanteRetencion().getInformacionComprobanteRetencion().getFechaEmision();
//        } else {
//            return null;
//        }
//        fecha = Utilidades.parsearCadenaFecha(fecha_emision, ddMMyyyyDate);
//        return fecha;
//    }
//    public static String obtenerRazonSocialReceptor(ec.bigdata.comprobanteelectronico.esquema.comprobantebase.ComprobanteElectronico comprobante) {
//
//        String razon_social_cliente = "";
//
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("01")) {
//            razon_social_cliente = comprobante.ConstruirFactura().getInformacionFactura().getRazonSocialComprador();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("04")) {
//            razon_social_cliente = comprobante.ConstruirNotaCredito().getInformacionNotaCredito().getRazonSocialComprador();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("05")) {
//            razon_social_cliente = comprobante.ConstruirNotaDebito().getInformacionNotaDebito().getRazonSocialComprador();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("06")) {
//            razon_social_cliente = comprobante.ConstruirGuiaRemision().getInformacionGuiaRemision().getRazonSocialTransportista();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("07")) {
//            razon_social_cliente = comprobante.ConstruirComprobanteRetencion().getInformacionComprobanteRetencion().getRazonSocialSujetoRetenido();
//        }
//
//        return razon_social_cliente;
//    }
    /**
     * Método que obtiene el RUC-cédula del receptor usando el objeto
     * ComprobanteElectronico
     *
     * @param comprobante
     * @return
     */
//    public static String obtenerRucReceptor(ec.bigdata.comprobanteelectronico.esquema.comprobantebase.ComprobanteElectronico comprobante) {
//
//        String receptor = "";
//
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("01")) {
//
//            receptor = comprobante.ConstruirFactura().getInformacionFactura().getIdentificacionComprador();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("04")) {
//
//            receptor = comprobante.ConstruirNotaCredito().getInformacionNotaCredito().getIdentificacionComprador();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("05")) {
//            receptor = comprobante.ConstruirNotaDebito().getInformacionNotaDebito().getIdentificacionComprador();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("06")) {
//            receptor = comprobante.ConstruirGuiaRemision().getDestinatarios().get(0).getIdentificacionDestinatario();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("07")) {
//            receptor = comprobante.ConstruirComprobanteRetencion().getInformacionComprobanteRetencion().getIdentificacionSujetoRetenido();
//        }
//
//        if (receptor != null) {
//            return receptor;
//        } else {
//            return null;
//        }
//    }
//    public static String obtenerTipoDocumento(ec.bigdata.comprobanteelectronico.esquema.comprobantebase.ComprobanteElectronico comprobante) {
//
//        String tipo_documento = "";
//
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("01")) {
//            tipo_documento = "FACTURA";
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("04")) {
//            tipo_documento = "NOTA DE CRÉDITO";
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("05")) {
//            tipo_documento = "NOTA DE DÉBITO";
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("06")) {
//            tipo_documento = "GUÍA DE REMISIÓN";
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("07")) {
//            tipo_documento = "COMPROBANTE DE RETENCIÓN";
//        }
//
//        return tipo_documento;
//    }
//    public static String obtenerFechaEmisionDocumento(ec.bigdata.comprobanteelectronico.esquema.comprobantebase.ComprobanteElectronico comprobante) {
//
//        String fecha_emision = "";
//
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("01")) {
//            fecha_emision = comprobante.ConstruirFactura().getInformacionFactura().getFechaEmision();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("04")) {
//            fecha_emision = comprobante.ConstruirNotaCredito().getInformacionNotaCredito().getFechaEmision();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("05")) {
//            fecha_emision = comprobante.ConstruirNotaDebito().getInformacionNotaDebito().getFechaEmision();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("06")) {
//            fecha_emision = comprobante.ConstruirGuiaRemision().getInformacionGuiaRemision().getFechaIniTransporte();
//        }
//        if (comprobante.getInformacionTributariaComprobanteElectronico().getCodDoc().equals("07")) {
//            fecha_emision = comprobante.ConstruirComprobanteRetencion().getInformacionComprobanteRetencion().getFechaEmision();
//        }
//
//        return fecha_emision;
//    }
    public static String obtenerCodigoTarifaImpuesto(Integer _id) {
        switch (_id) {
            case 1:
                return "2";
            case 3:
                return "3";
            case 4:
                return "5";
            default:
                return null;
        }
    }

    public static int obtenerLongitudDespuesPunto(String numero) {
        int punto = 0;
        for (int i = 0; i < numero.length(); i++) {
            if (numero.charAt(i) == '.') {
                punto = i;
                break;
            }
        }
        String n = "";
        if (punto > 0) {
            n = numero.substring(punto + 1);
        }
        return n.length();

    }

    public static String formatearNumeroDosCerosDecimales(String _numero) {
        StringBuilder sb = new StringBuilder("");
        if (obtenerLongitudDespuesPunto(_numero) == 0) {
            sb.append(_numero).append(".00");
        } else if (obtenerLongitudDespuesPunto(_numero) == 1 && !_numero.equals("0")) {
            sb.append(_numero).append("0");;
        } else {
            sb.append(_numero);
        }
        return sb.toString();
    }

    public static String getMacAddress() {
        StringBuilder sb = new StringBuilder();
        try {

            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface netInter = NetworkInterface.getByInetAddress(localHost);
            byte[] macAddressBytes = netInter.getHardwareAddress();

            for (int i = 0; i <= 5; i++) {

                sb.append((Integer.toHexString(macAddressBytes[i] & 0xff)).toUpperCase());
                if (i < 5) {
                    sb.append("-");
                }

            }
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public static String obtenerMesEnLetras(int mes) {

        String mes_letras = "";
        switch (mes) {
            case 1: {
                mes_letras = "Enero";
                break;
            }
            case 2: {
                mes_letras = "Febrero";
                break;
            }
            case 3: {
                mes_letras = "Marzo";
                break;
            }
            case 4: {
                mes_letras = "Abril";
                break;
            }
            case 5: {
                mes_letras = "Mayo";
                break;
            }
            case 6: {
                mes_letras = "Junio";
                break;
            }
            case 7: {
                mes_letras = "Julio";
                break;
            }
            case 8: {
                mes_letras = "Agosto";
                break;
            }
            case 9: {
                mes_letras = "Septiembre";
                break;
            }
            case 10: {
                mes_letras = "Octubre";
                break;
            }
            case 11: {
                mes_letras = "Noviembre";
                break;
            }
            case 12: {
                mes_letras = "Diciembre";
                break;
            }
            default: {
                mes_letras = "Error";
                break;
            }
        }
        return mes_letras.toUpperCase();

    }

    public static String obtenerMesAnioLetras(Date dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dia);

        int mes = calendar.get(Calendar.MONTH) + 1;
        String anio = String.valueOf(calendar.get(Calendar.YEAR));
        String mes_letras = "";
        switch (mes) {
            case 1: {
                mes_letras = "Enero";
                break;
            }
            case 2: {
                mes_letras = "Febrero";
                break;
            }
            case 3: {
                mes_letras = "Marzo";
                break;
            }
            case 4: {
                mes_letras = "Abril";
                break;
            }
            case 5: {
                mes_letras = "Mayo";
                break;
            }
            case 6: {
                mes_letras = "Junio";
                break;
            }
            case 7: {
                mes_letras = "Julio";
                break;
            }
            case 8: {
                mes_letras = "Agosto";
                break;
            }
            case 9: {
                mes_letras = "Septiembre";
                break;
            }
            case 10: {
                mes_letras = "Octubre";
                break;
            }
            case 11: {
                mes_letras = "Noviembre";
                break;
            }
            case 12: {
                mes_letras = "Diciembre";
                break;
            }
            default: {
                mes_letras = "Error";
                break;
            }
        }
        return mes_letras.concat("_").concat(anio).toUpperCase();

    }

    public static LinkedHashMap<Integer, String> obtenerMeses() {
        LinkedHashMap<Integer, String> lista_meses = new LinkedHashMap<>();
        lista_meses.put(1, "Enero");
        lista_meses.put(2, "Febrero");
        lista_meses.put(3, "Marzo");
        lista_meses.put(4, "Abril");
        lista_meses.put(5, "Mayo");
        lista_meses.put(6, "Junio");
        lista_meses.put(7, "Julio");
        lista_meses.put(8, "Agosto");
        lista_meses.put(9, "Septiembre");
        lista_meses.put(10, "Octubre");
        lista_meses.put(11, "Noviembre");
        lista_meses.put(12, "Diciembre");
        return lista_meses;
    }

    /**
     *
     * @param _valor
     * @return
     */
    public static String formatearNumero(BigDecimal _valor) {

        DecimalFormat decimal_format;
        DecimalFormatSymbols decimal_format_symbols;
        decimal_format_symbols = new DecimalFormatSymbols();
        decimal_format_symbols.setDecimalSeparator('.');
        decimal_format_symbols.setGroupingSeparator(',');
        decimal_format = new DecimalFormat("#,###.######", decimal_format_symbols);
        return decimal_format.format(_valor);
    }

    /*
     Método que retorna la lista de todas las formas de pago.
     */
    public static LinkedHashMap<String, String> obtenerFormasDePago() {
        LinkedHashMap<String, String> formas_de_pago = new LinkedHashMap<>();
        formas_de_pago.put("01", "SIN UTILIZACION DEL SISTEMA FINANCIERO");
        formas_de_pago.put("02", "CHEQUE PROPIO");
        formas_de_pago.put("03", "CHEQUE CERTIFICADO");
        formas_de_pago.put("04", "CHEQUE DE GERENCIA");
        formas_de_pago.put("05", "CHEQUE DEL EXTERIOR");
        formas_de_pago.put("06", "DÉBITO DE CUENTA");
        formas_de_pago.put("07", "TRANSFERENCIA PROPIO BANCO");
        formas_de_pago.put("08", "TRANSFERENCIA OTRO BANCO NACIONAL");
        formas_de_pago.put("09", "TRANSFERENCIA BANCO EXTERIOR");
        formas_de_pago.put("10", "TARJETA DE CRÉDITO NACIONAL");
        formas_de_pago.put("11", "TARJETA DE CRÉDITO INTERNACIONAL");
        formas_de_pago.put("12", "GIRO");
        formas_de_pago.put("13", "DEPOSITO EN CUENTA (CORRIENTE/AHORROS)");
        formas_de_pago.put("14", "ENDOSO DE INVERSIÓN");
        formas_de_pago.put("15", "COMPENSACIÓN DE DEUDAS");
        formas_de_pago.put("16", "TARJETA DE DÉBITO");
        formas_de_pago.put("17", "DINERO ELECTRÓNICO");
        formas_de_pago.put("18", "TARJETA PREPAGO");
        formas_de_pago.put("19", "TARJETA DE CRÉDITO");
        formas_de_pago.put("20", "OTROS CON UTILIZACION DEL SISTEMA FINANCIERO");
        formas_de_pago.put("21", "ENDOSO DE TÍTULOS");
        return formas_de_pago;

    }

    public static String obtenerFechaFormatoServicioWebSERIVAR(Date d) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            sdf.setLenient(false);
            String feString = sdf.format(d);
            return feString;
        } catch (Exception ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.WARNING, "No se pudo formatear la fecha", ex);
            return null;
        }
    }

    public static Date parsearFechaFormatoServicioWebSERIVAR(String _cadenaFecha) {
        Date fecha = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            simpleDateFormat.setLenient(false);
            fecha = simpleDateFormat.parse(_cadenaFecha);
        } catch (ParseException pe) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.WARNING, "No se pudo formatear la fecha", pe);
        }
        return fecha;
    }

    public static String obtenerFechaFormatoAnioMesDia(Date _cadenaFecha) {
        try {
            String fechaStr = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setLenient(false);
            fechaStr = simpleDateFormat.format(_cadenaFecha);
            return fechaStr;
        } catch (Exception ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.WARNING, "No se pudo formatear la fecha", ex);
            return null;
        }
    }

    /*
     Método que retorna la lista de las formas de pago discpobiles para la facturación.
     */
    public static LinkedHashMap<String, String> obtenerFormasDePagoFacturacion() {
        LinkedHashMap<String, String> formas_de_pago = new LinkedHashMap<>();
        formas_de_pago.put("01", "Sin Utilización del Sistema Financiero");
        formas_de_pago.put("15", "Compensación de Deudas");
        formas_de_pago.put("16", "Tarjeta de Débito");
        formas_de_pago.put("17", "Dinero Electrónico");
        formas_de_pago.put("18", "Tarjeta Pre-Pago");
        formas_de_pago.put("19", "Tarjeta de Crédito");
        formas_de_pago.put("20", "Otros con Utilización del Sistema Financiero");
        formas_de_pago.put("21", "Endoso de Títulos");
        return formas_de_pago;

    }

    public static String obtenerHoraMinutosSegundos(Date _cadenaFecha) {
        try {
            HHmmssDate.setLenient(false);
            return HHmmssDate.format(_cadenaFecha).replace(":", "_");
        } catch (Exception ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.WARNING, "No se pudo formatear la fecha", ex);
            return null;
        }
    }

    /**
     * Ejecuta el comando ping a una URL validada.
     *
     * @param _url URL a la que se quiere hacer ping.
     * @return Integer [], posición 0 paquetes enviados, posición 1 paquetes
     * recibidos, posición 3 paquetes perdidos.
     */
    public static String[] ejecutarComandoPing(String _url) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedReader reader = null;
        String valoresRespuesta[] = {"0", "0", "-1"};
        try {

            String resultado = "";
            String resultados[] = new String[3];
            URL url = new URL(_url);
            String sistemaOperativo = detectarSistemaOperativo();

            CommandLine cmdLine = null;
            if (isWindows(sistemaOperativo)) {

                cmdLine = CommandLine.parse("ping " + url.getHost());

            } else if (isLinux(sistemaOperativo)) {

                cmdLine = new CommandLine("/bin/ping");

                cmdLine.addArgument("-c");
                cmdLine.addArgument("4");
                cmdLine.addArgument(url.getHost());

            }

            DefaultExecutor executor = new DefaultExecutor();
            ExecuteWatchdog watchdog = new ExecuteWatchdog(5000);
            executor.setWatchdog(watchdog);

            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            executor.setStreamHandler(streamHandler);
            int exitValue = executor.execute(cmdLine);
            Logger.getLogger(Utilidades.class.getName()).log(Level.INFO, exitValue == 0 ? "OK" : "KO");
//            System.out.println(exitValue == 0 ? "OK" : "KO");
            reader
                    = new BufferedReader(new StringReader(outputStream.toString()));
            while ((resultado = reader.readLine()) != null) {

                Logger.getLogger(Utilidades.class.getName()).log(Level.INFO, resultado);
//                System.out.println(resultado);

                if (resultado.toLowerCase().contains("paquetes") || resultado.toLowerCase().contains("packets")) {
                    resultados = resultado.split(",");
                    if (isWindows(sistemaOperativo)) {
                        for (int i = 0; i < 3; i++) {
                            String valor[] = resultados[i].split("=");
                            valoresRespuesta[i] = valor[1].trim();
                        }

                    } else if (isLinux(sistemaOperativo)) {
                        for (int i = 0; i < 3; i++) {

                            valoresRespuesta[i] = String.valueOf(resultados[i].trim().charAt(0));
                        }
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, "Error al ejecutar PING.", ex);

        } finally {
            try {
                outputStream.close();
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, "Error al cerrar variables.", ex);
            }
        }
        return valoresRespuesta;
    }

    public static String detectarSistemaOperativo() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static boolean isWindows(String sistemaOperativo) {

        return (sistemaOperativo.indexOf("win") >= 0);

    }

    public static boolean isLinux(String sistemaOperativo) {

        return sistemaOperativo.indexOf("lin") >= 0;

    }

    public static long obtenerDiasDesdeFechaIngresadaHastaFechaActual(Date fechaInicio) {
        long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
        java.util.Date hoy = new Date(); //Fecha de hoy
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaInicio);
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);
        int segundos = cal.get(Calendar.SECOND);//Fecha anterior
        Calendar calendar = new GregorianCalendar(anio, mes, dia, horas, minutos, segundos);
        java.sql.Date fecha = new java.sql.Date(calendar.getTimeInMillis());
        return (hoy.getTime() - fecha.getTime()) / MILLSECS_PER_DAY;
    }
}
