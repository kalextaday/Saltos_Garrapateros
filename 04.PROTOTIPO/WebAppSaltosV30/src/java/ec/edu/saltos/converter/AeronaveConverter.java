package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.modelo.Aeronave;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("aeronaveConverter")
public class AeronaveConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Aeronave aeronave = null;
        if (string != null && string.trim().length() > 0) {
            aeronave = new DAOAeronave().obtenerPorId(Integer.parseInt(string));
        }
        return aeronave;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Aeronave) o).getIdAeronave());
        } else {
            return null;
        }

    }

}
