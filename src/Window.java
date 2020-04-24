import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public class Window {
	
	static int WIDTH = 500;
	static int HEIGHT = 500;
	static String title = "SIMPL Text Editor";
	static JFrame frame = new JFrame();
	static JTextArea textArea = new JTextArea();
	static JScrollPane scrollPane = new JScrollPane(textArea);
	static String selectedCipher;
	
	public static void main(String[] args) {
		window();
		int lineCount = textArea.getLineCount();
		String text = textArea.getText();
		
		JPanel panel = new JPanel();
		JLabel counterLabel = new JLabel("");
		
		String[] cryptoStr = { "Caesar Cipher" };
		JComboBox cryptoList = new JComboBox(cryptoStr);
		JButton encryptButton = new JButton("Encrypt");
		encryptButton.addActionListener(new encryptListener());
		JButton decryptButton = new JButton("Decrypt");
		decryptButton.addActionListener(new decryptListener());
		panel.add(cryptoList);
		panel.add(encryptButton);
		panel.add(decryptButton);
		panel.add(counterLabel);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		while(true) {
			selectedCipher = cryptoList.getSelectedItem().toString();
			counterLabel.setText("Total lines: " + lineCount + " Total words: " + wordCount(text));
			lineCount = textArea.getLineCount();
			text = textArea.getText();
		}
	}
	
	public static void open() {
		JFileChooser chooser = new JFileChooser();
		int response;
		
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogTitle("Open...");
		response = chooser.showOpenDialog(frame);
		
		if(response == JFileChooser.APPROVE_OPTION) {
			textArea.setText("");
			String tempStr = chooser.getSelectedFile().getAbsolutePath();
			String title = chooser.getSelectedFile().getName();
			frame.setTitle(title + " - SIMPL Text Editor");
			Path path = Paths.get(tempStr);
			try {
				String readContents = Files.readString(path, StandardCharsets.US_ASCII);
				textArea.insert(readContents, 0);
			} catch (IOException e) {
				System.out.println("ERR: IOException!");
				e.printStackTrace();
			}
		}
	}
	
	public static void saveAs() {
		String text = textArea.getText();
		JFileChooser chooser = new JFileChooser();
		int response; 
		
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogTitle("Save as...");
		response = chooser.showSaveDialog(frame);
		
		if(response == JFileChooser.APPROVE_OPTION) {
			try (FileWriter fw = new FileWriter(chooser.getSelectedFile() + ".txt")){
				fw.write(text.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void checkSelectedCipher(boolean shouldEncrypt) {
		if(selectedCipher.contains("Caesar Cipher")) {
			String text = textArea.getText();
			System.out.println("Caeser Cipher selected!");
			if(shouldEncrypt == true) {
				CaesarCipher.encrypt(text);
				textArea.setText("");
				textArea.setText(CaesarCipher.encryptedText);
			} else {
				CaesarCipher.decrypt(text);
				textArea.setText("");
				textArea.setText(CaesarCipher.decryptedText);
			}
			CaesarCipher.cleanUp();
		}
	}
	
	public static int wordCount(String text) {
		int wordCount = 0;
		
		if(text == null) {
			return wordCount;
		}
		text = text.trim();
		
		if(text.contentEquals("")) {
			return wordCount;
		}
		
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == ' ' ) {
				wordCount++;
			}
		}
		wordCount = wordCount + 1;
		return wordCount;
	}

	public static void window() {
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setResizable(true);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar();
		JMenu fileTab = new JMenu("File");
		JMenu quitTab = new JMenu("Exit");
		JMenu editTab = new JMenu("Edit");
		mb.add(fileTab);
		mb.add(editTab);
		mb.add(quitTab);
		
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new OpenListener());
		JMenuItem saveAsItem = new JMenuItem("Save as...");
		saveAsItem.addActionListener(new SaveAsListener());
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new AboutListener());
		fileTab.add(openItem);
		fileTab.add(saveAsItem);
		fileTab.add(aboutItem);
		
		JMenuItem textStyleItem = new JMenuItem("Text Style");
		textStyleItem.addActionListener(new textStyleListener());
		JMenuItem encryptionSettingsItem = new JMenuItem("Encryption settings");
		encryptionSettingsItem.addActionListener(new encryptionSettingsListener());
		editTab.add(textStyleItem);
		editTab.add(encryptionSettingsItem);
		
		textArea.setLineWrap(true);
		
		frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
		frame.getContentPane().add(BorderLayout.NORTH, mb);
		frame.setVisible(true);
	}
	
}

class encryptionSettingsListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed encryption settings button!");
		EncryptionSettings.encryptionSettings();
	}
}

class textStyleListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed text style button!");
		TextStyle.textStyle();
	}
}

class encryptListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed encrypt button!");
		Window.checkSelectedCipher(true);
	}
}

class decryptListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed decrypt button!");
		Window.checkSelectedCipher(false);
	}
}

class OpenListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed open button!");
		Window.open();
	}
}
class SaveAsListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed save as button!");
		Window.saveAs();
	}
}
class AboutListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed about button!");
		aboutWindow();
	}
	private void aboutWindow() {
		JFrame aboutFrame = new JFrame();
		aboutFrame.setSize(250, 250);
		aboutFrame.setResizable(false);
		aboutFrame.setLocationRelativeTo(null);
		aboutFrame.setAlwaysOnTop(true);
		
		String text = "- Version: 2.0 - by c2a4x6 -";
		JLabel aboutLabel = new JLabel(text, SwingConstants.CENTER);
		
		aboutFrame.getContentPane().add(BorderLayout.CENTER, aboutLabel);
		aboutFrame.setVisible(true);
	}
}