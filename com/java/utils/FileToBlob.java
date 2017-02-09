package com.java.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author Elavarasan
 * @category filehandling(file to blob)
 * @since  08/10/2016 
 * @version jdk 1.8
 *
 */
public class FileToBlob {
	
	/**
	 * 
	 * @param Filepath in String
	 * @return Encoded String of file
	 * @throws IOException
	 * @since  08/10/2016  
     */
	public String fileToEncodedStr(String Filepath) throws IOException {
		File file = new File(Filepath);
		byte[] filebytes = new byte[(int) file.length()];
		DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
		dataInputStream.read(filebytes);
		String encoded_file = java.util.Base64.getEncoder().encodeToString(filebytes);
		return encoded_file;
	}
	/**
	 * 
	 * @param encodedString
	 * @return decodedString
	 * 
	 */
	public String encodedStrToDecodedStr(String encodedString){
        byte[] decodedByte = java.util.Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedByte);		
		return decodedString;
	 }
	
	public static void main(String[] args) throws IOException{
		FileToBlob blob = new FileToBlob();
		String a = blob.fileToEncodedStr("//USER-PC/workshare/Hareesh/INV-2 (1).pdf");
		System.out.println(a);
	}

}
