package bll.validators;

import model.Product;

import javax.swing.*;

/**
 * A class that checks if the price of a product is smaller than 0 - if it is, if throws an exception.
 */
public class PretValidator implements Validator<Product> {

	/**
	 * This method is used for checking if the price of a product is correct (it has to be equal to or greater than 0).
	 * @param t
	 */
	public void validate(Product t) {

		if (t.getPretKilogram() <= 0) {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"The Price limit is not respected!","Error",JOptionPane.INFORMATION_MESSAGE);
			throw new IllegalArgumentException("The Price limit is not respected!");
		}
	}
}