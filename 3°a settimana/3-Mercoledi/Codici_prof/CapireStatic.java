package md.corsojava;

class CapireStatic {    
    static int var_a = 3;                                                       
    static int var_b = 4;
    
    CapireStatic (int i, int j) {
         var_a = i;
         var_b = j;  
         System.out.println("Nel costruttore var_b:" + var_b + "<"); 
    }

    static {
        System.out.println("blocco static inizializzato");        //
        var_b = var_a * 4;
        System.out.println("Nel blocco var_b:" + var_b + "< var_a:" + var_a);
    }
    
    /*static*/ void mostra_valori (int x) {                                         
        System.out.println("    x:" + x + "<");                    //
        System.out.println("var_a:" + var_a + "<");                //
        System.out.println("var_b:" + var_b + "<");                //
    }
    
    public static void main(String[] args) {
        /*
        System.out.println("INIZIO main di Capire Static");        //
        mostra_valori(42);                                      
        System.out.println("FINE main di Capire Static");                       
        */
        
        System.out.println("INIZIO main di Capire Static");
        
        CapireStatic miocapirestatic = new CapireStatic(74, 88);   
        miocapirestatic.mostra_valori(42);
        
        System.out.println("FINE main di Capire Static");
       
    }
    

}