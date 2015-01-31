package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.ObjetivoEstrategico;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("objetivoEstrategicoController")
@SessionScoped
public class ObjetivoEstrategicoController extends AbstractController<ObjetivoEstrategico> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ObjetivoEstrategicoFacade ejbFacade;

    public ObjetivoEstrategicoController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
