package ucuenca.edu.sg.controller.util;

import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }
public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }
 public static String getComponentMessages(String clientComponent, String defaultMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc).findComponent(clientComponent);
        if (component instanceof UIInput) {
            UIInput inputComponent = (UIInput) component;
            if (inputComponent.isValid()) {
                return defaultMessage;
            } else {
                Iterator<FacesMessage> iter = fc.getMessages(inputComponent.getClientId());
                if (iter.hasNext()) {
                    return iter.next().getDetail();
                }
            }
        }
        return "";
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }
}
