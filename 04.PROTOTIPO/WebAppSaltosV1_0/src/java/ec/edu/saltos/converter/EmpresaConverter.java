package ec.edu.saltos.converter;

import ec.edu.saltos.modelo.Empresa;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.persistencia.DAOEmpresa;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOPersona;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("empresaConverter")
public class EmpresaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Empresa oficina = null;
        if (string != null && string.trim().length() > 0) {
            oficina = new DAOEmpresa().obtenerPorId(Integer.parseInt(string));
        }
        return oficina;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Empresa) o).getIdEmpresa());
        } else {
            return null;
        }

    }

}
