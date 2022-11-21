package bll.validators;

import model.Order;
import model.Product;

import javax.swing.*;

/**
 * A class that checks if there is enough quantity for a new order - if it is not, if throws an exception.
 */
public class CantitateValidator implements Validator<Order> {
	/**
	 * This method is used for checking the  disposable quantity.
	 * @param o
	 */
	public void validate(Order o) {
		Product t = new Product();
		if (o.getCantitateCeruta() < t.getCantitateDisponibila()) {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Cantitatea ceruta nu este disponibila!","Error",JOptionPane.INFORMATION_MESSAGE);
			throw new IllegalArgumentException("Cantitatea ceruta nu este disponibila!");
		}
	}

}
