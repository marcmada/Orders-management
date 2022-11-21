package bll.validators;

import model.Client;

import javax.swing.*;

/**
 * A class that checks if the age of a client is between 18 and 100 - if it is not, if throws an exception.
 */
public class AgeValidator implements Validator<Client> {
	private static final int MIN_AGE = 18;
	private static final int MAX_AGE = 100;

	/**
	 * This method is used for checking the age of a client.
	 * @param t
	 */
	public void validate(Client t) {

		if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
			JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"The Client Age limit is not respected!","Error",JOptionPane.INFORMATION_MESSAGE);
			throw new IllegalArgumentException("The Client Age limit is not respected!");
		}

	}
}
