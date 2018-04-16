package SAP.speech.ai.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import SAP.speech.ai.model.Exam;
import SAP.speech.ai.model.ExamRepository;
import SAP.speech.ai.model.Response;

@Service
public class ExamService extends BaseService {
	@Autowired
	private ExamRepository repository;

	@Value("${spa.ai.loadmeta}")
	private boolean isLoad;

	@Autowired
	private void loadInitExam() {
		if (!isLoad)
			return;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new ClassPathResource("metadata/exam.csv").getFile()), "UTF-8"));
			String line = br.readLine();

			List<Exam> exams = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				line = line.trim();
				String[] values = line.split(",");

				Exam exam = new Exam();
				exam.setQuestion(values[0]);
				exam.setA(values[1]);
				exam.setB(values[2]);
				exam.setC(values[3]);
				exam.setD(values[4]);
				exam.setAnswer(values[5]);
				exams.add(exam);
			}
			repository.save(exams);
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

	/**
	 * @param size
	 *            获取题目的数量
	 * @return 返回标准格式的response
	 **/
	public Response getExams(int size) {

		Response res = new Response();
		List<Exam> exams = new ArrayList<>();
		HttpSession session = getSession();

		long total = repository.count();
		if (total <= size) {// 如果题目数量不够
			exams = repository.findAll();
		} else {
			int begin = (int) (Math.random() * (total - size));// 得到随机的开始位置
			exams = repository.findWithBeginAndSize(begin, size);

		}
		List<String> answers = new ArrayList<>();
		for (Exam exam : exams) {
			answers.add(exam.getAnswer());
			exam.setAnswer(null);
		}
		// 将考题答案放入Session，为了批改
		session.setAttribute("answers", answers);
		res.setState(true);
		res.setContent(exams);
		return res;
	}

	public Response getAnswer(String[] answer) {
		HttpSession session = getSession();
		Response res = new Response();
		List<String> answers = (List<String>) session.getAttribute("answers");
		int score = 0;
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).equals(answer[i]))
				score++;
		}
		res.setContent(Integer.valueOf(score));
		res.setState(true);
		return res;
	}
}
