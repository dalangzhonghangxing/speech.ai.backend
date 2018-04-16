package SAP.speech.ai.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RepositoryRestResource(exported = false)
public interface QARepository extends JpaRepository<QA, Long> {

	@Query("select answer from QA where question = ?1")
	public String findAnswer(String question);

}
