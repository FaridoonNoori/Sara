package com.example.demo;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepository repo;
	//@GetMapping(path="/aliens", produces= {"application/xml"})
	
	@GetMapping("/")
	public List<Alien> getAliens()
	{
		List<Alien> aliens=  repo.findAll();
		return aliens;
	}
	
	@PostMapping(path="/alien")
	// @RequestBody for row data
	public Alien add( Alien alien) {
		repo.save(alien);
		return alien;	
	}
	
	@GetMapping("/alien/{aid}")
	public Optional<Alien> getAlienOne(@PathVariable("aid") int aid) 
	{
		return repo.findById(aid);
	}
	
//	@DeleteMapping("/alien/{aid}/")
//	public String delete(@PathVariable("aid") int aid) {
//		
//		//Optional<Alien> a= repo.findById(aid);
//		repo.deleteById(aid);
//		
//		return "Deleted";
//	}
	@DeleteMapping( value = "/alien/{id}")
	@Transactional
    public String doDelete(@PathVariable int id){
        repo.deleteById(id);
        return "Deleted";
       
    }
	
	@PutMapping(path="/alien", consumes= {"application/json"})
	public Alien SaveOrUpdate(@RequestBody Alien alien) {
		
		repo.save(alien);
		return alien;	
	}

}
