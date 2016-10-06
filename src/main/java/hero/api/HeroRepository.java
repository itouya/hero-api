package hero.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Integer> {
	// Hero.name に token を含む Hero を検索する
	public List<Hero> findByNameContainsOrderByIdAsc(String token);
}
