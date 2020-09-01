package ec.edu.saltos.util;


import ec.edu.saltos.config.DocumentoConfig;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author guffenix
 */
public class TxtUtiles {

    private static final Logger LOG = Logger.getLogger(TxtUtiles.class.getName());

//    public void leerArchivo(String _nombreTxt) {
//        Path path = Paths.get(_nombreTxt);
//
//        try (Stream<String> stream = Files.lines(path)) {
//            stream.forEach(System.out::println);
//        } catch (IOException e) {
//            LOG.log(Level.SEVERE, null, e);
//
//        }
//    }
    public static String validarEncabezado(String _nombreTxt) throws IOException, ParseException {

        StringBuilder result = new StringBuilder();
        Path filePath = Paths.get(_nombreTxt);

        try (Stream<String> lines = Files.lines(filePath, Charset.forName("Cp1252"))) {
            result.append(lines.skip(DocumentoConfig.TARGET_LINE.getCodigo() - 1).findFirst().get());
        }
        return result.toString();
    }

    public static Boolean subirFichero(UploadedFile archivo, String ruta) throws IOException {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        Boolean estatus = false;

        try {
            inputStream = archivo.getInputStream();
            outputStream = new FileOutputStream(new File(ruta));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            estatus = true;

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

        return estatus;
    }

    public static boolean subirFichero(byte[] archivo, String ruta) {
        boolean success = false;
        BufferedOutputStream bs = null;
        try {
            FileOutputStream fs = new FileOutputStream(new File(ruta));
            bs = new BufferedOutputStream(fs);
            bs.write(archivo);
            success = true;
        } catch (IOException e) {
            LOG.log(Level.SEVERE, null, e);

        } finally {
            if (bs != null) {
                try {
                    bs.close();
                } catch (IOException e) {
                    LOG.log(Level.SEVERE, null, e);

                }
            }
        }
        return success;
    }
}
