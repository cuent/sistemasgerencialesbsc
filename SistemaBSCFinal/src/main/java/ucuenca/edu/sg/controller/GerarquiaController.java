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
    private List<Gerarquia> listgerarquia;
    private Gerarquia gerarquia;
    
    private List<ObjetivoEstrategico> listObjetivosEstrategicos;
    public GerarquiaController() {
        
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListgerarquia(ejbFacade.findAll());
        setListObjetivosEstrategicos(ejbObjetivoEstrategicoFacade.findAll());
        
    }
    public void inicarNuevo(){
      this.setGerarquia(new Gerarquia());
    }
    
    public void guadar(){
        
      this.create();
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

    /**
     * @return the listgerarquia
     */
    public List<Gerarquia> getListgerarquia() {
        return listgerarquia;
    }

    /**
     * @param listgerarquia the listgerarquia to set
     */
    public void setListgerarquia(List<Gerarquia> listgerarquia) {
        this.listgerarquia = listgerarquia;
    }

    /**
     * @return the gerarquia
     */
    public Gerarquia getGerarquia() {
        return gerarquia;
    }

    /**
     * @param gerarquia the gerarquia to set
     */
    public void setGerarquia(Gerarquia gerarquia) {
        this.gerarquia = gerarquia;
    }
    
   
}
