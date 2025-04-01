package webApplication.server;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "movieTV")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieTV {
    @Id 
    @Indexed(unique=true)
    public Integer id;
    public String title;
    public String type;
    public String smallPoster;
    public String largePoster;
    public String synopsis;
    public String pricePurchase;
    public String priceToRent;
    public String views;
    public String releaseDate;
    public ArrayList<String> genre;
    public String rating;
    public Boolean featured;
}
