package webApplication.server;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    @Id 
    @Indexed(unique=true)
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
}
