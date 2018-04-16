package SAP.speech.ai.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);

}
