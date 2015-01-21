package pl.januszb.rest.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.januszb.rest.model.Message;

@Controller
@RequestMapping("/")
public class ClientController {

	@RequestMapping(value="", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		Message status;
		
		status = RestClient.isServiceAvailable();
		model.addAttribute("message", status.getMessage());
		status = new Message();
		status = RestClient.getMessage(667);
		model.addAttribute("messagePostParam", status.getMessage());
		status = new Message();
		status = RestClient.getMessage("norma semiimperatywna");
		model.addAttribute("messagePostBody", status.getMessage());
		
		return "diagnostic";
	}
}
