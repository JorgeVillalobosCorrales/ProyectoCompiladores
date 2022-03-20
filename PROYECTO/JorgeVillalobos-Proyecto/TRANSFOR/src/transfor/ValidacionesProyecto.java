/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfor;

import static transfor.ValidacionesTarea1.listaIdentificadores;
import static transfor.ValidacionesTarea2.listaEtiquetas;

/**
 *
 * @author Jorge
 */
public class ValidacionesProyecto {
    ValidacionesTarea1 llamarValidaciones = new ValidacionesTarea1();
    ValidacionesTarea2 llamarValidaciones2 = new ValidacionesTarea2();
    ComandoPrint llamarValidacion = new ComandoPrint();
    Reservadas buscarpalabra = new Reservadas();
    FormatoErrores error;
    
    public boolean esEtiqueta(String etiqueta) { //Validar que la variable debe haber sido definida previamente
        boolean esEtiqueta = false;
        for (Etiquetas id : listaEtiquetas) {
            if (id.getEtiqueta().equalsIgnoreCase(etiqueta)) {
                esEtiqueta = true;
            }
        }
        return esEtiqueta;//True para verificar si es variable
    }
    
    public boolean validarEtiquetaGoto(String etiqueta, int numLinea) {//Recibe dos parametros 
        boolean esEtiqueta = false;//Declaración de variable
        try {
            if(etiqueta.matches("^[0-9]{1,5}$")){
                listaEtiquetas.add(new Etiquetas(etiqueta));
                esEtiqueta = true;//Aquí se guarda la etiqueta
            }else{
                esEtiqueta = false;//Si hay problema muestra el error.
            }
            
        } catch (Exception e) {
            esEtiqueta = false;//Si hay problema muestra el error.
        }
        return esEtiqueta;//False si hay error y true si no hay error.
    }
    
    public boolean validarGoto(String linea, int numLinea) {
        boolean GotoValido = false;
        linea = llamarValidaciones.limpiarLinea(linea);//Se usa el formateo de la línea
        String expresion[] = linea.split(" ");
        try {         
            if (expresion.length <= 1) {//Si aparace GOTO solo sin la etiqueta.
                error = new FormatoErrores(Errores.listaErrores(24, linea), numLinea);//Error formato incorrecto de etiquetas
                TRANSFOR.lista.add(error);
                GotoValido = false;
            }else{
                if(validarEtiquetaGoto(expresion[1], numLinea) == true){
                    GotoValido = true;
                }else {
                    error = new FormatoErrores(Errores.listaErrores(20, linea), numLinea);//Error formato incorrecto de etiquetas
                    TRANSFOR.lista.add(error);
                    GotoValido = false;
                }

                if(!GotoValido){
                    if(expresion[0].equalsIgnoreCase("GOTO") && esEtiqueta(expresion[1])){
                        GotoValido = true;
                    }else{
                        error = new FormatoErrores(Errores.listaErrores(23, linea), numLinea);//Error formato incorrecto de etiquetas
                        TRANSFOR.lista.add(error);
                        GotoValido = false;
                    }
                }
            }
            
        } catch (Exception e) {
        }
        return GotoValido;
    }
    
    public boolean cantidadComillaSimple(String linea, int numLinea) {
        boolean comillaValida = false;
        int contador = 0;
        try {
            linea = linea.trim();
            for (int i = 0; i < linea.length(); i++) {
                if(linea.contains("'")){
                    contador++;
                }
            }
            
            if (contador % 2 == 0) {//Si encuentran 2 apostrofes leyendo la línea esta bien el formato.
                comillaValida = true;
            } else {//Falta una comilla por lo tanto muestra un error.
                error = new FormatoErrores(Errores.listaErrores(25, linea), numLinea);//Error formato incorrecto de etiquetas
                TRANSFOR.lista.add(error);
                comillaValida =  false;
            }
        } catch (Exception e) {
        }
        return comillaValida;
    }
    
    public boolean validarStop(String linea, int numLinea) {
        boolean stopValido = false;
        linea = llamarValidaciones.limpiarLinea(linea);
        linea = linea.replace("'", " ' ");
        linea = linea.replaceAll(" +", " ");
        linea = linea.trim();
        String expresion[] = linea.split(" ");
        //System.out.println(linea);
        try {
            if(expresion[0].equalsIgnoreCase("STOP") && expresion[1].equals("'")){ //Tiene la sintaxis correcta
                linea = llamarValidacion.validarMensajePRINT(linea);//Valida la linea
                if (linea.equalsIgnoreCase("Error")) {//Se verifica el error
                    error = new FormatoErrores(Errores.listaErrores(26, linea), numLinea);//Aquí se imprime.
                    TRANSFOR.lista.add(error);
                }
                //System.out.println("entra en con mensaje");
                stopValido = true;
            }else if(expresion[0].equalsIgnoreCase("STOP")){ //Tiene la sintaxis correcta
                //System.out.println("entra solo stop");
                stopValido = true;//Permite finalizar el programa
            }
        } catch (Exception e) {
        }
        return stopValido;
    }
    
    //Método para eliminar el DO
    public String extraerDo(String linea) {
        try {
            linea = linea.replace("DO", "");
            linea = llamarValidaciones.limpiarLinea(linea);
            System.out.println("La linea sin DO tiene: " + linea);
        } catch (Exception e) {
        }
        return linea;
    }
    
    //Método para obtener el contenido entre el DO hasta el igual "="
    public String extraerDoHastaIgual(String linea) {
        try {
            int empezar = linea.indexOf("DO");
            int terminar = linea.indexOf("=", empezar);
            linea = linea.substring(empezar + 1, terminar);// Captura la variable que este en medio de Do y el igual
            
        } catch (Exception e) {

        }
        return linea;
    }
    
    //Método para obtener el contenido entre el igual "=" hasta la coma ","
    public String extraerIgualHastaComa(String linea) {
        try {
            int empezar = linea.indexOf("=");
            int terminar = linea.indexOf(",", empezar);
            if (empezar != -1 && terminar != -1) {
                linea = linea.substring(empezar + 1, terminar);
            } else if (terminar == -1) {
            }
        } catch (Exception e) {
        }
        return linea;
    }
    
    //Método para obtener el contenido entre la coma "," hasta el final de la línea
    public String extraerComaHastaFinal(String linea) {
        try {
            linea = linea.substring(linea.lastIndexOf(",") + 1).trim();
        } catch (Exception e) {
        }
        return linea;
    }
    
    //Validar si la el dato a pasar por parámetro es entero, constante minima 
    public boolean validarEntero(String linea, int numLinea) {
        boolean resultado = false;
        try {
            linea = linea.trim();
            if(!linea.matches("[0-9]+")){//Cualquier número del 1 al 9 siendo entero ejemplo 10,15,6
                error = new FormatoErrores(Errores.listaErrores(28, linea), numLinea);//Imprimir error
                TRANSFOR.lista.add(error);
                resultado = false;
            }
        } catch (Exception e) {
        }
        return resultado;//Si devuelve false es por que hay error, si no detecta el error lo devuelve en True
    }
    
    //Que la variable este definida anteriormente
    public boolean esVariables(String linea, int numLinea) {
        boolean variableValido = false;
        linea = linea.trim();//Quitar espacios
        
        for (Variables variable : listaIdentificadores) {
            //Este ciclo se recorre varias veces si la lista tiene varios elementos 3,5,7
            if (variable.getNombreIdentificador().equalsIgnoreCase(linea)) {
                variableValido = true;
                break;//Se sale del for si se encuentra la variable igual al nombre identificador
            }else{
                variableValido = false;//Como la variable es diferente a un identificador valido queda en false y va a otro if
            }
        }
        
        if(variableValido == false){//En este if muestra el error si el booleano es falso 
            error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);//Imprimir error
            TRANSFOR.lista.add(error);
        }
        
        return variableValido;
    }

    public boolean validaTipo(String linea, int numLinea) {
        boolean esTipo = false;
        linea = linea.trim();
        for (Variables variables : listaIdentificadores) {//Variable de la clase para obtener los métodos
            //Se valida el identificador que sea igual a lo que se recibe en la linea y el tipo de dato.
            if (variables.getNombreIdentificador().equalsIgnoreCase(linea) && variables.getTipoDato().equals("INTEGER")) {
                esTipo = true;
                //Como el tipo de dato es true continua de forma correcta
            } else if (variables.getNombreIdentificador().equalsIgnoreCase(linea) && !variables.getTipoDato().equals("INTEGER")) {
                esTipo = false;//Tipo de dato false por lo tanto pasa al if
            }
        }
        
        if(esTipo == false){
            error = new FormatoErrores(Errores.listaErrores(27, linea), numLinea);//Imprimir error
            TRANSFOR.lista.add(error);
        }
    
        return esTipo;
    }
    
    //valida si es constante o variable
    public boolean validaConstanteVariable(String linea, int numLinea) {
        boolean esConstanteVariable = false;
        System.out.println("Const o Variable: "+ linea);
        if(linea.matches("[+-]?\\d*(\\.\\d+)?")){ //DO ANO = 1, MAX_ANOS
            validarEntero(linea, numLinea);//Si se cumple este del método el boolean siguiente cambia true-
            esConstanteVariable = true;
        }else { //DO ANO = MAX_ANOS, MAX_ANOS
            esVariables(linea, numLinea);//Se llama el método, para comprobar si es variable.
            validaTipo(linea, numLinea);//Se valida que la varible sea tipo Integer
            esConstanteVariable = true;
        }
    
        return esConstanteVariable;
    }
    
    //DO ANO = 1, MAX_ANOS
    public boolean validarDo(String linea, int numLinea) {
        boolean doValido = false;
        linea = llamarValidaciones.limpiarLinea(linea);
        String expresion[] = linea.split(" ");
       // System.out.println("Entra: " + linea);
        
        try {
            if(linea.contains("=") && linea.contains(",")){
                doValido = true;
            }else if(!linea.contains("=") && linea.contains(",")){
                error = new FormatoErrores(Errores.listaErrores(29, linea), numLinea);//Imprimir error
                TRANSFOR.lista.add(error);
                doValido = false;
            }else if(linea.contains("=") && !linea.contains(",")){
                error = new FormatoErrores(Errores.listaErrores(30, linea), numLinea);//Imprimir error
                TRANSFOR.lista.add(error);
                doValido = false;
            }else if(!linea.contains("=") && !linea.contains(",")){
                error = new FormatoErrores(Errores.listaErrores(29, linea), numLinea);//Imprimir error
                TRANSFOR.lista.add(error);
                error = new FormatoErrores(Errores.listaErrores(30, linea), numLinea);//Imprimir error
                TRANSFOR.lista.add(error);
                doValido = false;
            }
            
            if(doValido == true){
                String dato = linea = extraerDo(linea);
                linea = linea.trim();
                String dato1 = extraerDoHastaIgual(expresion[1]);//Extrae año o variable tipo Integer expresión uno es año
                //System.out.println("Dato1: "+dato1);
                String dato2 = extraerIgualHastaComa(expresion[3]);//Extrae constante 1,2,3,0 o variable, expresión es 1 en el archivo o puede cambiar a variable
                //System.out.println("Dato2: "+dato2);
                String dato3 = extraerComaHastaFinal(linea);
                //Extrae año o variable tipo Integer que se encuentra despues de la , MAX_ANOS
                //System.out.println("Dato3: "+dato3);

                if(expresion[0].equalsIgnoreCase("DO") && (esVariables(dato1, numLinea) &&
                   validaTipo(dato1, numLinea)) && expresion[2].equals("=") && validaConstanteVariable(dato2, numLinea) &&
                   expresion[4].equals(",") && (esVariables(dato3, numLinea) && validaTipo(dato3, numLinea))){
                    doValido = true;
                }
            }
        } catch (Exception e) {
        }
        return doValido;
    }
    
    public boolean validarParentesisAgrupacion(String linea) {
        int abre = 0;
        int cierra = 0;
        boolean aperturaParentesis = false;
        for (int i = 0; i < linea.length(); i++) {
            if (linea.contains("(") && linea.contains(")")) {
                if (linea.charAt(i) == '(') {
                    cierra++;//Aumenta contador si encontro uno o mas parentesis de cierre
                    aperturaParentesis = true;
                } else if (linea.charAt(i) == ')') {
                    aperturaParentesis = false;
                    abre++;//Aumenta contador si encontro uno o mas parentesis de inicio
                }
            } else if (linea.contains("(") && !linea.contains(")")) {
                if (linea.charAt(i) == '(') {
                    cierra++;
                    aperturaParentesis = true;
                } else if (linea.charAt(i) == ')') {
                    aperturaParentesis = false;
                    abre++;
                }
            } else if (!linea.contains("(") && linea.contains(")")) {
                if (linea.charAt(i) == '(') {
                    cierra++;
                    aperturaParentesis = true;
                } else if (linea.charAt(i) == ')') {
                    aperturaParentesis = false;
                    abre++;
                }
            }
        }
        if (abre != cierra) {// Si son diferentes muestra un error que se imprime en la clase AnalisisLexico
            return false;
        } else {
            return true;//Si el if(abre != cierra) es igual los valores de abre y cierre, no hay errores, la estructura es correcta.
        }
    }
    
    public String [] operadoresRelacionales = {".EQ.", ".NE.", ".LT.", ".GT.", ".LE.", ".GE.", "=", "/=", "<", ">", "<=", ">="};
    
    public boolean validarOperadorRelacional(String linea, int numLinea) {
        boolean palabraEncontrada = false;

        for (String palabra : operadoresRelacionales) {
            if (palabra.equals(linea)) {
                //Si palabra es igual a la palabra que recibe por referencia, es un tipo de dato
                palabraEncontrada = true;//Si se cumple que retorne verdadero
                break;
            }else{
                palabraEncontrada = false;
            }
        }
        
        if(palabraEncontrada == false){
            error = new FormatoErrores(Errores.listaErrores(34, linea), numLinea);//Imprimir error
            TRANSFOR.lista.add(error);
        }
        
        return palabraEncontrada;
    }
    
    public boolean validaVariableFortran(String linea, int numLinea){
        /*
         *  IF ( MAX_ANOS .LE. 0 ) THEN
         *  STOP '*** FIN DEL PROGRAMA PORQUE INDICO 0 ANOS ***'
         *  GOTO 99999
         *  ENDIF
        */
        
        boolean esVariableFortran = false;
        linea = llamarValidaciones.limpiarLinea(linea);
        linea = linea.trim();
        //System.out.println(linea);
        if(buscarpalabra.esReservadaFortran(linea)){
            esVariableFortran = true;//Por ejemplo si son palabras reservadas de Fortran "Logical,etc" continua.
        }else{
            esVariables(linea, numLinea);//Se evalua si es una variable en caso de ingresar en la variable linea
            esVariableFortran = true;
        }
        return esVariableFortran;
    }
    
    //IF ( MAX_ANOS .LE. 0 ) THEN
    public boolean validarIf(String linea, int numLinea) {
        boolean ifValido = false;
        linea = llamarValidaciones.limpiarLinea(linea);
        linea = linea.trim();
        String expresion[] = linea.split(" ");
        //System.out.println("Entra: " + linea);
        
        try {
            if(!linea.contains("THEN")){
                error = new FormatoErrores(Errores.listaErrores(35, linea), numLinea);//Imprimir error
                TRANSFOR.lista.add(error);
                ifValido = false; 
            }else if(linea.contains("THEN")){
                ifValido = true; 
            }
            if(ifValido == true){
                if(expresion[0].equalsIgnoreCase("IF") && expresion[1].equals("(")
                    && validaVariableFortran(expresion[2], numLinea) &&
                    validarOperadorRelacional(expresion[3], numLinea) && expresion[4].equalsIgnoreCase("0")
                    && expresion[5].equals(")") && expresion[6].equalsIgnoreCase("THEN")){
                    ifValido = true;
                }
            }
           
        } catch (Exception e) {
        }
        return ifValido;
    }
}
