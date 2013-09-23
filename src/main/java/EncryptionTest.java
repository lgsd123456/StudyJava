import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.CryptoPrimitive;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class EncryptionTest {

	public static void main(String[] args){
		try {
//			if(args[0].equals("-genkey")){
//				KeyGenerator keygen = KeyGenerator.getInstance("AES");
//				SecureRandom random = new SecureRandom();
//				keygen.init(random);
//				SecretKey key = keygen.generateKey();
//				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1]));
//				out.writeObject(key);
//				out.close();
//			}
//			else{
//				int mode;
//				if(args[0].equals("-encrypt")) mode = Cipher.ENCRYPT_MODE;
//				else mode = Cipher.DECRYPT_MODE;
//				
//				ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
//				Key key = (Key)keyIn.readObject();
//				keyIn.close();
//				
//				InputStream in = new FileInputStream(args[1]);
//				OutputStream out = new FileOutputStream(args[2]);
//				Cipher cipher = Cipher.getInstance("AES", "SunJCE");
//				cipher.init(mode, key);
//				
//				crypt(in, out, cipher);
//				in.close();
//				out.close();
//			}			
			
			//产生key
			
//			KeyGenerator keygen = KeyGenerator.getInstance("AES");
//			SecureRandom random = new SecureRandom();
//			keygen.init(random);
//			SecretKey key = keygen.generateKey();
//			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("key"));
//			out.writeObject(key);
//			out.close();
			
			
			ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("key"));
			Key key = (Key)in1.readObject();
			in1.close();
			//加密
//			Cipher cipher = Cipher.getInstance("AES");
//			cipher.init(Cipher.ENCRYPT_MODE, key);
//			CipherOutputStream out = new CipherOutputStream(new FileOutputStream("encrypt.xml"), cipher);
//			int blockSize = cipher.getBlockSize();
//			byte[] bytes = "I am sorry,Thank you!".getBytes();
//			out.write(bytes, 0, bytes.length);
//			out.flush();
//			out.close();
			
			
			//解密
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			CipherInputStream in = new CipherInputStream(new FileInputStream("encrypt.xml"), cipher);
			int inLength = 0;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			while((inLength = in.read()) != -1){
				outputStream.write(inLength);
			}
			System.out.println(outputStream.toString());
			outputStream.close();
			in.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void crypt(InputStream in, OutputStream out, Cipher cipher)throws IOException, GeneralSecurityException{
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		byte[] inBytes = new byte[blockSize];
		byte[] outBytes = new byte[outputSize];
		
		int inLength = 0;
		boolean more = true;
		while(more){
			inLength = in.read(inBytes);
			if(inLength == blockSize){
				int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
				out.write(outBytes, 0, outLength);
			}
			else {
				more = false;
			}
		}
		if(inLength > 0) outBytes = cipher.doFinal(inBytes, 0, inLength);
		else outBytes = cipher.doFinal();
		out.write(outBytes);
	}
}
