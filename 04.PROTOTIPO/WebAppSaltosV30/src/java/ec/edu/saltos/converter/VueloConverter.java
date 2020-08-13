package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAOVuelo;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.Vuelo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("vueloConverter")
public class VueloConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Vuelo vuelo = null;
        if (string != null && string.trim().length() > 0) {
            vuelo = new DAOVuelo().obtenerPorId(Integer.parseInt(string));
        }
        return vuelo;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Vuelo) o).getIdVuelo());
        } else {
            return null;
        }

    }

}
