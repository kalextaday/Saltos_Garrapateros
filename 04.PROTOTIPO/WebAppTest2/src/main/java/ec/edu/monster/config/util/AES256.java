package ec.edu.monster.config.util;

import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Daniela
 */
public class AES256 {

    private static final String ALGORITHM = "AES";
    private static final String TIPOALGORITHM = "AES/CBC/PKCS5Padding";
//    private static final int ITERATIONS = 2;
    private static final String VENDOR = "BDDCA";
    private static final String INITVECTOR = "EmagiGREcuador19";

    /**
     * Método que genera un AES256 a partir de un String
     *
     * @param _sinDescifrar String que se desea cifrar
     * @return Cadena de texto cifrada
     * @throws Exception
     */
    public static String toAES256(String _sinDescifrar) throws Exception {
        Key key = generateKey();
        String valueToEnc = null;
        IvParameterSpec iv = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
        String salt = VENDOR;
        Cipher c = Cipher.getInstance(TIPOALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = c.doFinal(_sinDescifrar.getBytes());
        valueToEnc = Base64.encodeBase64String(encrypted);
        valueToEnc = valueToEnc.replaceAll("[\n\r]", "");
        return valueToEnc;
    }

    /**
     * Obtiene un mensaje original a partir del cifrado.
     *
     * @param value cadena que se desea decrifrar
     * @return cadena decifrada
     * @throws Exception
     */
    public static String toMessage(String value) throws Exception {
        Key key = generateKey();
        IvParameterSpec iv = new IvParameterSpec(INITVECTOR.getBytes("UTF-8"));
        String salt = VENDOR;
        String dValue = "";
        Cipher c = Cipher.getInstance(TIPOALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] original = c.doFinal(Base64.decodeBase64(value));
        dValue = new String(original);
        dValue = dValue.replaceAll("[\n\r]", "");
        return dValue;

    }

    /**
     * Generador de la clave con la que se encripta el mensaje
     *
     * @return
     * @throws Exception
     */
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec("kdx0S6GP0SIqZqUYGgexm1x1xIUobBBh".getBytes(), ALGORITHM);
        return key;
    }

    public static boolean verificarHash(String _cadena, String _hash) {
        boolean resultado = false;
        try {
            if (_hash.equals(toAES256(_cadena))) {
                resultado = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AES256.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

//    public static void main(String[] args) throws Exception {
////        System.out.println(toAES256("12345678"));
////        System.out.println(verificarHash("Admin1234.", "Zn8DDhBMZpKhTSFoDEFfAA=="));
////        System.out.println(toMessage("R5cQufocANw1jCdA5SD58A=="));
////        System.out.println(toMessage("lx0j4xeX4yXsWBHQhfqUXw=="));
//        System.out.println(toMessage("I6+ydefV5yREblMzr/nv0Q=="));
//        System.out.println(toMessage("SWKno3Of9uyM9Vf65SVOiw=="));
//
//    }
}
