package model;

/**
 * This class contains all the details about the products.
 * There are getters, setters and a toString method which are used for products' information processing.
 */
public class Product {
	public int idProduct;
	public String numeProdus;
	public int cantitateDisponibila;
	public int pretKilogram;

	/**
	 * This is a constructor with all the information a product must have - idProduct, numeProdus, cantitateDisponibila and pretKilogram.
	 * @param idProduct - a primary key (there is a validator for this)
	 * @param numeProdus - the name of the product
	 * @param cantitateDisponibila - the disposable quantity (there is a validator for this)
	 * @param pretKilogram - the price per kilogram of a product (there is a validator for this)
	 */
	public Product (int idProduct, String numeProdus, int cantitateDisponibila, int pretKilogram) {
		super();
		this.idProduct = idProduct;
		this.numeProdus = numeProdus;
		this.cantitateDisponibila = cantitateDisponibila;
		this.pretKilogram = pretKilogram;
	}

	/**
	 * A default constructor.
	 */
	public Product() {}

	/**
	 * A constructor that contains only the id.
	 * @param idProduct
	 */
	public Product(int idProduct) {
		this.idProduct = idProduct;
	}

	public Product (String numeProdus, int cantitateDisponibila, int pretKilogram) {
		super();
		this.numeProdus = numeProdus;
		this.cantitateDisponibila = cantitateDisponibila;
		this.pretKilogram = pretKilogram;
	}

	/**
	 * getIdProduct method
	 * @return
	 */
	public int getIdProduct() { return idProduct; }

	/**
	 * setIdProduct method
	 * @param idProduct
	 */
	public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

	/**
	 * getNumeProdus method
	 * @return
	 */
	public String getNumeProdus() { return numeProdus; }

	/**
	 * getCantitateDisponibila method
	 * @return
	 */
	public int getCantitateDisponibila() { return cantitateDisponibila; }

	/**
	 * setCantitateDisponibila method
	 * @param cantitateDisponibila
	 */
	public void setCantitateDisponibila(int cantitateDisponibila) { this.cantitateDisponibila = cantitateDisponibila; }

	/**
	 * getPretKilogram method
	 * @return
	 */
	public int getPretKilogram() { return pretKilogram; }

	/**
	 * toString method - used to display the details about the products
	 * @return
	 */
	@Override
	public String toString() {
		return "ProductBLL [idProduct=" + idProduct + ", numeProdus=" + numeProdus + ", cantitateDisponibila="
				+ cantitateDisponibila + ", pretKilogram=" + pretKilogram + "]"; }
	
}
