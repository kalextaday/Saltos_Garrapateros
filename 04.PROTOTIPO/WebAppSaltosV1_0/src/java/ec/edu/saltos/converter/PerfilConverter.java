package ec.edu.saltos.converter;

import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.persistencia.DAOPerfil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("perfilConverter")
public class PerfilConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Perfil oficina = null;
        if (string != null && string.trim().length() > 0) {
            oficina = new DAOPerfil().obtenerPorId(Integer.parseInt(string));
        }
        return oficina;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Perfil) o).getIdPerfil());
        } else {
            return null;
        }

    }

}
