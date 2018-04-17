package SAP.speech.ai.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
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

	private Map<String, List<Long>> keyWordsMapping = new HashMap<>();

	@Autowired
	@Transactional
	public void loadInitQA() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new ClassPathResource("metadata/QA.csv").getFile()), "UTF-8"));
			String line = br.readLine();

			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] values = line.split(",");

				QA qa = new QA();
				qa.setQuestion(values[0]);
				qa.setKey_words(values[1]);
				qa.setAnswer(values[2]);
				if (isLoad)
					qa = repository.save(qa);
				else {
					qa = repository.findByQuestion(values[0]);
				}
				String[] keyWords = values[1].split(";");
				for (String keyWord : keyWords) {
					List<Long> l = keyWordsMapping.getOrDefault(keyWord, new ArrayList<>());
					l.add(qa.getId());
					keyWordsMapping.put(keyWord, l);
				}
			}
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
		List<Word> words = WordSegmenter.seg(question);
		Set<Long> ids = new HashSet<>();

		for (Word w : words) {
			if (keyWordsMapping.get(w.toString()) != null) {
				ids.addAll(keyWordsMapping.get(w.toString()));
			}
		}

		List<QA> answer = repository.findAll(ids);
		StringBuffer answers = new StringBuffer();
		for (QA qa : answer) {
			answers.append(qa.getQuestion()).append(":").append(qa.getAnswer() + ". ");
		}

		Response res = new Response();
		if (answer.size() > 0) {
			res.setState(true);
			res.setContent(answers.toString());
			res.setMsg("找到答案!");
		} else {
			res.setState(true);
			res.setMsg("对不起，未找到答案！");
		}
		return res;
	}
}
