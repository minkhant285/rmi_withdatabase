import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Dbmanager extends UnicastRemoteObject implements Dbinterface
{Connection con;
PreparedStatement pstmt;
ResultSet rs;
Dbmanager()throws RemoteException{
super();
}

public void createDb() {
	try {
		con=DriverManager.getConnection("jdbc:ucanaccess://Student.accdb");
		if(con != null) {
			System.out.println("Database Selected...");
		}
		else {
			System.out.println("No Database found");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public String Insert(String name, String rno, String ph, String add) throws RemoteException, Exception {
	// TODO Auto-generated method stub
	try {
	createDb();
	pstmt=con.prepareStatement("Insert into Student_info (Name,Roll,Phone,Address) values (?,?,?,?)");
	pstmt.setString(1, name);
	pstmt.setString(2, rno);
	pstmt.setString(3, ph);
	pstmt.setString(4, add);
	pstmt.executeUpdate();
	pstmt.close();
	con.close();
	}catch(Exception e1) {
	System.out.println("Error in Dbmanager : "+e1);	
	}
	return ("Inserted....");
	}
@Override
public String Delete(String rno) throws RemoteException, Exception {
	createDb();
	String query="Delete from Student_info where id="+rno;
	Statement stmt=con.createStatement();
	int failorS=stmt.executeUpdate(query);
	stmt.close();
	con.close();
	if(failorS==0)
	return "Error in Delete...........";
	else
		return "Deleted..";
}
@Override
public String Selectrno(String rnoo) throws RemoteException, Exception {
	createDb();
	StringBuilder sb=new StringBuilder();
	
	//String query = "SELECT Name,Roll,Phone,Address FROM Student_info";
	   pstmt=con.prepareStatement("SELECT * FROM Student_info where id="+rnoo);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
	      String id=rs.getString("ID");
	      String name = rs.getString("Name");
	      String rno = rs.getString("Roll");
	      String phno = rs.getString("Phone");
	      String address= rs.getString("Address");
	      sb.append(id+"\t->");
	      sb.append(name+"\t->");
	      
	      sb.append(rno+"\t->");
	     
	      sb.append(phno+"\t->");
	      
	      sb.append(address+"\n\n");
	      
	    }
	    pstmt.close();
	    con.close();
		return sb.toString();
	
}


@Override

public String Selectall() throws RemoteException, Exception {
	createDb();
	StringBuilder sb=new StringBuilder();
	
	//String query = "SELECT Name,Roll,Phone,Address FROM Student_info";
	   pstmt=con.prepareStatement("SELECT * FROM Student_info");
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
	      String id=rs.getString("ID");
	      String name = rs.getString("Name");
	      String rno = rs.getString("Roll");
	      String phno = rs.getString("Phone");
	      String address= rs.getString("Address");
	      sb.append(id+"\t->");
	      sb.append(name+"\t->");
	      
	      sb.append(rno+"\t->");
	     
	      sb.append(phno+"\t->");
	      
	      sb.append(address+"\n\n");
	      
	    }
	    pstmt.close();
	    con.close();
		return sb.toString();
	
}
}