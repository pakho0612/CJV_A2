package webApplication.server;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieTVRepository extends MongoRepository<MovieTV, String> {
    Optional<MovieTV> findById(Integer Id);
    
}