import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.*;
public class Library {
	boolean packFrame=false;
	public Library() {
		LibMain frame=new LibMain();
		frame.setLocation(300,100);
		frame.setSize(700,500);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
		
		try {  
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		new Library();
		Service.connect();
	}	

}
