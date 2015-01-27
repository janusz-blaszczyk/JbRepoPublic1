package pl.januszb.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pl.januszb.rest.model.Message;

public class PublicController {

	private static final String SERVICE_URI = "http://localhost:8080/sapp/api/public/";
	private static final String GET_MESSAGE_POST_SERVICE = "/getMessagePost";
	private static final String GET_MESSAGE_GET_SERVICE = "/getMessageGet";
	private static final String CHECK_SERVICE = "/isAlive";
	
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
}
