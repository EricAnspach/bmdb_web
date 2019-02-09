package bmdb.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieGenre {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int movieId;
	private int genreId;	
	
	public MovieGenre() {
		super();
	}

	public MovieGenre(int id, int movieId, int genreId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.genreId = genreId;
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

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}	
}
