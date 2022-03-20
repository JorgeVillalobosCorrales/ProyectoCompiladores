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
public class AnalisisLexico {
    //Instancias de clases
    FormatoErrores error;
    Reservadas buscarpalabra = new Reservadas();
    ValidacionesTarea1 validarTarea1 = new ValidacionesTarea1();
    ValidacionesTarea2 validarTarea2 = new ValidacionesTarea2();
    ValidacionesProyecto validarProyecto = new ValidacionesProyecto();
    ComandoPrint llamadaPrint = new ComandoPrint();
    
    //Declaración de variables
    public static boolean existeProgram = false;//Llama una variable desde otra clase, su valor aquí es falso, permite comprobar que no se encuentre program
    public static boolean existeEndprogram = false;//Llama una variable desde otra clase, su valor aquí es falso, permite comprobar que no se encuentre endprogram
    public static boolean existeDo = false;//Llama una variable desde otra clase, su valor aquí es falso, permite comprobar que que no se encuentre DO
    public static boolean existeEndDo = false;//Llama una variable desde otra clase, su valor aquí es falso, permite comprobar que no se encuentre ENDDO
    public static boolean existeIf = false;//Llama una variable desde otra clase, su valor aquí es falso, permite comprobar que que no se encuentre IF
    public static boolean existeENDIF = false;//Llama una variable desde otra clase, su valor aquí es falso, permite comprobar que no se encuentre ENDIF
    
    public void analisisLexicoLineas(String linea, int numLinea) {
        linea = linea.toUpperCase();//Convierte lo que tiene línea a mayusculas
        linea = linea.replaceAll("^\\s*","");//Quitar espacios al inicion de la línea
        //validar.validarAmpersand(linea);//Este método esta pendiente de resolver
        if(!linea.isEmpty()){//Si la línea no esta vacía 
            linea = validarTarea1.limpiarLinea(linea);//Se limpia la linea para hacer un mejor formato
            
            String expresion[] = linea.split(" ");//Asignar cada toxen en el vector de expresiones
            if(buscarpalabra.esReservadaFortran(expresion[0]) || expresion[0].startsWith("&")){//Si en la línea una palabra reservada
            }else{//Si no encuentra una palabra reservada sigue con las siguientes instrucciones
                
                validarTarea1.validarEspaciosBlancos(linea);//Llama un método que ese método llama otros desde desde su clase
                
                if(!validarTarea1.validarCantidadLinea(linea)){//Si la línea no cumple con los 80 carácteres, se muestra un mensaje.
                    /*El primer valor es el del case, Linea es igual a mensaje,
                    numLinea es donde se encontro el error de la lína analizada*/
                    error = new FormatoErrores(Errores.listaErrores(1, linea), numLinea);
                    TRANSFOR.lista.add(error);//Se agrega en la lista
                }
                
                if(linea.contains("!")){
                    validarTarea1.esComentario(linea);//Si es comentario se ignora                       
                } else if(expresion[0].equalsIgnoreCase("PROGRAM") /*&& linea.contains("PROGRAM")*/){
                    existeProgram = true;//Si existe
                    validarTarea1.nombreIdentificadores(linea, numLinea);//Luego valida el nombre que sigue despues de Program
                }else if(expresion[0].equalsIgnoreCase("INTEGER") || expresion[0].equalsIgnoreCase("REAL") || expresion[0].equalsIgnoreCase("CHARACTER")){
                    validarTarea1.validarIdentificadores(linea, numLinea);
                     
                    if (linea.endsWith(",")) {// No puede quedar una coma al final de una varible MaxAnos,
                    /*El primer valor es el del case, Linea es igual a mensaje,
                    numLinea es donde se encontro el error de la lína analizada*/
                        error = new FormatoErrores(Errores.listaErrores(8, linea), numLinea);
                        TRANSFOR.lista.add(error);//Se agrega en la lista
                    }
                }else if(linea.contains("READ")){//Se comprueba que la linea tiene Read
                    validarTarea2.validarRead(linea, numLinea);//Se envía dos parametros y en el método validarRead se hace la comprobación
                }else if(linea.contains("PRINT")){//Se comprueba que la linea tiene Print
                    llamadaPrint.validarPrint(linea, numLinea);//Se envía dos parametros y en el método validarRead se hace la comprobación
                    
                    if (linea.endsWith(",")) {
                    /*El primer valor es el del case, Linea es igual a mensaje,
                    numLinea es donde se encontro el error de la lína analizada*/
                        error = new FormatoErrores(Errores.listaErrores(8, linea), numLinea);
                        TRANSFOR.lista.add(error);//Se agrega en la lista
                    }
                }else if(linea.contains("GOTO")){//Verificar la estructura correcta de GOTO
                    validarProyecto.validarGoto(linea, numLinea);
                }else if(linea.contains("STOP")){//Verificar la estructura correcta de STOP
                    validarProyecto.cantidadComillaSimple(linea, numLinea);
                    validarProyecto.validarStop(linea, numLinea);
                }else if(expresion[0].contains("DO") && expresion[0].equalsIgnoreCase("DO")){
                    existeDo = true; //Si existe
                    validarProyecto.validarDo(linea, numLinea);
                }else if(expresion[0].contains("ENDDO")){
                    existeEndDo = true; //Si existe
                }else if(expresion[0].contains("IF") && expresion[0].equalsIgnoreCase("IF")){
                    existeIf = true;
                    if (linea.contains("(") || linea.contains(")")) {
                        if (!validarProyecto.validarParentesisAgrupacion(linea)) {
                            error = new FormatoErrores(Errores.listaErrores(33, linea), numLinea);//Imprimir error
                            TRANSFOR.lista.add(error);//Agregar error en la lista
                        }
                    }
                    validarProyecto.validarIf(linea, numLinea);
                }else if(expresion[0].contains("ENDIF")){
                    existeENDIF = true; //Si existe
                }else if(linea.contains("ENDPROGRAM")){//Verificar si la última línea tiene o no el ENDPROGRAM
                    existeEndprogram = true;//Si existe, no muestra el error.
                    validarTarea2.validarENDPROGRAM(linea, numLinea);//Se muestra el error de que  ENDPROGRAM esta repetido
                    validarTarea2.validarENDPROGRAM2(linea, numLinea);//Se muestra el error de que  ENDPROGRAM esta repetido
                }else {
                    if(linea.contains("!")){
                        validarTarea1.esComentario(linea);//Si es comentario se ignora                       
                    } else{
                        if(!buscarpalabra.esReservadaTransfor(expresion[0])){//Verificar que la expresion[0] cuando se envía al método, no sea de TRANSFOR.  
                            if(validarTarea2.validacionID(expresion[0], numLinea)){//ValidacionID es para verificar el identificados se encuentre declarado.
                                //System.out.println("13"); //REVISAR MENSAJE NO DEBE SALIR
                                error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);//Imprimir error
                                TRANSFOR.lista.add(error);//Agregar error en la lista
                            }
                            if(validarTarea2.esVariable(expresion[0])){//Si expresión 0 contiene variable o el ampersand.
                                validarTarea2.unionLinea(linea, numLinea);//Se unen las lineas, esto genera un problema en la lectura del archivo CalcularInflacionBueno.
                                validarTarea2.validarExpresionAlgebraica(linea, numLinea);//Permite obtener lo que se encuentra despues del igual = en la linea del archivo.
                            }
                        } 
                    }
                }
            }
        }   
    }       
}
