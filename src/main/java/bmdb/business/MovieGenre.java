package bmdb.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MovieGenre {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
//	private int movieId;
	@ManyToOne
	@JoinColumn(name="movieId")
	private Movie movie;
//	private int genreId;	
	@ManyToOne
	@JoinColumn(name="genreId")
	private Genre genre;
	
	public MovieGenre() {
		super();
	}

	public MovieGenre(int id, int movieId, int genreId) {
		super();
		this.id = id;
//		this.movieId = movieId;
//		this.genreId = genreId;
		this.movie = movie;
		this.genre = genre;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "MovieGenre [id=" + id + ", movie=" + movie + ", genre=" + genre + "]";
	}


}
