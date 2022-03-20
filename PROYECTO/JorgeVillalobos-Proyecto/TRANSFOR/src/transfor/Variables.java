/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfor;

/**
 *
 * @author Jorge
 */
class Variables {
    private String tipoDato;//Para capturar el tipo de valor Interger, Character, Real
    private String nombreIdentificador;//Se usa para las variable del archivo .transfor MAXanos
    private String valorIdentificador;//Esta variable todavía no se usa, es para inicializar variables, ahorita tiene valor en 0

    public Variables(String tipoDato, String nombreIdentificador, String valorIdentificador) {
        this.tipoDato = tipoDato;
        this.nombreIdentificador = nombreIdentificador;
        this.valorIdentificador = valorIdentificador;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getNombreIdentificador() {
        return nombreIdentificador;
    }

    public void setNombreIdentificador(String nombreIdentificador) {
        this.nombreIdentificador = nombreIdentificador;
    }

    public String getValorIdentificador() {
        return valorIdentificador;
    }

    public void setValorIdentificador(String valorIdentificador) {
        this.valorIdentificador = valorIdentificador;
    }
}
