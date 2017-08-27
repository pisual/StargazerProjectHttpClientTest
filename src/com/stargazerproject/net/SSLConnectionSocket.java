package com.stargazerproject.net;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

public interface SSLConnectionSocket {
	public SSLConnectionSocketFactory sSLConnectionSocketFactoryInitialize() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException;
}
