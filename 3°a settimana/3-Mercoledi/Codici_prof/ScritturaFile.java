package md.corsojava;

import java.io.*;

public class ScritturaFile {
    public static void main(String[] args) {
                
        try {
            // Terza modalità
            // File scritto in append + metodo append + a capo

            FileWriter file_scrittura = new FileWriter(new File("C:\\temp\\fileprovascrittura.txt"), true);
            file_scrittura.write("\n14 - Questa è una prova di scrittura di file.");
            
            file_scrittura.write("\n15 - dove scriverà?");
            file_scrittura.write("\n");
            file_scrittura.write("16 - Questa è una terza riga");

            file_scrittura.close();
            
            
            
            
            
        } catch(FileNotFoundException e){
            System.out.println("non va bene!!!" + e);
            //creazione directory
        } catch(Exception e){
            System.out.println(e);
        }
        
    }    
}



/********************************************************************
// Prima modalità
        // File scritto in overwrite
        try {
            FileWriter file_scrittura = new FileWriter(new File("C:\\temp\\fileprovascrittura.txt"));
            file_scrittura.write("1 - Questa è una prima prova di scrittura di file.");

            file_scrittura.close();           
        } catch(FileNotFoundException e){
            System.out.println("non va bene!!!" + e);
            //creazione directory
        } catch(Exception e){
            System.out.println(e);
        }
*/

/********************************************************************
// Seconda modalità
// File scritto in overwrite + metodo append
            FileWriter file_scrittura = new FileWriter(new File("C:\\temp\\fileprovascrittura.txt"));
            file_scrittura.write("2 - Questa è una seconda prova di scrittura di file.");
            
            file_scrittura.append("3 - dove scriverà?");

            file_scrittura.close();

*/

/********************************************************************
// Terza modalità
// File scritto in append + metodo append + a capo

            FileWriter file_scrittura = new FileWriter(new File("C:\\temp\\fileprovascrittura.txt"), true);
            file_scrittura.write("4 - Questa è una prova di scrittura di file.");
            
            file_scrittura.append("5 - dove scriverà?");
            file_scrittura.append("\n");
            file_scrittura.append("6 - Questa è una terza riga");

            file_scrittura.close();
*/

/********************************************************************
// Quarta modalità
// File scritto in append
        try {
            FileWriter file_scrittura = new FileWriter(new File("C:\\temp\\fileprovascrittura.txt"), true);
            file_scrittura.write("\n");
            file_scrittura.write("13 - Questa è una prova di scrittura di file.");
            file_scrittura.write("\n");
            file_scrittura.write("14 - dove scriverà?");
            file_scrittura.write("\n");
            
            file_scrittura.write("15 - Questa è una terza riga");
                       
            file_scrittura.close();
        }
*/