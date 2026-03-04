package md.corsojava;

public class ClasseA {

  public static void metodo1a() {
      
      ClasseB obj_classeb = new ClasseB();
      
      System.out.println("ClasseA metodo1a");
      System.out.println("* 1 PRIMA DI ASSEGNAZIONE:");                 
      System.out.println("*                         obj_classeb.var_static:" + obj_classeb.var_static + "<");           //ATTENZIONE: è solo per la spiegazione
      System.out.println("*                             ClasseB.var_static:" + ClasseB.var_static + "<");               //QUESTO è il modo corretto
      System.out.println("*                     obj_classeb.var_NON_static:" + obj_classeb.var_NON_static + "<");       //
      System.out.println("*");
     
      obj_classeb.var_static = "CAMBIO STATIC in classeA";                                                              //
      obj_classeb.var_NON_static = "CAMBIO no STATIC in classeA";                                                       //
      
      System.out.println("* 1 DOPO ASSEGNAZIONE:");
      System.out.println("*                         obj_classeb.var_static:" + obj_classeb.var_static + "<");            //
      System.out.println("*                             ClasseB.var_static:" + ClasseB.var_static + "<");                //
      System.out.println("*                     obj_classeb.var_NON_static:" + obj_classeb.var_NON_static + "<");        //
      System.out.println("");                                                                                              
      
      ClasseB obj_classeb2 = new ClasseB(); 
      
      System.out.println("* 2 PRIMA DI ASSEGNAZIONE:");                 
      System.out.println("*                        obj_classeb2.var_static:" + obj_classeb2.var_static + "<");           //
      System.out.println("*                             ClasseB.var_static:" + ClasseB.var_static + "<");                //
      System.out.println("*                     obj_classeb2.var_NON_static:" + obj_classeb2.var_NON_static + "<");       //
      System.out.println("*");                                                                                              
      
      obj_classeb2.var_static = "5 in classe A";
      obj_classeb2.var_NON_static = "4 in classe A";
             
      System.out.println("* 2 DOPO ASSEGNAZIONE:");
      System.out.println("*                        obj_classeb2.var_static:" + obj_classeb2.var_static + "<");            //
      System.out.println("*                             ClasseB.var_static:" + ClasseB.var_static + "<");                 //
      System.out.println("*                     obj_classeb.var_NON_static:" + obj_classeb2.var_NON_static + "<");        //
      System.out.println("*");
      
      System.out.println("*                         obj_classeb.var_static:" + obj_classeb.var_static + "<");             //
      System.out.println("*                             ClasseB.var_static:" + ClasseB.var_static + "<");                 //
      System.out.println("*                     obj_classeb.var_NON_static:" + obj_classeb.var_NON_static + "<");         //
      System.out.println("*");
        
      metodo2a();
      
      System.out.println("FINE ClasseA metodo1a");
      System.out.println("*");
      
  }
  
  static void metodo2a() {
      ClasseB obj_classeb3 = new ClasseB();
      
      System.out.println("   ClasseA metodo2a");
      System.out.println("   *                        obj_classeb3.var_static:" + obj_classeb3.var_static + "<");            //
      System.out.println("   *                             ClasseB.var_static:" + ClasseB.var_static + "<");                 //
      System.out.println("   *                    obj_classeb3.var_NON_static:" + obj_classeb3.var_NON_static + "<");        //
      System.out.println("   FINE ClasseA metodo2a");
      System.out.println("   *");
  }  
}

 