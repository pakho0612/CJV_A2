package webApplication.server;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel addUser(UserModel user) throws Exception{
        Optional<UserModel> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            throw new Exception("user with email:"+user.getEmail()+" already exists");
        }
        if (user.getFirstName()==null || user.getFirstName().isEmpty())
            throw new Exception (" Trying to add user but first name not exists");
        if (user.getLastName()==null || user.getLastName().isEmpty())
            throw new Exception (" Trying to add user but last name not exists");
        if (user.getEmail()==null || user.getEmail().isEmpty())
            throw new Exception (" Trying to add user but email not exists");
        if (!user.getEmail().matches("\\w+@\\w+\\.com"))
            throw new Exception (" Email in incorrect format");
        if (user.getPassword()==null || user.getPassword().isEmpty())
            throw new Exception (" Trying to add user but password not exists");
        
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "_id"));
        UserModel lastUser = mongoTemplate.findOne(query, UserModel.class);
        user.setId(lastUser.getId() + 1);

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public Optional<UserModel> getUser(String id) throws Exception{
        Optional<UserModel> user = userRepository.findById(Integer.parseInt(id));
        if(!user.isPresent()) {
            throw new Exception("user with id:"+id+" not found");
        }
        return user;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<UserModel> user = userRepository.findByEmail(email);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("User with email: " +email+" not found");
        }
        String userE = user.get().getEmail();
        String userP = user.get().getPassword();
        return new User(userE, userP, new ArrayList<>());
    }
}
