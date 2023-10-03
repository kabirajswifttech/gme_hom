package com.gme.hom.security.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;


public class ChecksumService {
	
	public static String getChecksum(Object object, String hashAlgo) throws IOException, NoSuchAlgorithmException {

		ByteArrayOutputStream baos = null;

		ObjectOutputStream oos = null;

		try {

			baos = new ByteArrayOutputStream();

			oos = new ObjectOutputStream(baos);

			oos.writeObject(object);

			MessageDigest md = MessageDigest.getInstance(hashAlgo);

			byte[] thedigest = md.digest(baos.toByteArray());

			return DatatypeConverter.printHexBinary(thedigest);

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			oos.close();
			baos.close();
		}
	}
}
