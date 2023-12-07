package de.ollie.cube.core.service;

public interface TANService {

	String createTANCode();

	boolean checkTAN(String tan, String tanCode, String userName);

}
