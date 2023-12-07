package de.ollie.cube.core.service.impl;

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Named;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;

@Named
public class PasswordEncoder {

	private static final Logger LOGGER = LogManager.getLogger(PasswordEncoder.class);

	@Value("${password.key}")
	private String keyStr;

	private Key key;
	private Cipher cipher;

	@PostConstruct
	void postConstruct() {
		try {
			if ((keyStr == null) || (keyStr.length() != 16)) {
				throw new Exception("bad aes key configured");
			}
			if (key == null) {
				key = new SecretKeySpec(keyStr.getBytes(), "AES");
				cipher = Cipher.getInstance("AES");
			}
		} catch (Exception e) {
			LOGGER.error("while initializing the password encoder: " + e.getMessage(), e);
		}
	}

	public String decode(String s) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(s)));
	}

	public String encode(String s) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return DatatypeConverter.printHexBinary(cipher.doFinal(s.getBytes()));
	}

}