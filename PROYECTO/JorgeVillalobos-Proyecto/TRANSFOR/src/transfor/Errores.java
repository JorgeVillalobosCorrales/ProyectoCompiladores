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
public class Errores {
   public static boolean hayErrores;
    /*Permite mostrar los errores que ocurren, estos mensajes se copian en el txt*/
    public static String listaErrores(int numero, String mensaje) {
        hayErrores = true;
        switch (numero) {
            case 1:
                return "       Error 001: La línea excede los 80 caracteres permitidos";
            case 2:
                return "       Error 002: El nombre del identificador tiene que ser menor a 30 dígitos.";
            case 3:
                return "       Error 003: El nombre del identificador no tiene que empezar con guion bajo.";
            case 4:
                return "       Error 004: El nombre del identificador no tiene que terminar con guion bajo.";
            case 5:
                return "       Error 005: El nombre del identificador tiene que iniciar con una letra.";
            case 6:
                return "       Error 006: El nombre del identificador sólo permite letras, números o guion bajo.";
            case 7:
                return "       Error 007: El nombre del identificador no permite el uso de caracteres especiales.";

            case 8:
                return "       Error 008: No debe de quedar ninguna coma guindando.";
            case 9:
                return "       Error 009: Un identificador no puede ser una palabra reservada.";
            case 10:
                return "       Error 010: No existe comando PROGRAM.";
            case 11:
                return "       Error 011: EL comando PROGRAM no debe aparecer dos veces.";
            case 12:
                return "       Error 012: READ y el asterisco deben venir pegados sin blancos entre ellos.";
            case 13:
                return "       Error 013: La variable debe haber sido definida previamente.";
            case 14:
                return "       Error 014: La coma es obligatoria después de READ*";
            case 15:
                return "       Error 015: PRINT y el asterisco deben venir pegados sin blancos entre ellos.";
            case 16:
                return "       Error 016: La estructura del comando PRINT* contiene errores.";
            case 17:
                return "       Error 017: No existe comando ENDPROGRAM.";
            case 18:
                return "       Error 018: Sintaxis incorrecta de PRINT.";
            case 19:
                return "       Error 019: La expresión algebraica contiene errores.";
            case 20:
                return "       Error 020: El formato de la etiqueta no es el correcto.";
            case 21:
                return "       Error 021: La etiqueta no puede ser repetida.";
            case 22:
                return "       Error 022: EL comando ENDPROGRAM no debe aparecer dos veces.";
                case 23:
                return "       Error 023: La etiqueta debe haber sido definida.";
            case 24:
                return "       Error 024: Falta la etiqueta.";
            case 25:
                return "       Error 025: Cantidad de comillas simples incorrecta.";
            case 26:
                return "       Error 026: La estructura del comando STOP contiene errores.";
            case 27:
                return "       Error 027: El tipo de la variable no es Integer.";
            case 28:
                return "       Error 028: La constante mínima debe ser entera.";
            case 29:
                return "       Error 029: El igual es obligatorio.";
            case 30:
                return "       Error 030: La coma es obligatoria.";
            case 31:
                return "       Error 031: No existe comando DO.";
            case 32:
                return "       Error 032: No existe comando ENDDO.";
            case 33:
                return "       Error 033: Cantidad de Parentesis incorrecta.";
            case 34:
                return "       Error 034: El operador relacional no es válido.";
            case 35:
                return "       Error 035: La palabra THEN debe aparecer.";
            case 36:
                return "       Error 036: No existe comando IF.";
            case 37:
                return "       Error 037: No existe comando ENDIF.";
          
        }
        return null;//En caso de no existir un error
    }
}
