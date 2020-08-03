package ec.edu.monster.utilidades;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author guffenix
 */
public class SOUtiles {

    public static String obtenerIP() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        String ip_address = request.getHeader("X-FORWARDED-FOR");
        if (ip_address == null) {
            ip_address = request.getRemoteAddr();
        }
        return ip_address;
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
            Logger.getLogger(SOUtiles.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public static boolean isWindows(String sistemaOperativo) {

        return (sistemaOperativo.indexOf("win") >= 0);

    }

    public static boolean isLinux(String sistemaOperativo) {

        return sistemaOperativo.indexOf("lin") >= 0;

    }

    public static String arquitectura() {
        return System.getProperty("os.arch").toLowerCase();

    }

    public static String version() {
        return System.getProperty("os.version").toLowerCase();

    }

    public static String miSistemaOperativo() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static String navegador() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");

        if (userAgent.contains("MSIE")) {
            return "Internet Explorer";
        }
        if (userAgent.contains("Firefox")) {
            return "Firefox";
        }
        if (userAgent.contains("Chrome")) {
            return "Chrome";
        }
        if (userAgent.contains("Opera")) {
            return "Opera";
        }
        if (userAgent.contains("Safari")) {
            return "Safari";
        }
        return "Unknown";
    }

//    public static void main(String[] args) {
//        System.out.println("so: " + SOUtiles.miSistemaOperativo());
//        System.out.println("so: " + SOUtiles.arquitectura());
//        System.out.println("so: " + SOUtiles.version());
//
//        System.out.println("Nav:" + SOUtiles.navegador());
//    }
}
