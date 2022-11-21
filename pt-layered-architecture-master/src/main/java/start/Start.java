package start;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import model.Client;
import presentation.Controller;
import presentation.ViewClient;
import presentation.ViewOrder;
import presentation.ViewProduct;

/** The main class for this project.
 *
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	/**
	 * The main function.
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		ViewClient theView= new ViewClient();
		ViewOrder theView2 = new ViewOrder();
		ViewProduct theView3 = new ViewProduct();
		
		Controller theController= new Controller(theView, theView2, theView3);
		
		theView.setVisible(true);
		theView2.setVisible(true);
		theView3.setVisible(true);
	}

}
