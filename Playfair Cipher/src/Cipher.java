import java.awt.Point;
import java.io.File;
import java.util.Scanner;

public class Cipher {
	
	private String filename;
	private CipherModel model;
	private SwingWindow window;
	

	public void translateFile (String filename) { // processes file (removes spaces, insures even number of chars) and translates

		try {

			Scanner scanner = new Scanner(new File(filename));
			String out = "";
			String line = "";

			while (scanner.hasNextLine()) {
				line += scanner.nextLine();	
			}

			line = line.replaceAll(" ", "");

			if (line.length() % 2 == 1) {
				line += "x";
			}

			for (int i = 0; i < line.length(); i += 2) {
				out += translate(line.substring(i, i + 2));
			}	

			System.out.print(out);
			
			scanner.close();

		} catch (Exception e) {
			System.out.println(" Error Opening File");
		} 
	}

	public String translate(String characters) {
		Point a = getPointOfCharacterString(characters.substring(0,1).charAt(0)); // gets location of each pair of chars in the model's character grid
		Point b = getPointOfCharacterString(characters.substring(1,2).charAt(0));

		int x1 = (int) a.getX();
		int x2 = (int) b.getX();
		int y1 = (int) a.getY();
		int y2 = (int) b.getY();

		if (a.getX() == b.getX()) {   ////////////////    shifts/encodes characters according to the rules of the cipher
			System.out.print("vertical shift executed: ");
			y1 = (y1 + 1) % 5;
			y2 = (y2 + 1) % 5;
		}
		else if (a.getY() == b.getY()) {
			x1 = (x1 + 1) % 5;
			x2 = (x2 + 1) % 5;
			System.out.print("horizontal shift executed: ");	
		} else {
			int swap = x1;
			x1 = x2;
			x2 = swap;
			System.out.print("alternate diagonal shift executed: ");
		}
		System.out.println(model.getGrid()[x1][y1] + model.getGrid()[x2][y2]);
		return new String(String.valueOf(model.getGrid()[x1][y1]) + String.valueOf(model.getGrid()[x2][y2]));

	}

	public String decode(String characters) {
		Point a = getPointOfCharacterString(characters.substring(0,1).charAt(0)); // gets location of each pair of chars in the model.getGrid()
		Point b = getPointOfCharacterString(characters.substring(1,2).charAt(0));

		int x1 = (int) a.getX();
		int x2 = (int) b.getX();
		int y1 = (int) a.getY();
		int y2 = (int) b.getY();

		if (a.getX() == b.getX()) {   ////////////////    shifts/encodes characters according to the rules of the cipher
			System.out.print("vertical shift executed: ");
			y1 = (y1 - 1) % 5;
			y2 = (y2 - 1) % 5;
		}
		else if (a.getY() == b.getY()) {
			x1 = (x1 - 1) % 5;
			x2 = (x2 - 1) % 5;
			System.out.print("horizontal shift executed: ");	
		} else {
			int swap = x1;
			x1 = x2;
			x2 = swap;
			System.out.print("alternate diagonal shift executed: ");
		}


		System.out.println(model.getGrid()[x1][y1] + model.getGrid()[x2][y2]);

		return new String(String.valueOf(model.getGrid()[x1][y1]) + String.valueOf(model.getGrid()[x2][y2]));

	}

	public Point getPointOfCharacterString(char s) {
		for (int i = 0; i < 5; i++) {
			for( int j = 0; j < 5; j++ ) {
				if (model.getGrid()[i][j] == s) {
					return new Point(i,j);
				}
			}
		}

		return null;
	}



}
