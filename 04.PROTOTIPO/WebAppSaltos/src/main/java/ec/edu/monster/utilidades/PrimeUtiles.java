package ec.edu.monster.utilidades;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author guffenix
 */
public class PrimeUtiles {

    public static void primeUpdate(String _id) {
        if (_id != null && PrimeFaces.current().isAjaxRequest()) {
            PrimeFaces.current().ajax().update(_id);
        }
    }

    public static void primeExecute(String _id) {
        PrimeFaces primeface = PrimeFaces.current();
        primeface.executeScript(_id);
    }

    public static void mostrarMensaje(FacesMessage.Severity severityMessage, String _mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, null, _mensaje));
    }

    public static void mostrarMensaje(FacesMessage.Severity severityMessage, String _titulo, String _mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, _titulo, _mensaje));
    }

    public static void mostrarMensajeDialog(FacesMessage.Severity severityMessage, String mensaje) {
        FacesMessage message = new FacesMessage(severityMessage, "Mensaje", mensaje);
        PrimeFaces primeface = PrimeFaces.current();
        primeface.dialog().showMessageDynamic(message);
    }

    public static void mostrarMensajeEntrePaginas(FacesMessage.Severity severityMessage, String _titulo, String _mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, _titulo, _mensaje));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

}
