package md.corsojava;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Eccezioni {
    
    public static void main(String[] args) {
        
        //inizializzazione variabili
           int var_dividendo = 55;
           int var_divisore  = 0;
        double var_risultato = -999;
        
        System.out.println("Prima var_risultato:" + var_risultato);
        
        var_risultato = var_dividendo / var_divisore; 
        
        System.out.println("Dopo var_risultato:" + var_risultato);
        System.out.println("Sono dopo le istruzioni");
        
        
    }
}
 


/* **************************************************************************************
// primo esempio SENZA Eccezioni

        //inizializzazione variabili
           int var_dividendo = 55;
           int var_divisore  = 10;
        double var_risultato = -999;
        
        System.out.println("Prima var_risultato:" + var_risultato);
        
        var_risultato = var_dividendo / var_divisore; 
        
        System.out.println("Dopo var_risultato:" + var_risultato);
        System.out.println("Sono dopo le istruzioni");
        
    

/* **************************************************************************************
        
        System.out.println("Prima var_risultato:" + var_risultato);
        
        try {       
            var_risultato = var_dividendo / var_divisore; 
        }
        catch (ArithmeticException err) {
            System.out.println("È andato in errore senza scatafasciarsi");
            //var_risultato = var_dividendo / 6; 
        }
        System.out.println("Dopo var_risultato:" + var_risultato);
        System.out.println("Sono dopo le istruzioni");

/* **************************************************************************************/

/* ***************************************************************************************
        
        //errore di input, errore di divisore
        Scanner var_input = new Scanner(System.in);
        System.out.println("Si prega di inserire un numero:");
        try {
            var_divisore = Integer.parseInt(var_input.nextLine());
            var_risultato = var_dividendo / var_divisore; 
            System.out.println("Nella try var_risultato:" + var_risultato + "<");
        }
        catch (NumberFormatException err) {
            System.out.println("È necessario inserire un numero!");
        }
        catch(ArithmeticException err) {
          //System.out.println("Ho incontrato un errore aritmetico: " + err);
          System.out.println("Il divisore non può essere:" + var_divisore);
          var_risultato = -1;
        }
        catch (Exception err) {
          System.out.println("Ho incontrato un errore: " + err );
        }
        finally {
          System.out.println("Sono nella finally");
          var_input.close();
        }
         
        System.out.println("Il risultato della divisione è:" + var_risultato);
*****************************************************************************************/        
        
/* ***************************************************************************************       
        //errore di input, errore di divisore
        //Blocchi separati
        try {
            var_divisore = Integer.parseInt(var_input.nextLine());
            System.out.println("Nella try var_risultato:" + var_risultato + "<");
        }
        catch (NumberFormatException err) {
            System.out.println("È necessario inserire un numero!");
            var_divisore = 33;
        }
        
        try {
            var_risultato = var_dividendo / var_divisore; 
            System.out.println("var_dividendo:" + var_dividendo);
            System.out.println("var_divisore:" + var_divisore);
        }
        catch(ArithmeticException err) {
          System.out.println("Ho incontrato un errore aritmetico: " + err);
          var_risultato = -1;
        }
        
       System.out.println("Il risultato della divisione è:" + var_risultato);

/* ****************************************************************************************
   
    public static void main(String[] args) throws Exception {
        
        int var_eta = 17;
        
        try {
            
            if (var_eta  < 18) {
                throw new InputMismatchException("Accesso non consentito - Devi avere almeno 18 anni.");
            } else {
                System.out.println("Accesso consentito.");
            }
            System.out.println("Non vengo visualizzato in caso di eccezione" );
                    
        } catch (InputMismatchException err) {
            System.out.println("Il valore indicato non è corretto:" + err);
        } catch (Exception err) {
            System.out.println("Sono nella generica:" + err);
        }
        
    }
}

/*
} catch (ArithmeticException err) {
            System.out.println(err);
            //System.out.println("Sei minorenne");
    
        }
*/ 