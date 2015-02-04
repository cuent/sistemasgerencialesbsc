package ucuenca.edu.sg.controller;

import ucuenca.edu.sg.modelo.ObjetivoEstrategico;
import ucuenca.edu.sg.controller.util.JsfUtil;
import ucuenca.edu.sg.controller.util.JsfUtil.PersistAction;
import ucuenca.edu.sg.facade.ObjetivoEstrategicoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import ucuenca.edu.sg.modelo.ComponenteFormula;
import ucuenca.edu.sg.modelo.Indicador;
import ucuenca.edu.sg.modelo.Meta;
import ucuenca.edu.sg.modelo.ResponsableObjetivo;
import ucuenca.edu.sg.modelo.ResponsableObjetivoPK;
import ucuenca.edu.sg.modelo.Usuario;

@ManagedBean(name = "objetivoEstrategicoController")
@SessionScoped
public class ObjetivoController implements Serializable {

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
    ///listas modificadas
    private List<ObjetivoEstrategico> items = null;
    private List<Usuario> itemsResponsable = null;
    private List<Meta> itemsMeta = null;
    private List<Indicador> itemsIndicador = null;
    private List<ComponenteFormula> itemsComponenteFormula = null;
    //listas realies en la BD
    private List<Usuario> itemsRealResponsable = null;
    private List<Meta> itemsRealMeta = null;
    private List<Indicador> itemsRealIndicador = null;
    private List<ComponenteFormula> itemsRealComponenteFormula = null;

    private ObjetivoEstrategico selected;
    private Usuario usuario;
    private Meta meta;
    private Indicador indicador;
    private ComponenteFormula componenteFormula;

    ///Varibles de Control de vistas
    private boolean switchObjetivo = false;
    private boolean switchIndicador = true;
    ///Datos filtrados
    private List<Usuario> filterListResponsable = null;
    private List<Meta> filterListMeta = null;

    public ObjetivoController() {
    }

    @PostConstruct
    public void init() {

        this.setSelected(null);
        meta = new Meta();

        if (switchObjetivo) {
            items = new ArrayList<>();
            itemsResponsable = new ArrayList<>();
            itemsMeta = new ArrayList<>();
            itemsIndicador = new ArrayList<>();
            itemsComponenteFormula = new ArrayList<>();

            selected = new ObjetivoEstrategico();
            usuario = new Usuario();

            componenteFormula = new ComponenteFormula();

            switchObjetivo = false;
        } else {
            items = null;
            itemsResponsable = null;
            itemsMeta = null;

            itemsComponenteFormula = null;

            this.setSelected(null);
            usuario = null;

            switchObjetivo = true;
        }
        indicador = new Indicador();
        itemsIndicador = new ArrayList<>();

        itemsRealResponsable = new ArrayList<>();
        itemsRealMeta = new ArrayList<>();
        itemsRealIndicador = new ArrayList<>();
        itemsRealComponenteFormula = new ArrayList<>();
    }

    public void initIndicador() {
        if (switchIndicador) {
            itemsIndicador = new ArrayList<>();
            itemsComponenteFormula = new ArrayList<>();

            indicador = new Indicador();
            componenteFormula = new ComponenteFormula();
            switchIndicador = false;
        } else {
            itemsComponenteFormula = null;

            switchIndicador = true;
        }
        itemsRealComponenteFormula = new ArrayList<>();
        JsfUtil.addSuccessMessage("Indicadores: REAL:" + itemsRealIndicador.size() + "MEMORIA: " + itemsIndicador.size());
    }

    public void addResponsable() {
        JsfUtil.addSuccessMessage("Si ingreeso a addResponsable()");
        if (itemsResponsable != null) {
            for (Usuario item : itemsResponsable) {
                if (item.getEmail().equals(usuario.getEmail())) {
                    JsfUtil.addSuccessMessage("Responsables: REAL:" + itemsRealResponsable.size() + "MEMORIA: " + itemsResponsable.size());
                    return;
                }
            }
            itemsResponsable.add(usuario);
        }
        JsfUtil.addSuccessMessage("Responsables: REAL:" + itemsRealResponsable.size() + "MEMORIA: " + itemsResponsable.size());
    }

    public void addMeta() {
        JsfUtil.addSuccessMessage("Si ingreeso a addMeta()");
        if (itemsMeta != null && meta != null && meta.getDescripcion() != null) {
            for (Meta item : itemsMeta) {
                if (item.getDescripcion().equals(meta.getDescripcion())) {
                    JsfUtil.addSuccessMessage("Meta: REAL:" + itemsRealMeta.size() + "MEMORIA: " + itemsMeta.size());
                    return;
                }
            }
            itemsMeta.add(meta);
        }
        meta = new Meta();
    }

    public void addIndicador() {
        JsfUtil.addSuccessMessage("Si ingreeso a addIndicador()");
        JsfUtil.addSuccessMessage("Datos: id  " + indicador.getIdIndicador() + " nom:" + indicador.getNombreIndicador());
        if (itemsIndicador != null && indicador != null
                && indicador.getNombreIndicador() != null
                && indicador.getIdIndicador() == null) {
            itemsIndicador.add(indicador);
            System.out.println("Si es nuevo");
        } else if (itemsIndicador != null && indicador != null
                && indicador.getNombreIndicador() != null
                && indicador.getIdIndicador() != null) {
            for (Indicador item : itemsIndicador) {
                if (item.getIdIndicador().equals(indicador.getIdIndicador())) {
                    itemsIndicador.remove(item);
                    itemsIndicador.add(indicador);
                    break;
                }
            }
        }
        indicador = new Indicador();
        JsfUtil.addSuccessMessage("Indicadores: REAL:" + itemsRealIndicador.size() + "MEMORIA: " + itemsIndicador.size());
    }

    public void addComponenteFormula() {
        JsfUtil.addSuccessMessage("Si ingreeso a addComponenteFormula()");
        if (itemsComponenteFormula != null && componenteFormula != null && componenteFormula.getFormula() != null) {
            itemsComponenteFormula.add(componenteFormula);
        }
        componenteFormula = new ComponenteFormula();
    }

    public void updateItemsResponsable() {
        itemsResponsable = new ArrayList<>();
        System.out.println("oe: " + this.getSelected().getIdObjetivoEstrategico());
        //System.out.println("Tamano: "+ejbResponsableObjetivoFacade.getitemsObjetivoResponsable(this.getSelected().getIdObjetivoEstrategico()));
        itemsResponsable.addAll(ejbResponsableObjetivoFacade.getitemsObjetivoResponsable(this.getSelected().getIdObjetivoEstrategico()));

    }

    public void updateItemsMeta() {
        itemsMeta = new ArrayList<>();
        System.out.println("oe: " + this.getSelected().getIdObjetivoEstrategico());
        //System.out.println("Tamano: "+ejbResponsableObjetivoFacade.getitemsObjetivoResponsable(this.getSelected().getIdObjetivoEstrategico()));
        itemsMeta.addAll(ejbMetaFacade.getitemsMeta(this.getSelected().getIdObjetivoEstrategico()));
    }

    public void updateItemsComponenteFormula() {
        itemsComponenteFormula = new ArrayList<>();
        //System.out.println("Tamano: "+ejbResponsableObjetivoFacade.getitemsObjetivoResponsable(this.getSelected().getIdObjetivoEstrategico()));
        if (indicador.getIdIndicador() != null) {
            itemsComponenteFormula.addAll(ejbComponenteFormulaFacade.getitemsComponenteFormula(indicador.getIdIndicador()));
        }
        JsfUtil.addSuccessMessage("Indicadores: REAL:" + itemsRealIndicador.size() + "MEMORIA: " + itemsIndicador.size());
    }

    public void updateItemsIndicador() {
        itemsIndicador = new ArrayList<>();
        System.out.println("oe: " + this.getSelected().getIdObjetivoEstrategico());
        //System.out.println("Tamano: "+ejbResponsableObjetivoFacade.getitemsObjetivoResponsable(this.getSelected().getIdObjetivoEstrategico()));
        itemsIndicador.addAll(ejbIndicadorFacade.getitemsIndicador(this.getSelected().getIdObjetivoEstrategico()));
        System.out.println("listaIndicador: " + itemsIndicador.size());
    }

    public void deleteItemsResponsable(Usuario user) {
        System.out.println("Si valio...");
        itemsResponsable.remove(user);
    }

    public void deleteItemsComponenteFormula(ComponenteFormula formula) {
        System.out.println("Si valio...");
        itemsComponenteFormula.remove(formula);
    }

    public void deleteItemsMeta(Meta meta) {
        System.out.println("Si valio...");
        itemsMeta.remove(meta);
    }

    //private Integer idOE = 0;
    public void faceUpdateAll() {
        ObjetivoEstrategico oe = new ObjetivoEstrategico();
        boolean llave = switchObjetivo;
        boolean llave1 = switchIndicador;
        JsfUtil.addSuccessMessage("faceUpdateAll()");
        oe = selected;
        this.init();
        System.out.println("clave: " + oe);
        selected = oe;

        updateItemsResponsable();
        updateItemsMeta();
        updateItemsIndicador();

        switchObjetivo = llave;
        switchIndicador = llave1;
        itemsRealResponsable = new ArrayList<>();
        itemsRealMeta = new ArrayList<>();
        if (itemsResponsable != null) {
            itemsRealResponsable.addAll(itemsResponsable);
        }
        if (itemsRealMeta != null) {
            itemsRealMeta.addAll(itemsMeta);
        }
        if (itemsIndicador != null) {
            itemsRealIndicador.addAll(itemsIndicador);
        }
    }

    public void createResponsable() {
        JsfUtil.addSuccessMessage("createResponsable()" + " real:" + itemsRealResponsable.size()
                + "nuevo:" + itemsResponsable.size());
        if ((itemsRealResponsable == null || itemsRealResponsable.isEmpty()) && itemsResponsable != null) {
            for (Usuario crear : itemsResponsable) {
                ResponsableObjetivoPK pk = new ResponsableObjetivoPK();
                pk.setIdUsuario(crear.getIdUsuario());
                pk.setIdObjetivoEstrategico(this.getSelected().getIdObjetivoEstrategico());
                ResponsableObjetivo pob = new ResponsableObjetivo(pk);
                ejbResponsableObjetivoFacade.create(pob);
            }
            return;
        }
        if ((itemsResponsable == null || itemsResponsable.isEmpty()) && itemsRealResponsable != null) {
            for (Usuario eliminar : itemsRealResponsable) {
                ResponsableObjetivo pob = ejbResponsableObjetivoFacade.getObjetivoEstrategicoResponsable(eliminar.getIdUsuario(), this.getSelected().getIdObjetivoEstrategico());
                ejbResponsableObjetivoFacade.remove(pob);

            }
            return;
        }

        for (Usuario crear : itemsResponsable) {
            if (!itemsRealResponsable.contains(crear)) {
                ResponsableObjetivoPK pk = new ResponsableObjetivoPK();
                pk.setIdUsuario(crear.getIdUsuario());
                pk.setIdObjetivoEstrategico(this.getSelected().getIdObjetivoEstrategico());
                ResponsableObjetivo pob = new ResponsableObjetivo(pk);
                ejbResponsableObjetivoFacade.create(pob);
            }
        }
        for (Usuario eliminar : itemsRealResponsable) {
            if (!itemsResponsable.contains(eliminar)) {
                ResponsableObjetivo pob = ejbResponsableObjetivoFacade.getObjetivoEstrategicoResponsable(eliminar.getIdUsuario(), this.getSelected().getIdObjetivoEstrategico());
                ejbResponsableObjetivoFacade.remove(pob);
            }
        }
        for (Usuario actualizar : itemsResponsable) {
            ResponsableObjetivo pob = ejbResponsableObjetivoFacade.getObjetivoEstrategicoResponsable(actualizar.getIdUsuario(), this.getSelected().getIdObjetivoEstrategico());
            ejbResponsableObjetivoFacade.edit(pob);
        }
//        if (!JsfUtil.isValidationFailed()) {
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
    }

    public void createMeta() {
        JsfUtil.addSuccessMessage("createMeta()");
        if ((itemsRealMeta == null || itemsRealMeta.isEmpty()) && itemsMeta != null) {
            for (Meta crear : itemsMeta) {
                crear.setIdObjetivoEstrategico(this.getSelected());
                ejbMetaFacade.create(crear);
            }
            return;
        }
        if ((itemsMeta == null || itemsMeta.isEmpty()) && itemsRealMeta != null) {
            for (Meta eliminar : itemsRealMeta) {
                ejbMetaFacade.remove(eliminar);

            }
            return;
        }

        for (Meta crear : itemsMeta) {
            if (!itemsRealMeta.contains(crear)) {
                crear.setIdObjetivoEstrategico(this.getSelected());
                ejbMetaFacade.create(crear);
            }
        }
        for (Meta eliminar : itemsRealMeta) {
            if (!itemsMeta.contains(eliminar)) {
                ejbMetaFacade.remove(eliminar);
            }
        }
        for (Meta actualizar : itemsMeta) {
            actualizar.setIdObjetivoEstrategico(this.getSelected());
            ejbMetaFacade.edit(actualizar);
        }
//        if (!JsfUtil.isValidationFailed()) {
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
    }

    public void createIndicador() {

        JsfUtil.addSuccessMessage("createIndicador()");
        for (Indicador i : itemsIndicador) {
            System.out.println("Indicado: " + i.toString());
        }
        if ((itemsRealIndicador == null || itemsRealIndicador.isEmpty()) && itemsIndicador != null) {
            for (Indicador crear : itemsIndicador) {
                crear.setIdObjetivoEstrategico(this.getSelected());
                ejbIndicadorFacade.create(crear);
            }
            return;
        }
        if ((itemsIndicador == null || itemsIndicador.isEmpty()) && itemsRealIndicador != null) {
            for (Indicador eliminar : itemsRealIndicador) {
                ejbIndicadorFacade.remove(eliminar);

            }
            return;
        }

        for (Indicador crear : itemsIndicador) {
            if (!itemsRealIndicador.contains(crear)) {
                crear.setIdObjetivoEstrategico(this.getSelected());
                ejbIndicadorFacade.create(crear);
            }
        }
        for (Indicador eliminar : itemsRealIndicador) {
            if (!itemsIndicador.contains(eliminar)) {
                ejbIndicadorFacade.remove(eliminar);
            }
        }
//        if (!JsfUtil.isValidationFailed()) {
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
    }

    public void createComponenteFormula() {
        JsfUtil.addSuccessMessage("createComponenteFormula()");
        Indicador indi = itemsIndicador.get(0);
        if ((itemsRealComponenteFormula == null || itemsRealComponenteFormula.isEmpty()) && itemsComponenteFormula != null) {
            for (ComponenteFormula crear : itemsComponenteFormula) {
                crear.setIdIndicador(indi);
                ejbComponenteFormulaFacade.create(crear);
            }
            return;
        }
        if ((itemsComponenteFormula == null || itemsComponenteFormula.isEmpty()) && itemsRealComponenteFormula != null) {
            for (ComponenteFormula eliminar : itemsRealComponenteFormula) {
                ejbComponenteFormulaFacade.remove(eliminar);

            }
            return;
        }

        for (ComponenteFormula crear : itemsComponenteFormula) {
            if (!itemsRealComponenteFormula.contains(crear)) {
                crear.setIdIndicador(indi);
                ejbComponenteFormulaFacade.create(crear);
            }
        }
        for (ComponenteFormula eliminar : itemsRealComponenteFormula) {
            if (!itemsComponenteFormula.contains(eliminar)) {
                ejbComponenteFormulaFacade.remove(eliminar);
            }
        }
//        if (!JsfUtil.isValidationFailed()) {
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
    }

    public void updateIndicador() {
        JsfUtil.addSuccessMessage("updateIndicador()");
        //persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoUpdated"));
    }

    public void updateResponsable() {
        JsfUtil.addSuccessMessage("updateResponsable()");
        //persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoUpdated"));
    }

    public void updateMeta() {
        JsfUtil.addSuccessMessage("updateMeta()");
        //persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoUpdated"));
    }

    public void updateComponenteFormula() {
        JsfUtil.addSuccessMessage("updateComponenteFormula()");
        //persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoUpdated"));
    }

    public void destroyIndicador() {
        JsfUtil.addSuccessMessage("destroyIndicador()");
//        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoDeleted"));
//        if (!JsfUtil.isValidationFailed()) {
//            selected = null; // Remove selection
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
    }

    public void destroyResponsable() {
        JsfUtil.addSuccessMessage("destroyResponsable()");
//        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoDeleted"));
//        if (!JsfUtil.isValidationFailed()) {
//            selected = null; // Remove selection
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
    }

    public void destroyMeta() {
        JsfUtil.addSuccessMessage("destroyMeta()");
//        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoDeleted"));
//        if (!JsfUtil.isValidationFailed()) {
//            selected = null; // Remove selection
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
    }

    public void destroyComponenteFormula() {
        JsfUtil.addSuccessMessage("destroyComponenteFormula()");
//        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoDeleted"));
//        if (!JsfUtil.isValidationFailed()) {
//            selected = null; // Remove selection
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
    }

    public ObjetivoEstrategico getSelected() {
        return selected;
    }

    public void setSelected(ObjetivoEstrategico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ObjetivoEstrategicoFacade getFacade() {
        return ejbFacade;
    }

    public ObjetivoEstrategico prepareCreate() {
        selected = new ObjetivoEstrategico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        JsfUtil.addSuccessMessage("create()");
        if (ejbFacade.getObjetivoEstrategico(selected.getIdObjetivoEstrategico()) != null) {
            System.out.println("Es nulo" + selected);
            if (!switchObjetivo) {
                return;
            }
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        selected = ejbFacade.getObjetivoEstrategico(selected.getObjetivo());
        System.out.println("ANTES");
        this.createResponsable();
        System.out.println("INTERMEDIO");
        this.createMeta();
        System.out.println("FINAL");
        // createIndicador();
        //createComponenteFormula();
    }

    public void update() {
        //persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoEstrategicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ObjetivoEstrategico> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            selected.setFechaModificacion(new Date());
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ObjetivoEstrategico getObjetivoEstrategico(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ObjetivoEstrategico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ObjetivoEstrategico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the itemsResponsable
     */
    public List<Usuario> getItemsResponsable() {
        return itemsResponsable;
    }

    /**
     * @param itemsResponsable the itemsResponsable to set
     */
    public void setItemsResponsable(List<Usuario> itemsResponsable) {
        this.itemsResponsable = itemsResponsable;
    }

    /**
     * @return the itemsMeta
     */
    public List<Meta> getItemsMeta() {
        return itemsMeta;
    }

    /**
     * @param itemsMeta the itemsMeta to set
     */
    public void setItemsMeta(List<Meta> itemsMeta) {
        this.itemsMeta = itemsMeta;
    }

    /**
     * @return the itemsIndicador
     */
    public List<Indicador> getItemsIndicador() {
        return itemsIndicador;
    }

    /**
     * @param itemsIndicador the itemsIndicador to set
     */
    public void setItemsIndicador(List<Indicador> itemsIndicador) {
        this.itemsIndicador = itemsIndicador;
    }

    /**
     * @return the itemsComponenteFormula
     */
    public List<ComponenteFormula> getItemsComponenteFormula() {
        return itemsComponenteFormula;
    }

    /**
     * @param itemsComponenteFormula the itemsComponenteFormula to set
     */
    public void setItemsComponenteFormula(List<ComponenteFormula> itemsComponenteFormula) {
        this.itemsComponenteFormula = itemsComponenteFormula;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    /**
     * @return the switchObjetivo
     */
    public boolean isSwitchObjetivo() {
        return switchObjetivo;
    }

    /**
     * @param switchObjetivo the switchObjetivo to set
     */
    public void setSwitchObjetivo(boolean switchObjetivo) {
        this.switchObjetivo = switchObjetivo;
    }

    /**
     * @return the filterListResponsable
     */
    public List<Usuario> getFilterListResponsable() {
        List<Usuario> totales = ejbUsuarioFacade.findAll();
        filterListResponsable = new ArrayList<>();
        for (Usuario totale : totales) {
            if (itemsResponsable == null) {
                filterListResponsable.addAll(totales);
                break;
            }
            if (!itemsResponsable.contains(totale)) {
                filterListResponsable.add(totale);
            }
        }
        return filterListResponsable;
    }

    /**
     * @param filterListResponsable the filterListResponsable to set
     */
    public void setFilterListResponsable(List<Usuario> filterListResponsable) {
        this.filterListResponsable = filterListResponsable;
    }

    /**
     * @return the filterListMeta
     */
    public List<Meta> getFilterListMeta() {
        List<Meta> totales = ejbMetaFacade.findAll();
        filterListMeta = new ArrayList<>();
        for (Meta totale : totales) {
            if (itemsMeta == null) {
                filterListMeta.addAll(totales);
                break;
            }
            if (!itemsMeta.contains(totale)) {
                filterListMeta.add(totale);
            }
        }
        return filterListMeta;
    }

    /**
     * @param filterListMeta the filterListMeta to set
     */
    public void setFilterListMeta(List<Meta> filterListMeta) {
        this.filterListMeta = filterListMeta;
    }

    /**
     * @return the switchIndicador
     */
    public boolean isSwitchIndicador() {
        return switchIndicador;
    }

    /**
     * @param switchIndicador the switchIndicador to set
     */
    public void setSwitchIndicador(boolean switchIndicador) {
        this.switchIndicador = switchIndicador;
    }

    @FacesConverter(forClass = ObjetivoEstrategico.class)
    public static class ObjetivoEstrategicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObjetivoController controller = (ObjetivoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "objetivoController");
            return controller.getObjetivoEstrategico(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ObjetivoEstrategico) {
                ObjetivoEstrategico o = (ObjetivoEstrategico) object;
                return getStringKey(o.getIdObjetivoEstrategico());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ObjetivoEstrategico.class.getName()});
                return null;
            }
        }

    }

}
