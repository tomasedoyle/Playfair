import java.awt.Point;

public class CipherModel {
	private String keyword;
	private String sourceText;
	private String destinationText;
	private boolean encoding;
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
	
	public void setKeywordAndGrid(String k) {
		keyword = k;
		removeRepeatedCharsFromKeyword();
		populateGrid();
	}
	
	public void removeRepeatedCharsFromKeyword() { // removes repeated characters to ensure entire alphabet (sans j) can fit in the encoding grid
		String iterated = "";
		
		int length = keyword.length();
		
		for (int i = 0; i < length; i++) {
			if ((iterated.indexOf(keyword.charAt(i)) < 0)) {
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
			if (keyword.indexOf(letter) < 0 && letter != 'j') { // to account for a 5X5 grid vs 26 letter alphabet
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
	

	public void translateString () {		
		
		//sourceText = sourceText.replaceAll(" ", ""); ///////// preparing string for encoding
		//sourceText = sourceText.replaceAll("//,$", ""); ///////// preparing string for encoding
		//sourceText = sourceText.replaceAll("/.", ""); ///////// preparing string for encoding
		//sourceText = sourceText.replaceAll("/-", ""); ///////// preparing string for encoding
		sourceText = sourceText.replaceAll("j", "i"); ///////// preparing string for encoding

		System.out.println("This is the source text: " + sourceText);

		if (sourceText.length() % 2 == 1) {
			sourceText = sourceText + "x";
		}
		
		String out = "";
		int length = sourceText.length();   // Encoding the String two characters at a time
		
		if (encoding == true) {
			for (int i = 0; i < length; i += 2) {
				out = out + encode(sourceText.substring(i, i + 2));
			}	
		} else {
			for (int i = 0; i < length; i += 2) {
				out = out + decode(sourceText.substring(i, i + 2));
			}
		}		
		
     	destinationText = out;
	}

	public String encode(String characters) {
		Point a = getPointOfCharacterString(characters.charAt(0)); // gets location of each pair of chars in the model's character grid
		Point b = getPointOfCharacterString(characters.charAt(1));
		
		int x1 = (int) a.getX();
		int x2 = (int) b.getX();
		int y1 = (int) a.getY();
		int y2 = (int) b.getY();

		if (a.getX() == b.getX()) {   //////////////// shifts/encodes characters according to the rules of the cipher
			y1 = (y1 + 1) % 5;
			y2 = (y2 + 1) % 5;
		}
		else if (a.getY() == b.getY()) {
			x1 = (x1 + 1) % 5;
			x2 = (x2 + 1) % 5;
		} else {
			int swap = x1;
			x1 = x2;
			x2 = swap;
		}
		
		return new String(String.valueOf(grid[x1][y1]) + String.valueOf(grid[x2][y2]));
	}

	public String decode(String characters) {
		Point a = getPointOfCharacterString(characters.charAt(0)); // gets location of each pair of chars in the model's character grid
		Point b = getPointOfCharacterString(characters.charAt(1));

		int x1 = (int) a.getX();
		int x2 = (int) b.getX();
		int y1 = (int) a.getY();
		int y2 = (int) b.getY();

		if (a.getX() == b.getX()) {   //////////////// shifts/encodes characters according to the rules of the cipher
			y1 = (y1 == 0) ? y1 = 4 : (y1 - 1);
			y2 = (y2 == 0) ? y2 = 4 : (y2 - 1);	
			}
		else if (a.getY() == b.getY()) {
			x1 = (x1 == 0) ? x1 = 4 : (x1 - 1);		
			x2 = (x2 == 0) ? x2 = 4 : (x2 - 1);
		} else {
			int swap = x1;
			x1 = x2;
			x2 = swap;
		}
		return new String(String.valueOf(grid[x1][y1]) + String.valueOf(grid[x2][y2]));
	}

	public Point getPointOfCharacterString(char s) {  // returns coordinates in grid of a letter
		for (int i = 0; i < 5; i++) {
			for( int j = 0; j < 5; j++ ) {
				if (grid[i][j] == s) {
					return new Point(i,j);
				}
			}
		}
		return null;
	}
	
	public void printEncodingGrid() {
		for (int i = 0; i < 5; i++) {
			for( int j = 0; j < 5; j++ ) {				
					System.out.print(String.valueOf(grid[i][j]));
			}
			System.out.println();
		}
	}
	
	public String getDestinationFileText() {
		return destinationText;
	}
	
	public void setOriginFileText(String text) {
		sourceText = text;
	}
	
	public void setDestinationFileText(String text) {
		destinationText = text;
	}
	
	public void setEncoding (boolean e) {
		encoding = e;
	}

	public void setKeyword(String keyword2) {
		keyword = keyword2;
	}
}
