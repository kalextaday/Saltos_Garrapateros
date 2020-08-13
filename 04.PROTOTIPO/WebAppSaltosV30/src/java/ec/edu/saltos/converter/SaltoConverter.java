package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOCliente;
import ec.edu.saltos.dao.DAOSalto;
import ec.edu.saltos.modelo.Cliente;
import ec.edu.saltos.modelo.Salto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("saltoConverter")
public class SaltoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Salto salto = null;
        if (string != null && string.trim().length() > 0) {
            salto = new DAOSalto().obtenerPorId(Integer.parseInt(string));
        }
        return salto;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Salto) o).getIdSalto());
        } else {
            return null;
        }

    }

}
