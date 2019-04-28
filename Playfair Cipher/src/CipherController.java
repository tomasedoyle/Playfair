import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class CipherController {
	
	private CipherModel model;
	private SwingWindow window;
	private String selectedInputFile;
	
	public CipherController(CipherModel model, SwingWindow window) {
		this.model = model;
		this.window = window;
	}
	
	public static void main(String args[]) {

		CipherController cipher = new CipherController(new CipherModel(), new SwingWindow());

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				cipher.runProgram();
			}
		});
	}
	
	public void runProgram () {
		window.buildAndRunGUI();
		window.addEncodeListener(new EncodeListener());
		window.addDecodeListener(new DecodeListener());
		window.addEditableItemListener(new EditableItemListener());
		window.addMenuListener(new MenuListener());
	}
	
	// IO Methods
	//
	
	public String getFileText (String filename) { // processes file (removes spaces, insures even number of chars) and translates

		try {
			Scanner scanner = new Scanner(new File(filename));
			String line = "";

			while (scanner.hasNextLine()) {
				line += scanner.nextLine();	
			}

			scanner.close();
			System.out.println(line);
			return line;

		} catch (Exception e) {
			System.out.println(" Error Opening or Closing File");
			return null;
		} 
	}
	
	public void saveTextToFile () { // attempts to write to the file stored in the class - or asks user for a filename
		
		if (selectedInputFile == null ||selectedInputFile.equals("")) {
			selectedInputFile = JOptionPane.showInputDialog(null, "Enter your filename (excluding .txt): ");
		}
		
		try 
		{
			PrintWriter outFile = new PrintWriter(window.getOutputFileText() + ".txt");
			outFile.write(window.getTextAreaText());
			outFile.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sorry the file could not be opened");
		}
	}
	
	public void writeTextToFile () { // attempts to write to the file stored in the class - or asks user for a filename
		
		if (window.getOutputFileText() == null || window.getOutputFileText().equals("")) {
			window.setOutputFileText(JOptionPane.showInputDialog(null, "Enter your filename (excluding .txt): "));
		}
		
		try 
		{
			PrintWriter outFile = new PrintWriter(window.getOutputFileText() + ".txt");
			outFile.write(window.getTextAreaText());
			outFile.close();
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sorry the file could not be opened");
		}
	}
	
	public SwingWindow getWindow() {
		return window;
	}
	
	///// Listener's and GUI controllers
	///// 
	
	class EncodeListener implements ActionListener { // Runs after encode button is pressed

		@Override
		public void actionPerformed(ActionEvent arg0) {
						
			model.setKeywordAndGrid(window.getKeywordText());
			model.setEncoding(true);
			model.setOriginFileText(window.getTextAreaText());
			model.translateString();
			window.setTextAreaText(model.getDestinationFileText());
			writeTextToFile();
		}
	}

	class DecodeListener implements ActionListener { // Runs after decode button is pressed
		@Override
		public void actionPerformed(ActionEvent arg0) {
			model.setKeywordAndGrid(window.getKeywordText());
			model.setEncoding(false);
			model.setOriginFileText(window.getTextAreaText());
			model.translateString();
			window.setTextAreaText(model.getDestinationFileText());
			writeTextToFile();
		}	
	}
	
	class EditableItemListener implements ActionListener { //  Toggles whether text field is editable (checkbox in menu bar)
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (((JCheckBoxMenuItem)arg0.getSource()).isSelected()) {
					((JTextArea)window.getTextArea()).setEditable(true);	
			}else {
					((JTextArea)window.getTextArea()).setEditable(false);
			}
		}	
	}
	
	class MenuListener implements ActionListener {   ///////// performs file operations from menu bar

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String text = ((JMenuItem)arg0.getSource()).getText();
			model.setKeyword(window.getKeywordText());

			switch (text) {
			
			case "Open":
				window.setTextAreaText(getFileText(window.getOutputFileText()));
				break;
			case "Save":
				saveTextToFile();
				break;
			case "Save As":
				selectedInputFile = JOptionPane.showInputDialog(null, "Enter a filename (excluding .txt)");
				saveTextToFile();	
				break;
			case "Set Output Folder":
				String filename2 = JOptionPane.showInputDialog(null, "Enter a filename (excluding .txt)");
				window.setOutputFileText(filename2);	
				break;
			case "Allow Editing?":
				if (((JCheckBoxMenuItem)arg0.getSource()).isSelected()) {
					((JTextArea)window.getTextArea()).setEditable(true);	
				}else {
					((JTextArea)window.getTextArea()).setEditable(false);
				}
				break;
			case "Encode Text":
				model.setKeywordAndGrid(window.getKeywordText());
				model.setEncoding(true);
				model.setOriginFileText(window.getTextAreaText());
				model.translateString();
				window.setTextAreaText(model.getDestinationFileText());
				writeTextToFile();
				break;
			case "Decode Text":
				model.setKeywordAndGrid(window.getKeywordText());
				model.setEncoding(false);
				model.setOriginFileText(window.getTextAreaText());
				model.translateString();
				window.setTextAreaText(model.getDestinationFileText());
				writeTextToFile();
				break;
			case "Set Keyword":
				String keyword = JOptionPane.showInputDialog(null, "Enter a keyword: ");
				model.setKeywordAndGrid(window.getKeywordText());
				window.setKeywordText(keyword);	
				break;
			}			
		}	
	}
}
