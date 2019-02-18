package bmdb.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Credit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
//	private int movieId;
	@ManyToOne
	@JoinColumn(name="movieId")
	private Movie movie;
//	private int actorId;
	@ManyToOne
	@JoinColumn(name="actorId")
	private Actor actor;
	private String characterName;
	
	public Credit() {
		super();
	}

	public Credit(int id, int movieId, int actorId, String characterName) {
		super();
		this.id = id;
//		this.movieId = movieId;
		this.movie = movie;
//		this.actorId = actorId;
		this.actor = actor;
		this.characterName = characterName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	@Override
	public String toString() {
		return "Credit [id=" + id + ", movie=" + movie + ", actor=" + actor + ", characterName=" + characterName + "]";
	}	
	
	
}
