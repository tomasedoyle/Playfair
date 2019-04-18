import java.util.Scanner;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.File;

public class CipherModel {
	private String keyword;
	private char[][] grid;
	
	public CipherModel() {  // creates object and initializes the encoding grid
		grid = new char[5][5];
	}
	
	public CipherModel(String keyword) { // Initializes an encoding grid and sets up the cipher
		this.keyword = keyword;
		grid = new char[5][5];
		removeRepeatedCharsFromKeyword();

		populateGrid();
	}
	
	public void removeRepeatedCharsFromKeyword() { // removes repeated characters to ensure entire alphabet (sans j) can fit in the encoding grid
		String iterated = "";
		
		int length = keyword.length();
		
		for (int i = 0; i < length; i++) {
			if ((iterated.indexOf(keyword.charAt(i)) < 0)) {
				System.out.println ( "added: " + keyword.charAt(i) + " to: " + iterated);
				iterated = new String (iterated + keyword.charAt(i)); // why aren't strings mutable?!
			}
		}
		
		keyword = iterated;
	}
	
	public void populateGrid() { // uniquely populates the encoding grid given the keyword and rules of playfair cipher
		
		int size = keyword.length();
		int i = 0;
		char letter = 'a';
		
		
		for (; i < size; i++) { // adds keyword to the beginning of the grid, left to right and then top to bottom 
			grid[(i - (i % 5))/5][i % 5] = keyword.charAt(i); 
		}
		
		for (; i < 25; i++) {  // populates the rest of the grid alphabetically excluding letters in the keyword and j
			                   // to account for a 5X5 grid vs 26 letter alphabet
			
			if (keyword.indexOf(letter) < 0 && letter != 'j') {
				grid[(i - (i % 5))/5][i % 5] = letter;
			} else {
				i--; // resets counter so an index in the grid is not skipped if a letter is skipped
			}
			letter++;
		}	
	}
	
	public char[][] getGrid() {
		return grid;
	}
	
	public void printGrid() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		
		System.out.print("Enter a Keyword: ");
		
		Scanner s = new Scanner(System.in);
		
		String k = s.next();
		
		CipherModel model = new CipherModel(k);
			
		model.printGrid();
	
		s.close();
	}
	
}
