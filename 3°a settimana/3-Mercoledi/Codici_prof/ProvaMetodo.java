package md.corsojava;

/**
 *
 * @author margh
 */
public class ProvaMetodo {
    
    public static void main(String[] args) { // JRE chiama il main 
        System.out.println("Sono nel main"); 
        
        metodo_pubblico();  // la chiamata ad un metodo
        
        //metodo_no_static();
        
        metodo_privato();
        
        //metodo_senza_parametri();
        
        //int var_prova = metodo_con_parametri (3, 2);
        //System.out.println("Sono sempre nel main, var_prova vale:" + var_prova);
        
        //metodo_no_static(); 
        ProvaMetodo mioprovametodo = new ProvaMetodo(); 
        mioprovametodo.metodo_no_static();
        
        System.out.println("Fine del main");
       
    }      
    
    // definizione del metodo public senza restituzione di valore e senza parametri   
    public static void metodo_pubblico(){ 
        //qualsiasi istruzione
        System.out.println("Metodo Pubblico"); 
    }    
    
    // definizione del metodo private senza restituzione di valore senza parametri
    private static void metodo_privato(){
        //qualsiasi istruzione
        System.out.println("Metodo Privato");
    }
    
    // definizione metodo senza restituzione di valore, senza parametri
    public static void metodo_senza_parametri (){
        System.out.println("--- Sono in prova metodo - il valore del metodo è:" + "Ciao, passo di qui" );
    }
  
    // definizione metodo con restituzione di valore, con parametri
    public static int metodo_con_parametri (int p_num1, int p_num2){
        //prova del metodo con parametri  
        System.out.println("--- Sono in metodo_con_parametri - il valore di p_num1 è:" + p_num1);
        System.out.println("--- Sono in metodo_con_parametri - il valore di p_num2 è:" + p_num2);
               
        return p_num1 + p_num2;        
    } 
    
    public void metodo_no_static (){
        //prova del metodo non static 
        // devi accedere per forza in COPIA
        System.out.println("Il metodo non è static");
     } 
    
    public void ancora_metodo_no_static (){
        //prova del metodo non static  
        System.out.println("Il metodo non è static");
        
    } 
    
    protected static void calcoli() {
        
        int var_num1 = 1000;
        int var_num2 = 882;
        int var_somma;
        
        System.out.println("-- Prima - Sono nel metodo calcoli");
        
        var_somma = metodo_con_parametri(var_num1, var_num2);                           // chiamata al metodo
        
        System.out.println("-- Dopo - Sono nel metodo calcoli");
        
        System.out.println("var_somma vale:" + var_somma);

    }

}