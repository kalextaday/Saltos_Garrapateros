package ec.edu.saltos.converter;

import ec.edu.saltos.modelo.Salto;
import ec.edu.saltos.modelo.ServicioAdicional;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOSalto;
import ec.edu.saltos.persistencia.DAOServicioAdicional;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("servicioAdicionalConverter")
public class ServicioAdicionalConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        ServicioAdicional oficina = null;
        if (string != null && string.trim().length() > 0) {
            oficina = new DAOServicioAdicional().obtenerPorId(Integer.parseInt(string));
        }
        return oficina;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((ServicioAdicional) o).getIdServicioAdicional());
        } else {
            return null;
        }

    }

}
