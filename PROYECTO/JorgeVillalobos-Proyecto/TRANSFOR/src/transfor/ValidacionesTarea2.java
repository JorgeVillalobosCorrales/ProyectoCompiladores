/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfor;

import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.script.ScriptEngine;//Librerias adicionales para hacer comprobaciones
import javax.script.ScriptEngineManager;//Librerias adicionales para hacer comprobaciones
import static transfor.ValidacionesTarea1.listaIdentificadores;

/**
 *
 * @author Jorge 
 */
class ValidacionesTarea2 {
    ValidacionesTarea1 llamarValidaciones = new ValidacionesTarea1();
    FormatoErrores error;
    private static ScriptEngineManager resul = new ScriptEngineManager();
    private static ScriptEngine motor = resul.getEngineByName("js");
    static ArrayList<Etiquetas> listaEtiquetas = new ArrayList<Etiquetas>();
    
    public boolean validarENDPROGRAM (String linea, int numLinea){//Recibe dos parametros 
        boolean repetirEndprogram = false;//Inica en false por que endprogram se encuentra dos veces
        
        StringTokenizer token = new StringTokenizer(linea);
        String palabraBuscar = "ENDPROGRAM";
        int EndProgram = 0;
        
        while (token.hasMoreTokens()) {
            if(token.nextElement().equals(palabraBuscar)){
                EndProgram++;  
            }
        }
        
        if (EndProgram > 1) {//Si la palabra Endprogram aparece dos veces en la misma línea muestra un error
          //  System.out.println("1");
            error = new FormatoErrores(Errores.listaErrores(22, linea), numLinea);
            TRANSFOR.lista.add(error);
            repetirEndprogram = false;//Se encontro endprogram 2 veces
        }
        
        return repetirEndprogram;
    }
    
    public boolean validarENDPROGRAM2 (String linea, int numLinea){//Recibe dos parametros 
        boolean repetirEndprogram = false;//Inica en false por que endprogram se encuentra dos veces
        
        String palabraBuscar = "ENDPROGRAM";
        int EndProgram = 0;
        for (int i = 0; i < linea.length(); i++) {
            StringTokenizer token = new StringTokenizer(linea);
            while (token.hasMoreTokens()) {
                if(token.nextElement().equals(palabraBuscar)){
                    EndProgram++;
                }
                break;
            }
        }
        
        if (EndProgram > 1) {//Si la palabra Endprogram aparece dos veces en líneas diferentes muestra un error
           // System.out.println("2");
            error = new FormatoErrores(Errores.listaErrores(22, linea), numLinea);
            TRANSFOR.lista.add(error);
            repetirEndprogram = false;//Se encontro endprogram 2 veces
        }
        
        return repetirEndprogram;
    }
    
    public boolean validarEtiqueta(String etiqueta, int numLinea) {//Recibe dos parametros 
        boolean esEtiqueta = false;//Declaración de variable
        try {
            for (Etiquetas id : listaEtiquetas) {//For de la clase Etiquetas, para almacenar con ID
                if (id.getEtiqueta().equalsIgnoreCase(etiqueta)) {//si el id.getEtiqueta ess igual a etiqueta que se recibe por parametro
                    error = new FormatoErrores(Errores.listaErrores(21, etiqueta), numLinea);//Muestra el error, no pueden existir dos etiquetas repetidas
                    TRANSFOR.lista.add(error);
                    esEtiqueta = false;
                    
                }else{
                    esEtiqueta = true;//Etiqueta no esta la lista 10000, 
                }
            }
            
            if(esEtiqueta == false){
                if(etiqueta.matches("^[0-9]{1,5}$")){
                    listaEtiquetas.add(new Etiquetas(etiqueta));
                    esEtiqueta = true;//Aquí se guarda la etiqueta
                }else{
                    esEtiqueta = false;//Si hay problema muestra el error.
                }
            }
            
        } catch (Exception e) {
            esEtiqueta = false;//Si hay problema muestra el error.
        }
        return esEtiqueta;//False si hay error y true si no hay error.
    }

    //Verificar que variable exista, definido anteriormente
    public boolean esVariable(String identificador) { //Validar que la variable debe haber sido definida previamente
        boolean esVariable = false;
        for (Variables id : listaIdentificadores) {
            if (id.getNombreIdentificador().equalsIgnoreCase(identificador)) {
                esVariable = true;
            }
        }
        return esVariable;//True para verificar si es variable
    }
    
    public boolean validarRead(String linea, int numLinea) {
        boolean readValido = false;
        linea = llamarValidaciones.limpiarLinea(linea);//Se usa el formateo de la línea
        String expresion[] = linea.split(" ");
        System.out.println("Hola: " + linea);
        try {
            if(validarEtiqueta(expresion[0], numLinea) == true){//Primero se validan las etiquetas 10080
                //System.out.println("Bueno");
                //Se recomienda siempre el comando Read en la posición 1 del archivo
                if(expresion[1].equalsIgnoreCase("READ*") && expresion[2].equals(",") && esVariable(expresion[3])){ //Tiene la sintaxis correcta
                    readValido = true;
                }else if(expresion[1].equalsIgnoreCase("READ") && expresion[2].equals("*") && expresion[3].equals(",") && esVariable(expresion[4])){ 
                                    //READ y el asterisco deben venir pegados sin blancos entre ellos.
                    error = new FormatoErrores(Errores.listaErrores(12, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    readValido = false;
                }else if (expresion[1].equalsIgnoreCase("READ*") && expresion[2].equals(",") && !esVariable(expresion[3])) {// La variable debe haber sido definida previamente.
                    error = new FormatoErrores(Errores.listaErrores(13, linea), numLinea);
                    TRANSFOR.lista.add(error);
                    readValido = false;
                }else{
                    error = new FormatoErrores(Errores.listaErrores(14, linea), numLinea);//Muestra error de formato de ,
                    TRANSFOR.lista.add(error);
                    readValido = false;
                }
            }else{
                error = new FormatoErrores(Errores.listaErrores(20, linea), numLinea);//Error formato incorrecto de etiquetas
                TRANSFOR.lista.add(error);
            }
        } catch (Exception e) {
        }
        return readValido;//True no hay ningun problema y paso bien en el if, pero si es false paso por diferentes else
    }

    //Este método es para unir la linea del & aunque no esta del todo bien jeje
    public String unionLinea(String linea, int numLinea) {
        String cadena1 = "";
        String cadena2 = "";
        
        if(linea.endsWith("&")){
            cadena1 = linea.trim();
        }
        
        if(linea.startsWith("&")){
            cadena2 = linea.trim();
        }
        
        linea = cadena1.concat(cadena2).trim();

        return linea;//Se guarda la unión de la línea
        
    }
    
    //Este método sirve para leer del = hasta el final de la linea ejemplo ANO = 0 va a leer del 0 en adelante
    public String extraerPatron(String linea, int numLinea) {
        try {
            linea = linea.substring(linea.lastIndexOf("=") + 1).trim();//Se obtiene despues del igual por medio del ultimo indice
        } catch (Exception e) {
        }
        return linea;
    }
    
    //Agregar el valor de la asignación de variables si ANO = 1 el valor a asignar va a ser ese 1
    public static String otorgarValorVariable(String palabra, String valor) {
        for (Variables id : listaIdentificadores) {
            if (id.getNombreIdentificador().equalsIgnoreCase(palabra)) {
                valor = contieneVariable(valor);//Tiene el valor obtenido al inicio
                id.setValorIdentificador(valor);//Este valor esta cambiando por eso se usa en set
            }
        }
        return valor;//Retorna el valor del set
    }
    
    //Este método se llama en el de arriba
    public static String contieneVariable(String palabra) {
        for (Variables id : listaIdentificadores) {
               
            if (palabra.contains(id.getNombreIdentificador())) {//Si la palabra que recibe la contiene la lista
                palabra = palabra.replace(id.getNombreIdentificador(), id.getValorIdentificador());
                //Se reemplaza el nombre por el valor que tenia antes getNombreIdentificador
            }
         //   System.out.println(id.getTipoDato() + " " + id.getNombreIdentificador() + " " + id.getValorIdentificador());
         //Este print me muestra los cambio cuando una o varias variables, cambia su valor
        }
        return palabra;//Retorna el nuevo valor
    }
    
    //sirve para verificar que las variables existan, esten definidas anteriormente
    public boolean validacionID (String linea, int numLinea){
        boolean variable = false;
        linea = linea.trim();
        linea = linea.replace(" ","");
        linea = linea.replaceAll(" +", " ");
        String expresion[]= linea.split(" ");
        
        for (int i = 0; i < expresion.length; i++) {
            if (esVariable(expresion[i])) {//Llamo un método y verifico lo que contiene la línea
                variable = false; // Si existe la variable
                break;//Salir del for
            }else{
                variable= true; // no existe la variable
            }
        }
        return variable;
    }
    
    //Siempre use lo del cuatri pasasdo pero hay unas cosas que debo validar aún, más que todo es para validar que la exresion algebraica esté bien escrita
    public boolean validarExpresionAlgebraica(String linea, int numLinea) {
        boolean esExpresion = false;
        try {
            linea = linea.replace(")", " ) ");
            linea = linea.replace("(", " ( ");
            linea = linea.replace("=", " = ");     
            linea = linea.replace("&", "");
            linea = linea.replaceAll(" +", " ");
            linea = linea.trim();

            String expresion[] = linea.split(" ");
            //System.out.println(linea);
            
            if (esVariable(expresion[0])) {//Llama un método y le envía la expresión
                String patron = "";
                patron = extraerPatron(linea, numLinea);//Método que extrae lo que se encuetra despues del igual
                patron = patron.trim();
                System.out.println("Patron" + patron);
                
                Object resultado = null;
                patron = otorgarValorVariable(expresion[0], patron);//Metodo que cambia el valor anterior de la variable por el nuevo
                for (Variables listaIdentificadores : listaIdentificadores) {
                    motor.put(listaIdentificadores.getNombreIdentificador(), listaIdentificadores.getValorIdentificador());
                    //Se agregan para obtener un resultado.
                    //System.out.println(listaIdentificadores.getTipoDato() + " " + listaIdentificadores.getNombreIdentificador() + " " + listaIdentificadores.getValorIdentificador());
                }
                try {
                    if(patron.contains("&")){
                        patron = patron.replace("&", "");
                        resultado = motor.eval(patron);//Obtengo los nuevos valores 0   1.0  1.0
                    }
                    //System.out.println("result: " + patron);
                } catch (Exception e) {
                    error = new FormatoErrores(Errores.listaErrores(19, linea), numLinea);//Error expresión algebraica contiene errores
                    TRANSFOR.lista.add(error);
                }
            }
        } catch (Exception e) {
            esExpresion = false;//Manda un error
        }
        return esExpresion;//Si esta true no muestra errores, si es false muestra los errores
    }
}
//^ {5}$ 5 espacios en blanco al inicio
