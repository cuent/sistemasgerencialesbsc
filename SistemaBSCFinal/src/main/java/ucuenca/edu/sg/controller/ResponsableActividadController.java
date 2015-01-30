package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.sistemabscfinal.ResponsableActividad;
import ucuenca.edu.sg.controller.util.JsfUtil;
import ucuenca.edu.sg.controller.util.JsfUtil.PersistAction;
import ucuenca.edu.sg.facade.ResponsableActividadFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("responsableActividadController")
@SessionScoped
public class ResponsableActividadController implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ResponsableActividadFacade ejbFacade;
    private List<ResponsableActividad> items = null;
    private ResponsableActividad selected;

    public ResponsableActividadController() {
    }

    public ResponsableActividad getSelected() {
        return selected;
    }

    public void setSelected(ResponsableActividad selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getResponsableActividadPK().setIdUsuario(selected.getUsuario().getIdUsuario());
    }

    protected void initializeEmbeddableKey() {
        selected.setResponsableActividadPK(new ucuenca.edu.sg.sistemabscfinal.ResponsableActividadPK());
    }

    private ResponsableActividadFacade getFacade() {
        return ejbFacade;
    }

    public ResponsableActividad prepareCreate() {
        selected = new ResponsableActividad();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ResponsableActividadCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ResponsableActividadUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ResponsableActividadDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ResponsableActividad> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ResponsableActividad getResponsableActividad(ucuenca.edu.sg.sistemabscfinal.ResponsableActividadPK id) {
        return getFacade().find(id);
    }

    public List<ResponsableActividad> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ResponsableActividad> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ResponsableActividad.class)
    public static class ResponsableActividadControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ResponsableActividadController controller = (ResponsableActividadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "responsableActividadController");
            return controller.getResponsableActividad(getKey(value));
        }

        ucuenca.edu.sg.sistemabscfinal.ResponsableActividadPK getKey(String value) {
            ucuenca.edu.sg.sistemabscfinal.ResponsableActividadPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ucuenca.edu.sg.sistemabscfinal.ResponsableActividadPK();
            key.setIdResponsableActividad(Integer.parseInt(values[0]));
            key.setIdActividad(Integer.parseInt(values[1]));
            key.setIdUsuario(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(ucuenca.edu.sg.sistemabscfinal.ResponsableActividadPK value) {
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

}
