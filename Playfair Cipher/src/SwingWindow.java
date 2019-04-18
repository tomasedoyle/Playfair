import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class SwingWindow extends JFrame {
	
	private JButton encodeButton;
	private JButton decodeButton;
	private JTextField originFileField;
	private JTextField destinationFileField;
	private JTextArea originFileArea;
	private JTextArea destinationFileArea;
	private JScrollPane originFilePane;
	private JScrollPane destinationFilePane;
	
	private BorderLayout mainLayout;
	private FlowLayout topLayout;
	private BorderLayout bottomLayout;
	
	private JFrame upperFrame;
	private JFrame lowerFrame;
	
	
	public SwingWindow () {
		setVisible(true);
		setSize(600,600);  // change this for 4k vs hd
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		encodeButton = new JButton("Encode");
		decodeButton = new JButton("Decode");
		originFileField = new JTextField("origin(.txt)");
		destinationFileField = new JTextField("destination(.txt)");
		destinationFileArea = new JTextArea();
		originFileArea  = new JTextArea();
		originFilePane = new JScrollPane();
		destinationFilePane =  new JScrollPane();
		
		upperFrame = new JFrame();
		lowerFrame = new JFrame();
		
		lowerFrame.setLayout(bottomLayout);
		upperFrame.setLayout(topLayout);
		this.setLayout(mainLayout);
		
		upperFrame.add(encodeButton);
		upperFrame.add(decodeButton);
		upperFrame.add(originFileField);
		upperFrame.add(destinationFileField);
		
		lowerFrame.add(BorderLayout.SOUTH, destinationFileArea);
		lowerFrame.add(BorderLayout.NORTH, originFileArea);

		
		this.add(BorderLayout.NORTH,upperFrame);
		this.add(BorderLayout.SOUTH, lowerFrame);

			
	}  ///////////  TODO add action listeners to this class and controller, implement various file functions (read/encode, display to screen etc.)
}
