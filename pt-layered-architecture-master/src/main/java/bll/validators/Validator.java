package bll.validators;

/**
 * An interface used for the validating part.
 * @param <T>
 */
public interface Validator<T> {

	void validate(T t);
}
