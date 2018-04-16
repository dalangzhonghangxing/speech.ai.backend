package SAP.speech.ai.model;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = -6688817206580410840L;

	private String msg = "";

	private boolean state = false;

	private Object content;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
