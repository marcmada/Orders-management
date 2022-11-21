package bll.validators;

import model.Client;

/**
 * A class that checks if the id of a client is smaller than 1 - if it is, if throws an exception.
 */
public class IdValidator implements Validator<Client> {
	/**
	 * This method is used for checking if the id of a client is correct.
	 * @param t
	 */
	public void validate(Client t) {

		if (t.getIdClient() < 1) {
			throw new IllegalArgumentException("The Id limit is not respected!");
		}
	}
}
