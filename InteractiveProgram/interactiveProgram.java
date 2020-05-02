import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class interactiveProgram {

	/**
	 * arrayList of retailLocations to hold the data from the file we read in
	 */
	static List<RetailLocation> locations = new ArrayList<RetailLocation>();

	/**
	 * empty constructor
	 */
	public interactiveProgram() {

	}

	/**
	 * read in the file and for the first element assign it to ID
	 * the next 12 elements assign to an array
	 * the next one assign to city
	 * then state, zip, and the rest of the line to street
	 * put all of those in to a retailLocation object
	 * put that object into the arrayList
	 */
	public void readFile() {
		FileReader read;
		double[] sales = new double[12];

		try {
			read = new FileReader("retailData1.txt");
			Scanner scan = new Scanner(read);
			while(scan.hasNext()) {

				int locId = scan.nextInt();
				for(int i = 0; i < sales.length; i++) {
					sales[i] = scan.nextDouble();
				}
				
				String city = scan.next();
				String state = scan.next();
				String zip = scan.next();
				String street = scan.nextLine();

				Address tempAd = new Address(street, city, state, zip);
				RetailLocation temp = new RetailLocation(locId, tempAd, sales);
				locations.add(temp);


			}


		} catch(Throwable throwable) {
			System.out.println("Trying to read file");
			throwable.printStackTrace();
		} 
	}




	/**
	 * print the options available and let the user choose one. once its chosen, then call the method that 
	 * corresponds to the option
	 */
	public void menu() {
		System.out.println("Choose an option by entering the corresponding number: \n1. Print a list of retail locations \n"
				+ "2. List the monthly sales for a particular retail location \n"
				+ "3. Print the identification number of the retail location that has the highest TOTAL sales for the past twelve months \n"
				+ "4. Print the identification number of the retail location that has the highest AVERAGE sales for the past twelve months \n"
				+ "5. Delete a retail location \n"
				+ "6. Update the monthly sales for a particular retail location by adding the newest month's sales\n"
				+ "7. Quit and save changes\n >");
		Scanner scan = new Scanner(System.in);
		int userChoice;
		userChoice = scan.nextInt();
		if(userChoice == 1) {
			option1();
			menu();
		} else if(userChoice == 2) {
			option2();
			menu();
		} else if(userChoice == 3) {
			option3();
			menu();
		} else if(userChoice == 4) {
			option4();
			menu();
		} else if(userChoice == 5) {
			option5();
			menu();
		} else if(userChoice == 6) {
			option6();
			menu();
		} else if(userChoice == 7) {
			option7();
			menu();
		} else {
			System.out.println("Please enter a valid option");
			userChoice = scan.nextInt();
		}

	}

	/**
	 * go through the locations array list and print the ID and mailing address for each 
	 * then call menu again
	 */
	public static void option1() {
		for(int i = 0; i < locations.size(); i++) {
			System.out.print(locations.get(i).getLocationId());
			System.out.print(" " + locations.get(i).getMailingAddress());
			System.out.println();
		}

	}

	/**
	 * get the locationID input from the user then go through the arraylist of locations
	 * and when that ID is found, assign it to something and print out it's monthly sales.
	 * call menu again
	 */
	public static void option2() {
		System.out.println("Which locationID would you like to see the monthly sales?");
		Scanner scan = new Scanner(System.in);
		int chosenId = scan.nextInt();
		RetailLocation chosen = null;
		for(int v = 0; v < locations.size(); v++) {
			if(locations.get(v).getLocationId() == chosenId) {
				chosen = locations.get(v);
			} 
		}
		if(chosen != null) {
			double[] temp = new double[12];
			chosen.getMonthlySales(temp);
			for(int f = 0; f < temp.length; f++) {
				System.out.print(temp[f] + "\t");
			} 
			System.out.println();
			System.out.println();
		} else {
			System.out.println("No such Id");
		}


	}

	/**
	 * go through the locations arrayList and when the largest total sales are found assign 
	 * that to a temporary object and print it out
	 */
	public static void option3() {
		RetailLocation largest = null;
		for(int i = 0; i < locations.size() - 1; i++) {
			if(locations.get(i).getTotalSales() > locations.get(i + 1).getTotalSales()) {
				largest = locations.get(i);
			}
		}

		System.out.println("Location with highest total sales: ID " + largest.getLocationId());
		System.out.println();

	}

	/**
	 * do the same as the previous method where it goes through the arrayList
	 * until it finds the highest average sales this time and prints it
	 */
	public static void option4() {
		RetailLocation bestAvg = null;
		for(int i = 0; i < locations.size() - 1; i++) {
			if(locations.get(i).getAverageSales() > locations.get(i + 1).getAverageSales()) {
				bestAvg = locations.get(i);
			}
		}

		System.out.println("Location with highest average sales: ID " + bestAvg.getLocationId());
		System.out.println();

	}

	/**
	 * prompts the user for which location to delete and goes through the arrayList until it finds it and then removes
	 * it. If it doesn't find it then tell them it's not a valid id
	 */
	public static void option5() {
		System.out.println("Which location would you like to delete?");
		Scanner scan = new Scanner(System.in);
		int locId = scan.nextInt();
		int deleted = -1;
		for(int i = 0; i< locations.size(); i++) {
			if(locations.get(i).getLocationId() == locId) {
				deleted = i;
			}
		}

		if(deleted > -1) {
			locations.remove(deleted);
			System.out.println("Deleted");
			System.out.println();
		} else {
			System.out.println("Not a valid Id");
		}

	}

	/**
	 * ask the user for which location to update, then assign that to a temporary RetailLocation object.
	 * Save the place in the arrayList that its in as well then call the update sales method and get from 
	 * the user what value to update to and then put that object back and replace the old one.
	 */
	public static void option6() {
		System.out.println("Which location would you like to update?");
		Scanner scan = new Scanner(System.in);
		int locId = scan.nextInt();
		RetailLocation locToUpdate = null;
		int arrayLocation = 0;
		for(int i = 0; i < locations.size(); i++) {
			if(locId == locations.get(i).getLocationId()) {
				locToUpdate = locations.get(i);
				arrayLocation = i;
			}
		}

		if (locToUpdate != null) {
			System.out.println("What is the value of the newest month's sales?");
			double newSale;
			newSale = scan.nextDouble();
			locToUpdate.updateMonthlySales(newSale);
			locations.add(arrayLocation, locToUpdate);
			System.out.println();
		} else if(locToUpdate == null) {
			System.out.println("No location Id found");
		}



	}

	/**
	 * loop through the locations arrayList and print out the data in the same order that
	 * the file gives it then exit the program
	 */
	public static void option7() {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(new FileWriter("retailData1.txt"));

			for(int i = 0; i < locations.size(); i++) {
				writer.print(locations.get(i).getLocationId() + "\t");

				double[] tempSales = new double[12];
				locations.get(i).getMonthlySales(tempSales);

				for(int j = 0; j < tempSales.length; j++) {
					writer.print(tempSales[j] + " ");
				}

				writer.print(locations.get(i).getMailingAddress().getCity() + "\t");
				writer.print(locations.get(i).getMailingAddress().getState() + "\t");
				writer.print(locations.get(i).getMailingAddress().getZip() + "\t");
				writer.print(locations.get(i).getMailingAddress().getStreet());
				writer.println();

			}


		} catch(Throwable throwable) {
			System.out.println("Trying to write file");
			throwable.printStackTrace();
		} finally {
			writer.close();
		}
		System.exit(0);

	}


	/**
	 * call the readFile method so the file is read and then call the menu method where everything else gets called from.
	 * @param args
	 */
	public static void main(String[] args) {
		interactiveProgram me = new interactiveProgram();
		me.readFile();
		me.menu();
	}

}
