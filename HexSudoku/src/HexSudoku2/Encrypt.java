/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HexSudoku2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author joaob
 */
public class Encrypt {
    
    public SecretKey keyGenerator() throws NoSuchAlgorithmException {
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey myDesKey = keygenerator.generateKey();
        return myDesKey;
    }

    public byte[] encrypted(SecretKey myDesKey) throws FileNotFoundException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        File file = new File("C:\\Users\\joaob\\OneDrive\\Documentos\\GitHub\\HexSudokuTeste\\HexSudoku\\src\\HexSudoku2\\accounts.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "";
        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }


        Cipher desCipher;
        desCipher = Cipher.getInstance("DES");

        byte[] text = fileContent.getBytes("UTF8");

        desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
        byte[] textEncrypted = desCipher.doFinal(text);

        String s = new String(textEncrypted);
        
        FileWriter writer = new FileWriter("C:\\Users\\joaob\\OneDrive\\Documentos\\GitHub\\HexSudokuTeste\\HexSudoku\\src\\HexSudoku2\\encrypedAcc.txt");
        writer.write(s);
        writer.close();
        return textEncrypted;
    }

    public void decrypted(SecretKey myDesKey, byte[] textEncrypted) throws FileNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException {
        File file = new File("C:\\Users\\joaob\\OneDrive\\Documentos\\GitHub\\HexSudokuTeste\\HexSudoku\\src\\HexSudoku2\\encrypedAcc.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "";
        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }


        Cipher desCipher;
        desCipher = Cipher.getInstance("DES");

        desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
        byte[] textDecrypted = desCipher.doFinal(textEncrypted);

        String s = new String(textDecrypted);
        
        FileWriter writer = new FileWriter("C:\\Users\\joaob\\OneDrive\\Documentos\\GitHub\\HexSudokuTeste\\HexSudoku\\src\\HexSudoku2\\decrypted.txt");
        writer.write(s);
        writer.close();
    }
}
