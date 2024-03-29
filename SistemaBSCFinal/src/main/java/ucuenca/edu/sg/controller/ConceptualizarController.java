package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Conceptualizar;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("conceptualizarController")
@SessionScoped
public class ConceptualizarController extends AbstractController<Conceptualizar> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ConceptualizarFacade ejbFacade;

    public ConceptualizarController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
