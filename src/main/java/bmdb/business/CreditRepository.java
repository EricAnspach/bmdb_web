package bmdb.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CreditRepository extends CrudRepository <Credit, Integer> {
	List<Credit> findByActor(Optional<Actor> actor);
	List<Credit> findAllByActorId(int id);
}
