package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAOFormacionParacaidista;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.FormacionParacaidista;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("forParConverter")
public class ForParConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        FormacionParacaidista formacionParacaidista = null;
        if (string != null && string.trim().length() > 0) {
            formacionParacaidista = new DAOFormacionParacaidista().obtenerPorId(Integer.parseInt(string));
        }
        return formacionParacaidista;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((FormacionParacaidista) o).getIdForpar());
        } else {
            return null;
        }

    }

}
