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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
    private ObjetivoEstrategico sucesora;
    private ObjetivoEstrategico predecesora;
    private String PersSucesora;
    private String PersPredecesora;

    private List<ObjetivoEstrategico> listObjetivosEstrategicos;

    public GerarquiaController() {

    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        setListgerarquia(ejbFacade.findAll());
        setListObjetivosEstrategicos(ejbObjetivoEstrategicoFacade.findAll());

    }

    public void inicarNuevo() {
        System.out.println("comooooooo");
      
        this.setSelected(new Gerarquia());
        

    }

    public void guadarNuevo() {
        System.out.println("holaaaaa");
        if (getSucesora() != null || getPredecesora() != null) {
            this.getSelected().setSucesora(getSucesora());
            this.getSelected().setPredecesora(getPredecesora());
            this.create();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", "No se Pudo Guadar"));
        }
    }
    public void cargarPerspectivasucesora(){
        setPersSucesora(getSucesora().getIdPerspectiva().getPerspectiva());
    }
    public void cargarPerspectivaPredecesora(){
        setPersPredecesora(getPredecesora().getIdPerspectiva().getPerspectiva());
    }
    public void guadar(ActionEvent evet) {

        if (getSucesora() != null || getPredecesora() != null) {
            this.getSelected().setSucesora(getSucesora());
            this.getSelected().setPredecesora(getPredecesora());
            this.create();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", "No se Pudo Guadar"));
        }

    }

    public void generarMapa() {

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

    /**
     * @return the sucesora
     */
    public ObjetivoEstrategico getSucesora() {
        return sucesora;
    }

    /**
     * @param sucesora the sucesora to set
     */
    public void setSucesora(ObjetivoEstrategico sucesora) {
        this.sucesora = sucesora;
    }

    /**
     * @return the predecesora
     */
    public ObjetivoEstrategico getPredecesora() {
        return predecesora;
    }

    /**
     * @param predecesora the predecesora to set
     */
    public void setPredecesora(ObjetivoEstrategico predecesora) {
        this.predecesora = predecesora;
    }

    /**
     * @return the PersSucesora
     */
    public String getPersSucesora() {
        return PersSucesora;
    }

    /**
     * @param PersSucesora the PersSucesora to set
     */
    public void setPersSucesora(String PersSucesora) {
        this.PersSucesora = PersSucesora;
    }

    /**
     * @return the PersPredecesora
     */
    public String getPersPredecesora() {
        return PersPredecesora;
    }

    /**
     * @param PersPredecesora the PersPredecesora to set
     */
    public void setPersPredecesora(String PersPredecesora) {
        this.PersPredecesora = PersPredecesora;
    }

}
