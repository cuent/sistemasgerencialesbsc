package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Perspectiva;
import ucuenca.edu.sg.controller.util.JsfUtil;
import ucuenca.edu.sg.controller.util.JsfUtil.PersistAction;
import ucuenca.edu.sg.facade.PerspectivaFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("perspectivaController")
@SessionScoped
public class PerspectivaController extends AbstractController<Perspectiva> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.PerspectivaFacade ejbFacade;

    public PerspectivaController() {
    }
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
