package SAP.speech.ai.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
public class User extends EntityId {

	private static final long serialVersionUID = -708410042083613443L;

	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
