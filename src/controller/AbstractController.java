package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;
import model.Model;

public abstract class AbstractController extends Observable implements Serializable, Controller {

	
	public void serialize(List list, String fileName) {
		{
		    // create output stream
		    try {
		      FileOutputStream out = new FileOutputStream(fileName);
		      System.out.println( "  serialize");
		      ObjectOutputStream objOut = new ObjectOutputStream(out);

		      // write out object, flush stream and close output
		      objOut.writeObject(list);
		      objOut.flush();
		      out.close();
		    }
		    catch (IOException e) {
		     System.out.println(e);
		    }
		  }
	}
		  public List deserialize(List list, String fileName) {
		    // create input stream
		    try {
		      FileInputStream in = new FileInputStream(fileName);
		      System.out.println( "  desearialize");
		      ObjectInputStream objIn = new ObjectInputStream(in);

		      // read in data and close input
		      list = (List) objIn.readObject();
		      in.close();
		      return list;
		    }
		    catch (IOException e) {
		    	list = new ArrayList<>();
		    	return list;
		    }
		    catch (ClassNotFoundException e) {
		      reportError(e.toString());
		      return null;
		    }
		  }
		  
		  public void reportError(String aMessage) {
			    JOptionPane.showMessageDialog(null, aMessage, "Flight Reservation System", JOptionPane.ERROR_MESSAGE);
			  }
		 
}
