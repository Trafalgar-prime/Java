package md.corsojava;

class DimostraSetGet {
    
    private String var_parola = "Prova 2";           
               int var_numero = 10;

    public String getVar_parola() {
        return var_parola;
    }
    
    public void set_var_parola(String p_parola) {                   //non è nominato in modo conforme
        var_parola = p_parola;
    }
}

public class DimostraPrivate {
    public static void main (String[] args) throws Exception{    
          
        String var_temp_parola;
        
        DimostraSetGet obj_dimostraSetGet = new DimostraSetGet(); 
                    
        var_temp_parola = obj_dimostraSetGet.getVar_parola();
        System.out.println(var_temp_parola);
        
        System.out.println(obj_dimostraSetGet.var_numero);
        
        //obj_dimostraSetGet.var_parola = "Pippo";
        
        obj_dimostraSetGet.set_var_parola("Pippo");
        obj_dimostraSetGet.var_numero = 55;
        
        System.out.println(obj_dimostraSetGet.getVar_parola());
        System.out.println(obj_dimostraSetGet.var_numero);
        
        /*
        var_temp_parola = obj_dimostraSetGet.getVar_parola();
        System.out.println(var_temp_parola);
        System.out.println(obj_dimostraSetGet.var_numero);
        */
    }
}