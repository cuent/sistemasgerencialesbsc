package ucuenca.edu.sg.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ucuenca.edu.sg.modelo.Indicador;

@Named("indicadorController")
@SessionScoped
public class IndicadorController extends AbstractController<Indicador> implements Serializable{

    @EJB
    private ucuenca.edu.sg.facade.IndicadorFacade ejbFacade;
    
    private List<Indicador> listaIndicadorFiltrado;
    
    private List<String> estructuraFormula; 
    public IndicadorController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSelected(new Indicador());
        estructuraFormula =new ArrayList<>();
    }

    public List<Indicador> getListaIndicadorFiltrado() {
        return listaIndicadorFiltrado;
    }

    public void setListaIndicadorFiltrado(List<Indicador> listaIndicadorFiltrado) {
        this.listaIndicadorFiltrado = listaIndicadorFiltrado;
    }

    
    /**
     * @return the estructuraFormula
     */
    public List<String> getEstructuraFormula() {
        return estructuraFormula;
    }
    

    /**
     * @param estructuraFormula the estructuraFormula to set
     */
    public void setEstructuraFormula(List<String> estructuraFormula) {
        this.estructuraFormula = estructuraFormula;
    }
    
}
