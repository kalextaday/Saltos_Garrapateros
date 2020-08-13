package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAOServicioAdicional;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.ServicioAdicional;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("servicioConverter")
public class ServicioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        ServicioAdicional servicio = null;
        if (string != null && string.trim().length() > 0) {
            servicio = new DAOServicioAdicional().obtenerPorId(Integer.parseInt(string));
        }
        return servicio;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((ServicioAdicional) o).getIdServicio());
        } else {
            return null;
        }

    }

}
