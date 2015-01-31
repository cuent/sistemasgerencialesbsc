package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Indicador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("indicadorController")
@SessionScoped
public class IndicadorController extends AbstractController<Indicador> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.IndicadorFacade ejbFacade;

    public IndicadorController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
