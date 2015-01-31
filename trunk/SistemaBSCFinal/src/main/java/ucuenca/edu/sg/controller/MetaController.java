package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Meta;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("metaController")
@SessionScoped
public class MetaController extends AbstractController<Meta> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.MetaFacade ejbFacade;

    public MetaController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
