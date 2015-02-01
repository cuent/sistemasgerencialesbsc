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
import ucuenca.edu.sg.facade.EstrategiaGlobalFacade;
import ucuenca.edu.sg.modelo.EstrategiaGlobal;

/**
 *
 * @author ESTUDIANTE 1-06
 */
@ManagedBean
public class EstrategiaGlobalConverter implements javax.faces.convert.Converter{
    @EJB
    private EstrategiaGlobalFacade ejbFacade;
    @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            return this.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EstrategiaGlobal) {
                EstrategiaGlobal o = (EstrategiaGlobal) object;
                return getStringKey(o.getIdEstrategiaGlobal());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EstrategiaGlobal.class.getName()});
                return null;
            }
        }
}
