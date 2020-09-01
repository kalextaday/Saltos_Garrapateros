package ec.edu.saltos.converter;

import ec.edu.saltos.modelo.Salto;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOSalto;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
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
        Salto oficina = null;
        if (string != null && string.trim().length() > 0) {
            oficina = new DAOSalto().obtenerPorId(Integer.parseInt(string));
        }
        return oficina;
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
