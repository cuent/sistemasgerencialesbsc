package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.ResponsableObjetivo;
import ucuenca.edu.sg.controller.util.JsfUtil;
import ucuenca.edu.sg.controller.util.JsfUtil.PersistAction;
import ucuenca.edu.sg.facade.ResponsableObjetivoFacade;

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

@Named("responsableObjetivoController")
@SessionScoped
public class ResponsableObjetivoController implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ResponsableObjetivoFacade ejbFacade;
    private List<ResponsableObjetivo> items = null;
    private ResponsableObjetivo selected;

    public ResponsableObjetivoController() {
    }

    public ResponsableObjetivo getSelected() {
        return selected;
    }

    public void setSelected(ResponsableObjetivo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getResponsableObjetivoPK().setIdObjetivoEstrategico(selected.getObjetivoEstrategico().getIdObjetivoEstrategico());
        selected.getResponsableObjetivoPK().setIdUsuario(selected.getUsuario().getIdUsuario());
    }

    protected void initializeEmbeddableKey() {
        selected.setResponsableObjetivoPK(new ucuenca.edu.sg.modelo.ResponsableObjetivoPK());
    }

    private ResponsableObjetivoFacade getFacade() {
        return ejbFacade;
    }

    public ResponsableObjetivo prepareCreate() {
        selected = new ResponsableObjetivo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ResponsableObjetivoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ResponsableObjetivoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ResponsableObjetivoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ResponsableObjetivo> getItems() {
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

    public ResponsableObjetivo getResponsableObjetivo(ucuenca.edu.sg.modelo.ResponsableObjetivoPK id) {
        return getFacade().find(id);
    }

    public List<ResponsableObjetivo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ResponsableObjetivo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ResponsableObjetivo.class)
    public static class ResponsableObjetivoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ResponsableObjetivoController controller = (ResponsableObjetivoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "responsableObjetivoController");
            return controller.getResponsableObjetivo(getKey(value));
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

}
