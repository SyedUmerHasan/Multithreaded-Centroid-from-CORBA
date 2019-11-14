package HelloApp;


/**
* HelloApp/HelloPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Hello.idl
* Thursday, November 14, 2019 8:46:44 PM PKT
*/

public abstract class HelloPOA extends org.omg.PortableServer.Servant
 implements HelloApp.HelloOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("sayHello", new java.lang.Integer (0));
    _methods.put ("shutdown", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // HelloApp/Hello/sayHello
       {
         int x1 = in.read_long ();
         int x2 = in.read_long ();
         int x3 = in.read_long ();
         int y1 = in.read_long ();
         int y2 = in.read_long ();
         int y3 = in.read_long ();
         String $result = null;
         $result = this.sayHello (x1, x2, x3, y1, y2, y3);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // HelloApp/Hello/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:HelloApp/Hello:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Hello _this() 
  {
    return HelloHelper.narrow(
    super._this_object());
  }

  public Hello _this(org.omg.CORBA.ORB orb) 
  {
    return HelloHelper.narrow(
    super._this_object(orb));
  }


} // class HelloPOA
