/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.controller.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import ucuenca.edu.sg.modelo.Gerarquia;
import ucuenca.edu.sg.modelo.ObjetivoEstrategico;

/**
 *
 * @author pablito
 */
public class GenerarMapa {

    private List<ObjetivoEstrategico> listObjetivosEstrategicos;
    private List<Gerarquia> listGerarquia;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    private String codigo = "digraph example {\n"
            + "subgraph clusterTodo {\n"
            + "    subgraph clusterA {\n"
            + "\n"
            + "        node [shape=\"rect\",style=\"filled\", color=\"beige\", fontname=\"Arial\", fontsize=10, tooltip=\"Applicatie\"];\n"
            + "     	{rank=\"f\";}\n"
            + "        node [shape=\"ellipse\", style=\"invisible\", color=\"#3CB371\", fontsize=1];\n"
            + "        cluA [label=\".\"];\n"
            + "	style=\"filled\";\n"
            + "	fontname = \"Arial\";\n"
            + "	fontcolor=\"white\";\n"
            + "        color=\"grey\";\n"
            + "        label=\"Financiera\";\n"
            + "    }\n"
            + "\n"
            + "    subgraph clusterB {\n"
            + "        node [shape=\"ellipse\", style=\"filled\", fontname=\"Arial\", color=\"whitesmoke\", fontsize=10, tooltip=\"Frontend Service\"];\n"
            + "        {rank=\"c\";}\n"
            + "	node [shape=\"ellipse\", style=\"invisible\", color=\"#3CB371\", fontsize=1, tooltip=\"Applicatie\"];\n"
            + "        cluB [label=\".\"];\n"
            + "	style=\"filled\";\n"
            + "	fontname = \"Arial\";\n"
            + "	fontcolor=\"white\";\n"
            + "        color=\"darkgoldenrod1\";\n"
            + "        label=\"Cliente\";\n"
            + "    }\n"
            + "\n"
            + "\n"
            + "    subgraph clusterC {\n"
            + "        node [shape=\"octagon\", style=\"filled\", fontname=\"Arial\", color=\"yellow\", fontsize=10, tooltip=\"Applicatie Service\"];        \n"
            + "	{rank=\"p\";}\n"
            + "        node [shape=\"ellipse\", style=\"invisible\", color=\"#3CB371\", fontsize=1, tooltip=\"Applicatie\"];\n"
            + "        cluC [label=\".\"];\n"
            + "	style=\"filled\";\n"
            + "	fontname = \"Arial\";\n"
            + "	fontcolor=\"white\";\n"
            + "        color=\"dodgerblue3\";\n"
            + "\n"
            + "	label=\"Procesos Internos\";\n"
            + "    }\n"
            + "\n"
            + "  subgraph clusterD {  \n"
            + "\n"
            + "        node [shape=\"egg\", style=\"filled\",fontname=\"Arial\", color=\"#3CB371\", fontsize=10];\n"
            + "        {rank=\"a\";}\n"
            + " 	node [shape=\"ellipse\", style=\"invisible\", color=\"#3CB371\", fontsize=1, tooltip=\"Applicatie\"];\n"
            + "        cluD [label=\".\"];\n"
            + "	\n"
            + "	fontname = \"Arial\";\n"
            + "	fontcolor=\"white\";\n"
            + "	style=\"filled\";\n"
            + "        color=\"firebrick3\";\n"
            + "        label=\"Aplicacion Y Crecimiento\";\n"
            + "       \n"
            + "    } \n"
            + "\n"
            + "  \"cluA\" -> \"cluB\" [style=invis ltail=clusterA lhead=clusterB];\n"
            + "  \"cluB\" -> \"cluC\" [style=invis ltail=clusterB lhead=clusterC];\n"
            + "  \"cluC\" -> \"cluD\" [style=invis ltail=clusterC lhead=clusterD];\n"
            + "  edge [color=\"red\", tooltip=\"Opvragen\"];\n"
            + "   remincross=\"true\";"
            + "}\n"
            + "\n"
            + "}";

    public void cargarDatos() {
        String inicio = "";
        String finaciero = "";
        String cliente = "";
        String proceso = "";
        String aprendizaje = "";
        for (ObjetivoEstrategico objetivo : getListObjetivosEstrategicos()) {
            String perspectiva = objetivo.getIdPerspectiva().getPerspectiva();
            if (perspectiva.equalsIgnoreCase("Financiera")) {
                finaciero = finaciero + "f" + objetivo.getIdObjetivoEstrategico() + " [label=\"" + separar(objetivo.getObjetivo()) + "\"]; ";
              
            }
            if (perspectiva.equalsIgnoreCase("Clientes")) {
                cliente = cliente + "c" + objetivo.getIdObjetivoEstrategico() + " [label=\"" + separar(objetivo.getObjetivo()) + "\"]; ";
            }
            if (perspectiva.equalsIgnoreCase("Procesos internos")) {
                proceso = proceso + "p" + objetivo.getIdObjetivoEstrategico() + " [label=\"" + separar(objetivo.getObjetivo()) + "\"]; ";
            }
            if (perspectiva.equalsIgnoreCase("Aprendizaje")) {
                aprendizaje = aprendizaje + "a" + objetivo.getIdObjetivoEstrategico() + " [label=\"" + separar(objetivo.getObjetivo()) + "\"]; ";
            }
        }
        procesoRemplazar(finaciero, cliente, proceso, aprendizaje);

    }

    public void procesoRemplazar(String f, String c, String p, String a) {
        codigo = codigo.replaceAll("rank=\"f\";", f);
        codigo = codigo.replaceAll("rank=\"c\";", c);
        codigo = codigo.replaceAll("rank=\"p\";", p);
        codigo = codigo.replaceAll("rank=\"a\";", a);
        codigo=codigo.replaceAll("remincross=\"true\";", cargarRelaciones());
        System.out.println("entrooo: "+"/*relaciones*/");
        

    }

    public String separar(String valor) {
        StringTokenizer st = new StringTokenizer(valor);
        int nuerosaltos = st.countTokens() + 5;
        String resultado = "";
        int cont = 0;
        while (st.hasMoreElements()) {
            String v = st.nextElement().toString() + " ";
            cont += v.length();
            if (cont > nuerosaltos) {
                resultado += "\\\n";
                cont = 0;
            }
            resultado += v;
        }
        return resultado;
    }

    public String cargarRelaciones() {
        String relaciones = "";
        for (Gerarquia gerarquia : getListGerarquia()) {

            String perspectivaPredeceso = identificarPerspectiva(gerarquia.getPredecesora().getIdPerspectiva().getPerspectiva());
            String sucesora = identificarPerspectiva(gerarquia.getSucesora().getIdPerspectiva().getPerspectiva());

            relaciones += perspectivaPredeceso + gerarquia.getPredecesora().getIdObjetivoEstrategico() + " -> " + sucesora + gerarquia.getSucesora().getIdObjetivoEstrategico()+"; ";

        }
        System.out.println(relaciones);
        return relaciones;
    }

    public String identificarPerspectiva(String perspectiva) {
        if (perspectiva.equalsIgnoreCase("Financiera")) {
            return "f";
        }
        if (perspectiva.equalsIgnoreCase("Clientes")) {
            return "c";
        }
        if (perspectiva.equalsIgnoreCase("Procesos internos")) {
            return "p";
        }
        if (perspectiva.equalsIgnoreCase("Aprendizaje")) {
            return "a";
        }
        return "";
    }

    public void genearImagenMapa() throws IOException {
        String ruta = getEc().getRealPath("resources");
        cargarDatos();
        generarArchivo();
        try {

            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String fileInputPath = ruta + "\\grafo1.txt";
            String fileOutputPath = ruta + "\\grafo1.gif";
            String tParam = "-Tgif";
            String tOParam = "-o";
            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);

            // setRutaImagen("resources/grafo1.jpg");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
        }
    }

    public void generarArchivo() throws IOException {

        String ruta = getEc().getRealPath("resources") + "/grafo1.txt";
        System.out.println(getEc().getRealPath("resources"));
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            //System.out.println(llenarFinanciera());
            bw.write(codigo);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("Acabo de crear el fichero de texto.");
        }
        bw.close();
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
     * @return the listGerarquia
     */
    public List<Gerarquia> getListGerarquia() {
        return listGerarquia;
    }

    /**
     * @param listGerarquia the listGerarquia to set
     */
    public void setListGerarquia(List<Gerarquia> listGerarquia) {
        this.listGerarquia = listGerarquia;
    }

    /**
     * @return the listFinaciera
     */
    /**
     * @param listFinaciera the listFinaciera to set
     */
    /**
     * @return the cabezera
     */
}
