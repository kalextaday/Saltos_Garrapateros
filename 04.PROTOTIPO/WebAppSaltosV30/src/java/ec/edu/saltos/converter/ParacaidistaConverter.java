package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAOParacaidista;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.Paracaidista;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("paracaidistaConverter")
public class ParacaidistaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Paracaidista paracaidista = null;
        if (string != null && string.trim().length() > 0) {
            paracaidista = new DAOParacaidista().obtenerPorId(Integer.parseInt(string));
        }
        return paracaidista;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Paracaidista) o).getIdParacaidista());
        } else {
            return null;
        }

    }

}
