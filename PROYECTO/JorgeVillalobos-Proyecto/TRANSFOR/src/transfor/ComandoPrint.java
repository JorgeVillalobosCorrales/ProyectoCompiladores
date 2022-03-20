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
public class ComandoPrint {
    
    //Instancias de la clase
    ValidacionesTarea1 llamarValidaciones = new ValidacionesTarea1();
    ValidacionesTarea2 llamarValidaciones2 = new ValidacionesTarea2();
    FormatoErrores error;
    
    //Cuando encuentre la comilla simple inicio y cuando encuentre la final ese mensaje se remplaza por vacío
    public static String validarMensajePRINT(String linea) {
        String mensaje = "";//Se declara vacio
        int inicio = 0;
        int fin = 0;
        try {
            if (linea.contains("'")) {//Si la linea contiene una comilla individual
                mensaje = linea.trim();//Eliminar caracteres iniciales y finales.
                inicio = mensaje.indexOf("'");//Obtiene el índice de donde se obtiene ese caracter.
                fin = mensaje.indexOf("'", inicio + 1);//Lee de la primera comilla simple hasta donde termina la otra comilla simple que se encuentre.
                                                       //En caso de que no se encuentra, muestra un errror
                mensaje = mensaje.substring(inicio, fin + 1);//Se obtiene el mensaje de comilla simple a comilla simple 
                linea = linea.replace(mensaje, "");//Reemplazar el mensaje anterior por vacio.
            }
        } catch (Exception e) {
            linea = "Error";//Si algo no cumple bien, muestra el error del mensaje
        }

        if (inicio == 0 && fin == 0) {//Si inicio y fin es 0 en el indice, en caso de que las variables tiene 1 no entra aquí
            linea = "Error";//Muestra un error
        }
        return linea;//Retorna si encuentra o no error
    }
    
    public boolean validarPrint(String linea, int numLinea) {
        boolean printValido = false;//Se inicia un boolean falso, este luego se cambia estado.
        linea = llamarValidaciones.limpiarLinea(linea);//Se usa el formateo de la línea
        linea = linea.replace("'", " ' ");
        linea = linea.replaceAll(" +", " ");
        linea= linea.trim();
        //System.out.println("ORIGINAL: " + linea);
        String expresion[] = linea.split(" ");
        try {
            if(linea.startsWith("PRINT*")){//En caso de no inicie con etiquetas en la linea 10000, 10020, 050, 3
                System.out.println("No tiene etiqueta: " + linea);
                if(expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && expresion[2].equals("'")){//Detecta antes de la comilla del mensaje Por Favor
                    linea = validarMensajePRINT(linea);//Valida la linea en caso de obtener un error.
                    if (linea.equalsIgnoreCase("Error")) {//Se verifica el error
                        error = new FormatoErrores(Errores.listaErrores(16, linea), numLinea);//Aquí se imprime.
                        TRANSFOR.lista.add(error);
                    }
                } else if(expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && llamarValidaciones2.esVariable(expresion[8])){
                    //La línea anterior debe recibir 4 variables ya declaradas
                    printValido = true; //4 Variables correctas, no se imprime error
                } else if(expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && !llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && !llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && !llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && !llamarValidaciones2.esVariable(expresion[8])){
                     //La línea anterior recibe 4 variables no declaradas
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);//Se muestra el errror
                    TRANSFOR.lista.add(error); //4 Variables incorrectas
                    printValido = false;//Imprir error en el txt errores
                } else if((expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && !llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && !llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && !llamarValidaciones2.esVariable(expresion[8])) 
                        || (expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && !llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && llamarValidaciones2.esVariable(expresion[8]))){
                    //Incumple en la declaración de algunas variables
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    printValido = false;
                } else if((expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && !llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && !llamarValidaciones2.esVariable(expresion[8])) 
                       || (expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && !llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && !llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && llamarValidaciones2.esVariable(expresion[8]))){
                    //Incumple en la declaración de algunas variables
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    printValido = false;//Si el print es falso hay error
                } else if((expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && !llamarValidaciones2.esVariable(expresion[8])) 
                       || (expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && !llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && !llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && !llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && llamarValidaciones2.esVariable(expresion[8]))){
                    //Incumple en la declaración de algunas variables
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    printValido = false;//Si el print es falso hay error
                } else if((expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && !llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && llamarValidaciones2.esVariable(expresion[8])) 
                       || (expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && !llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && !llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && !llamarValidaciones2.esVariable(expresion[8]))){
                    //Incumple en la declaración de algunas variables
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    printValido = false;//Si el print es falso hay error
                } else if((expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && !llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && llamarValidaciones2.esVariable(expresion[8])) 
                       || (expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && !llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && !llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && !llamarValidaciones2.esVariable(expresion[8]))){
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    printValido = false;
                } else if((expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && !llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && !llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && llamarValidaciones2.esVariable(expresion[8])) 
                       || (expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && !llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && !llamarValidaciones2.esVariable(expresion[8]))){
                   //Incumple en la declaración de algunas variables
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    printValido = false;//Si el print es falso hay error
                } else if((expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && !llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && !llamarValidaciones2.esVariable(expresion[8])) 
                       || (expresion[0].equalsIgnoreCase("PRINT*") && expresion[1].equals(",") && !llamarValidaciones2.esVariable(expresion[2]) && expresion[3].equals(",") && llamarValidaciones2.esVariable(expresion[4]) && expresion[5].equals(",") && !llamarValidaciones2.esVariable(expresion[6]) && expresion[7].equals(",") && llamarValidaciones2.esVariable(expresion[8]))){
                    //Incumple en la declaración de algunas variables
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    printValido = false;//Si el print es falso hay error
                }else if(expresion[0].equalsIgnoreCase("PRINT*")){
                    printValido = true; // Bien viene Print*
                } else {
                    error = new FormatoErrores(Errores.listaErrores(18, linea), numLinea);
                    TRANSFOR.lista.add(error); //Error sintaxis print
                    printValido = false;//Si el print es falso hay error
                }
            } else if(linea.startsWith("PRINT")){//Si la linea tiene el print pero no con el asterisco *
                if(expresion[0].equalsIgnoreCase("PRINT*")){//Si tiene que venir el asterisco
                    printValido = true; // Bien viene Print*
                } else if (expresion[0].equalsIgnoreCase("PRINT") && expresion[1].equals("*")){//Se cumprueba si despues del print hay un espacio
                    error = new FormatoErrores(Errores.listaErrores(15, linea), numLinea);//Imprimir el error
                    TRANSFOR.lista.add(error);// mal viene Print ---  *
                    printValido = false;//Si el print es falso hay error
                } else{
                    error = new FormatoErrores(Errores.listaErrores(18, linea), numLinea);
                    TRANSFOR.lista.add(error); //Error sintaxis print
                    printValido = false;//Si el print es falso hay error
                }
            }else{
                //Tiene etiqueta
                if(llamarValidaciones2.validarEtiqueta(expresion[0], numLinea) == true){//Primero se valida la etiqueta
                    //System.out.println("Si tiene etiqueta: " + linea);
                    if(expresion[1].equalsIgnoreCase("PRINT*") && expresion[2].equals(",") && expresion[3].equals("'")){
                        linea = validarMensajePRINT(linea);//Valida que se encuentre la etiqueta ' en ambas partes
                        if (linea.equalsIgnoreCase("Error")) {//Verifica si print tiene algo mal escrito
                            error = new FormatoErrores(Errores.listaErrores(16, linea), numLinea);
                            TRANSFOR.lista.add(error);
                        }
                    } else if(expresion[1].equalsIgnoreCase("PRINT*")){
                        printValido = true; // Bien viene Print*
                    } else if (expresion[1].equalsIgnoreCase("PRINT") && expresion[2].equals("*")){
                        //Print solo y luego viene el * separada
                        error = new FormatoErrores(Errores.listaErrores(15, linea), numLinea);
                        TRANSFOR.lista.add(error);// mal viene Print ---  *
                        printValido = false;
                    } else {
                        error = new FormatoErrores(Errores.listaErrores(18, linea), numLinea);
                        TRANSFOR.lista.add(error); //Error sintaxis print
                        printValido = false;
                    }
                }else{
                    error = new FormatoErrores(Errores.listaErrores(20, linea), numLinea);//Error etiqueta no tiene formato correcto
                    TRANSFOR.lista.add(error);
                }
            }
        } catch (Exception e) {
        }
        return printValido;//Retorna si es true no imprime nada, en caso de ser false muestra errores.
    }
}