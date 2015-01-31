package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Semaforo;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("semaforoController")
@SessionScoped
public class SemaforoController extends AbstractController<Semaforo> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.SemaforoFacade ejbFacade;

    public SemaforoController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
