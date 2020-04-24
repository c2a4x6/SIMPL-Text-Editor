import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EncryptionSettings {
	
	static JFrame frame = new JFrame();
	static JLabel caesarLabel = new JLabel("Caesar Cipher Key:");
	static JTextField caesarKeyField = new JTextField("3", 5);
	static JPanel panel = new JPanel();
	
	public static void encryptionSettings() {
		frame.setSize(250, 125);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeOperation();
			}
		});
		
		panel.add(caesarLabel);
		panel.add(caesarKeyField);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeOperation();
			}
		});
		frame.setVisible(true);
	}
	
	public static void closeOperation() {
		String temp = caesarKeyField.getText().toString();
		CaesarCipher.key = Integer.parseInt(temp);
	}

}
