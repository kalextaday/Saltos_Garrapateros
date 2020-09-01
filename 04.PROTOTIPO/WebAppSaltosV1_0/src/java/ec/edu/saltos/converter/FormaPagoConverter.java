package ec.edu.saltos.converter;

import ec.edu.saltos.modelo.FormaPago;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOFormaPago;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("formaPagoConverter")
public class FormaPagoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        FormaPago oficina = null;
        if (string != null && string.trim().length() > 0) {
            oficina = new DAOFormaPago().obtenerPorId(Integer.parseInt(string));
        }
        return oficina;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((FormaPago) o).getIdFormaPago());
        } else {
            return null;
        }

    }

}
