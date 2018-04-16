package SAP.speech.ai.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SAP.speech.ai.model.Response;
import SAP.speech.ai.service.ExamService;

@RestController
public class ExamController {
	private final static String PREFIX = "api/exam";

	@Autowired
	private ExamService service;

	@RequestMapping(value = PREFIX + "test-paper", method = GET)
	@ResponseBody
	public Response getExams(@RequestParam(defaultValue = "10") int size) {
		return service.getExams(size);
	}

	@RequestMapping(value = PREFIX + "answer", method = POST)
	@ResponseBody
	public Response getAnswer(@RequestParam String answer[]) {
		return service.getAnswer(answer);
	}

}
