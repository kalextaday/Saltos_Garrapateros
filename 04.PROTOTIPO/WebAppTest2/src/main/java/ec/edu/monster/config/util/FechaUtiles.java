package ec.edu.monster.config.util;

/*
import ec.fin.emagic.corevo.VoBalanceDatoPublico;
import ec.fin.emagic.corevo.VoCaptacionesB45;
import ec.fin.emagic.modelo.BalanceDato;
*/
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author guffenix
 */
public class FechaUtiles {

    private static final Logger LOG = Logger.getLogger(FechaUtiles.class.getName());

    public FechaUtiles() {
    }

    public static Date ahora() {
        return Calendar.getInstance().getTime();
    }

    public static Date ahoraSinHora() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Integer ahoraDia() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static Integer ahoraMes() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;

    }

    public static Integer ahoraAnho() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);

    }

    public static Date inicioDia(Date _fecha) {
        Calendar _nuevoAnho = Calendar.getInstance();
        _nuevoAnho.setTime(_fecha);
        _nuevoAnho.set(Calendar.HOUR_OF_DAY, 0);
        _nuevoAnho.set(Calendar.MINUTE, 0);
        _nuevoAnho.set(Calendar.SECOND, 0);
        _nuevoAnho.set(Calendar.MILLISECOND, 0);

        return _nuevoAnho.getTime();
    }

    public static Date finDia(Date _fecha) {
        Calendar _nuevoAnho = Calendar.getInstance();
        _nuevoAnho.setTime(_fecha);
        _nuevoAnho.set(Calendar.HOUR_OF_DAY, 23);
        _nuevoAnho.set(Calendar.MINUTE, 59);
        _nuevoAnho.set(Calendar.SECOND, 59);
        _nuevoAnho.set(Calendar.MILLISECOND, 999);

        return _nuevoAnho.getTime();
    }

    public static String fechaFormato(Date fecha, Integer formato) {
        if (null == fecha) {
            return null;
        }
        switch (formato) {
            case 0:
                return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
            case 1:
                return new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(fecha);
            case 2:
                return new SimpleDateFormat("dd-MM-yyyy").format(fecha);
            case 3:
                return new SimpleDateFormat("ddMMyyyy").format(fecha);
            case 4:
                return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
            case 5:
                return new SimpleDateFormat("MM").format(fecha);
            case 6:
                return new SimpleDateFormat("MM/yyyy").format(fecha);
            case 7:
                return new SimpleDateFormat("yyyy/MM/dd").format(fecha);
            case 8:
                return new SimpleDateFormat("HH:mm:ss").format(fecha);
            default:
                return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
        }
    }

    public static String fechaFormatoSQL(Date fecha, Integer formato) {
        if (null == fecha) {
            return null;
        }
        switch (formato) {
            case 0:
                return "'" + new SimpleDateFormat("dd/MM/yyyy").format(fecha) + "'";
            case 1:
                return "'" + new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(fecha) + "'";
            case 2:
                return "'" + new SimpleDateFormat("dd-MM-yyyy").format(fecha) + "'";
            case 3:
                return "'" + new SimpleDateFormat("ddMMyyyy").format(fecha) + "'";
            case 4:
                return "'" + new SimpleDateFormat("yyyy-MM-dd").format(fecha) + "'";// 00:00:00'";
            case 5:
                return "'" + new SimpleDateFormat("MM").format(fecha) + "'";
            case 6:
                return "'" + new SimpleDateFormat("MM/yyyy").format(fecha) + "'";
            case 7:
                return "'" + new SimpleDateFormat("yyyy/MM/dd").format(fecha) + "'";
            default:
                return "'" + new SimpleDateFormat("dd/MM/yyyy").format(fecha) + "'";
        }
    }

    public static Date stringToFecha(String _fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");

        Date fecha = null;
        try {

            fecha = formatoDelTexto.parse(_fecha);

        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, null, ex);

        }
        return fecha;
    }

    public static Date aFecha(String cadena, String mascara) throws ParseException {
        if (cadena == null || cadena.isEmpty() || cadena.length() < 6) {
            return null;
        }
        return DateUtils.parseDate(cadena, new String[]{mascara});
//            String m = mascara == null ? "nulo" : mascara;
    }

    public static Date aFecha(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date localeDateyyyyMMdd(String _fecha, String _mascara) {
        Date result = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(_mascara);
            result = formatter.parse(_fecha);
        } catch (ParseException ex) {
//            System.out.println("No es posible convertir " + _fecha + " a formato de fecha por favor actualizar el campo e intentar nuevamente.");
            LOG.log(Level.SEVERE, "No es posible convertir " + _fecha + " a formato de fecha por favor actualizar el campo e intentar nuevamente.", ex);
        }
        return result;
    }

//    ultimo dia mes
    public static Date ultimoDiaMes(Date _fecha) {
        Calendar _ultimoDia = Calendar.getInstance();
        _ultimoDia.setTime(_fecha);
        _ultimoDia.set(_ultimoDia.get(Calendar.YEAR), _ultimoDia.get(Calendar.MONTH), _ultimoDia.getActualMaximum(Calendar.DAY_OF_MONTH));
        return _ultimoDia.getTime();
    }

    public static Date restarAnhoCompleto(Date _fecha, Integer _nanhos) {
        Calendar _nuevoAnho = Calendar.getInstance();
        _nuevoAnho.setTime(_fecha);
        _nuevoAnho.set(Calendar.HOUR, 0);
        _nuevoAnho.set(Calendar.MINUTE, 0);
        _nuevoAnho.set(Calendar.SECOND, 0);
        _nuevoAnho.set(Calendar.MILLISECOND, 0);
        _nuevoAnho.add(Calendar.YEAR, _nanhos * -1);

        return _nuevoAnho.getTime();
    }

    public static Date restarAnhos(Date _fecha, Integer _nanhos) {
        Calendar _nuevoAnho = Calendar.getInstance();
        _nuevoAnho.setTime(_fecha);
        _nuevoAnho.set(Calendar.HOUR, 0);
        _nuevoAnho.set(Calendar.MINUTE, 0);
        _nuevoAnho.set(Calendar.SECOND, 0);
        _nuevoAnho.set(Calendar.MILLISECOND, 0);
        _nuevoAnho.add(Calendar.YEAR, _nanhos * -1);
        _nuevoAnho.set(Calendar.MONTH, 11);
        _nuevoAnho.set(_nuevoAnho.get(Calendar.YEAR), _nuevoAnho.get(Calendar.MONTH), _nuevoAnho.getActualMaximum(Calendar.DAY_OF_MONTH));

        return _nuevoAnho.getTime();
    }

    public static Date restarMeses(Date _fecha, Integer _nmes) {
        Calendar _nuevoMes = Calendar.getInstance();
        _nuevoMes.setTime(_fecha);
        _nuevoMes.set(Calendar.HOUR, 0);
        _nuevoMes.set(Calendar.MINUTE, 0);
        _nuevoMes.set(Calendar.SECOND, 0);
        _nuevoMes.set(Calendar.MILLISECOND, 0);
        _nuevoMes.add(Calendar.MONTH, _nmes * -1);
        return _nuevoMes.getTime();
    }

    public static Date restarDias(Date _fecha, Integer _ndia) {
        Calendar _nuevoDia = Calendar.getInstance();
        _nuevoDia.setTime(_fecha);
        _nuevoDia.set(Calendar.HOUR, 0);
        _nuevoDia.set(Calendar.MINUTE, 0);
        _nuevoDia.set(Calendar.SECOND, 0);
        _nuevoDia.set(Calendar.MILLISECOND, 0);
        _nuevoDia.add(Calendar.DAY_OF_MONTH, _ndia);
        return _nuevoDia.getTime();
    }

    public static Date encerarFecha(Date _fecha) {
        Calendar _fechaZero = Calendar.getInstance();
        _fechaZero.setTime(_fecha);
        _fechaZero.set(Calendar.HOUR, 0);
        _fechaZero.set(Calendar.MINUTE, 0);
        _fechaZero.set(Calendar.SECOND, 0);
        _fechaZero.set(Calendar.MILLISECOND, 0);
        return _fechaZero.getTime();
    }

    public static Date primerDiaMes(Date _fecha) {
        Calendar _fechaZero = Calendar.getInstance();
        _fechaZero.setTime(_fecha);
        _fechaZero.set(Calendar.DAY_OF_MONTH, 1);
        _fechaZero.set(Calendar.HOUR, 0);
        _fechaZero.set(Calendar.MINUTE, 0);
        _fechaZero.set(Calendar.SECOND, 0);
        _fechaZero.set(Calendar.MILLISECOND, 0);
        return _fechaZero.getTime();
    }

    public static List<Date> obtenerNDiasAtras(Date _fechaInicio, Integer _diasAtras) {
        Date _finicio = primerDiaMes(_fechaInicio);
        Date finicio = restarMeses(_finicio, 1);

        List<Date> _nfechasi = new ArrayList<>();
        List<Date> _nfechasf = new ArrayList<>();
        for (int i = 0; i < _diasAtras; i++) {
            _nfechasi.add(restarMeses(finicio, i));
        }
        /*
        _nfechasi.forEach(f -> {
            _nfechasf.add(ultimoDiaMes(f));
        });*/

        return _nfechasf;
    }

    public static List<Date> obtenerNDiasAtrasSinFinSemana(Date _fechaInicio, Integer _diasAtras) {
        List<Date> _nfechasf = new ArrayList<>();

        Calendar _fechaZero = Calendar.getInstance();
        _fechaZero.setTime(_fechaInicio);
        _fechaZero.set(Calendar.HOUR, 0);
        _fechaZero.set(Calendar.MINUTE, 0);
        _fechaZero.set(Calendar.SECOND, 0);

        for (int i = 0; i < _diasAtras; i++) {
            _nfechasf.add(_fechaZero.getTime());

            int dayOfWeek = _fechaZero.get(Calendar.DAY_OF_WEEK);

            switch (dayOfWeek) {
                case Calendar.MONDAY:
                    _fechaZero.add(Calendar.DATE, -3);
                    break;
                case Calendar.SUNDAY:
                    _fechaZero.add(Calendar.DATE, -2);
                    break;
                case Calendar.SATURDAY:
                    _fechaZero.add(Calendar.DATE, -1);
                    break;
                default:
                    _fechaZero.add(Calendar.DATE, -1);
                    break;
            }
        }

        return _nfechasf;

    }

    public static List<Date> obtenerNDiasFinSemana(Date _fechaInicio, Integer _diasAtras) {
        List<Date> _nfechasf = new ArrayList<>();

        Calendar _fechaUno = Calendar.getInstance();
        _fechaUno.setTime(_fechaInicio);
        _fechaUno.set(Calendar.HOUR, 0);
        _fechaUno.set(Calendar.MINUTE, 0);
        _fechaUno.set(Calendar.SECOND, 0);
        for (int i = 0; i < _diasAtras; i++) {

            int dayOfWeek = _fechaUno.get(Calendar.DAY_OF_WEEK);

            switch (dayOfWeek) {
                case Calendar.SUNDAY:
                    _nfechasf.add(_fechaUno.getTime());
                    _fechaUno.add(Calendar.DATE, 1);
                    break;
                case Calendar.SATURDAY:
                    _nfechasf.add(_fechaUno.getTime());
                    _fechaUno.add(Calendar.DATE, 1);
                    break;
                default:
                    _fechaUno.add(Calendar.DATE, 1);
                    break;
            }
        }
        Collections.reverse(_nfechasf);

        Calendar _fechaZero = Calendar.getInstance();
        _fechaZero.setTime(new Date());
        _fechaZero.set(Calendar.HOUR, 0);
        _fechaZero.set(Calendar.MINUTE, 0);
        _fechaZero.set(Calendar.SECOND, 0);

        for (int i = 0; i < _diasAtras; i++) {

            int dayOfWeek = _fechaZero.get(Calendar.DAY_OF_WEEK);

            switch (dayOfWeek) {
                case Calendar.SUNDAY:
                    _nfechasf.add(_fechaZero.getTime());
                    _fechaZero.add(Calendar.DATE, -1);
                    break;
                case Calendar.SATURDAY:
                    _nfechasf.add(_fechaZero.getTime());
                    _fechaZero.add(Calendar.DATE, -1);
                    break;
                default:
                    _fechaZero.add(Calendar.DATE, -1);
                    break;
            }
        }

        return _nfechasf;

    }

    public static List<Date> obtenerNMesesAtras(Date _fechaInicio, Integer _mesAtras) {
        Date _finicio = primerDiaMes(_fechaInicio);
        Date finicio = restarMeses(_finicio, 1);

        List<Date> _nfechasi = new ArrayList<>();
        List<Date> _nfechasf = new ArrayList<>();
        for (int i = 0; i < _mesAtras; i++) {
            _nfechasi.add(restarMeses(finicio, i));
        }
        /*
        _nfechasi.forEach(f -> {
            _nfechasf.add(ultimoDiaMes(f));
        });*/

        return _nfechasf;
    }

    public static List<Date> obtenerNAnhosAtras(Date _fechaInicio, Integer _anhoAtras) {
        Date _finicio = primerDiaMes(_fechaInicio);
        Date finicio = restarAnhos(_finicio, 1);

        List<Date> _nfechas = new ArrayList<>();
        for (int i = 0; i < _anhoAtras; i++) {
            _nfechas.add(restarAnhos(finicio, i));
        }

        return _nfechas;
    }

    public static List<Date> revertirList(List<Date> revertido) {
        Collections.reverse(revertido);
        return revertido;
    }

    public static Integer anioActual() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /*
    public static Date obtenerMax(List<BalanceDato> lista) {
        
        Date result = lista.stream()
                .map(bd -> bd.getBalance().getBalFecha())
                .max(Date::compareTo).get();
        return encerarFecha(result);
    }*/

    /*
    public static Date obtenerMin(List<BalanceDato> lista) {
        Date result = lista.stream()
                .map(bd -> bd.getBalance().getBalFecha())
                .min(Date::compareTo).get();
        return encerarFecha(result);
    }*/

    /*
    public static Date obtenerMinC(List<VoCaptacionesB45> lista) {
        Date result = lista.stream()
                .map(bd -> bd.getFecha())
                .min(Date::compareTo).get();
        return encerarFecha(result);
    }*/

    /*
    public static Date obtenerMinPublico(List<VoBalanceDatoPublico> lista) {
        Date result = lista.stream()
                .map(bd -> bd.getFecha())
                .min(Date::compareTo).get();
        return encerarFecha(result);
    }*/

    public static long obtenerTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public static long obtenerLongFecha(String _date) {
        long result = 0L;
        try {
            Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(_date);
            result = date.getTime();
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return result;

    }

    public static Date fechaYYToFechaYYYY(Date _date) {

        Date date = new Date();
        try {
            date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(fechaFormato(_date, 0));
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return date;

    }

    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            LOG.log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }

}
