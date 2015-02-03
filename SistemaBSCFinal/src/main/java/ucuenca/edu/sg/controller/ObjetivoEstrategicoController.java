package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.ObjetivoEstrategico;
import ucuenca.edu.sg.modelo.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import ucuenca.edu.sg.modelo.ComponenteFormula;
import ucuenca.edu.sg.modelo.Indicador;
import ucuenca.edu.sg.modelo.Meta;
import ucuenca.edu.sg.modelo.ResponsableObjetivo;
import ucuenca.edu.sg.modelo.ResponsableObjetivoPK;

@ManagedBean(name = "objetivoEstrategicoController")
@SessionScoped
public class ObjetivoEstrategicoController extends AbstractController<ObjetivoEstrategico> implements Serializable {
    
    @EJB
    private ucuenca.edu.sg.facade.ObjetivoEstrategicoFacade ejbFacade;
    @EJB
    private ucuenca.edu.sg.facade.UsuarioFacade ejbUsuarioFacade;
    @EJB
    private ucuenca.edu.sg.facade.ComponenteFormulaFacade ejbComponenteFormulaFacade;
    @EJB
    private ucuenca.edu.sg.facade.MetaFacade ejbMetaFacade;
    @EJB
    private ucuenca.edu.sg.facade.IndicadorFacade ejbIndicadorFacade;
    @EJB
    private ucuenca.edu.sg.facade.ResponsableObjetivoFacade ejbResponsableObjetivoFacade;
    private Usuario usuario;
    private List<Usuario> listTotalUsuario, listUsuariosObjetivos;
    private List<ComponenteFormula> listComponenteFormulas;
    private List<Meta> listMetas;
    private Meta meta, metaNueva;
    private Indicador indicador;
    private ComponenteFormula componenteFormula;
    
    public ObjetivoEstrategicoController() {
    }
    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSelected(new ObjetivoEstrategico());
        usuario = new Usuario();
        setListTotalUsuario(ejbUsuarioFacade.findAll());
        listUsuariosObjetivos = new ArrayList<>();
        listMetas = new ArrayList();
        meta = new Meta();
        listComponenteFormulas = new ArrayList<>();
        setMetaNueva(new Meta());
        indicador = null;
        componenteFormula = new ComponenteFormula();
        
    }

    public void guardar(ActionEvent event) {
        this.getSelected().setFechaModificacion(new Date());
        if (this.getSelected() != null
                && this.getSelected().getObjetivo() != null
                && this.getSelected().getFechaModificacion() != null) {
            ejbFacade.create(this.getSelected());
            
        }
        if (this.getSelected() != null
                && listUsuariosObjetivos != null
                && !listUsuariosObjetivos.isEmpty()) {
            
            List<ResponsableObjetivo> ros = this.getSelected().getResponsableObjetivoList();
            for (ResponsableObjetivo ro : ros) {
                ejbResponsableObjetivoFacade.remove(ro);
            }
            for (Usuario usuarios : listUsuariosObjetivos) {
                ResponsableObjetivoPK pk = new ResponsableObjetivoPK();
                pk.setIdUsuario(usuarios.getIdUsuario());
                pk.setIdObjetivoEstrategico(this.getSelected().getIdObjetivoEstrategico());
                ResponsableObjetivo pob = new ResponsableObjetivo(pk);
                ejbResponsableObjetivoFacade.create(pob);
            }
        }
        if (this.getSelected() != null
                && listMetas != null && !listMetas.isEmpty()) {
            if (!this.getSelected().getMetaList().isEmpty()) {
                List<Meta> listas = this.getSelected().getMetaList();
                for (Meta lista : listas) {
                    ejbMetaFacade.remove(lista);
                }
            }
            for (Meta listMeta : listMetas) {
                ejbMetaFacade.create(listMeta);
            }
        }
        
    }

    public void guardarIndicador(ActionEvent event) {
        if (indicador != null && indicador.getIdIndicador() != null) {
            if ((ejbIndicadorFacade.find(indicador.getIdIndicador())) != null) {
                ejbIndicadorFacade.edit(indicador);
            } else {
                ejbIndicadorFacade.create(indicador);
            }
        }
        if(indicador != null && !listComponenteFormulas.isEmpty()){
            for (ComponenteFormula ls : listComponenteFormulas) {
                
                if(ls.getIdComponenteFormula()!=null && (ejbComponenteFormulaFacade.find(ls.getIdComponenteFormula()))!=null){
                    ejbComponenteFormulaFacade.edit(ls);
            }else{
                ejbComponenteFormulaFacade.create(ls);
                }
            }
            
        }
    }

    public void prueba() {
        System.out.println("Si vale");
    }
    
    public void actualizarVariables() {
        if (indicador != null || indicador.getComponenteFormulaList() != null) {
            setListComponenteFormulas(indicador.getComponenteFormulaList());
        }
    }
    
    public void agregarUsuario() {
        System.out.println("Paso por aki");
        if (listUsuariosObjetivos == null) {
            listUsuariosObjetivos = new ArrayList<>();
        }
        if (usuario != null) {
            listUsuariosObjetivos.add(usuario);
        }
    }
    
    public String estadoActualProyecto() {
        if (indicador != null && indicador.getEstadoActual() != null) {
            if (indicador.getEstadoActual().equalsIgnoreCase("verde")) {
                return "../../resources/img/verde.png";
            }
        }
        if (indicador.getEstadoActual().equalsIgnoreCase("rojo")) {
            return "../../resources/img/rojo.png";
        }
        if (indicador.getEstadoActual().equalsIgnoreCase("amarillo")) {
            return "../../resources/img/amarillo.png";
        }
        return "../../resources/img/rojo.png";
    }
//public String mostrarMeta(Meta meta){
//this.meta = meta;
//return meta.getDescripcion();
//}

    public void actualizarListaUsuariosTotales() {
        setListTotalUsuario(ejbUsuarioFacade.findAll());
    }
    
    public void actualzar() {
        this.setItems(ejbFacade.findAll());
        System.out.println("el selectes:;" + ((this.getSelected().getObjetivo() != null) ? this.getSelected().getObjetivo() : " Esta nulo el selcted"));
        listUsuariosObjetivos = new ArrayList<>();
        // listMetas=new ArrayList<>();
        if (this.getSelected() != null & this.getSelected().getResponsableObjetivoList() != null) {
            List<ResponsableObjetivo> usuarioObjetivos = this.getSelected().getResponsableObjetivoList();
            for (ResponsableObjetivo usuarioObjetivo : usuarioObjetivos) {
                listUsuariosObjetivos.add(usuarioObjetivo.getUsuario());
            }
        } else {
            System.out.println("No esta seleccionado");
        }
        if (this.getSelected() != null & this.getSelected().getMetaList() != null) {
            listMetas = this.getSelected().getMetaList();
        }
        if (this.getSelected() != null & this.getSelected().getIndicadorList() != null
                & !this.getSelected().getIndicadorList().isEmpty() & this.getSelected().getIndicadorList().size() == 1) {
            System.out.println("Solo un indicador");
            listComponenteFormulas = this.getSelected().getIndicadorList().get(0).getComponenteFormulaList();
        } else {
            System.out.println("no hay indicadores");
            listComponenteFormulas = null;
        }
        System.out.println("metas tamano : " + listMetas.size());
    }
    
    public void agregarMeta() {
        System.out.println("Si ingreso en meta");
        if (listMetas == null) {
            listMetas = new ArrayList();
        }
        listMetas.add(metaNueva);
    }
    
    public void agregarVariable() {
        System.out.println("Si ingreso en variable");
        if (listComponenteFormulas == null) {
            listComponenteFormulas = new ArrayList();
        }
        if (componenteFormula != null) {
            listComponenteFormulas.add(componenteFormula);
        }
        componenteFormula = new ComponenteFormula();
    }
    
    public void eliminarVariable(ComponenteFormula componenteFormula) {
        if (componenteFormula != null) {
            System.out.println("metas tamano eliminacion: " + listComponenteFormulas.size());
            listComponenteFormulas.remove(componenteFormula);
            System.out.println("metas tamano eliminacion: " + listComponenteFormulas.size());
        }
    }
    
    public void eliminarMeta(Meta meta) {
        if (meta != null) {
            System.out.println("metas tamano eliminacion: " + listMetas.size());
            listMetas.remove(meta);
            System.out.println("metas tamano eliminacion: " + listMetas.size());
        }
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
    
    public List<Usuario> autoCompleteUsuario(String query) {
        List<Usuario> allThemes = getListTotalUsuario();
        List<Usuario> filteredThemes = new ArrayList<>();
        
        for (Usuario skin : allThemes) {
            if (skin.getNombres().toLowerCase().startsWith(query)
                    || skin.getApellidos().toLowerCase().startsWith(query)) {
                filteredThemes.add(skin);
            }
        }
        
        return filteredThemes;
    }

    /**
     * @return the usuarios
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuarios to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String actualizarListTotalUsuarios() {
        setListTotalUsuario(ejbUsuarioFacade.findAll());
        System.out.println("tamano: " + getListTotalUsuario().size());
        String dialog = "PF('representanteDialog').show()";
        return dialog;
    }

    /**
     * @return the listUsuariosObjetivos
     */
    public List<Usuario> getListUsuariosObjetivos() {
        return listUsuariosObjetivos;
    }

    /**
     * @param listUsuariosObjetivos the listUsuariosObjetivos to set
     */
    public void setListUsuariosObjetivos(List<Usuario> listUsuariosObjetivos) {
        this.listUsuariosObjetivos = listUsuariosObjetivos;
    }
    
    @Override
    public ObjetivoEstrategico prepareCreate(javax.faces.event.ActionEvent event) {
        this.setSelected(new ObjetivoEstrategico());
        initializeEmbeddableKey();
        return this.getSelected();
    }

    /**
     * @return the listMetas
     */
    public List<Meta> getListMetas() {
        return listMetas;
    }

    /**
     * @param listMetas the listMetas to set
     */
    public void setListMetas(List<Meta> listMetas) {
        this.listMetas = listMetas;
    }

    /**
     * @return the meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta the meta to set
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return the metaNueva
     */
    public Meta getMetaNueva() {
        return metaNueva;
    }

    /**
     * @param metaNueva the metaNueva to set
     */
    public void setMetaNueva(Meta metaNueva) {
        this.metaNueva = metaNueva;
    }

    /**
     * @return the listTotalUsuario
     */
    public List<Usuario> getListTotalUsuario() {
        return listTotalUsuario;
    }

    /**
     * @param listTotalUsuario the listTotalUsuario to set
     */
    public void setListTotalUsuario(List<Usuario> listTotalUsuario) {
        this.listTotalUsuario = listTotalUsuario;
    }

    /**
     * @return the indicador
     */
    public Indicador getIndicador() {
        return indicador;
    }

    /**
     * @param indicador the indicador to set
     */
    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    /**
     * @return the listComponenteFormulas
     */
    public List<ComponenteFormula> getListComponenteFormulas() {
        return listComponenteFormulas;
    }

    /**
     * @param listComponenteFormulas the listComponenteFormulas to set
     */
    public void setListComponenteFormulas(List<ComponenteFormula> listComponenteFormulas) {
        this.listComponenteFormulas = listComponenteFormulas;
    }

    /**
     * @return the componenteFormula
     */
    public ComponenteFormula getComponenteFormula() {
        return componenteFormula;
    }

    /**
     * @param componenteFormula the componenteFormula to set
     */
    public void setComponenteFormula(ComponenteFormula componenteFormula) {
        this.componenteFormula = componenteFormula;
    }
    
}
