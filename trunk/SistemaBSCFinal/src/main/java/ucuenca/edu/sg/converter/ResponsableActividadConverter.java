/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import ucuenca.edu.sg.controller.ResponsableActividadController;
import ucuenca.edu.sg.facade.ResponsableActividadFacade;
import ucuenca.edu.sg.modelo.ResponsableActividad;

/**
 *
 * @author ESTUDIANTE 1-06
 */

@ManagedBean
public class ResponsableActividadConverter implements javax.faces.convert.Converter{
    @EJB
    private ResponsableActividadFacade ejbFacade;
    private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ResponsableActividadController controller = (ResponsableActividadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "responsableActividadController");
            return this.ejbFacade.find(getKey(value));
        }

        ucuenca.edu.sg.modelo.ResponsableActividadPK getKey(String value) {
            ucuenca.edu.sg.modelo.ResponsableActividadPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ucuenca.edu.sg.modelo.ResponsableActividadPK();
            key.setIdResponsableActividad(Integer.parseInt(values[0]));
            key.setIdActividad(Integer.parseInt(values[1]));
            key.setIdUsuario(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(ucuenca.edu.sg.modelo.ResponsableActividadPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdResponsableActividad());
            sb.append(SEPARATOR);
            sb.append(value.getIdActividad());
            sb.append(SEPARATOR);
            sb.append(value.getIdUsuario());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ResponsableActividad) {
                ResponsableActividad o = (ResponsableActividad) object;
                return getStringKey(o.getResponsableActividadPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ResponsableActividad.class.getName()});
                return null;
            }
        }
}
