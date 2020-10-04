/**
 * @author FaridaBelhous
 * @date 10/01/2020
 * Phase1-project
 * simpliLearn
 * LockedMe.com Application
 */

package root;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class LockedMeMain {

	public static void main(String[] args) throws IOException {
		// Create a new scanner 
		Scanner input = new Scanner(System.in);
		int option = 0;	
		
		// Printing the application name and the developer details
		welcomeInfo();
		

		
		// A do-while loop to give the user options to select
		do {
			// Printing the menu
			menu();
			divider();
			
			try {
				option = Integer.parseInt(input.nextLine());
				
			} catch(NumberFormatException exp) {
				System.out.println("Exception : " + exp);
			}
			
			// Using the switch to select the right option
			switch(option) {
			
			case 1:
				getListOfFiles("."); // For the current directory
				divider();
				break;
				
			case 2:
				System.out.println("Please enter the file name with its extension ");
				
					String aFileName = input.nextLine();
					addFile(aFileName);			
				divider();
				break;
				
			case 3:
				System.out.println("Please enter the file name with its extension you are looking for : ");
				String myFile = input.nextLine();
				searchFile(myFile);
				divider();
				break;
				
			case 4:
				System.out.println("Please enter the file name with its extension that you want to delete");
				String aFile = input.nextLine();
				deleteFile(aFile);
				divider();
				break;
				
			case 5:
				System.out.println("----Program ended!----");
				System.exit(0); // Terminate JVM
				break;
				
			default:
				System.out.println("You should enter a number between 1 and 5 only !!");
				divider();
				break;
			
			}
			
		} while(option != 5);
		input.close();// close scanner
		
	} // Main() ends
	
	
	
	/**
	 *  Printing the application name and the developer details
	 */
	public static void welcomeInfo() {
		
		Date date = new Date();
		System.out.println("-------------------------------------------------------------");
		System.out.println("		Welcome to : LockedMe.com App");
		System.out.println("		The developer details : Farida Belhous");
		System.out.println("		The date :" + date);
		System.out.println("-------------------------------------------------------------");
		System.out.println();
	}
	
	/**
	 * To printout the menu
	 */
	public static void menu() {
		
		System.out.println("Please enter a number from 1 - 5 : ");
		System.out.println("1 : Display the files names  ");
		System.out.println("2 : Add a new file ");
		System.out.println("3 : Search for a file");
		System.out.println("4 : Delete a file");
		System.out.println("5 : Exit");
		System.out.println();
		
	}
	
	
	/**
	 * Printouts to divide the outputs into clear sections
	 */
	public static void divider() {
		System.out.println("----------------");
		System.out.println();
	}
	
	
	
	/**
	 * To retrieve files /directories by names in ascending order
	 * @param directoryPath : The path to the directory (current directory)
	 * @void prints out a list of files 
	 * in an ascending order
	 */
	public static void getListOfFiles(String directoryPath) {
		File folder = new File(directoryPath);
		File[] filesList = folder.listFiles();
		if(!folder.exists()) {
			System.out.println("The directory is empty");
		}
		else {
			Arrays.sort(filesList, (file1, file2) -> {
				if (file1.isDirectory() && !file2.isDirectory()) {
				    return -1;
				  }
				  else if (!file1.isDirectory() && file2.isDirectory()) {
					  return 1;
				  }
				  else {
					return file1.compareTo(file2);
				}	
			});
			  
			for (int i = 0; i < filesList.length; i++) {
				if(!filesList[i].isHidden()) {
					if(filesList[i].isDirectory()) {
						System.out.println("Directory :" + filesList[i].getName());
					}
					else {
						System.out.println("File :" + filesList[i].getName());
					}
				}
			 
			}
		}
		
	}
	
	/**
	 * To add a new file
	 * @param fileName : the name of the file with its extension
	 * @throws FileAlreadyExistsException
	 * @void ,it prints out a confirmation message when the file is added 
	 * or a message when there is a file with the same name
	 * it ignores the case-sensitive
	 *
	 */
	public static void addFile(String fileName) throws FileAlreadyExistsException {
		try {
			File myFile = new File(fileName);
			
			if (myFile.createNewFile()) {
				System.out.println("A new file is created : " + myFile.getName());  
				
			} else {
				System.out.println("A file already exists with the same name !");  

			}
		}
		catch (IOException exp) {
			exp.printStackTrace();
		}
	}
	
	 
	/**
	 * To search for a specific file
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public static void searchFile(String fileName) throws FileNotFoundException {
		
		
		File folder = new File(".");
		File[] filesList = folder.listFiles();
		
		for (File file : filesList) {
			if (file.getName().equals(fileName)) {
				System.out.println(fileName + " exists");
				break;
			} 
			else {
				System.out.println(" File Not Found ");
				break;
			}
		}
		
	}
		
	
	
	
	/**
	 * To delete a specific file
	 * @param fileName : the name of the file you want to delete
	 * prints out a message when the file is deleted
	 * it is case-sensitive
	 */
	public static void deleteFile(String fileName){
		File myFile = new File(fileName);
		try {
			
			if (myFile.delete()) {
				System.out.println("The file : "+ myFile.getName() + " is deleted ");  
				
			} else {
				System.out.println("The file you entered does not exist !");  

			} 
		} catch (Exception exp) {
			System.out.println("Exception :" );
			exp.printStackTrace();;
		} 

	}

}
