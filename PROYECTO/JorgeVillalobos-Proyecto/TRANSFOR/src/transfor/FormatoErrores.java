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
public class FormatoErrores {
    private String mensajeError;
    private int numeroError;
    
    //Método constructor
    public FormatoErrores(String mensajeError, int numeroError) {
        this.mensajeError = mensajeError;
        this.numeroError = numeroError;
    }
    
    public FormatoErrores() {
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public int getNumeroError() {
        return numeroError;
    }

    public void setNumeroError(int numeroError) {
        this.numeroError = numeroError;
    }
    
    //Imprimir el mensaje de error, cuando se llama el método
    @Override
    public String toString() {
        return "Error{" + "mensajeError=" + mensajeError + ", numeroError=" + numeroError + '}';
    }
}
