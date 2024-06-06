package org.example.demojwtclient.conf;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.apache.hc.core5.ssl.TrustStrategy;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.function.Supplier;
import java.io.InputStream;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    //    @Bean
    //    public RestTemplate restTemplate() {
    //        return new RestTemplate();
    //    }
    @Value("${trust.store}")
    private String trustStore;

    @Value("${trust.store.password}")
    private String trustStorePassword;

    @Value("${server.ssl.key-alias}")
    private String alias;

//
//    @Value("${trust.store}")
//    private String trustStorePath;
//
//    @Value("${trust.store.password}")
//    private String trustStorePassword;
//
//    @Value("${key.store}")
//    private String keyStorePath;
//
//    @Value("${key.store.password}")
//    private String keyStorePassword;
//
//    @Value("${key.alias}")
//    private String keyAlias;
//
//    @Bean
//    public RestTemplate restTemplate() throws Exception {
//        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        try (FileInputStream keyStoreStream = new FileInputStream(keyStorePath)) {
//            keyStore.load(keyStoreStream, keyStorePassword.toCharArray());
//        }
//
//        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());
//
//        SSLContext sslContext = SSLContextBuilder.create()
//                .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
//                .build();
//
//        HttpClient httpClient = HttpClients.custom()
//                .setSSLContext(sslContext)
//                .build();
//
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        return new RestTemplate(requestFactory);
//    }
//}

//    @Bean
//    public RestTemplate restTemplate() throws Exception {
//        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
//        try (FileInputStream keystoreStream = new FileInputStream(trustStore)) {
//            keystore.load(keystoreStream, trustStorePassword.toCharArray());
//        }
//
//        // Pobierz certyfikat klienta na podstawie aliasu
//        Certificate certificate = keystore.getCertificate(alias);
//
//        // Pobierz klucz prywatny certyfikatu na podstawie aliasu i hasła
//        Key key = keystore.getKey(alias, trustStorePassword.toCharArray());
//
//        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        keyManagerFactory.init(keystore, trustStorePassword.toCharArray());
//
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
//
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        ((HttpComponentsClientHttpRequestFactory) requestFactory).setHttpClient(HttpClients.custom()
//                .setSSLContext(sslContext)
//                .build());
//
//        return new RestTemplate(requestFactory);
//    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
//        // Load the client's keystore
//        KeyStore clientStore = KeyStore.getInstance("PKCS12");
//        try (InputStream clientStream = getClass().getResourceAsStream(trustStore)) {
//            clientStore.load(clientStream, trustStorePassword.toCharArray());
//        }
//
//        // Load the server's truststore
//        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        try (InputStream trustStoreStream = getClass().getResourceAsStream(String.valueOf(trustStore))) {
//            trustStore.load(trustStoreStream, trustStorePassword.toCharArray());
//        }
//
//        SSLContext sslContext = SSLContextBuilder
//                .create()
//                .loadKeyMaterial(clientStore, trustStorePassword.toCharArray()) // Load client certificate
//                .loadTrustMaterial(trustStore, null) // Load server truststore
//                .build();
//
//        HttpClient client = HttpClients.custom()
//                .setSSLContext(sslContext)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
//
//        return builder
//                .requestFactory(() -> factory)
//                .build();
//    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
//
//        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        try (InputStream keyStoreInputStream = getClass().getResourceAsStream(trustStore)) {
//            keyStore.load(keyStoreInputStream, trustStorePassword.toCharArray());
//        }
//
//        SSLContext sslContext = SSLContextBuilder
//                .create()
//                .loadKeyMaterial(keyStore, trustStorePassword.toCharArray())
////                .loadTrustMaterial(keyStore, null)
////                .loadTrustMaterial(ResourceUtils.getFile("classpath:truststore.jks"), trustStorePassword.toCharArray())
//                .build();
//
//        HttpClient client = HttpClients.custom()
//                .setSSLContext(sslContext)
//                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//                .build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
//
//        return new RestTemplate(factory);
//    }
//
//    @Bean
//    public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, MalformedURLException, IOException {
//
//        SSLContext sslContext = new SSLContextBuilder()
//                .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray()).build();
//        SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);
//        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
//                .setSSLSocketFactory(sslConFactory)
//                .build();
//        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        return new RestTemplate(requestFactory);
//    }

//    @Bean
//    public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, MalformedURLException, IOException {
//        // Konfiguracja TrustManager, który zaakceptuje wszystkie certyfikaty
//        TrustManager[] trustAllCerts = new TrustManager[]{
//                new X509TrustManager() {
//                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
//                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
//                    public X509Certificate[] getAcceptedIssuers() {
//                        return new X509Certificate[0];
//                    }
//                }
//        };
//
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//
//        SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);
//
//        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
//                .setSSLSocketFactory(sslConFactory)
//                .build();
//
//        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
//
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        return new RestTemplate(requestFactory);
//    }

//    @Bean
//    public RestTemplate restTemplate() throws Exception {
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClientWithInterceptor());
//        return new RestTemplate(requestFactory);
//    }
//
//    private CloseableHttpClient httpClientWithInterceptor() throws Exception {
//        SSLContext sslContext = SSLContextBuilder.create()
//                .loadKeyMaterial(new File(trustStore), trustStorePassword.toCharArray(), trustStorePassword.toCharArray(),
//                        (aliases, socket) -> alias)
//                .loadTrustMaterial(new File(trustStore), trustStorePassword.toCharArray())
//                .build();
//
//        return HttpClients.custom()
//                .setSSLContext(sslContext)
//                .addInterceptorFirst(new RequestInterceptor())
//                .build();
//    }
//
//    private static class RequestInterceptor implements HttpRequestInterceptor {
//        private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);
//
//        @Override
//        public void process(HttpRequest request, HttpContext context) {
//            LOGGER.info("Outgoing request: {}", request.getRequestLine());
//        }
//    }

//    import org.apache.http.HttpRequest;
//import org.apache.http.HttpRequestInterceptor;
//import org.apache.http.protocol.HttpContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//import java.util.Collections;

//    @Configuration
//    public class RestTemplateConfig {
//
//        @Value("${trust.store}")
//        private String trustStorePath;
//
//        @Value("${trust.store.password}")
//        private String trustStorePassword;
//
//        @Value("${key.store}")
//        private String keyStorePath;
//
//        @Value("${key.store.password}")
//        private String keyStorePassword;
//
//        @Value("${key.alias}")
//        private String keyAlias;

    @Bean
    public RestTemplate restTemplate() throws Exception {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(new RequestInterceptor()));
        return restTemplate;
    }

    private HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() throws Exception {
        CloseableHttpClient httpClient = httpClientWithInterceptor();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    private CloseableHttpClient httpClientWithInterceptor() throws Exception {
        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(new File(trustStore), trustStorePassword.toCharArray(), trustStorePassword.toCharArray(),
                        (aliases, socket) -> alias)
                .loadTrustMaterial(new File(trustStore), trustStorePassword.toCharArray(), TrustStrategy.ALLOW_ALL)
                .build();

        return HttpClients.custom()
                .setSSLContext(sslContext)
                .build();
    }

    private static class RequestInterceptor implements ClientHttpRequestInterceptor {
        private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            LOGGER.info("Outgoing request: {}", request);
            return execution.execute(request, body);
        }
    }

}
