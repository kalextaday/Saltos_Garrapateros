package ec.edu.saltos.converter;


import ec.edu.saltos.dao.DAOEmpleado;
import ec.edu.saltos.modelo.Empleado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("empleadoConverter")
public class EmpleadoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Empleado empleado = null;
        if (string != null && string.trim().length() > 0) {
            empleado = new DAOEmpleado().obtenerPorId(Integer.parseInt(string));
        }
        return empleado;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Empleado) o).getIdEmpleado());
        } else {
            return null;
        }

    }

}
