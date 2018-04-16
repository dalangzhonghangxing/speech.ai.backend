package SAP.speech.ai.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam extends EntityId {

	private static final long serialVersionUID = 8479616950318236557L;

	private String question;

	private String A;

	private String B;

	private String C;

	private String D;

	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}

	public String getD() {
		return D;
	}

	public void setD(String d) {
		D = d;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
