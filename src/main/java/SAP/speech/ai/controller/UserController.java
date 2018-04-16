package SAP.speech.ai.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SAP.speech.ai.model.Response;
import SAP.speech.ai.service.UserService;

@RestController
public class UserController {

	private final static String PREFIX = "api/user";

	@Autowired
	private UserService service;

	@RequestMapping(value = PREFIX + "/verify", method = POST)
	@ResponseBody
	public Response verify(@RequestParam String userName, @RequestParam String password) {
		return service.verify(userName, password);
	}

	@RequestMapping(value = PREFIX + "/register", method = POST)
	@ResponseBody
	public Response register(@RequestParam String userName, @RequestParam String password) {
		return service.register(userName, password);
	}

}
