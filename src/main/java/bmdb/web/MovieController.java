package bmdb.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import bmdb.business.Movie;
import bmdb.business.MovieRepository;
import bmdb.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping(path="/movies")
public class MovieController {
	@Autowired
	private MovieRepository movieRepo;
	
	@GetMapping("/")	
	public @ResponseBody JsonResponse getAllMovies() {
		JsonResponse jsonResponse = null;
		try {
			jsonResponse = JsonResponse.getInstance(movieRepo.findAll());			
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody JsonResponse getMovie(@PathVariable int id) {
		JsonResponse jsonResponse = null;		
		try {
			Optional<Movie> m = movieRepo.findById(id);
			if (m.isPresent()) {
				jsonResponse = JsonResponse.getInstance(m);
			} else {
				jsonResponse = JsonResponse.getInstance(new Exception("No movie found for id = " + id));
			}
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
	@PostMapping(path="/")
	public @ResponseBody JsonResponse addNewMovie(@RequestBody Movie m) {
		JsonResponse jsonResponse = null;
		jsonResponse = JsonResponse.getInstance(saveMovie(m));	
		return jsonResponse;
	}
	
	@PutMapping("/{id}")
	public @ResponseBody JsonResponse updateMovie(@PathVariable int id, @RequestBody Movie m) {
		// should check to see if movie exists first		
		return saveMovie(m);
	}	

	private @ResponseBody JsonResponse saveMovie(Movie m) {
		JsonResponse jsonResponse = null;
		try {
			movieRepo.save(m);
			jsonResponse = JsonResponse.getInstance(m);
		} catch (DataIntegrityViolationException e) {
			jsonResponse = JsonResponse.getInstance(new Exception(e.getMessage()));
		}
		return jsonResponse;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse removeMovie(@PathVariable int id) {
		JsonResponse jsonResponse = null;
		Optional<Movie> m = movieRepo.findById(id);
		if (m.isPresent()) {
			movieRepo.deleteById(id);
			jsonResponse = JsonResponse.getInstance(m);
		} else {
			jsonResponse = JsonResponse.getInstance(new Exception("Movie delete unsuccessful, movie " + id + " does not exist."));
		}
		return jsonResponse;
	}
	
//	@GetMapping("/getMoviesByActor")
	
}
