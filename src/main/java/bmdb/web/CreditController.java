package bmdb.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import bmdb.business.Actor;
import bmdb.business.ActorRepository;
import bmdb.business.Credit;
import bmdb.business.CreditRepository;
import bmdb.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping(path="/credits")
public class CreditController {

	@Autowired
	private CreditRepository creditRepo;
	
	@Autowired
	private ActorRepository actorRepo;
	
	@GetMapping("/")	
	public @ResponseBody JsonResponse getAllCredits() {
		JsonResponse jsonResponse = null;
		try {
			jsonResponse = JsonResponse.getInstance(creditRepo.findAll());			
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody JsonResponse getCredit(@PathVariable int id) {
		JsonResponse jsonResponse = null;		
		try {
			Optional<Credit> c = creditRepo.findById(id);
			if (c.isPresent()) {
				jsonResponse = JsonResponse.getInstance(c);
			} else {
				jsonResponse = JsonResponse.getInstance(new Exception("No credit found for id = " + id));
			}
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
	@PostMapping(path="/")
	public @ResponseBody JsonResponse addNewCredit(@RequestBody Credit c) {
		JsonResponse jsonResponse = null;
		jsonResponse = JsonResponse.getInstance(saveCredit(c));
		return jsonResponse;
	}
	
	@PutMapping("/{id}")
	public @ResponseBody JsonResponse updateCredit(@PathVariable int id, @RequestBody Credit c) {
		// should check to see if credit exists first		
		return saveCredit(c);
	}	

	private @ResponseBody JsonResponse saveCredit(Credit c) {
		JsonResponse jsonResponse = null;
		try {
			creditRepo.save(c);
			jsonResponse = JsonResponse.getInstance(c);
		} catch (DataIntegrityViolationException e) {
			jsonResponse = JsonResponse.getInstance(new Exception(e.getMessage()));
		}
		return jsonResponse;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse removeCredit(@PathVariable int id) {
		JsonResponse jsonResponse = null;
		Optional<Credit> c = creditRepo.findById(id);
		if (c.isPresent()) {
			creditRepo.deleteById(id);
			jsonResponse = JsonResponse.getInstance(c);
		} else {
			jsonResponse = JsonResponse.getInstance(new Exception("Credit delete unsuccessful, credit " + id + " does not exist."));
		}
		return jsonResponse;
	}
	
	@GetMapping("/getByActorId/{id}")
	public @ResponseBody JsonResponse getCreditsByActorId(@PathVariable int id) {
		Optional<Actor> actor = actorRepo.findById(id);
		
		JsonResponse jsonResponse = null;
		try {
			jsonResponse = JsonResponse.getInstance(creditRepo.findByActor(actor));			
		} catch (Exception e) {
			jsonResponse = JsonResponse.getInstance(e);
		}		
		return jsonResponse;
	}
	
//	@GetMapping("/getCreditsByActorName/{lastName}")
//	public JsonResponse getCreditsByActorLastName(@PathVariable String lastName) {
//		Actor actor = actorRepo.findByLastName(lastName);
//		
//		JsonResponse jsonResponse = null;
//		try {
//			jsonResponse = JsonResponse.getInstance(creditRepo.findByActor(actor));			
//		} catch (Exception e) {
//			jsonResponse = JsonResponse.getInstance(e);
//		}		
//		return jsonResponse;
//	}
//	
//	@GetMapping("/getCreditsByActorFullName/{lastName}/{firstName}")
//	public JsonResponse getCreditsByActorFullName(@PathVariable String lastName, String firstName) {
//		Actor actor = actorRepo.findByLastNameAndFirstName(lastName, firstName);
//		
//		JsonResponse jsonResponse = null;
//		try {
//			jsonResponse = JsonResponse.getInstance(creditRepo.findByActor(actor));			
//		} catch (Exception e) {
//			jsonResponse = JsonResponse.getInstance(e);
//		}		
//		return jsonResponse;
//	}
}
