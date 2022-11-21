package model;

/**
 * This class contains all the details about the clients.
 * There are getters, setters and a toString method which are used for clients' information processing.
 */
public class Client {
	public int idClient;
	public String name;
	public String email;
	public int age;

	/**
	 * This is a constructor with all the information a client must have - idClient, name, email and age.
	 * @param idClient - a primary key (there is a validator for this)
	 * @param name - the name of the client
	 * @param email - a string that matches a specific pattern (there is a validator for this)
	 * @param age - a number between 18 and 100 (there is a validator for this)
	 */
	public Client(int idClient, String name, String email, int age) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.email = email;
		this.age = age; 
	}

	/**
	 * A default constructor.
	 */
	public Client () { }

	/**
	 * A constructor that contains only the id.
	 * @param idClient
	 */
	public Client (int idClient) {
		this.idClient = idClient;
	}

	/**
	 * getIdClient method
	 * @return
	 */

	/**
	 * This is a constructor with the information a client must have but without the id.
	 * @param name - the name of the client
	 * @param email - a string that matches a specific pattern (there is a validator for this)
	 * @param age - a number between 18 and 100 (there is a validator for this)
	 */
	public Client(String name, String email, int age) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public int getIdClient() { return idClient; }

	/**
	 * setIdClient method
	 * @param idClient
	 */
	public void setIdClient(int idClient) { this.idClient = idClient; }

	/**
	 * getName method
	 * @return
	 */
	public String getName() { return name; }

	/**
	 * setName method
	 * @param name
	 */
	public void setName(String name) { this.name = name; }

	/**
	 * getEmail method
	 * @return
	 */
	public String getEmail() { return email; }

	/**
	 * setEmail method
	 * @param email
	 */
	public void setEmail(String email) { this.email = email; }

	/**
	 * getAge method
	 * @return
	 */
	public int getAge() { return age; }

	/**
	 * setAge method
	 * @param age
	 */
	public void setAge(int age) { this.age = age; }

	/**
	 * toString method - used to display the details about the clients
	 * @return
	 */
	@Override
	public String toString() {
		return "ClientBLL [idClient=" + idClient + ", name=" + name + ", email=" + email + ", age=" + age + "]"; }
	
}
