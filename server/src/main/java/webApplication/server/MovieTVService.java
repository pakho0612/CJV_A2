package webApplication.server;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MovieTVService {
    
    @Autowired
    private MovieTVRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public Optional<MovieTV> findMovieTvByID(String id) throws Exception{
        Optional<MovieTV> movietv = repository.findById(Integer.parseInt(id));
        if (!movietv.isPresent())
        {
            throw new Exception (" Movie with " + id + " is not found ");
        }
        return movietv;
    }
    
    public List<MovieTV> getAllMovies() {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("Movie"));
        List<MovieTV> movies = mongoTemplate.find(query, MovieTV.class);
        return movies;
    }

    public List<MovieTV> getfeaturedMovies(Boolean featured) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("Movie"));
        query.addCriteria(Criteria.where("featured").is(featured));
        List<MovieTV> TV = mongoTemplate.find(query, MovieTV.class);
        return TV;
    }

    public List<MovieTV> getAllTVs() {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("TV"));
        List<MovieTV> TV = mongoTemplate.find(query, MovieTV.class);
        return TV;
    }

    public List<MovieTV> getfeaturedTVs(Boolean featured) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("TV"));
        query.addCriteria(Criteria.where("featured").is(featured));
        List<MovieTV> TV = mongoTemplate.find(query, MovieTV.class);
        return TV;
    }

    public List<MovieTV> searchMovieTVs(String titleString) throws Exception{
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(titleString, "i"));
        List<MovieTV> movietvs = mongoTemplate.find(query, MovieTV.class);
        if (movietvs.isEmpty())
        {
            throw new Exception (" Movie/TV with title" + titleString + " is not found ");
        }
        return movietvs;
    }
}
