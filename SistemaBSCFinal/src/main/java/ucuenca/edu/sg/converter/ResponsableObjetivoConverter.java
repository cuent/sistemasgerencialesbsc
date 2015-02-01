/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import ucuenca.edu.sg.facade.ResponsableObjetivoFacade;
import ucuenca.edu.sg.modelo.ResponsableObjetivo;

/**
 *
 * @author mivkys
 */
@ManagedBean
public class ResponsableObjetivoConverter implements javax.faces.convert.Converter {
    @EJB
    private ResponsableObjetivoFacade ejbFacade;
    private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            return this.ejbFacade.find(getKey(value));
        }

        ucuenca.edu.sg.modelo.ResponsableObjetivoPK getKey(String value) {
            ucuenca.edu.sg.modelo.ResponsableObjetivoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ucuenca.edu.sg.modelo.ResponsableObjetivoPK();
            key.setIdResponsable(Integer.parseInt(values[0]));
            key.setIdObjetivoEstrategico(Integer.parseInt(values[1]));
            key.setIdUsuario(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(ucuenca.edu.sg.modelo.ResponsableObjetivoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdResponsable());
            sb.append(SEPARATOR);
            sb.append(value.getIdObjetivoEstrategico());
            sb.append(SEPARATOR);
            sb.append(value.getIdUsuario());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ResponsableObjetivo) {
                ResponsableObjetivo o = (ResponsableObjetivo) object;
                return getStringKey(o.getResponsableObjetivoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ResponsableObjetivo.class.getName()});
                return null;
            }
        }
}
