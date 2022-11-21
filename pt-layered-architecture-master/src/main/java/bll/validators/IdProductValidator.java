package bll.validators;

import model.Product;

/**
 * A class that checks if the id of a product is smaller than 1 - if it is, if throws an exception.
 */
public class IdProductValidator implements Validator<Product> {
	/**
	 * This method is used for checking if the id of a product is correct.
	 * @param t
	 */
	public void validate(Product t) {

		if (t.getIdProduct() < 1) {
			throw new IllegalArgumentException("The Id limit is not respected!");
		}
	}
}
