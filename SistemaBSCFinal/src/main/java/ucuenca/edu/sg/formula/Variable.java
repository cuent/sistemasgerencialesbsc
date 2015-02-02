/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucuenca.edu.sg.formula;

import ucuenca.edu.sg.modelo.ComponenteFormula;

/**
 *
 * @author ESTUDIANTE 3-05
 */
public class Variable {

    private ComponenteFormula componenteFormula;
    private double valor;

    public Variable(ComponenteFormula componenteFormula, double valor) {
        this.componenteFormula = componenteFormula;
        this.valor = valor;
    }

    public ComponenteFormula getComponenteFormula() {
        return componenteFormula;
    }

    public void setComponenteFormula(ComponenteFormula componenteFormula) {
        this.componenteFormula = componenteFormula;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
