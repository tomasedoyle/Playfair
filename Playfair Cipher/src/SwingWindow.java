import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class SwingWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JMenu fileMenu; // first row
	private JMenu editMenu;
	private JMenuBar menuBar;
	
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem outputLocationItem; // File Menu
	
	private JCheckBoxMenuItem editableItem;
	private JMenuItem encodeItem;
	private JMenuItem decodeItem;
	private JMenuItem keywordItem;

	private JPanel bottomPanel; // contains the following buttons

	private JButton encodeButton; // second row
	private JButton decodeButton; 
	private JTextField outputFileField;
	private JLabel outputFileLabel;
	private JLabel keywordLabel;
	private JTextField keywordField;
	
	private JTextArea fileArea;	// text Area
	
	private BorderLayout layout;
	private BoxLayout bottomPanelLayout;
	
	public SwingWindow () {
				
	}
	
	public void buildAndRunGUI () {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		
		bottomPanel = new JPanel();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		encodeButton = new JButton("Encode");
		decodeButton = new JButton("Decode");
		outputFileLabel = new JLabel("Output filename (.txt): ");
		keywordLabel = new JLabel("Keyword: ");
		keywordField = new JTextField();
		
		fileArea = new JTextArea();
		encodeItem = new JMenuItem("Encode Text");
		decodeItem = new JMenuItem("Decode Text");
		editableItem = new JCheckBoxMenuItem("Allow Editing?");
		outputFileField = new JTextField();
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		saveAsItem = new JMenuItem("Save As");
		keywordItem = new JMenuItem("Set Keyword");
		outputLocationItem = new JMenuItem("Set Output Folder");
		menuBar = new JMenuBar();
		
		fileMenu.add(openItem);	
		fileMenu.add(saveItem);	
		fileMenu.add(saveAsItem);	
		fileMenu.add(outputLocationItem);
		editMenu.add(editableItem);
		editMenu.add(encodeItem);
		editMenu.add(decodeItem);
		editMenu.add(keywordItem);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));   // spacing in between components
		bottomPanel.add(encodeButton);	
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		bottomPanel.add(decodeButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		bottomPanel.add(outputFileLabel);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		bottomPanel.add(outputFileField);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		bottomPanel.add(keywordLabel);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		bottomPanel.add(keywordField);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		layout = new BorderLayout();
		bottomPanelLayout = new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS);
		bottomPanel.setLayout(bottomPanelLayout);
		
		setLayout(layout);
		
		add(menuBar, BorderLayout.NORTH);
		add(fileArea, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		resizeApplication(); // alters application window size and components for high DPI screens
	}
	
	public void resizeApplication() {
		setSize(getOptimalWindowSize()); // sets window size based on screen resolution
		setComponentSizes(); // sets individual component sizes (by manipulating fonts)
	}

	private void setComponentSizes() {
		if (screenIsHighRes()) {
			encodeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			decodeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			menuBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			editMenu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			fileMenu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			
			editableItem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			encodeItem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			decodeItem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			keywordItem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			openItem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			saveItem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			saveAsItem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			outputLocationItem.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			
			fileArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			
			outputFileLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			keywordLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			keywordField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
			outputFileField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		}
	}
	
	public boolean screenIsHighRes() {  // basic, but hey, its swing
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		return (size.getHeight() >= 1080 || size.getWidth() >= 1920);
	}

	
	public Dimension getOptimalWindowSize() {   //// returns a screen size base on the screen resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = (int)screenSize.getHeight();
		int width = (int)screenSize.getWidth();
		
		int windowHeight = (int) Math.max(300, (int)height*0.6);
		int windowWidth = (int) Math.max(400, (int)width*0.6);
		
		return new Dimension(windowWidth, windowHeight);
	}
	
	public void addEncodeListener(ActionListener listener) {
		encodeButton.addActionListener(listener);
	}
	
	public void addDecodeListener(ActionListener listener) {
		decodeButton.addActionListener(listener);
	}
	
	public void addEditableItemListener(ActionListener listener) {
		editableItem.addActionListener(listener);
	}
	
	public void addMenuListener (ActionListener listener) {
		editableItem.addActionListener(listener);
		encodeItem.addActionListener(listener);
		decodeItem.addActionListener(listener);
		keywordItem.addActionListener(listener);
		openItem.addActionListener(listener);
		saveItem.addActionListener(listener);
		saveAsItem.addActionListener(listener);
		outputLocationItem.addActionListener(listener);
	}
	
	public String getOutputFileText() {
		return outputFileField.getText();
	}
	
	public void setOutputFileText(String text) {
		outputFileField.setText(text);
	}
	
	public JTextArea getTextArea() {
		return fileArea;
	}
	
	public String getTextAreaText() {
		return fileArea.getText();
	}
	
	public String getKeywordText() {
		while (keywordField.getText().equals("")) {
			keywordField.setText(JOptionPane.showInputDialog(null, "Enter a keyword: "));
		}
		return keywordField.getText();
	}

	public void setTextAreaText(String destinationFileText) {
		fileArea.setText(destinationFileText);		
	}

	public void setKeywordText(String keyword) {
		keywordField.setText(keyword);		
	}
} 
