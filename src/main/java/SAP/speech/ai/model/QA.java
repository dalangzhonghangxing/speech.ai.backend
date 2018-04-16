package SAP.speech.ai.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class QA extends EntityId {

	private static final long serialVersionUID = 373727047063325487L;

	private String question;

	private String key_words;

	private String answer;

	public QA() {
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getKey_words() {
		return key_words;
	}

	public void setKey_words(String key_words) {
		this.key_words = key_words;
	}

	public String getAnswers() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
