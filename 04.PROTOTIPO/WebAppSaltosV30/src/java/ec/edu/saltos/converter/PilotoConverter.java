package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAOPiloto;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.Piloto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("pilotoConverter")
public class PilotoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Piloto piloto = null;
        if (string != null && string.trim().length() > 0) {
            piloto = new DAOPiloto().obtenerPorId(Integer.parseInt(string));
        }
        return piloto;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Piloto) o).getIdPiloto());
        } else {
            return null;
        }

    }

}
