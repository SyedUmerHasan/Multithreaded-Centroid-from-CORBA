
// HelloServer.java
// Copyright and License 
import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.io.Console;
import java.util.Properties;

class HelloImpl extends HelloPOA {

	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}

	// implement sayHello() method
	public String sayHello(int x1, int x2, int x3, int y1, int y2, int y3) {
		String tableString = "";

		Centroid t1 = new Centroid(x1, x2, x3);
		Centroid t2 = new Centroid(y1, y2, y3);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableString = t1.myStr + "\n" + t2.myStr;

		return tableString;
	}

	// implement shutdown() method
	public void shutdown() {
		orb.shutdown(false);
	}
}

class Centroid extends Thread {
	public volatile String myStr = "";
	public int X1;
	public int X2;
	public int X3;
	public float centroid;

	public Centroid(int x1, int x2, int x3) {
		this.myStr = "";
		this.X1 = x1;
		this.X2 = x2; 
		this.X3 = x3;
		this.centroid = 0;
	}

	public void run() {
		synchronized (this) {
			this.centroid = (X1+X2+X3)/3;
			myStr = "The Centroid is : " + this.centroid;
		}
	}

	public String returnString() {
		return myStr;
	}
}

public class HelloServer {

	public static void main(String args[]) {
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			HelloImpl helloImpl = new HelloImpl();
			helloImpl.setORB(orb);

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
			Hello href = HelloHelper.narrow(ref);

			// get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			String name = "Hello";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			System.out.println("HelloServer ready and waiting ...");

			// wait for invocations from clients
			orb.run();
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("HelloServer Exiting ...");

	}
}