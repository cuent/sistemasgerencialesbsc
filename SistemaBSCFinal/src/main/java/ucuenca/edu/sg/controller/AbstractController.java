/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.controller;


import ucuenca.edu.sg.controller.util.JsfUtil;
import ucuenca.edu.sg.facade.AbstractFacade;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.event.ActionEvent;
/**
 *
 * @author ESTUDIANTE 1-06
 */
public abstract class AbstractController<T> {

    private AbstractFacade<T> ejbFacade;
    private Class<T> itemClass;
    private T selected;
    private List<T> items;

    private enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    protected AbstractFacade<T> getFacade() {
        return ejbFacade;
    }

    protected void setFacade(AbstractFacade<T> ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    protected void postCreate() {
        // Nothing to do if entity does not have any embeddable key.
    }

    protected void postUpdate() {
        // Nothing to do if entity does not have any embeddable key.
    }

    protected void postDestroy() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items in a List object
     *
     * @return
     */
    public List<T> getItems() {
        if (items == null) {
            items = this.ejbFacade.findAll();
        }
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void create() {
        persist(PersistAction.CREATE, "");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        this.postCreate();
    }

    public void update() {
        persist(PersistAction.UPDATE, "");
        this.postUpdate();
    }

    public void destroy() {
        persist(PersistAction.DELETE, "");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
        this.postDestroy();
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                String msg = "";
                if (persistAction != PersistAction.DELETE) {
                    this.ejbFacade.edit(selected);
                    JsfUtil.addSuccessMessage(successMessage);
                } else {
                    this.ejbFacade.remove(selected);
                    JsfUtil.addSuccessMessage(successMessage);
                }

            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
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

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event
     * @return
     */
    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            if(itemClass==null){
                System.out.println("La clase no esta instanciada");}
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    public void prepareEdit(ActionEvent event) {
        // Nothing to do if entity does not have any embeddable key.
    }
public T getItem(java.lang.Integer id) {
        return getFacade().find(id);
    }
    public List<T> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<T> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
}
