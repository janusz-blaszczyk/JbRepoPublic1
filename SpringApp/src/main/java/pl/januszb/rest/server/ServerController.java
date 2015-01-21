package pl.januszb.rest.server;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import pl.januszb.rest.model.Message;

@RestController
@RequestMapping("/rest")
public class ServerController implements ServletContextAware {
	//injected servlet context
	private ServletContext context;

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
	
	@RequestMapping(value = "/isAlive", method = RequestMethod.GET)
	public ResponseEntity<Message> isServiceAlive() {
		Message message;
		message = new Message();
		message.setMessage("server is alive");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/getMessagePost", method = RequestMethod.POST)
	public ResponseEntity<Message> getMessagePost(
			@RequestBody String number) {
		Message message;
		message = new Message();
		message.setMessage("server return new message, bodyRequest: " + number);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	@RequestMapping(value = "/getMessageGet", method = RequestMethod.GET)
	public ResponseEntity<Message> getMessageGet(
			@RequestParam(value="reqMsg", defaultValue="DEFAULT") String reqMsg) {
		Message message;
		message = new Message();
		message.setMessage("server return new message, paramMessage: " + reqMsg);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}	