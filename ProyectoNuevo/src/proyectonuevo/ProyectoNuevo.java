package proyectonuevo;

import static proyectonuevo.CL.*;
import java.util.Scanner;

public class ProyectoNuevo {

    static Scanner sc = new Scanner(System.in);

//    public static void main(String[] args) {
//        FillSopaM();
//        PrintSopaM();
//        GetWords();
//        //pedirPalabras();
//        SelectRandomWords();
//        mostrarPalabaras();
//        
//    }

//    public static void pedirPalabras(){
//        String theWord;
//        for(int i=0; i<maxWords; i++){
//            Message("Digite la palabra " + (i + 1) + ": ", Color.Blue);
//                theWord = sc.nextLine();
//                Words[i]= theWord;
//                CL.GetWords(theWord, i);
//        }
//        
//    }
    public static void mostrarPalabaras() {
        for (int i = 0; i < maxSelectedWords; i++) {
            Messageln("" + SelectedWords[i], Color.Yellow);

        }
    }
}
