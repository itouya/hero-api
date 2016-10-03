package hero.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hero")
public class HeroRestController {
	@Autowired
	HeroService service;
	
	@CrossOrigin("http://localhost:8080")
	@RequestMapping(method=RequestMethod.GET)
	public List<Hero> getHeroes() {
		return service.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	public Hero getHero(@PathVariable Integer id) {
		return service.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Hero postHero(@RequestBody Hero hero) {
		return service.create(hero);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public Hero putHero(@PathVariable Integer id, @RequestBody Hero hero) {
		hero.setId(id);
		return service.update(hero);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
