
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class StudentServer{
public static void main(String args[]) throws Exception{
Dbmanager Dbm = new Dbmanager();
Registry reg=LocateRegistry.createRegistry(1099);
reg.rebind("Studentservice", Dbm);
System.out.println("Student Server is ready................................");
}
}