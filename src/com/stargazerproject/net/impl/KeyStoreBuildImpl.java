package com.stargazerproject.net.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import com.google.common.base.Optional;
import com.stargazerproject.net.KeyStoreBuild;

public class KeyStoreBuildImpl implements KeyStoreBuild{
	
	protected String filePath;
	protected String keyPassword;
	protected String storePassword;

	public KeyStoreBuildImpl(Optional<String> filePathArg, Optional<String> storePasswordArg, Optional<String> keyPasswordArg) {
		filePath = filePathArg.get();
		keyPassword = keyPasswordArg.get();
		storePassword = storePasswordArg.get();
	}
	
	@Override
	public KeyStore keyStoreInitialize() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		FileInputStream fileInputStream = keyStoreFileInputStream(filePath);
		return keyStoreLoad(fileInputStream);
	}
	
	private FileInputStream keyStoreFileInputStream(String filePath) throws FileNotFoundException{
		FileInputStream fileInputStream = new FileInputStream(new File(filePath));
		return fileInputStream;
	}
	
	private KeyStore keyStoreLoad(FileInputStream keyStoreFileInputStream) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(keyStoreFileInputStream, storePassword.toCharArray());
		return keyStore;
	}
	
}
