import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dbobj {
	public static void main(String args[]) throws Exception {
	 Connection con = null;
	 
	  try {
			
			con=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Lenovo\\Documents\\inventory.accdb");
		}catch(Exception e1) {
			e1.printStackTrace();
		}
    String query = "SELECT Name,Description,Qty,Cost FROM Stock";
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    while (rs.next()) {
      String name = rs.getString("Name");
      String desc = rs.getString("Description");
      int qty = rs.getInt("Qty");
      int cost = rs.getInt("Cost");
      System.out.println(name + ", " + desc + "\t, " + qty + "\t  $" + cost);
    }
    con.close();
  }
}