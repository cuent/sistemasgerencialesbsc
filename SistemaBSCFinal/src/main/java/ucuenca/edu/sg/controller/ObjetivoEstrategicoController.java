package ucuenca.edu.sg.controller;

import java.awt.event.ActionEvent;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
        this.setSelected(new ObjetivoEstrategico());
    }

    public void prueba() {
        System.out.println("el selectes:;" + ((this.getSelected().getObjetivo() != null) ? this.getSelected().getObjetivo() : " "));
    }

    @Override
    public List<ObjetivoEstrategico> getItems() {
        return super.getItems(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ObjetivoEstrategico> autoComplete(String query) {
        List<ObjetivoEstrategico> allThemes = this.getItems();
        List<ObjetivoEstrategico> filteredThemes = new ArrayList<>();

        for (ObjetivoEstrategico skin : allThemes) {
            if (skin.getObjetivo().toLowerCase().startsWith(query)) {
                filteredThemes.add(skin);
            }
        }

        return filteredThemes;
    }
}
