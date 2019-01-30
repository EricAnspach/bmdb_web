package bmdb.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Credit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int movieId;
	private int actorId;
	private String characterNameString;
	
	public Credit(int id, int movieId, int actorId, String characterNameString) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.actorId = actorId;
		this.characterNameString = characterNameString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getCharacterNameString() {
		return characterNameString;
	}

	public void setCharacterNameString(String characterNameString) {
		this.characterNameString = characterNameString;
	}	
}
