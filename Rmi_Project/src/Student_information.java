import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
public class Student_information extends JFrame implements ActionListener{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton all=new JButton("All");
	JButton rno=new JButton("Select by ID Number");
    JButton del=new JButton("Delete Student");
    JButton ins=new JButton("Insert new Student");
    JButton refresh=new JButton("Refresh");
	JTextArea area=new JTextArea(25,85);
	JLabel lname=new JLabel("Enter Name");
	JLabel lrno=new JLabel("Enter Roll Number");
	JLabel lph=new JLabel("Enter Phone No");
	JLabel ladd=new JLabel("Enter Address");
	JLabel checkserver=new JLabel("Connected to server......");
	JLabel checkserver1=new JLabel("Can't Connect to server.....");
	JTextField t1=new JTextField(20);
	JTextField t2=new JTextField(20);
	JTextField t3=new JTextField(20);
	JTextField t4=new JTextField(20);
	
	JButton btnSave=new JButton("Save");
	JButton btnCancel=new JButton("Cancel");
	
	JPanel p0,p1,p2,p3;
	
	public Student_information() {
		
		setTitle("Students Information System");
		setSize(1000,550);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		p0=new JPanel();
		p0.setBorder(BorderFactory.createEtchedBorder());
		p0.setBackground(Color.BLACK);
		Dbinterface DI=null;
		try {
			DI=(Dbinterface)Naming.lookup("rmi://localhost:1099/Studentservice");
		}catch(Exception e) {
			
		}
		if(DI != null) {
			checkserver.setForeground(Color.GREEN);
			p0.add(checkserver);
		}
		else {
			checkserver1.setForeground(Color.RED);
			p0.add(checkserver1);
		}
		p1=new JPanel();
		p1.setBorder(BorderFactory.createEtchedBorder());
		p1.add(refresh);p1.add(all);p1.add(rno);p1.add(ins);p1.add(del);
		
		p2=new JPanel();
		p2.setBorder(BorderFactory.createEtchedBorder());
		
		p2.add(area);
		
		p3=new JPanel();
		p3.setBorder(BorderFactory.createEtchedBorder());
		p3.setLayout(new GridLayout(5, 2));
		p3.add(lname);p3.add(t1);
		p3.add(lrno);p3.add(t2);
		p3.add(lph);p3.add(t3);
		p3.add(ladd);p3.add(t4);
		p3.add(btnSave);p3.add(btnCancel);
		
		setLayout(new BorderLayout());
		add(p0,BorderLayout.NORTH);
		add(p1,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
		
		all.addActionListener(this);
		area.setEditable(false);
	    rno.addActionListener(this);
		del.addActionListener(this);
		ins.addActionListener(this);
		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		refresh.addActionListener(this);
		p3.setVisible(false);
		show();
		add(p3);
	}
	public static void main(String[]args) {
		new Student_information();
		
	}
	

	public void actionPerformed(ActionEvent e) {
		
		area.setText("");
		Dbinterface DI = null;
		try {
			DI=(Dbinterface)Naming.lookup("rmi://localhost:1099/Studentservice");
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(e.getSource()==all || e.getSource()==refresh) {
			
			try {
				area.append(DI.Selectall());
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource()==ins) {
			p2.setVisible(false);
			p3.setVisible(true);
			p1.setVisible(false);
		}
		
		else if(e.getSource()==btnSave){
			String Sname=t1.getText();
			String Srno=t2.getText();
			String Sph=t3.getText();
			String Saddress=t4.getText();
			try {
				JOptionPane.showMessageDialog(null, DI.Insert(Sname,Srno,Sph,Saddress));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==btnCancel) {
			p3.setVisible(false);
			p2.setVisible(true);
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			p1.setVisible(true);
		}
		
		else if(e.getSource()==del) {
			String delid=JOptionPane.showInputDialog("Please Enter Student ID to Delete ");
			try {
				JOptionPane.showMessageDialog(null,DI.Delete(delid));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource()==rno) {

			String Selid=JOptionPane.showInputDialog("Please Enter Student ID to Select ");
			try {
				area.append(DI.Selectrno(Selid));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private String[] append(String[] finaldata, String[] data) {
		// TODO Auto-generated method stub
		return null;
	}

}
