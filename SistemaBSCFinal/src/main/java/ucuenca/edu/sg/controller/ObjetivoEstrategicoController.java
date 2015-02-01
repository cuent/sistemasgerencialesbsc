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
import ucuenca.edu.sg.modelo.Usuario;

@Named("objetivoEstrategicoController")
@SessionScoped
public class ObjetivoEstrategicoController extends AbstractController<ObjetivoEstrategico> implements Serializable {

    @EJB
    private ucuenca.edu.sg.facade.ObjetivoEstrategicoFacade ejbFacade;
     @EJB
    private ucuenca.edu.sg.facade.UsuarioFacade ejbUsuarioFacade;
    private Usuario usuario;
    private List<Usuario> listTotalUsuario, listUsuariosObjetivos;

    
    public ObjetivoEstrategicoController() {
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSelected(new ObjetivoEstrategico());
        usuario = new Usuario();
        listTotalUsuario = ejbUsuarioFacade.findAll();
        listUsuariosObjetivos=new ArrayList<>();
    }
public void agregarUsuarioObjetivo(){
    System.out.println("Paso por aki");
    listUsuariosObjetivos.add(usuario);
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
public List<Usuario> autoCompleteUsuario(String query) {
        List<Usuario> allThemes = listTotalUsuario;
        List<Usuario> filteredThemes = new ArrayList<>();
         
        for (Usuario skin : allThemes) {
            if(skin.getNombres().toLowerCase().startsWith(query) ||
                    skin.getApellidos().toLowerCase().startsWith(query)   ) {
                filteredThemes.add(skin);
            }
        }
         
        return filteredThemes;
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
    public String actualizarListTotalUsuarios() {
        listTotalUsuario = ejbUsuarioFacade.findAll();
        System.out.println("tamano: " +  listTotalUsuario.size());
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

}
