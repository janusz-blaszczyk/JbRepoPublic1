package pl.januszb.rest.client;

import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import pl.januszb.rest.model.Message;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/check")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SecureController {
	private String endpointUrl = "http://localhost:8080/sapp/api/secure/isAliveSecure";
	
	private String znanyLekarzPlKey = "demo";
	private String znanyLekarzPlSecret = "26e25689f09b44092c5b4485e5761aedf9d7e732";
	private String znanyLekarzPlEndpointUrl = "https://www.znanylekarz.pl/api/?method=doctor.categories";
			
	private final String SIGNATURE_METHOD = "HMAC-SHA1";
	private OAuthClientFilter oauthFilter; 
	private Client client;

	public SecureController() {
		initSSL(); 
		initClient();
	}

	@GET
	public String fetchOAuthProtectedData() {
		initOAuthFilter("3a4393c3da1a4e316ee66c0cc61c71",
				"fe1372c074185b19c309964812bb8f3f2256ba514aea8a318");

		WebResource webResource = getSignedWebResource(endpointUrl);
		WebResource.Builder webBuilder = webResource.getRequestBuilder()
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
		try {
			Message msg = webBuilder.get(Message.class);
			return webBuilder.get(String.class);
		} catch (UniformInterfaceException ex) {
			return ex.getResponse().getEntity(String.class);
		}
	}
	
	@GET
	@Path("/znanylekarzpl")
	public String fetchTestDataZnanyLekarzPl() {
		initOAuthFilter(znanyLekarzPlKey, znanyLekarzPlSecret);

		WebResource webResource = getSignedWebResource(znanyLekarzPlEndpointUrl);
		WebResource.Builder webBuilder = webResource.getRequestBuilder()
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
		try {
			return webBuilder.get(String.class);
		} catch (UniformInterfaceException ex) {
			return ex.getResponse().getEntity(String.class);
		}
	}
	public WebResource getSignedWebResource(String endpointUrl) {
		WebResource webResource = client.resource(UriBuilder.fromUri(
				endpointUrl).build());
		webResource.addFilter(oauthFilter);
		return webResource;
	}
	private void initClient() {
		ClientConfig config = new DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		config.getClasses().add(JacksonJaxbJsonProvider.class);
		client = Client.create(config);
	}
	private void initOAuthFilter(String consumerKey, String consumerSecret) {
		OAuthParameters params = new OAuthParameters();

		params.signatureMethod(SIGNATURE_METHOD);
		params.consumerKey(consumerKey);
		params.setVersion("1.0");
		params.nonce();

		OAuthSecrets secrets = new OAuthSecrets();
		secrets.consumerSecret(consumerSecret);

		oauthFilter = new OAuthClientFilter(client.getProviders(), params,
				secrets);
	}
	public void initSSL() {
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String urlHostName, SSLSession session) {
					return true;
				}
			};

			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs,
						String authType) {
				}
			} };

			sc.init(null, trustAllCerts, new java.security.SecureRandom());

			SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
		}
	}
}