package bmdb.business;

import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository <Actor, Integer> {
	Actor findByLastName(String lastName);
	Actor findByLastNameAndFirstName(String lastName, String firstName);
}
