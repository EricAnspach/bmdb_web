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
	private String characterName;
	
	public Credit() {
		super();
	}

	public Credit(int id, int movieId, int actorId, String characterName) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.actorId = actorId;
		this.characterName = characterName;
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
		return characterName;
	}

	public void setCharacterNameString(String characterNameString) {
		this.characterName = characterNameString;
	}	
}
