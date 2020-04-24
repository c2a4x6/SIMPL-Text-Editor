import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextStyle {
	
	static JPanel panel = new JPanel();
	
	static int fontSize;
	static String selectedFont;
	static String selectedFontOptions;
	static String[] fontList = { "American Typewriter", "Arial", "Georgia", "Monospaced" };
	static String[] fontOptionsList = { "Plain", "Bold"};
	static JComboBox fontSelect = new JComboBox(fontList);
	static JComboBox fontOptions = new JComboBox(fontOptionsList);
	static JLabel fontSizeLabel = new JLabel("Font size:");
	static JTextField fontSizeField = new JTextField(5);
	
	public static void textStyle() {
		JFrame frame = new JFrame();
		frame.setSize(250, 125);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeOperation();
			}
		});
		
		panel.add(fontSelect);
		panel.add(fontOptions);
		panel.add(fontSizeLabel);
		panel.add(fontSizeField);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setVisible(true);
	}
	
	public static void closeOperation() {
		String temp = fontSizeField.getText();
		fontSize = Integer.parseInt(temp);
		selectedFont = fontSelect.getSelectedItem().toString();
		selectedFontOptions = fontOptions.getSelectedItem().toString();
		
		if(selectedFontOptions.contains("Plain")) {
			Font font = new Font(selectedFont, Font.PLAIN, fontSize);
			Window.textArea.setFont(font);
		} else if(selectedFontOptions.contains("Bold")) {
			Font font = new Font(selectedFont, Font.BOLD, fontSize);
			Window.textArea.setFont(font);
		}
	}
	
	public void errorMessage() {
		JOptionPane.showMessageDialog(panel, "Missing desired font size!", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

}
