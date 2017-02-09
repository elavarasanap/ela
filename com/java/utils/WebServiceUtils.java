package com.java.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.Endpoint;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
/**
 * 
 * @author Elavarasan
 * @since 12/11/2016
 * @category webservice utils
 *  
 */
public class WebServiceUtils {
	/**
	 *  used to convert and publish endpoint as https url
	 * @param sslFileName
	 * @param sslFilePass
	 * @param ipaddress
	 * @param port
	 * @param url
	 * @param implementationClassName
	 * @throws UnrecoverableKeyException
	 * @return void
	 */
	 
	public void publishEndPoint(String sslFileName, String sslFilePass,String ipaddress,int port,String url,Object implementationClassName) throws UnrecoverableKeyException {
	try{	
		SSLContext ssl = SSLContext.getInstance("TLS");
		KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		KeyStore store = KeyStore.getInstance(KeyStore.getDefaultType());
		store.load(new FileInputStream(sslFileName),sslFilePass.toCharArray());
		keyFactory.init(store, sslFilePass.toCharArray());
		KeyManager[] keyManagers = new KeyManager[1];
        keyManagers = keyFactory.getKeyManagers();
		TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustFactory.init(store);
		TrustManager[] trustManagers = trustFactory.getTrustManagers();
		ssl.init(keyManagers,trustManagers, new SecureRandom());
		HttpsConfigurator configurator = new HttpsConfigurator(ssl);
		HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(ipaddress, port), port);
		httpsServer.setHttpsConfigurator(configurator);
		com.sun.net.httpserver.HttpContext httpContext = httpsServer.createContext(url);
		httpsServer.start();
		
		Endpoint endpoint = Endpoint.create(implementationClassName);
		endpoint.publish(httpContext);
	}
	catch(KeyManagementException|NoSuchAlgorithmException| CertificateException|IOException|UnrecoverableKeyException| 
		  KeyStoreException KeyStoreException){
		System.out.println("endpointexception-->"+KeyStoreException);}
	}
 	
}
