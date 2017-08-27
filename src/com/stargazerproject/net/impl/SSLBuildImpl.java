package com.stargazerproject.net.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import com.google.common.base.Optional;
import com.stargazerproject.net.SSLConnectionSocket;

public class SSLBuildImpl extends KeyStoreBuildImpl implements SSLConnectionSocket{
	
	public SSLBuildImpl(Optional<String> filePathArg, Optional<String> storePasswordArg, Optional<String> keyPasswordArg) {
		super(filePathArg, storePasswordArg, keyPasswordArg);
	}

	@Override
	public SSLConnectionSocketFactory sSLConnectionSocketFactoryInitialize() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException{
		KeyStore keyStore = keyStoreInitialize();
		SSLContext sSLContext = sSLContextBuild(keyStore);
		return sSLConnectionSocketFactoryBuild(sSLContext);
	}
	
	private SSLContext sSLContextBuild(KeyStore keyStore) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException{
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, keyPassword.toCharArray()).build();
		return sslcontext;
	}
	
	private SSLConnectionSocketFactory sSLConnectionSocketFactoryBuild(SSLContext sSLContext) throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		SSLConnectionSocketFactory sSLConnectionSocketFactory = new SSLConnectionSocketFactory(sSLContext);
		return sSLConnectionSocketFactory;
	}

}
