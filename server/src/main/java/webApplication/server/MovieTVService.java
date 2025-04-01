package webApplication.server;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public MovieTV updateMovieTVs(String id, MovieTV newMovieTV) throws Exception{
        Optional<MovieTV> movietv = repository.findById(Integer.parseInt(id));
        if (!movietv.isPresent())
        {
            throw new Exception (" Movie with id:" + id + " is not found ");
        }
        
        if (newMovieTV.getTitle()==null || newMovieTV.getTitle().isEmpty())
            throw new Exception (" Trying to Update Movie with id:" + id +" but Title value does not exist ");
        movietv.get().setTitle(newMovieTV.getTitle());

        if (newMovieTV.getType()==null || newMovieTV.getType().isEmpty())
            throw new Exception (" Trying to Update Movie with id:" + id +" but Type value does not exist ");
        movietv.get().setType(newMovieTV.getType());

        if (newMovieTV.getSynopsis()==null || newMovieTV.getSynopsis().isEmpty())
            throw new Exception (" Trying to Update Movie with id:" + id +" but Synopsis value does not exist ");
        movietv.get().setSynopsis(newMovieTV.getSynopsis());

        if (newMovieTV.getSmallPoster()==null || newMovieTV.getSmallPoster().isEmpty())
            throw new Exception (" Trying to Update Movie with id:" + id +" but SmallPoster value does not exist ");
        movietv.get().setSmallPoster(newMovieTV.getSmallPoster());

        if (newMovieTV.getLargePoster()==null || newMovieTV.getLargePoster().isEmpty())
            throw new Exception (" Trying to Update Movie with id:" + id +" but LargePoster value does not exist ");
        movietv.get().setLargePoster(newMovieTV.getLargePoster());
        
        if (newMovieTV.getPriceToRent()==null || newMovieTV.getPriceToRent().isEmpty())
            throw new Exception (" Trying to Update Movie with id:" + id +" but PriceToRent value does not exist ");
        movietv.get().setPriceToRent(newMovieTV.getPriceToRent());

        if (newMovieTV.getPricePurchase()==null || newMovieTV.getPricePurchase().isEmpty())
            throw new Exception (" Trying to Update Movie with id:" + id +" but PricePurchase value does not exist ");
        movietv.get().setPricePurchase(newMovieTV.getPricePurchase());

        if (newMovieTV.getFeatured()==null || newMovieTV.getFeatured()==null)
            throw new Exception (" Trying to Movie Update with id:" + id +" but Feature value does not exist ");
        movietv.get().setFeatured(newMovieTV.getFeatured());

        MovieTV updatedMovieTV = repository.save(movietv.get());
        return updatedMovieTV;
    }

    public MovieTV addMovieTV(MovieTV newMovieTV) throws Exception{
        MovieTV movietv = new MovieTV();
        if (newMovieTV.getTitle()==null || newMovieTV.getTitle().isEmpty())
            throw new Exception (" Trying to add Movie but Title value does not exist ");
        movietv.setTitle(newMovieTV.getTitle());

        if (newMovieTV.getType()==null || newMovieTV.getType().isEmpty())
            throw new Exception (" Trying to add Movie but Type value does not exist ");
        movietv.setType(newMovieTV.getType());

        if (newMovieTV.getSynopsis()==null || newMovieTV.getSynopsis().isEmpty())
            throw new Exception (" Trying to add Movie but Synopsis value does not exist ");
        movietv.setSynopsis(newMovieTV.getSynopsis());

        if (newMovieTV.getSmallPoster()==null || newMovieTV.getSmallPoster().isEmpty())
            throw new Exception (" Trying to add Movie but SmallPoster value does not exist ");
        movietv.setSmallPoster(newMovieTV.getSmallPoster());

        if (newMovieTV.getLargePoster()==null || newMovieTV.getLargePoster().isEmpty())
            throw new Exception (" Trying to add Movie but LargePoster value does not exist ");
        movietv.setLargePoster(newMovieTV.getLargePoster());
        
        if (newMovieTV.getPriceToRent()==null || newMovieTV.getPriceToRent().isEmpty())
            throw new Exception (" Trying to add Movie but PriceToRent value does not exist ");
        movietv.setPriceToRent(newMovieTV.getPriceToRent());

        if (newMovieTV.getPricePurchase()==null || newMovieTV.getPricePurchase().isEmpty())
            throw new Exception (" Trying to add Movie but PricePurchase value does not exist ");
        movietv.setPricePurchase(newMovieTV.getPricePurchase());

        if (newMovieTV.getFeatured()==null)
            throw new Exception (" Trying to add Movie but Feature value does not exist ");
        movietv.setFeatured(newMovieTV.getFeatured());

        //generate id
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "_id"));
        MovieTV lastmMovieTV = mongoTemplate.findOne(query, MovieTV.class);
        movietv.setId(lastmMovieTV.getId() + 1);

        MovieTV updatedMovieTV = repository.save(movietv);
        return updatedMovieTV;
    }

    public void deleteMovieTvByID(String id) throws Exception{
        Optional<MovieTV> movietv = repository.findById(Integer.parseInt(id));
        if (!movietv.isPresent())
        {
            throw new Exception (" Movie with " + id + " is not found ");
        }
        repository.delete(movietv.get());
    }
}
