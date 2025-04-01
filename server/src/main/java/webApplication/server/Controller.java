package webApplication.server;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MovieTV> allMovies() {
        CustomizedResponse customizedResponse = new CustomizedResponse(" A list of all movies" , service.getAllMovies());
        
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping(value = "/movies", params = "featured")
    public ResponseEntity<MovieTV> featuredMovies(@RequestParam Boolean featured) {
        CustomizedResponse customizedResponse = new CustomizedResponse(" A list of all movies featured: "+ featured , service.getfeaturedMovies(featured));
        
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/TVs")
    public ResponseEntity<MovieTV> allTvs() {
        CustomizedResponse customizedResponse = new CustomizedResponse(" A list of all TVs" , service.getAllTVs());
        
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/TVs", params = "featured")
    public ResponseEntity<MovieTV> featuredTVs(@RequestParam Boolean featured) {
        CustomizedResponse customizedResponse = new CustomizedResponse(" A list of all TVs featured: "+ featured , service.getfeaturedTVs(featured));
        
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/movieTv", params = "id")
    public ResponseEntity<MovieTV> findMoviesTvByID(@RequestParam String id) {
        System.out.println("accessing moviesTv id: "+id);

        CustomizedResponse customizedResponse = null;
        try{
            //moviestv = service.findMovieTvByID(id);
            customizedResponse = new CustomizedResponse(" Movie with id " + id , Collections.singletonList(service.findMovieTvByID(id)));
        } catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping(value = "/movieTv", params = "title")
    public ResponseEntity<MovieTV> findMoviesTvByTitle(@RequestParam String title) {
        CustomizedResponse customizedResponse = null;
        try{
            customizedResponse = new CustomizedResponse(" Movie / TV with title: " + title , service.searchMovieTVs(title));
        }catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
