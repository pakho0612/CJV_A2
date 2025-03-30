package webApplication.server;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private MovieTVRepository repository;
    @Autowired
    private MovieTVService service;
    
    @RequestMapping("/")
    public String home() {
        return "Hello Worldfsdfd";
    }
    
    @GetMapping("/movies")
    public List<MovieTV> allMovies() {
        System.out.println("accessing all movies");
        return service.getAllMovies();
    }
    @GetMapping(value = "/movies", params = "featured")
    public List<MovieTV> featuredMovies(@RequestParam Boolean featured) {
        System.out.println("accessing movies featured: "+featured);
        return service.getfeaturedMovies(featured);
    }

    @GetMapping("/TVs")
    public List<MovieTV> allTvs() {
        System.out.println("accessing all Tvs");
        return service.getAllTVs();
    }
    @GetMapping(value = "/TVs", params = "featured")
    public List<MovieTV> featuredTVs(@RequestParam Boolean featured) {
        System.out.println("accessing TVs featured: "+featured);
        return service.getfeaturedTVs(featured);
    }

    @GetMapping(value = "/movieTv", params = "id")
    public Optional<MovieTV> findMoviesTvByID(@RequestParam String id) {
        System.out.println("accessing moviesTv id: "+id);
        return repository.findById(Integer.parseInt(id));
    }
    @GetMapping(value = "/movieTv", params = "title")
    public List<MovieTV> findMoviesTvByTitle(@RequestParam String title) {
        System.out.println("accessing moviesTv querystring: "+title);
        return service.searchMovieTVs(title);
    }
}
