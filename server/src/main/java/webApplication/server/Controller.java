package webApplication.server;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private MovieTVRepository repository;
    @Autowired
    private MovieTVService service;
    @Autowired
    private UserService userService;
    
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
            customizedResponse = new CustomizedResponse(" Get Movie with id " + id , Collections.singletonList(service.findMovieTvByID(id)));
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
            customizedResponse = new CustomizedResponse(" Get Movie / TV with title: " + title , service.searchMovieTVs(title));
        }catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @PutMapping(value="/movieTv/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieTV> updateMovieTv(@PathVariable("id") String id, @RequestBody MovieTV newMovieTV) {
        CustomizedResponse customizedResponse = null;
        try{
            customizedResponse = new CustomizedResponse(" Updated Movie / TV with id: " + id , Collections.singletonList(service.updateMovieTVs(id, newMovieTV)));
        } catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @PostMapping(value="/movieTv", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieTV> addMovieTv(@RequestBody MovieTV newMovieTV) {
        CustomizedResponse customizedResponse = null;
        try{
            customizedResponse = new CustomizedResponse(" Added Movie / TV " , Collections.singletonList(service.addMovieTV(newMovieTV)));
        } catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @DeleteMapping(value="/movieTv/{id}")
    public ResponseEntity<MovieTV> deleteMovieTv(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try{
            service.deleteMovieTvByID(id);
        } catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel newUser) {
        CustomizedResponse customizedResponse = null;
        try{
            customizedResponse = new CustomizedResponse(" Added User " , Collections.singletonList(userService.addUser(newUser)));
        } catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try{
            customizedResponse = new CustomizedResponse(" Get User with id " + id , Collections.singletonList(userService.getUser(id)));
        } catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
