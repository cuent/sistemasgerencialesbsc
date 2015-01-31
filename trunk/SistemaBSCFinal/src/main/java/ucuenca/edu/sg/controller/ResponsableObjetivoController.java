package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.ResponsableObjetivo;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("responsableObjetivoController")
@SessionScoped
public class ResponsableObjetivoController extends AbstractController<ResponsableObjetivo> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ResponsableObjetivoFacade ejbFacade;

    public ResponsableObjetivoController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
