/**
 * Represents a single location of a retail business and includes data about
 * sales over the past twelve months.
 *
 * Student must complete this class as part of CS206 Spring 2020 Programming Assignment 1
 *
 * @author YOUR NAME(S)
 *
 * Tutors or other helpers: enter name of people other than the professor who
 * helped you with this assignment. Give a short description of the help
 * provided by each person.
 */
import java.util.Arrays;

public class RetailLocation {

	/** The identification number of this retail location */
	private int locationId;

	/** The mailing address of this retail location */
	private Address mailingAddress;

	/** The total sales per month for the past twelve months for this retail location.
	 * The data for the most recent month is at index 0, with earlier months in order
	 * so that the data from 12 months ago is at index 11 */
	private double[] monthlySales;

	/**
	 * Constructor initializes data members to parameter values.
	 *
	 * @param i The identification number of this retail location
	 * @param a The mailing address of this retail location
	 * @param m The total sales per month for the past twelve months for this retail location
	 */
	public RetailLocation(int i, Address a, double[] m) {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		setLocationId(i);
		setMailingAddress(a);
		setMonthlySales(m);
	}

	/**
	 * Default constructor initializes data members to default values
	 */

	public RetailLocation() {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		this(0, new Address(), new double[1]);



	}

	/**
	 * Returns the identification number of this retail location.
	 *
	 * @return The identification number of this retail location
	 */
	public int getLocationId() {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		return locationId;
	}

	/**
	 * Returns a copy of the mailing address of this retail location.
	 *
	 * @return A copy of the mailing address of this retail location
	 */
	public Address getMailingAddress() {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		Address adr = new Address(mailingAddress.getStreet(), mailingAddress.getCity(), 
				mailingAddress.getState(), mailingAddress.getZip());

		return adr;
	}

	/**
	 * Copies the total sales per month for the past twelve months into the
	 * parameter array.
	 *
	 * @param m An array to copy the total sales per month into. Array length must be 12.
	 */
	public void getMonthlySales(double[] m) {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		for(int i = 0; i < monthlySales.length; i++) {
			m[i] = monthlySales[i];
		}


	}

	/**
	 * Sets the identification number of this retail location.
	 *
	 * @param i The identification number of this retail location
	 */
	public void setLocationId(int i) {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		locationId = i;
	}

	/**
	 * Sets the mailing address of this retail location.
	 *
	 * @param a The mailing address of this retail location
	 */
	public void setMailingAddress(Address a) {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		Address temp = new Address(a.getStreet(),a.getCity(),a.getState(),a.getZip());
		
		mailingAddress = temp;
	}

	/**
	 * Sets the monthly sales by copying the total sales per month for the past
	 * twelve months from the parameter array.
	 *
	 * @param m An array to copy the total sales per month from. Array length must be 12.
	 */
	public void setMonthlySales(double[] m) {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		monthlySales = new double[12];
		for(int i = 0; i < m.length; i++) {
			monthlySales[i] = m[i];
		}
	}

	/**
	 * Returns a string representation of this RetailLocation object in the form:
	 * locationId; mailingAddress; list of monthly sales
	 *
	 * @return A string representation of this RetailLocation object
	 */
	public String toString() {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		getMonthlySales(monthlySales);
		String sales = "";
		for(int i = 0; i < monthlySales.length; i++) {
			sales+= monthlySales[i];
		}
		return getLocationId() + ", " + getMailingAddress() +
				", " + sales;
	}

	/**
	 * Returns true if the parameter object is the equal to this Address
	 * object. Equality is defined as having the same values for all data
	 * members.
	 *
	 * @param obj The object to compare this object with
	 * @return True if the two objects are the same
	 */
	public boolean equals(Object obj) {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		boolean equal = false;

		if(obj instanceof RetailLocation) {
			RetailLocation rl = (RetailLocation) obj;
			double[] tempSales = new double[12];
			rl.getMonthlySales(tempSales);
			
			if((rl.getLocationId() == getLocationId()) && 
					(rl.getMailingAddress().equals(getMailingAddress())) && 
					(Arrays.equals(tempSales, monthlySales))) {
				equal = true;
			} }


		return equal;



	}

	/**
	 * Returns the total sales from the past twelve months. This is the sum
	 * of the values in the sales per month data member.
	 *
	 * @return The total sales from the past twelve months
	 */
	public double getTotalSales() {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		double sum = 0;
		for(int i = 0 ; i < monthlySales.length; i++) {
			sum += monthlySales[i];
		}

		return sum;
	}

	/**
	 * Returns the average sales over the past twelve months.
	 *
	 * @return The average sales over the past twelve months
	 */
	public double getAverageSales() {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/
		double avgSum = 0;
		double average = 0;

		for(int i = 0 ; i < monthlySales.length; i++) {
			avgSum += monthlySales[i];
		}

		average = avgSum/monthlySales.length;

		return average;

	}

	/**
	 * Updates the monthly sales array. Drops the oldest month's data and
	 * adds the newest month's data that is passed as the parameter. In order
	 * to maintain the oldest to newest order of the array, the values in the
	 * array are shifted between when the oldest is dropped and the newest is
	 * added. Assume sales are ordered with newest at index 0 and oldest at the
	 * highest index.
	 *
	 * @param d Total sales for the new month
	 */
	public void updateMonthlySales(double d) {
		/**** STUDENT MUST COMPLETE THIS METHOD ****/

		double[] tempSales = new double[monthlySales.length];

		tempSales[0] = d;

		for(int i = 1; i < monthlySales.length; i++) {
			tempSales[i] = monthlySales[i - 1];
		}

		for(int v = 0; v < monthlySales.length; v++) {
			monthlySales[v] = tempSales[v];
		}


	}
}
