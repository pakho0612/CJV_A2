package webApplication.server;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <UserModel, String>{
    Optional<UserModel> findById(Integer Id);
    Optional<UserModel> findByEmail(String email);
}