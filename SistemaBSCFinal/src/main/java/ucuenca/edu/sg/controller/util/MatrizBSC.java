/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.controller.util;

import java.io.Serializable;
import java.util.Date;

public class MatrizBSC implements Serializable{
    
    private String clase;
    private Integer id;
    public MatrizBSC() {
    }

    public MatrizBSC( String clase, Integer id) {
        this.clase = clase;
        this.id = id;
    }

    
    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
