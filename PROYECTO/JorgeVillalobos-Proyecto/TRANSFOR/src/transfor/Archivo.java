/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfor;
import java.io.File;
/**
 *
 * @author Jorge
 */
public class Archivo {
   public static boolean validarNombre (String nombre) {
        if (!nombre.endsWith(".transfor")) {
            System.out.println("       Error 000: El nombre del archivo no tiene una extensión correcta.");
        }
        
        nombre = nombre.replace(".transfor", "");//Eliminar la extensión del archivo
        if(nombre.length() <= 30){//Validar que el tamaño del nombre no sea mayor a 30
        }else{
            System.out.println("       Error 001: El nombre del archivo tiene que ser menor a 30 dígitos.");
        }
        
        if(nombre.startsWith("_")){//Inicio de la raya en el nombre
            System.out.println("       Error 002: El nombre del archivo no debe empezar con guion bajo.");
        }
        
        if(nombre.endsWith("_")){// Final del nombre el simbolo es raya, muestra el error. 
            System.out.println("       Error 003: El nombre del archivo no debe terminar con guion bajo.");
        }
        
        if (Character.isLetter(nombre.charAt(0))) {//Siempre debe iniciar con letra el nombre del archivo
        } else {
            System.out.println("       Error 004: El nombre del archivo debe iniciar con una letra.");
        }
        
        /*
        Se recorre todo el nombre para verificar si empieza con letra, tiene un número
        Tambien permite el guión, exceptuando la regla del inicio y final
        */
        
        for (int a = 0; a < nombre.length(); a++) {
            if ((Character.isLetter(nombre.charAt(a)) || Character.isDigit(nombre.charAt(a))) || String.valueOf(nombre.charAt(a)).equals("_")) {
            } else {
                System.out.println("       Error 005: El nombre del archivo solo permite letras, números o guion bajo.");
                System.out.println("       Error 006 : El nombre del archivo no permite el uso de caracteres especiales.");
                break;
            }
        }
        return true;//Si retorno un true es por que todo se cumplio
    }
    
    public static boolean existeArchivo (String nombre) {
        if(nombre.contains(".")){// Si trae el punto es por que tiene la extensión
        } else {
            nombre += ".transfor";//Si no tiene el . se le agrega la extensión.
        }
        
        File file = new File(nombre);
        
        if (!file.exists()) {//Si el nombre del archivo no existe se agrega el mensaje
            System.out.println("       Error 007: El Archivo no existe.");
            return false;
        }
        return true;//Corre el archivo
    }
}
