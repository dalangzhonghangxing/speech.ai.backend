package SAP.speech.ai.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import SAP.speech.ai.model.QA;
import SAP.speech.ai.model.QARepository;
import SAP.speech.ai.model.Response;

@Service
public class QAService {
	@Autowired
	private QARepository repository;

	@Value("${spa.ai.loadmeta}")
	private boolean isLoad;

	@Autowired
	@Transactional
	public void loadInitQA() {
		if (!isLoad)
			return;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new ClassPathResource("metadata/QA.csv").getFile()), "UTF-8"));
			String line = br.readLine();

			List<QA> qas = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] values = line.split(",");

				QA qa = new QA();
				qa.setQuestion(values[0]);
				qa.setKey_words(values[1]);
				qa.setAnswer(values[2]);
				qas.add(qa);
			}
			repository.save(qas);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Response ask(String question) {
		String answer = repository.findAnswer(question);
		Response res = new Response();
		if (answer != null && !answer.equals("")) {
			res.setState(true);
			res.setContent(answer);
			res.setMsg("找到答案!");
		} else {
			res.setState(true);
			res.setMsg("对不起，未找到答案！");
		}
		return res;
	}
}
