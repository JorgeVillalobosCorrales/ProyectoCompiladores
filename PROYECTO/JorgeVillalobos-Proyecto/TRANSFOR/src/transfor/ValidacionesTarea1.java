/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfor;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author Jorge
 */
public class ValidacionesTarea1 {
    FormatoErrores error;
    static boolean esComentario = false;
    Variables sonVaribles;
    Reservadas buscarpalabra = new Reservadas();
    static ArrayList<Variables> listaIdentificadores = new ArrayList<>();//Arreglo de la clase variables
    
    public String limpiarLinea(String linealimpia) {
        linealimpia = linealimpia.replace(")", " ) ");
        linealimpia = linealimpia.replace("(", " ( ");
        linealimpia = linealimpia.replace(",", " , ");
        linealimpia = linealimpia.trim();
        linealimpia = linealimpia.replaceAll(" +", " ");// Espacios redundantes

        return linealimpia;
    }
    
    /*Aceptacion de los blancos y entre lineas
    *Permite espacios en medio de dos palabras Calcular Inflación
    *Los espacios en las columnas 0, 1, 7 y 35
    */
    
    public int lineaBlanca(String linea) {
        int cantidadEspacios = 0;
        if (linea.isEmpty()) {
            return 0;
        }
        try {
            for (int i = 0; i < linea.length(); i++) {
                if (linea.charAt(i) == ' ') {
                    cantidadEspacios += 1;//Permite contar los espacios
                }
            }
        } catch (Exception e) {
            return cantidadEspacios;//Este es de una excepción
        }
        return cantidadEspacios;//Retorna la cantidad de espacios encontrados
    }
    
    public boolean validarCantidadEspacios(int cantidadBlancos) {
        if (cantidadBlancos >= 0 && cantidadBlancos <= 35) {
            return true;
        }
        return false;
    }
    
    public boolean validarEspaciosBlancos(String linea) {
        int cantidadBlancos = lineaBlanca(linea);
        return validarCantidadEspacios(cantidadBlancos);
    }
    
    public boolean validarCantidadLinea(String linea) {
        if(!linea.contains("!")){ //Para no generar error 01 de que la linea supera los 80 caracteres en comentarios
            if (linea.length() <= 80) {//Línea menor a 80 palabras
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    
    public boolean nombreIdentificadores (String linea, int numLinea) {
        linea = limpiarLinea(linea);
        String expresion[] = linea.split(" ");
       
        StringTokenizer token = new StringTokenizer(linea);
        String palabraBuscar = "PROGRAM";
        int program = 0;
        
        while (token.hasMoreTokens()) {
            if(token.nextElement().equals(palabraBuscar)){
                program++;
            }
        }
         
        if (program > 1) {//Si la palabra program aparece dos veces en la misma línea muestra un error
            //System.out.println("1");
            error = new FormatoErrores(Errores.listaErrores(11, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        if(expresion[1].length() <= 30){
        }else{
            error = new FormatoErrores(Errores.listaErrores(2, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        if(expresion[1].startsWith("_")){
            error = new FormatoErrores(Errores.listaErrores(3, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        if(expresion[1].endsWith("_")){
            error = new FormatoErrores(Errores.listaErrores(4, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        if (!Character.isLetter(expresion[1].charAt(0))) {//Si no es letra muestra un error
            error = new FormatoErrores(Errores.listaErrores(5, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        for (int a = 0; a < expresion[1].length(); a++) {
            if (!Character.isLetter(expresion[1].charAt(a)) && !Character.isDigit(expresion[1].charAt(a)) && !String.valueOf(expresion[1].charAt(a)).equals("_")) {
                error = new FormatoErrores(Errores.listaErrores(6, linea), numLinea);
                TRANSFOR.lista.add(error);
                error = new FormatoErrores(Errores.listaErrores(7, linea), numLinea);
                TRANSFOR.lista.add(error);
                break;
            }
        }
        
        return true;
    }
    
    public boolean esComentario(String linea) {
        try {
            linea = linea.replace("!", " ! ");
            linea = linea.replaceAll(" +", " ");
            linea = linea.trim();
            String expresion[] = linea.split(" ");
            if (expresion[0].charAt(0) == '!') {//Si es comentario, se cumple entonces continua.
                esComentario = true;
            } else {
                esComentario = false;
            }
        } catch (Exception e) {
            esComentario = false;
        }
        return esComentario;
    }
    
    public boolean identificadores (String linea, int numLinea) {        
        if(linea.length() > 30){
            error = new FormatoErrores(Errores.listaErrores(2, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        if(linea.startsWith("_")){
            error = new FormatoErrores(Errores.listaErrores(3, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        if(linea.endsWith("_")){
            error = new FormatoErrores(Errores.listaErrores(4, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        if (!Character.isLetter(linea.charAt(0))) {
            error = new FormatoErrores(Errores.listaErrores(5, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        
        for (int a = 0; a < linea.length(); a++) {
            if (!Character.isLetter(linea.charAt(a)) && !Character.isDigit(linea.charAt(a)) && !String.valueOf(linea.charAt(a)).equals("_")) {
                error = new FormatoErrores(Errores.listaErrores(6, linea), numLinea);
                TRANSFOR.lista.add(error);
                error = new FormatoErrores(Errores.listaErrores(7, linea), numLinea);
                TRANSFOR.lista.add(error);
                break;
            }
        }
        
        //Verificar que un identificador no sea una palabra reservada
        if(buscarpalabra.esReservadaFortran(linea)){
            error = new FormatoErrores(Errores.listaErrores(9, linea), numLinea);
            TRANSFOR.lista.add(error);
        }
        return true;
    }
    
    /*
    *El siguiente método verifica que el inicio sea un Tipo de Dato
    *Despues que cumpla con identificador
    *Si la , esta al final y no hay un identificador, se muestra el mensaje.
    *Se identifican de mayor a menor por el tamaño de la linea del archivo .transfor donde estan las variables
    */
    
    public boolean validarIdentificadores(String linea, int numLinea) {
        boolean identificadorValido = false;
        linea = limpiarLinea(linea);//Se usa el formateo de la línea
        System.out.println("Validar Identificadores: " + linea);
        String expresion[] = linea.split(" ");      
        try {
            if (identificadores(expresion[1], numLinea)) {
                sonVaribles = new Variables(expresion[0], expresion[1], "0");//Almacenar los datos en la clase variable dentro la variable que esta como valor 0
                listaIdentificadores.add(sonVaribles);//Se agrega en una lista de identificadores
                identificadorValido = true;
            }else {
                identificadorValido = false;
            }
            
            if (identificadores(expresion[3], numLinea)) {
                sonVaribles = new Variables(expresion[0], expresion[3], "0");//Almacenar los datos en la clase variable dentro la variable que esta como valor 0
                listaIdentificadores.add(sonVaribles);//Se agrega en una lista de identificadores
                identificadorValido = true;
            }else {
                identificadorValido = false;
            }
            
            if (identificadores(expresion[5], numLinea)) {
                sonVaribles = new Variables(expresion[0], expresion[5], "0");//Almacenar los datos en la clase variable dentro la variable que esta como valor 0
                listaIdentificadores.add(sonVaribles);//Se agrega en una lista de identificadores
                identificadorValido = true;
            }else {
                identificadorValido = false;
            }
        } catch (Exception ex) {
        }
//        for (int i = 0; i < listaIdentificadores.size(); i++) {
//            System.out.println("ARREGLO CONTIENE "+ i + ": "+listaIdentificadores.get(i).getNombreIdentificador());
//        }
        return identificadorValido;//Se cumplio con los identificadores
    }
    
//    public void validarAmpersand(String linea) {
//        String ampersand;
//        if(linea.length() == 73){
//            if (linea.contains("&") || linea.contains(" ")) {
//                int pos = linea.indexOf("&");
//                System.out.println(pos+1);
//                System.out.println("Es correcto");
//            }else{
//                System.out.println("No es correcto");
//            }
//        }else{
//            System.out.println("No es correcto");
//        }
//    }
}
