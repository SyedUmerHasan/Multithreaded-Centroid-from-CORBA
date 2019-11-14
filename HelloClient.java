// Copyright and License 

import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import java.util.Scanner;

import org.omg.CORBA.*;

public class HelloClient {
  static Hello helloImpl;

  public static void main(String args[]) {
    try {
      // create and initialize the ORB
      ORB orb = ORB.init(args, null);

      // get the root naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      // Use NamingContextExt instead of NamingContext. This is
      // part of the Interoperable naming Service.
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // resolve the Object Reference in Naming
      String name = "Hello";
      helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));

      System.out.println("Obtained a handle on server object: " + helloImpl);
      System.out.println("\n\n\n\n");

      Scanner myInput = new Scanner( System.in );
      int x1, x2 , x3;
      int y1, y2 , y3;
      System.out.println("**************************************************");
      System.out.println("Enter Value of Parameter X");
      System.out.println("**************************************************");
      System.out.println("");      

      System.out.print("Please enter X1: ");
      x1 = myInput.nextInt();

      System.out.print("Please enter X2: ");
      x2 = myInput.nextInt();

      System.out.print("Please enter X3: ");
      x3 = myInput.nextInt();

      System.out.println("");    
      System.out.println("**************************************************");
      System.out.println("Enter Value of Parameter Y");
      System.out.println("**************************************************");
      System.out.println("");

      System.out.print("Please enter Y1: ");
      y1 = myInput.nextInt();

      System.out.print("Please enter Y2: ");
      y2 = myInput.nextInt();

      System.out.print("Please enter Y3: ");
      y3 = myInput.nextInt();

      System.out.println(helloImpl.sayHello(x1,x2,x3,y1,y2,y3));
      myInput.close();
      
      helloImpl.shutdown();

    } catch (Exception e) {
      System.out.println("ERROR : " + e);
      e.printStackTrace(System.out);
    }
  }
}