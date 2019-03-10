import java.rmi.*;
public interface Dbinterface extends Remote{
	public String Insert(String name,String rno,String ph,String add)throws RemoteException,Exception;
	public String Delete(String rno)throws RemoteException,Exception;
	public String Selectall()throws RemoteException,Exception;
	public String Selectrno(String rno)throws RemoteException,Exception;
}
