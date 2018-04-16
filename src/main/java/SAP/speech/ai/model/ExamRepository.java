package SAP.speech.ai.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RepositoryRestResource(exported = false)
public interface ExamRepository extends JpaRepository<Exam, Long> {

	@Query(value = "select * from exam limit begin,size", nativeQuery = true)
	List<Exam> findWithBeginAndSize(int begin, int size);

}
