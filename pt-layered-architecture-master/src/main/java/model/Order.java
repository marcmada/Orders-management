package model;

/**
 * This class contains all the details about the orders.
 * There are getters, setters and a toString method which are used for orders' information processing.
 */
public class Order {
	public int idClient;
	public int idProdus;
	public int cantitateCeruta;

	/**
	 * This is a constructor with all the information an order must have - idClient, idProdus and cantitateCeruta.
	 * @param idClient - a foreign key for the idClient in the Client table
	 * @param idProdus - a foreign key for the idPRodus in the Product table
	 * @param cantitateCeruta - the quantity that is requested by a client
	 */
	public Order (int idClient, int idProdus, int cantitateCeruta) {
		this.idClient = idClient;
		this.idProdus = idProdus;
		this.cantitateCeruta = cantitateCeruta;
	}

	/**
	 * A default constructor.
	 */
	public Order() { }

	/**
	 * getIdClient method
	 * @return
	 */
	public int getIdClient() { return idClient; }

	/**
	 * setIdClient method
	 * @param idClient
	 */
	public void setIdClient(int idClient) { this.idClient = idClient; }

	/**
	 * getIdProdus method
	 * @return
	 */
	public int getIdProdus() { return idProdus; }
	/**
	 * getCantitateCeruta method
	 * @return
	 */
	public int getCantitateCeruta() { return cantitateCeruta; }

	/**
	 * toString method - used to display the details about the orders
	 * @return
	 */
	@Override
	public String toString() {
		return "OrderBLL [idClient=" + idClient + ", idProdus=" + idProdus + ", cantitateCeruta=" + cantitateCeruta;}

}
