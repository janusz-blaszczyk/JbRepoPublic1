package pl.januszb.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pl.januszb.rest.model.Message;

public class RestClient {

	private static final String SERVICE_URI = "http://localhost:8080/sapp/rest/";
	private static final String SERVICE_URI_SECURE = "http://localhost:8080/sapp/api/";
	private static final String GET_MESSAGE_POST_SERVICE = "/getMessagePost";
	private static final String GET_MESSAGE_GET_SERVICE = "/getMessageGet";
	private static final String CHECK_SERVICE = "/isAliveJs";
	private static final String CHECK_SERVICE_SECURE = "/isAliveSecure";
	
	
	public static Message getMessage(Integer requestParam) {
		RestTemplate rt = new RestTemplate();
		Message status;
		try {
			List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
			list.add(new MappingJackson2HttpMessageConverter());
			rt.setMessageConverters(list);
			status = rt.getForObject(SERVICE_URI + GET_MESSAGE_GET_SERVICE + "?reqMsg="
					+ requestParam, Message.class);
		} catch (RestClientException rce) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (RestClientException), szczegó³y w StackTrace");
		} catch (IllegalArgumentException iae) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (IllegalArgumentException), szczegó³y w StackTrace");
		} catch (Exception e) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (Exception), szczegó³y w StackTrace");
		}
		return status;
	}

	public static Message getMessage(String bodyParam) {
		RestTemplate rt = new RestTemplate();
		Message status;
		try {
			List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
			list.add(new MappingJackson2HttpMessageConverter());
			rt.setMessageConverters(list);
			status = rt.postForObject(SERVICE_URI + GET_MESSAGE_POST_SERVICE,
					bodyParam, Message.class);
		} catch (RestClientException rce) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (RestClientException), szczegó³y w StackTrace");
		} catch (IllegalArgumentException iae) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (IllegalArgumentException), szczegó³y w StackTrace");
		} catch (Exception e) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (Exception), szczegó³y w StackTrace");
		}
		return status;
	}

	public static Message isServiceAvailable() {
		RestTemplate rt = new RestTemplate();
		Message status;
		try {
			List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
			list.add(new MappingJackson2HttpMessageConverter());
			rt.setMessageConverters(list);
			status = rt
					.getForObject(SERVICE_URI + CHECK_SERVICE, Message.class);
		} catch (RestClientException rce) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (RestClientException), szczegó³y w StackTrace");
		} catch (IllegalArgumentException iae) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (IllegalArgumentException), szczegó³y w StackTrace");
		} catch (Exception e) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (Exception), szczegó³y w StackTrace");
		}
		return status;
	}
	
	public static Message isServiceAvailableSecureMethod() {
		RestTemplate rt = new RestTemplate();
		Message status=null;
	    String consumerKey = "demo";
	    String consumerSecret = "26e25689f09b44092c5b4485e5761aedf9d7e732";
	 
		try {
			List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
			list.add(new MappingJackson2HttpMessageConverter());
			rt.setMessageConverters(list);
//			status = rt.getForObject(SERVICE_URI + CHECK_SERVICE, Message.class);

			
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			List<MediaType> listMedia = new ArrayList<MediaType>();
			listMedia.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(listMedia);
			
			 MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
			 mvm.add("Authorization", "OAuth realm=\"Example\","
			 		+ " consumer_key=\"" + consumerKey + "\","
			 		+ " consumer_secret=\"" + consumerSecret + "\","
			 		+ " oauth_token=\"ad180jjd733klru7\","
			 		+ " oauth_signature_method=\"HMAC-SHA1\","
			 		+ " oauth_version=\"1.0\"");

			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(mvm, requestHeaders);
			
			ResponseEntity<String> response = rt.exchange("https://www.znanylekarz.pl/api/?method=doctor.categories", HttpMethod.POST, requestEntity, String.class);
			String sstatus = response.getBody();
			
		} catch (RestClientException rce) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (RestClientException), szczegó³y w StackTrace");
			rce.printStackTrace();
		} catch (IllegalArgumentException iae) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (IllegalArgumentException), szczegó³y w StackTrace");
		} catch (Exception e) {
			status = new Message();
			status.setMessage("B³¹d podczas wywo³ywania serwisu (Exception), szczegó³y w StackTrace");
		}
		return status;
	}
	
	public static void main(String[] args) {
		RestClient.isServiceAvailableSecureMethod();
	}
}
