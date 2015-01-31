package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Perspectiva;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

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
