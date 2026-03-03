package md.corsojava;

import java.io.*; 

public class LetturaFile {

    public static void main(String[] args) {
        
        // Prima modalità
        // lettura di file un carattere alla volta
        try {    
            FileInputStream file_lettura = new FileInputStream("C:\\temp\\fileprova.txt");    

            int carattere_letto = file_lettura.read();  
            System.out.println(carattere_letto);    
            System.out.print((char)carattere_letto);    
  
            file_lettura.close();    
          } catch(Exception e){
              System.out.println(e);
          }    
    }     
}


/********************************************************************
// Prima modalità
        // lettura di file un carattere alla volta
        try {    
            FileInputStream file_lettura = new FileInputStream("C:\\temp\\fileprova.txt");    

            int carattere_letto = file_lettura.read();  
            System.out.println(carattere_letto);    
            System.out.print((char)carattere_letto);    
  
            file_lettura.close();    
          } catch(Exception e){
              System.out.println(e);
          }     
*/

/********************************************************************
// Seconda modalità
        // lettura di file un carattere alla volta FINO a fine file
        try {    
            FileInputStream file_lettura = new FileInputStream("C:\\temp\\fileprova.txt");    
            int carattere_letto; 
            
            carattere_letto = file_lettura.read();
            while(carattere_letto != -1){ 
                System.out.println((char)carattere_letto);    
                carattere_letto = file_lettura.read();
            }    
            
            //while( (carattere_letto = file_lettura.read())  != -1){ 
            //    System.out.print((char)carattere_letto);    
            //}
            
            file_lettura.close();    
            
          } catch(Exception e){
              System.out.println(e);
          }
*/

/********************************************************************
// Terza modalità
        // Lettura di una riga del file
        try {    
            
            BufferedReader file_lettura = new BufferedReader(new FileReader("C:\\temp\\fileprova.txt"));
            String riga;
            riga = file_lettura.readLine();
            
            while(riga != null) {                                               //se riga==null -> file finito
                System.out.println(riga);
                riga = file_lettura.readLine();
            }
            
            
            //while((riga = file_lettura.readLine()) != null) {                 //se riga==null -> file finito
            //    System.out.println(riga);
            //} 
                         
            file_lettura.close();

        } catch(Exception e){
              System.out.println(e);
        } 
*/

/********************************************************************
// Lettura file con sostituzione dicaratteri a video
        try {
            BufferedReader file_lettura = new BufferedReader(new FileReader("C:\\temp\\catalogoprodotti.txt"));
            
            String riga;
            
            riga = file_lettura.readLine();
            
            while(riga != null) {
                System.out.println(riga.replaceAll(";", " - "));
                riga = file_lettura.readLine();
            }  
            file_lettura.close(); 
        } 
        catch(IOException e){
            System.out.println(e);
        }        
*/
/* *******************************************************************
// Split (guardare anche charat, indexof, substring
        try {
            BufferedReader file_lettura = new BufferedReader(new FileReader("C:\\temp\\catalogoprodotti.txt"));
        
            String riga;
                       
            riga = file_lettura.readLine();  //leggo prima riga
            
            String var_riga_array []; //split  prima riga
            //suddivide la riga letta - è brutto ripetere la stessa istruzione, 
            //in questo momento, per spiegare la split, la teniamo
            var_riga_array = riga.split(";");    
            
            while(riga != null) {
                
                System.out.println("riga:" + riga + "<");
                var_riga_array = riga.split(";");    //suddivide la riga letta

                //System.out.println("quantità:" + var_riga_array[3] + "<");
                
                for (String var_temp_elemento: var_riga_array) {
            
                    System.out.println("il valore:" + var_temp_elemento + "<");
                }
                riga = file_lettura.readLine();
            }
            
            file_lettura.close(); 
        } 
        catch(IOException e){
            System.out.println(e);
        }   
*/
/********************************************************************



/********************************************************************
// OPZIONE DO WHILE
        // Lettura file con sostituzione dicaratteri a video
        try {
            BufferedReader file_lettura = new BufferedReader(new FileReader("C:\\temp\\catalogoprodotti.txt"));
            
            String riga;
            
            do {
                riga = file_lettura.readLine();
                if (riga != null) {
                    System.out.println(riga.replaceAll(";", " - "));
                }
                
            } while (riga != null);
            
            file_lettura.close(); 
        } 
        catch(IOException e){
            System.out.println(e);
        }    
    }  
*/