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

import bmdb.business.Credit;
import bmdb.business.CreditRepository;
import bmdb.util.JsonResponse;

@Controller
@RequestMapping(path="/credits")
public class CreditController {

	@Autowired
	private CreditRepository creditRepo;
	
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
	
}
