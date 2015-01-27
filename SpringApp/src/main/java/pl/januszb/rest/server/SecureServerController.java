package pl.januszb.rest.server;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContext;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import pl.januszb.rest.model.Message;

@RestController
@RequestMapping("/api/secure/")
@PreAuthorize("hasRole('ROLE_OAUTH')")
public class SecureServerController implements ServletContextAware {
	//injected servlet context
	private ServletContext context;

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
	
	@RequestMapping(value = "/isAliveSecure", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_OAUTH')")
	public @ResponseBody Message isServiceAlive() {
		Message message;
		message = new Message();
		message.setMessage("server is alive");
		message.setChildMessage((new Message()));
		message.getChildMessage().setMessage("child message");
		Collection<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		message.setCollectionOfMessage(list);
		return message;
	}
}	