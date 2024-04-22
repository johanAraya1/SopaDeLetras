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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CapaPresentacion {

    public static Scanner sc = new Scanner(System.in);
    public static int cantidadPalabras = 2;
    public static int cantidadPalabrasFiltar = 1;
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_GREEN = "\u001B[32m";
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static String[] solicitarPalabras(int cantPalabras) {
        //Aca guardamos las palabras en un arreglo
        String[] palabras = new String[cantPalabras];

        for (int i = 0; i < cantPalabras; i++) {

            String palabra = solicitarPalabra(i);
            palabras[i] = palabra;
        }

        return palabras;
    }

    public static String solicitarPalabra(int idx) {
       //Le solicitamos al usuario que ingrese las palabras 
        boolean ejecutar = true;
        String palabra = null;
        do {
            try {            
                palabra = solicitarString("Digite la palabra " + (idx + 1) + ": ");
                CapaLogica.validarPalabra(palabra);
                ejecutar = false;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (ejecutar);
        return palabra;
    }

    public static void iniciarJuego() {
        //Aca llamamos diferentes rutinas para empezar a jugar
        String[] palabras = solicitarPalabras(cantidadPalabras);
        String[] palabrasFiltradas = CapaLogica.filtrarPalabras(cantidadPalabrasFiltar, palabras);
        imprimirPalabrasFiltradas(palabrasFiltradas);
        jugar(palabrasFiltradas);
        llenarMatrizValorRandom();
        llenarMatrizValorRandom();
        imprimirMatriz();
        
    }

    public static void imprimirPalabrasFiltradas(String[] palabrasFiltradas) {
       //Acá le mostramos al usuario las palabras que debe de buscar
        String datos = "*****-";
        String color = ANSI_GREEN;

        for (int i = 0; i < palabrasFiltradas.length; i++) {
            datos += palabrasFiltradas[i] + "-";
        }
        datos += "*****";
        datos = color + datos + ANSI_RESET;
        System.out.println(datos);

    }

    public static void configurarEntorno() {
        System.out.println("Seleccione la cantidad de palabras que desea utilizar");
        cantidadPalabras = sc.nextInt();

        System.out.println("Ingrese la cantidad de palabras que desea filtar");
        cantidadPalabrasFiltar = sc.nextInt();

        //System.out.println("Desea activar modo trampa (S ó N)");
        //modoTrampa = sc.next().toUpperCase().equals("S");
        String[] palabras = solicitarPalabras(cantidadPalabras);
        String[] palabrasFiltradas = CapaLogica.filtrarPalabras(cantidadPalabrasFiltar, palabras);
        imprimirPalabrasFiltradas(palabrasFiltradas);
        jugar(palabrasFiltradas);
        

    }

    public static void imprimirMatriz() {
        //Aca imprimimos la matriz para enseñarsela al usuario
        String[][] sopa = CapaLogica.sopa;
        String linea = new String(new char[60]).replace('\0', '-');
        
        for (int i = 0; i < CapaLogica.tamanno; i++) {
            System.out.println(linea);
            for (int j = 0; j < CapaLogica.tamanno; j++) {

                String letra = sopa[i][j];

                System.out.print("| " + letra + "  " );
            }
            System.out.println();
        }
        System.out.println();
        

    }

    public static void llenarMatrizValorRandom() {
        //Aca se termina de rellenar la matriz con caracteres random
        String[][] sopa = CapaLogica.sopa;

        Character[] abecedario = CapaLogica.letras;

        for (int i = 0; i < CapaLogica.tamanno; i++) {

            for (int j = 0; j < CapaLogica.tamanno; j++) {

                String letra = sopa[i][j];
                if (letra == null) {
                    int indice = CapaLogica.darIndiceRandom(abecedario.length);
                    letra = "" + abecedario[indice];
                    sopa[i][j] = letra;
                }

            }

        }

    }

    public static void jugar(String[] palabrasFiltradas) {
        //Mediante esta rutina llamamos a la matriz con las palabras random que 
        //el sistema escogió dentro de la sopa de letras.
        CapaLogica.llenarMatriz(palabrasFiltradas);

    }

    public static String solicitarString(String mensaje) throws IOException  {
        // Se crea esta rutina para ayudarnos con un mensaje para poder captar 
        // espacios en las palabras que ingresa el usuario y así evitar que nos
        //provoque un error.
        System.out.println(mensaje);
        String valorMensaje = in.readLine();
       

        return valorMensaje;
    }

    public static void mostrarMenu() {
        //Esta rutina nos muestra el menú en el main
        int opcion = 0;

        do {

            System.out.println("*****MENU*****");
            System.out.println("¿Que desea realizar?");
            System.out.println("1-Configurar Juego");
            System.out.println("2-Iniciar Juego ");
            System.out.println("3-Configurar Entorno");
            System.out.println("4-Salir");
            System.out.print("Digite la opcion que desea realizar: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Configurar Juego");
                    iniciarJuego();
                    break;
                case 2:
                    System.out.println("Iniciar Juego ");
                    //Es esta opción llena la matriz con las palabras escogidas
                    // y rellena la matriz con letras aleatorias
                    seleccionarCoordenada();
                    imprimirMatriz();
                    break;
                case 3:
                    configurarEntorno();

                    imprimirMatriz();
                    break;
                case 4:
                    System.out.println("Salir");
                    break;
                default:
                    System.err.println("Ingresó una opción incorrecta");
                    break;
            }//fin del switch
        } while (opcion != 4);
    }

    public static void seleccionarCoordenada() {
        //Con esta rutina se interactúa con el usuario para elegir el campo de
        // la o las letras que requiere para completar la sopa de letras.
        boolean esValida=false;
        int x, y =0;
            do{
                System.out.println("Digite la fila que desea ubicar");
                x=sc.nextInt();
            
                System.out.println("Digite la columna que desea ubicar");
                y = sc.nextInt();
                    if(x>=12 || y>=12 ){
                        esValida=true;
                        System.out.println("El numero debe ser menor que 12");
                    } else {
                        esValida=false;
                    }
            }while(esValida);
        
        pintarLetra(x, y);
    }

    public static void pintarLetra(int x, int y) {
        //Con esta rutina cambiamos el color de la letra que el usuario eligió
        String letra = CapaLogica.sopa[x][y];

        if (letra.contains(ANSI_BLUE)) {

            letra = letra.replace(ANSI_BLUE, "");
            letra = letra.replace(ANSI_RESET, "");
        } else {
            CapaLogica.sopa[x][y] = ANSI_BLUE + letra + ANSI_RESET;

        }

    }

    public static void main(String args[]) {
        mostrarMenu();
        
    }//Fin del main    

}//Fin de la clase
