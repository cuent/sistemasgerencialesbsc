package ucuenca.edu.sg.controller;

import java.io.IOException;
import ucuenca.edu.sg.modelo.Gerarquia;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import ucuenca.edu.sg.controller.util.GenerarMapa;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico_;

@Named("gerarquiaController")
@SessionScoped
public class GerarquiaController extends AbstractController<Gerarquia> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.GerarquiaFacade ejbFacade;
    @EJB
    private ucuenca.edu.sg.facade.ObjetivoEstrategicoFacade ejbObjetivoEstrategicoFacade;
    
    
    private List<ObjetivoEstrategico> listObjetivosEstrategicos;
    public GerarquiaController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListObjetivosEstrategicos(ejbObjetivoEstrategicoFacade.findAll());
        
    }
    
    public void generarMapa(){
       
        try {
            GenerarMapa generoMapa = new GenerarMapa();
            generoMapa.setListGerarquia(this.getItems());
            generoMapa.setListObjetivosEstrategicos(getListObjetivosEstrategicos());
            
            generoMapa.genearImagenMapa();
        } catch (IOException ex) {
            Logger.getLogger(GerarquiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
     
    }

    /**
     * @return the listObjetivosEstrategicos
     */
    public List<ObjetivoEstrategico> getListObjetivosEstrategicos() {
        return listObjetivosEstrategicos;
    }

    /**
     * @param listObjetivosEstrategicos the listObjetivosEstrategicos to set
     */
    public void setListObjetivosEstrategicos(List<ObjetivoEstrategico> listObjetivosEstrategicos) {
        this.listObjetivosEstrategicos = listObjetivosEstrategicos;
    }
    
   
}
