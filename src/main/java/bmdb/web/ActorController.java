package bmdb.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bmdb.business.Actor;
import bmdb.business.ActorRepository;
import bmdb.util.JsonResponse;

@Controller
@RequestMapping(path="/actors")
public class ActorController {
	
	@Autowired
	private ActorRepository actorRepo;
	
	// Get all users
	@GetMapping("/")	
	public @ResponseBody JsonResponse getAllActors() {
		JsonResponse jsonResponse = null;
		try {
			jsonResponse = JsonResponse.getInstance(actorRepo.findAll());
			
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody JsonResponse getActor(@PathVariable int id) {
		JsonResponse jsonResponse = null;		
		try {
			Optional<Actor> u = actorRepo.findById(id);
			if (u.isPresent()) {
				jsonResponse = JsonResponse.getInstance(u);
			} else {
				jsonResponse = JsonResponse.getInstance(new Exception("No actor found for id = " + id));
			}
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
	@PostMapping(path="/")
	public @ResponseBody JsonResponse addNewActor(@RequestBody Actor actor) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		JsonResponse jsonResponse = null;
		jsonResponse = JsonResponse.getInstance(saveActor(actor));	
		return jsonResponse;
	}
	
	@PutMapping("/{id}")
	public @ResponseBody JsonResponse updateActor(@PathVariable int id, @RequestBody Actor a) {
		// should check to see if user exists first		
		return saveActor(a);
	}	

	private @ResponseBody JsonResponse saveActor(Actor actor) {
		JsonResponse jsonResponse = null;
		try {
			actorRepo.save(actor);
			jsonResponse = JsonResponse.getInstance(actor);
		} catch (DataIntegrityViolationException e) {
			jsonResponse = JsonResponse.getInstance(new Exception(e.getMessage()));
		}
		return jsonResponse;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse removeActor(@PathVariable int id) {
		JsonResponse jsonResponse = null;
		Optional<Actor> a = actorRepo.findById(id);
		if (a.isPresent()) {
			actorRepo.deleteById(id);
			jsonResponse = JsonResponse.getInstance(a);
		} else {
			jsonResponse = JsonResponse.getInstance(new Exception("Actor delete unsuccessful, actor " + id + " does not exist."));
		}
		return jsonResponse;
	}
}
