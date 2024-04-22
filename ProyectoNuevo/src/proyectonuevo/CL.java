
package proyectonuevo;

import java.util.Random;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;



public class CL {
    public static final int MatrixSize = 13;
    public static final int maxWords = 4;
    public static final int maxSelectedWords = 2;
    public static final String[][] SopaM = new String[MatrixSize][MatrixSize];
    public static final String[] Words = new String[maxWords];
    public static final String[] SelectedWords = new String[maxSelectedWords];
    
    public enum Color {
        Black, Blue, Cyan, Default, Green, Purple, Red, White, Yellow
    }

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_DEFAULT = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    static Scanner sc = new Scanner(System.in);

    
     public static void Messageln(String text, Color fontColor) {
        switch (fontColor) {
            case Black:
                System.out.println(ANSI_BLACK + text);
                break;
            case Blue:
                System.out.println(ANSI_BLUE + text);
                break;
            case Cyan:
                System.out.println(ANSI_CYAN + text);
                break;
            case Default:
                System.out.println(ANSI_DEFAULT + text);
                break;
            case Green:
                System.out.println(ANSI_GREEN + text);
                break;
            case Purple:
                System.out.println(ANSI_PURPLE + text);
                break;
            case Red:
                System.out.println(ANSI_RED + text);
                break;
            case White:
                System.out.println(ANSI_WHITE + text);
                break;
            case Yellow:
                System.out.println(ANSI_YELLOW + text);
                break;
        }
    }

    public static void Messageln(String text) {
        Messageln(text, Color.Default);
    }

    public static void Message(String text) {
        Message(text, Color.Default);
    }

    public static void Message(String text, Color fontColor) {
        switch (fontColor) {
            case Black:
                System.out.print(ANSI_BLACK + text);
                break;
            case Blue:
                System.out.print(ANSI_BLUE + text);
                break;
            case Cyan:
                System.out.print(ANSI_CYAN + text);
                break;
            case Default:
                System.out.print(ANSI_DEFAULT + text);
                break;
            case Green:
                System.out.print(ANSI_GREEN + text);
                break;
            case Purple:
                System.out.print(ANSI_PURPLE + text);
                break;
            case Red:
                System.out.print(ANSI_RED + text);
                break;
            case White:
                System.out.print(ANSI_WHITE + text);
                break;
            case Yellow:
                System.out.print(ANSI_YELLOW + text);
                break;
        }
    }
   
    public static void FillSopaM() {
       //llenar la fila y columna 0 con numeros y letras para identificarlas mas fácil
        SopaM[0][0] = "  ";

        SopaM[0][1] = "A";
        SopaM[0][2] = "B";
        SopaM[0][3] = "C";
        SopaM[0][4] = "D";
        SopaM[0][5] = "E";
        SopaM[0][6] = "F";
        SopaM[0][7] = "G";
        SopaM[0][8] = "H";
        SopaM[0][9] = "I";
        SopaM[0][10] = "J";
        SopaM[0][11] = "K";
        SopaM[0][12] = "L";

        SopaM[1][0] = "01";
        SopaM[2][0] = "02";
        SopaM[3][0] = "03";
        SopaM[4][0] = "04";
        SopaM[5][0] = "05";
        SopaM[6][0] = "06";
        SopaM[7][0] = "07";
        SopaM[8][0] = "08";
        SopaM[9][0] = "09";
        SopaM[10][0] = "10";
        SopaM[11][0] = "11";
        SopaM[12][0] = "12";

        for (int i = 1; i < MatrixSize; i++) {
            for (int j = 1; j < MatrixSize; j++) {
                SopaM[i][j] = null;
                //lleno la matriz con null para despúes reemplazarla con letras
            }
        }

    }
    
    public static void PrintSopaM() {
       //Se realizan dos cilco anidados para darle forma a la matriz
        for (int i = 0; i < MatrixSize; i++) {
            for (int j = 0; j < MatrixSize; j++) {
                if (i == 0 || j == 0) {
                    Message(" " + SopaM[i][j] + " ", Color.Blue);
                } else {
                    Message(" " + SopaM[i][j] + " ");
                }
            }
            Messageln("");
        }
    }
    
    public static boolean ArrayContains(final int[] array, final int v) {
        boolean result = false;

        try {
            for (int i : array) {
                if (i == v) {
                    result = true;
                    break;
                }
            }
        } catch (Exception ex) {
            Messageln("ERROR, General.RandoContainsmChar: " + ex.getMessage());
        }

        return result;
    }

    public static boolean ArrayContains(final String[] array, final String v) {
        boolean result = false;

        try {
            for (String i : array) {
                if (!(i == null || i.equals("")) && i.equals(v)) {
                    result = true;
                    break;
                }
            }
        } catch (Exception ex) {
            Messageln("ERROR, General.RandomChar: " + ex.getMessage());
        }

        return result;
    }
    
    public static void GetWords() {
        boolean nextWord;
        boolean validWord;
        String theWord = "";

        for (int i = 0; i < maxWords; i++) {
            nextWord = false;

            while (!nextWord) {
                validWord = true;

                Message("Digite la palabra " + (i + 1) + ": ", Color.Blue);
                theWord = sc.nextLine();
                theWord = toUpperCase(theWord);

                //Validar la longitud de la palabra (mayor que 1 y menor que 13
                if (theWord.length() < 2 || theWord.length() > 12) {
                    Messageln("La palabra debe contener de 2 a 12 letras.", Color.Red);
                    validWord = false;
                    
                }

                //Validar que sean solo letras mayusculas
                if (validWord) {
                    for (int j = 0; j < theWord.length(); j++) {
                        int ascii = theWord.charAt(j);
                        if (ascii < 65 || ascii > 90) {
                            String errorMessage;
                            if (ascii == 32) {
                                errorMessage = "No se deben colocar espacios.";
                            } else if (ascii >= 48 && ascii <= 57) {
                                errorMessage = "No se permiten números.";
                            } else {
                                errorMessage = "Solo se aceptan las letras de la A a la Z (sin Ñ).";
                            }

                            Messageln(errorMessage, Color.Red);
                            validWord = false;
                            break;
                        }
                    }
                }

                //Validar que la palabra no esté repetida.
                if (ArrayContains(Words, theWord)) {
                    Messageln("No se pueden repetir palabras.", Color.Red);
                    validWord = false;
                }

                //La palabra cumplió con todo, entonces la guarda y sigue con la siguiente.
                if (validWord) {
                    nextWord = true;
                    Words[i] = theWord;
                }
            }
        }
    }

     public static void SelectRandomWords() {
        try {
            int i = 0;
            Random rd;
            String NewWord;

            while (i < maxSelectedWords) {
                rd = new Random();
                NewWord = Words[rd.nextInt(maxWords - 1)];
                if (!ArrayContains(SelectedWords, NewWord)) {
                    SelectedWords[i] = NewWord;
                    i++;
                }
            }
        } catch (Exception ex) {
            Messageln("ERROR, Sopa.SelectRandomWords: " + ex.getMessage());
        }
    }

}
