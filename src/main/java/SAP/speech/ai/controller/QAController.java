package SAP.speech.ai.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SAP.speech.ai.model.Response;
import SAP.speech.ai.service.QAService;

@RestController
public class QAController {

	private final static String PREFIX = "api/qa";

	@Autowired
	private QAService service;

	@RequestMapping(value = PREFIX, method = GET)
	@ResponseBody
	public Response ask(@RequestParam String question) {
		return service.ask(question);
	}
}
