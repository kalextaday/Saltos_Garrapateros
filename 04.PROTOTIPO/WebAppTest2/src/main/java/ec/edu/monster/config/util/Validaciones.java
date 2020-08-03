package ec.edu.monster.config.util;

import ec.edu.monster.config.EstadosConfig;
//import ec.edu.monster.modelo.ConfigClave;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author david_crespin
 */
public class Validaciones {

    /**
     * Funcion que me permite verificar si el dato enviado es un mail
     *
     * @param correo
     * @return true si el dato es mail y false si no lo es
     */
    public static boolean isEmail(String correo) {
        Pattern patVerifMail = null;
        Matcher matVerifMail = null;
        patVerifMail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
        matVerifMail = patVerifMail.matcher(correo);
        return matVerifMail.find();
    }

    /**
     * Método que me verifica si el dato ingresado es un numero
     *
     * @param dato
     * @return true si es numero false si no lo es
     */
    public static boolean isNum(String dato) {
        boolean digitoDato = false;
        boolean letraDato = false;
        for (int i = 0; i < dato.length(); i++) {
            if (Character.isDigit(dato.charAt(i))) {
                digitoDato = true;
            } else {
                letraDato = true;
            }

        }
        return digitoDato && !letraDato;
    }

    public static boolean validarNumero(String dato) {
        boolean validado = false;
        try {
            Integer.parseInt(dato);
            validado = true;
        } catch (NumberFormatException e) {
            try {
                Double.parseDouble(dato);
                validado = true;
            } catch (NumberFormatException ex) {

            }
        }
        return validado;
    }

    /**
     * Método para determinar si es solo texto
     *
     * @param dato
     * @return true si es solo texto y false si no lo es
     */
    public static boolean isTexto(String dato) {
        boolean digitoDato = false;
        boolean letraDato = false;
        for (int i = 0; i < dato.length(); i++) {
            if (Character.isDigit(dato.charAt(i))) {
                digitoDato = true;
            } else {
                letraDato = true;
            }

        }
        return !digitoDato && letraDato;
    }

    /**
     * Método para determinar si una cedula es válida
     *
     * @param cedula
     * @return true si es una cedula correcta y false si no lo es
     */
    public static boolean validarCedula(String cedula) {
        int nProv = 24;
        //verifica que tenga 10 dígitos y que contenga solo valores numéricos
        if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
            return false;
        }

        //verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= nProv))) {
            return false;
        }

        //verifica que el último dígito de la cédula sea válido
        int[] digCedula = new int[10];

        //Asignamos el string a un array
        for (int i = 0; i < digCedula.length; i++) {
            digCedula[i] = Integer.parseInt(cedula.charAt(i) + "");
        }

        int imp = 0;
        int par = 0;

        //sumamos los duplos de posición impar
        for (int i = 0; i < digCedula.length; i += 2) {
            digCedula[i] = ((digCedula[i] * 2) > 9) ? ((digCedula[i] * 2) - 9) : (digCedula[i] * 2);
            imp += digCedula[i];
        }

        //sumamos los digitos de posición par
        for (int i = 1; i < (digCedula.length - 1); i += 2) {
            par += digCedula[i];
        }

        //Sumamos los dos resultados
        int suma = imp + par;

        //Restamos de la decena superior
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1)
                + "0") - suma;

        //Si es diez el décimo dígito es cero
        d10 = (d10 == 10) ? 0 : d10;

        //si el décimo dígito calculado es igual al digitado la cédula es correcta
        return d10 == digCedula[9];
    }

    public static boolean validarRucPersonaNatural(String numero) {
        return validarInicial(numero, 13)
                && validarCodigoProvincia(numero.substring(0, 2))
                && validarTercerDigito(String.valueOf(numero.charAt(2)), 1)
                && validarCodigoEstablecimiento(numero.substring(10, 13), 1)
                && validarCedula(numero.substring(0, 10));
    }

    public static boolean validarRucSociedadPrivada(String numero) {
        return validarInicial(numero, 13) && validarCodigoProvincia(numero.substring(0, 2))
                && validarTercerDigito(String.valueOf(numero.charAt(2)), 2)
                && validarCodigoEstablecimiento(numero.substring(10, 13), 1) && ejecMod11(numero.substring(0, 9), Integer.parseInt(String.valueOf(numero.charAt(9))), 3);
    }

    public static boolean validarRucSociedadPublica(String numero) {
        return validarInicial(numero, 13) && validarCodigoProvincia(numero.substring(0, 2)) && validarTercerDigito(String.valueOf(numero.charAt(2)), 3) && validarCodigoEstablecimiento(numero.substring(9, 13), 2)
                && ejecMod11(numero.substring(0, 9), Integer.parseInt(String.valueOf(numero.charAt(8))), 4);
    }

    private static boolean validarInicial(String numero, int caracteres) {
        boolean resultado = true;
        if (StringUtils.isEmpty(numero)) {
            resultado = false;
        }

        if (!NumberUtils.isDigits(numero)) {
            resultado = false;
        }

        if (numero.length() != caracteres) {
            resultado = false;
        }

        return resultado;
    }

    private static boolean validarCodigoProvincia(String numero) {
        boolean resultado = true;
        if (Integer.parseInt(numero) < 0 || Integer.parseInt(numero) > 24) {
            resultado = false;
        }

        return resultado;
    }

    private static boolean validarTercerDigito(String numero, Integer tipo) {
        boolean resultado = true;
        switch (tipo) {
            case 1:
                /**
                 * El tercer dígito debe ser mayor o igual a 0 y menor a 6 para
                 * cédulas y RUC de persona natural, se permiten de 0 a 5
                 */
                if (Integer.parseInt(numero) < 0 || Integer.parseInt(numero) > 5) {
                    resultado = false;
                }
                break;
            case 2:
                /**
                 * El tercer dígito debe ser igual a 9 para extranjeros no
                 * residentes, Sociedades Privadas, Misiones Diplomáticas y
                 * Organismos Internacionales
                 */
                if (Integer.parseInt(numero) != 9) {
                    resultado = false;
                }
                break;
            /**
             * El tercer dígito debe ser igual a 6 para Entidades Públicas
             */
            case 3:
                if (Integer.parseInt(numero) != 6) {
                    resultado = false;
                }
                break;
        }
        return resultado;
    }

    private static boolean validarCodigoEstablecimiento(String numero, int tipo) {
        boolean resultado = true;
        if (tipo == 1) {
            if (!numero.equals("001")) {
                resultado = false;
            }
        } else if (tipo == 2) {
            if (!numero.equals("0001")) {
                resultado = false;
            }
        }
        return resultado;
    }

    private static boolean ejecMod11(String digitosIniciales, int digitoVerificador, Integer tipo) {
        Integer[] coefArray = null;
        boolean resultado = true;
        switch (tipo) {
            case 3:
                coefArray = new Integer[]{4, 3, 2, 7, 6, 5, 4, 3, 2};
                break;
            case 4:
                coefArray = new Integer[]{3, 2, 7, 6, 5, 4, 3, 2};
                break;
        }
        Integer[] tmpDigIniciales = new Integer[digitosIniciales.length()];
        int indice = 0;
        for (char valPos : digitosIniciales.toCharArray()) {
            tmpDigIniciales[indice] = NumberUtils.createInteger(String.valueOf(valPos));
            indice++;
        }
        int total = 0;
        int key = 0;
        for (Integer valPos : tmpDigIniciales) {
            if (key < coefArray.length) {
                valPos = (tmpDigIniciales[key] * coefArray[key]);
//                if (valorPosicion >= 10) {
//                    char[] valorPosicionSplit = String.valueOf(valorPosicion).toCharArray();
//                    valorPosicion = (Integer.parseInt(String.valueOf(valorPosicionSplit[0]))) + (Integer.parseInt(String.valueOf(valorPosicionSplit[1])));
//                }
                total = total + valPos;
            }

            key++;
        }
        int residuo = total % 11;
        int valor;

        if (residuo == 0) {
            valor = 0;
        } else {
            valor = (11 - residuo);
        }

        if (valor != digitoVerificador) {
            resultado = false;
        }

        return resultado;
    }

    public static String completarSecuencial(String _secuencial) {
        String secuencialCompleto = null;
        if (_secuencial.length() < 9) {
            if (_secuencial.length() == 8) {
                secuencialCompleto = "0" + _secuencial;
            }

            if (_secuencial.length() == 7) {
                secuencialCompleto = "00" + _secuencial;
            }

            if (_secuencial.length() == 6) {
                secuencialCompleto = "000" + _secuencial;
            }

            if (_secuencial.length() == 5) {
                secuencialCompleto = "0000" + _secuencial;
            }

            if (_secuencial.length() == 4) {
                secuencialCompleto = "00000" + _secuencial;
            }

            if (_secuencial.length() == 3) {
                secuencialCompleto = "000000" + _secuencial;
            }

            if (_secuencial.length() == 2) {
                secuencialCompleto = "0000000" + _secuencial;
            }

            if (_secuencial.length() == 1) {
                secuencialCompleto = "00000000" + _secuencial;
            }
        } else {
            secuencialCompleto = _secuencial;
        }

        return secuencialCompleto;
    }

    public static String comprTipoId(String cadena) {
        Pattern patternTipoId = Pattern.compile("[A-Za-z]");
        Matcher matcherTipoId;
        String tipoId = "";
        int cont = 0;

        matcherTipoId = patternTipoId.matcher(cadena);
        if (!matcherTipoId.find()) {
            cont = cadena.trim().length();
            if (cont == 13) {
                if (cadena.equals("9999999999999")) {
                    tipoId = "07";
                } else if (validarRucPersonaNatural(cadena) || validarRucSociedadPrivada(cadena) || validarRucSociedadPublica(cadena)) {
                    tipoId = "04";
                } else {
                    tipoId = "06";
                }
            } else if (validarCedula(cadena)) {
                tipoId = "05";
            } else {
                tipoId = "06";
            }

        } else {
            tipoId = "06";
        }
        return tipoId;
    }

    /**
     * Funcion que redondea los numeros decimales
     *
     * @param numero
     * @return numero con 2 decimales
     */
    public static Double redondear(Double numero) {
        return Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2);
    }

    public static String borrarEsp(String texto) {
        java.util.StringTokenizer tokStr = new java.util.StringTokenizer(texto);
        StringBuilder buffStrBld = new StringBuilder();
        while (tokStr.hasMoreTokens()) {
            buffStrBld.append(" ").append(tokStr.nextToken());
        }
        return buffStrBld.toString().trim();
    }

    public static boolean esDecimal(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String obtenerObligadoContabilidad(String valor) {
        String resultado = valor.equals("true") ? "SI" : valor.equals("false") ? "NO" : "";
        return resultado;
    }

    /*
    public static String validarClave(String clave, String nombreCliente, String identificacionCliente, ConfigClave configClave) {
        String mensaje = "No se ha podido validar la clave";
        if (clave != null && !clave.equals("")) {
            if (clave.length() >= configClave.getClaveCharMin() && clave.length() <= configClave.getClaveCharMax()) {
                if (clave.toLowerCase().contains(nombreCliente)) {
                    mensaje = "La clave no debe contener el nombre del cliente";
                } else {
                    boolean contieneNombre = false;
                    String[] cadena = identificacionCliente.split("\\ ");
                    for (String cad : cadena) {
                        if (!cad.contains("\\.") && clave.toLowerCase().contains(cad.toLowerCase())) {
                            contieneNombre = true;
                            break;
                        }
                    }
                    if (contieneNombre) {
                        mensaje = "La clave no debe contener su identificacion";
                    } else {
                        boolean estadoCharMayus = configClave.getClaveCharMayus();
                        boolean estadoCharMinus = configClave.getClaveCharMinus();
                        boolean estadoTieneNumeros = configClave.getClaveCharAlfanumerico();
                        boolean estadoTieneCaracterEspecial = configClave.getClaveCharEspecial();

                        String mayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
                        String minusculas = "abcdefghijklmnñopqrstuvwxyz";
                        String numeros = "0123456789";
                        String especiales = configClave.getClaveStrEspecial();

                        if (estadoCharMayus) {
                            if (!validarConfigClave(clave, mayusculas)) {
                                return "La clave debe tener por lo menos una letra mayúscula.";
                            } else {
                                mensaje = "OK";
                            }
                        }
                        if (estadoCharMinus) {
                            if (!validarConfigClave(clave, minusculas)) {
                                return "La clave debe tener por lo menos una letra minuscula.";
                            } else {
                                mensaje = "OK";
                            }
                        }
                        if (estadoTieneNumeros) {
                            if (!validarConfigClave(clave, numeros)) {
                                return "La clave debe tener por lo menos un numero.";
                            } else {
                                mensaje = "OK";
                            }
                        }
                        if (estadoTieneCaracterEspecial) {
                            if (!validarConfigClave(clave, especiales)) {
                                return "La clave debe tener por lo menos uno de estos caracteres especiales= " + configClave.getClaveStrEspecial();
                            } else {
                                mensaje = "OK";
                            }
                        } else {
                            return "OK";
                        }
                    }
                }
            } else {
                mensaje = "La clave no debe ser menor a " + configClave.getClaveCharMin() + " ni mayor a " + configClave.getClaveCharMax() + " caracteres.";
            }
        }
        return mensaje;
    }*/

    public static boolean validarConfigClave(String clave, String parametros) {
        boolean validacion = false;

        for (int i = 0; i < clave.length(); i++) {
            if (parametros.contains("" + clave.charAt(i))) {
                validacion = true;
            }
        }
        return validacion;
    }

    /*
    public static String validarUsuario(String identificacionCliente, ConfigClave configClave) {
        String mensaje;
        //if (identificacionCliente.length() >= EstadosConfig.CARACTERES_MIN_CLAVES.getId() && identificacionCliente.length() <= EstadosConfig.CARACTERES_MAX_CLAVES.getId()) {
        if (identificacionCliente.length() >= configClave.getClaveCharMin() && identificacionCliente.length() <= configClave.getClaveCharMax()) {
            mensaje = (identificacionCliente.length() >= configClave.getClaveCharMin() && identificacionCliente.length() <= configClave.getClaveCharMax()) ? "OK" : "El usuario solo puede tener catacteres alfanuméricos y/o números";
        } else {
            mensaje = "El usuario no debe ser menor a " + configClave.getClaveCharMin() + " ni mayor a " + configClave.getClaveCharMax() + " caracteres.";
        }

        return mensaje;
    }*/

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
