package bmdb.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import bmdb.business.MovieGenre;
import bmdb.business.MovieGenreRepository;
import bmdb.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping(path="/movieGenres")
public class MovieGenreController {

	@Autowired
	private MovieGenreRepository movieGenreRepo;
	
	@GetMapping("/")	
	public @ResponseBody JsonResponse getAllMovieGenres() {
		JsonResponse jsonResponse = null;
		try {
			jsonResponse = JsonResponse.getInstance(movieGenreRepo.findAll());			
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody JsonResponse getMovieGenre(@PathVariable int id) {
		JsonResponse jsonResponse = null;		
		try {
			Optional<MovieGenre> m = movieGenreRepo.findById(id);
			if (m.isPresent()) {
				jsonResponse = JsonResponse.getInstance(m);
			} else {
				jsonResponse = JsonResponse.getInstance(new Exception("No movie/genre combination found for id = " + id));
			}
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
	@PostMapping(path="/")
	public @ResponseBody JsonResponse addNewMovieGenre(@RequestBody MovieGenre m) {
		JsonResponse jsonResponse = null;
		jsonResponse = JsonResponse.getInstance(saveMovieGenre(m));
		return jsonResponse;
	}
	
	@PutMapping("/{id}")
	public @ResponseBody JsonResponse updateMovieGenre(@PathVariable int id, @RequestBody MovieGenre m) {
		// should check to see if movieGenre exists first		
		return saveMovieGenre(m);
	}	

	private @ResponseBody JsonResponse saveMovieGenre(MovieGenre m) {
		JsonResponse jsonResponse = null;
		try {
			movieGenreRepo.save(m);
			jsonResponse = JsonResponse.getInstance(m);
		} catch (DataIntegrityViolationException e) {
			jsonResponse = JsonResponse.getInstance(new Exception(e.getMessage()));
		}
		return jsonResponse;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse removeMovieGenre(@PathVariable int id) {
		JsonResponse jsonResponse = null;
		Optional<MovieGenre> m = movieGenreRepo.findById(id);
		if (m.isPresent()) {
			movieGenreRepo.deleteById(id);
			jsonResponse = JsonResponse.getInstance(m);
		} else {
			jsonResponse = JsonResponse.getInstance(new Exception("Movie/genre combination delete unsuccessful, movie/genre " + id + " does not exist."));
		}
		return jsonResponse;
	}
	
}
