import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Network3rdStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fileStream = new FileOutputStream("tt.dat");
			generateCharacters(fileStream);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public static void generateCharacters(OutputStream out) throws IOException {
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		
		int start = firstPrintableCharacter;
		while(true){
			for(int i = start; i < start + numberOfCharactersPerLine; i++){
				out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter);
			}
			out.write('\r');
			out.write('\n');
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
		}
	}
	
	public static void generateBetterCharacters(OutputStream out) throws IOException {
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		byte[] line = new byte[numberOfCharactersPerLine + 2];
		
		int start = firstPrintableCharacter;
		while(true){
			int i = 0;
			for(i = start; i < start + numberOfCharactersPerLine; i++){
				line[i] = (byte)(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter);
			}
			line[i++] = (byte)'\r';
			line[i] = (byte)'\n';
			out.write(line);
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
		}
	}

}
