/** *******************************************************
 * Nombre del programa: Sopa de Letras.
 * Descripción:Mediante rutinas, arreglos y matrices, crear un juego que se 
 * llama sopa de letras. 
 * Fecha de creación: 15-11-2019.
 * Autor: Johan Araya González.
 * Fecha de modificación:17-12-2019.
 * Modificado por: Ale Garcia, Johan Araya, Sebastian Moya.
 */
package com.cenfotec.proyecto;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class CapaLogica {

    public static int tamanno = 12;
    public static String[][] sopa = new String[tamanno][tamanno];
    public static Character[] letras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    
     //Este metodo toma un set de palabras y los filtra de acuerdo al tamaño
     //especificado

    public static String[] filtrarPalabras(int cantidadPalabrasFiltrar, String[] palabrasSolicitadas) {
        //Aca creamos un arreglo del tamaño de las palabras que queremos retornar
        //este arreglo contiene las palabras ya filtradas
        String[] palabrasFiltradas = new String[cantidadPalabrasFiltrar];
        ArrayList indices = new ArrayList();

        //Vamos a iterar de acuerdo a la cantidad de palabras que queremos filtrar
        for (int i = 0; i < cantidadPalabrasFiltrar; i++) {
            int idx = darIndiceValido(indices, palabrasSolicitadas.length);
            //Aqui tomamos la palabra que ingreso el usuario de acuerdo
            //al numero random que encontramos
            palabrasFiltradas[i] = palabrasSolicitadas[idx];
        }

        //Aca tenemos ya el arreglo con las palabras a ingresar en la sopa de letras
        return palabrasFiltradas;
    }

    public static int darIndiceValido(ArrayList indices, int longitud) {
        boolean esValido = false;
        int indiceValido = 0;

        while (esValido == false) {
            //Vamos a seleccionar un numero random para buscar la palabra que queremos mover

            //Vamos a definir que el numero random que queremos buscar sea entre 0 y el tamanno 
            //del arreglo que queremos filtrar para asi buscar en todo el arreglo por igual
            int idx = darIndiceRandom(longitud);

            esValido = !indices.contains(idx);
            if (esValido) {
                indiceValido = idx;
                indices.add(indiceValido);
            }
        }

        return indiceValido;
    }

    public static int darIndiceRandom(int mayor) {
        Random rd = new Random();
        int numero = rd.nextInt(mayor);
        return numero;
    }

    public static boolean ingresarCaracteres(int x, int y, Direccion direccion, String palabra) {

        char[] letras = palabra.toCharArray();
        char[] letrasIngresar = new char[letras.length];
        int[] letrasX = new int[letras.length];
        int[] letrasY = new int[letras.length];

        boolean esValida = true;
        //validar toda la palabra
        int i = 0;
        do {
            char letra = letras[i];

            if (Direccion.HORIZONTAL_DERECHA.equals(direccion)
                    || Direccion.HORIZONTAL_IZQUIERDA.equals(direccion)
                    || Direccion.DIAGONAL_DERECHA_ABAJO.equals(direccion)
                    || Direccion.DIAGONAL_DERECHA_ARRIBA.equals(direccion)) {
                y++;
            }

            if (y < sopa.length && x < sopa.length) {
                esValida = validarCampoMatriz(x, y, letra);
                if (esValida) {
                    //guardar en lugares preventivos a errores y no ensuciar la sopa
                    letrasX[i] = x;
                    letrasY[i] = y;
                    letrasIngresar[i] = letra;

                }

            } else {
                esValida = false;
            }
            if (Direccion.VERTICAL_ARRIBA.equals(direccion)
                    || Direccion.VERTICAL_ABAJO.equals(direccion)
                    || Direccion.DIAGONAL_DERECHA_ABAJO.equals(direccion)
                    || Direccion.DIAGONAL_DERECHA_ARRIBA.equals(direccion)) {
                x++;
            }

            i++;


        } while (esValida && i < letras.length && x <= sopa.length && y <= sopa.length);

        if (esValida) {
            for (int j = 0; j < letras.length; j++) {
                char datoLetra = letrasIngresar[j];
                int datoX = letrasX[j];
                int datoY = letrasY[j];
                sopa[datoX][datoY] = "" + datoLetra;
            }

        }
        return esValida;
    }

    public static boolean validarCampoMatriz(int x, int y, char letra) {
        boolean valido = false;
        String letraSopa = sopa[x][y];
        if (letraSopa == null) {
            valido = true;
        }
        if (letraSopa != null && letraSopa.equals(letra)) {
            valido = true;
        }
        return valido;
    }

    public static void llenarMatriz(String palabras[]) {
        boolean valido = true;
        Direccion[] direcciones = Direccion.values();
        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];

            //ciclo para buscar un punto valido y direccion
            do {
                int idx = darIndiceRandom(direcciones.length);
                Direccion direccion = direcciones[idx];
                palabra = modificarPalabra(palabra, direccion);
                int x = darIndiceRandom(tamanno);
                int y = darIndiceRandom(tamanno);
                //mientras que no sea valido va a buscar una direecion correcta
                valido = !ingresarCaracteres(x, y, direccion, palabra);

            } while (valido);
        }
    }

    public static String modificarPalabra(String palabra, Direccion direccion) {
        //descomponga la palabra en letras le doy vuelta
        if (Direccion.HORIZONTAL_IZQUIERDA.equals(direccion)
                || Direccion.VERTICAL_ARRIBA.equals(direccion)
                || Direccion.DIAGONAL_DERECHA_ARRIBA.equals(direccion)) {
            palabra = new StringBuilder(palabra).reverse().toString();
        }

        return palabra.toLowerCase();
    }

         
     // Realizamos distintas validaciones sobre la palabra que queremos utilizar.
     
    public static void validarPalabra(String palabra) {

        if (palabra == null) {
            throw new RuntimeException("Ingrese una palabra valida");
        }
        boolean tieneEspacios = contieneEspacios(palabra);
        if (tieneEspacios == true) {
            throw new RuntimeException("La palabra no debe contener espacios");
        }
        boolean esPalabra = esPalabra(palabra);
        if (esPalabra == false) {
            throw new RuntimeException("Deben ser solo palabras");
        }

        if (palabra.length() < 2) {
            throw new RuntimeException("La palabra debe ser mayor que 1 caracter");
        }
        if (palabra.length() >= 12) {
            throw new RuntimeException("La palabra debe ser menor que 12 caracteres");
        }

    }

    public static boolean esPalabra(String palabra) {

        return Pattern.matches("[a-zA-Z]*", palabra);
    }

    public static boolean contieneEspacios(String palabra) {
        boolean valida = false;

        //Convertimos la palabra en un arreglo de letras
        char[] letras = palabra.toCharArray();
        int longitud = letras.length;
        int i = 0;

        //Vamos a recorrer todas las letras hasta encontrar el error
        //Tenemos que recorrerlo siempre y cuando la longitud sea mayor al indice
        //cuando la longitud no sea igual al indice, esto para cerrar la ejecucion 
        //y evitar el enciclado y cuando no haya encontrado errores, si aparece 
        //un error terminamos la ejecucion e indicaria que hay un error
        while (longitud > i && longitud != i && valida == false) {
            char letra = letras[i];

            //Si existe un espacio la validacion falla, por lo tanto la palabra 
            //es invalida
            if (Character.isWhitespace(letra)) {
                valida = true;
            }
            i++;
        }

        return valida;
        
        
    }

}
