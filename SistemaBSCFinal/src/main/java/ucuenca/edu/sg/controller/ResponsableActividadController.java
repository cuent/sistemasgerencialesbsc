package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.ResponsableActividad;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("responsableActividadController")
@SessionScoped
public class ResponsableActividadController extends AbstractController<ResponsableActividad> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ResponsableActividadFacade ejbFacade;

    public ResponsableActividadController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
