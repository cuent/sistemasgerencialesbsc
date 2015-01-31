package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.Usuario;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("usuarioController")
@SessionScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.UsuarioFacade ejbFacade;

    public UsuarioController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
