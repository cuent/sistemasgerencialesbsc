/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.controller.util;

import com.itextpdf.text.DocumentException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jq733100.utilidades.CodigoBarra;
import jq733100.utilidades.Documento;
import jq733100.utilidades.Encabezado;
import jq733100.utilidades.EspacioBlanco;
import jq733100.utilidades.Imagen;
import jq733100.utilidades.LineaCentrada;
import jq733100.utilidades.LineaNormal;
import jq733100.utilidades.TablaHorizontal;
import jq733100.utilidades.TablaVertical;
import jq733100.utilidades.Titulo1;
import jq733100.utilidades.Titulo2;
import jq733100.utilidades.Titulo3;
import jq733100.utilidades.Titulo4;
import ucuenca.edu.sg.modelo.Actividades;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;
import ucuenca.edu.sg.modelo.ResponsableActividad;

/**
 *
 * @author pablito
 */
public class GenerarActividades {

    String rutaDestino = "";
    private Encabezado encabezado;
    private CodigoBarra codigoBarra;

    private LineaNormal lineaNormal;
    private LineaCentrada lineaCentrada;
    private TablaHorizontal tablaHorizontal;
    private TablaVertical tablaVertical;
    private Imagen imagen;
    private Titulo1 titulo1;
    private Titulo2 titulo2;
    private Titulo3 titulo3;
    private Titulo4 titulo4;
    private EspacioBlanco espacioBlanco;
    private Documento documento;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    public GenerarActividades() {

        encabezado = new Encabezado();
        codigoBarra = new CodigoBarra();
        espacioBlanco = new EspacioBlanco();
        lineaNormal = new LineaNormal();
        lineaCentrada = new LineaCentrada();
        tablaHorizontal = new TablaHorizontal();
        tablaVertical = new TablaVertical();
        imagen = new Imagen();
        titulo1 = new Titulo1();
        titulo2 = new Titulo2();
        titulo3 = new Titulo3();
        titulo4 = new Titulo4();
    }

    public void generardocumento(List<Actividades> listaGlobales, List<ObjetivoEstrategico> listObjetivo) throws DocumentException {
        String ruta = getEc().getRealPath("resources");
        System.out.println(ruta);
        documento = new Documento(ruta + "/rep1.pdf");
        documento.setMargins(60, 30, 30, 40);
        documento.open();
        cabezera();
        cuerpo(listaGlobales, listObjetivo);
        documento.close();
    }

    public void cabezera() throws DocumentException {
        titulo1.setTexto("INFORME DE TAREA POR OBJETIVO");
        titulo1.getElementoRojo();
        espacioBlanco.getElemento();
        //imagen.setDir("");

    }

    public void cuerpo(List<Actividades> actividades, List<ObjetivoEstrategico> listObjetivo) throws DocumentException {

        documento.add(titulo1.getElemento());
        documento.add(espacioBlanco.getElemento());
        documento.add(espacioBlanco.getElemento());

        documento.add(lineaNormal.getElemento());
        documento.add(espacioBlanco.getElemento());
        int cont = 0;
        documento.add(espacioBlanco.getElemento());
        List<Actividades> listactividad = new ArrayList<>();
        int j = 0;
        for (ObjetivoEstrategico objetivo : listObjetivo) {
            titulo3.setTexto("Obj. Estrategico: " + "<" + objetivo.getObjetivo() + ">");
            documento.add(titulo3.getElemento());
            for (Actividades i : actividades) {

                if (objetivo.getIdObjetivoEstrategico() == i.getIdObjetivoEstrategico().getIdObjetivoEstrategico()) {

                    listactividad.add(i);

                } else {

                }
            }
            cargarActividades(listactividad);
        }

    }

    public void cargarActividades(List<Actividades> actividadeses) throws DocumentException {

        if (!actividadeses.isEmpty()) {
            titulo3.setTexto("Actividades:");
            documento.add(titulo3.getElemento());
            documento.add(espacioBlanco.getElemento());
            tablaHorizontal.setColumnas(6);
            tablaHorizontal.setTitulos("ACTIVIDAD", "CONTROL", "FECHA INICIO", "FECHA FIN", "DURACION", "RESPONSABLE");
            Vector vector = new Vector();

            for (Actividades i : actividadeses) {

                ArrayList<String> dato = new ArrayList<String>();
                dato.add(String.valueOf(i.getActividad()));
                dato.add(String.valueOf(i.getControl()));
                dato.add(String.valueOf(i.getFechaInicio()));
                dato.add(String.valueOf(i.getFechaFin()));
                dato.add(String.valueOf(i.getDuracion()));
                dato.add(String.valueOf(cargarResponsables(i.getResponsableActividadList())));

                vector.add(dato);
            }
            tablaHorizontal.setContenidos(vector.toArray());
            tablaHorizontal.setAlineamientos(new int[]{1, 0, 1, 1, 1, 0});
            tablaHorizontal.setTamanos(new int[]{35, 20, 15, 15, 10, 20});
            tablaHorizontal.setAnchoTabla(100);
            tablaHorizontal.llenarTabla();
            documento.add(tablaHorizontal.getTabla());
            documento.add(espacioBlanco.getElemento());
            documento.add(espacioBlanco.getElemento());
            actividadeses.clear();
        }

    }

    public String cargarResponsables(List<ResponsableActividad> lista) {
        String respuesta = "";
        for (ResponsableActividad res : lista) {
            respuesta+=res.getUsuario().getNombres()+"\n";
        }
  return respuesta;
    }

    public void piePaguina() {

    }

    /**
     * @return the ec
     */
    public ExternalContext getEc() {
        return ec;
    }

    /**
     * @param ec the ec to set
     */
    public void setEc(ExternalContext ec) {
        this.ec = ec;
    }

}
