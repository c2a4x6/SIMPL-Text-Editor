
public class CaesarCipher {
	
	static int key = 3;
	static String encryptedText = "";
	static String decryptedText = "";
	
	public static void encrypt(String text) {
		char ch;
		for(int i = 0; i < text.length(); i++) {
			ch = text.charAt(i);
			
			if(ch >= 'a' && ch <= 'z') {
				ch = (char)(ch + key);
				
				if(ch > 'z') {
					ch = (char)(ch - 'z' + 'a' - 1);
				}
				
				encryptedText += ch;
			}
			else if(ch >= 'A' && ch <= 'Z') {
				ch = (char)(ch + key);
				
				if(ch > 'Z' ) {
					ch = (char)(ch - 'Z' + 'A' - 1);
				}
				
				encryptedText += ch;
			}
			else {
				encryptedText += ch;
			}
		}
	}
	
	public static void decrypt(String text) {
		char ch;
		for(int i = 0; i < text.length(); i++) {
			ch = text.charAt(i);
			
			if(ch >= 'a' && ch <= 'z') {
				ch = (char)(ch - key);
				
				if(ch > 'z') {
					ch = (char)(ch + 'z' - 'a' + 1);
				}
				
				decryptedText += ch;
			}
			else if(ch >= 'A' && ch <= 'Z') {
				ch = (char)(ch - key);
				
				if(ch > 'Z') {
					ch = (char)(ch + 'Z' - 'A' + 1);
				}
				
				decryptedText += ch;
			}
			else {
				decryptedText += ch;
			}
		}
	}
	
	public static void cleanUp() {
		encryptedText = "";
		decryptedText = "";
	}

}
