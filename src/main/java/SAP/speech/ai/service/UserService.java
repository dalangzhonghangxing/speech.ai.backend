package SAP.speech.ai.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import SAP.speech.ai.model.Response;
import SAP.speech.ai.model.User;
import SAP.speech.ai.model.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Value("${spa.ai.loadmeta}")
	private boolean isLoad;

	@Autowired
	@Transactional
	public void loadInitUsers() {
		if (!isLoad)
			return;
		User u = new User();
		u.setUserName("test");
		u.setPassword("test");
		repository.save(u);
	}

	public Response verify(String userName, String password) {

		Response rb = new Response();
		User user = repository.findByUserName(userName);
		if (user == null || !user.getPassword().equals(password)) {
			rb.setMsg("用户名或密码错误！");
		} else {
			rb.setState(true);
			rb.setMsg("登录成功！");
		}
		return rb;
	}

	@Transactional
	public Response register(String userName, String password) {
		User u;
		u = repository.findByUserName(userName);
		Response rb = new Response();
		if (u != null) {
			rb.setState(false);
			rb.setMsg("用户名已存在！");
		} else {
			u = new User();
			u.setUserName(userName);
			u.setPassword(password);
			u = repository.save(u);
			rb.setState(true);
			rb.setMsg("注册成功！");
		}
		return rb;
	}
}
