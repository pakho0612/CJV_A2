package webApplication.server;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movieTV")
public record MovieTV(
    @Id String id,
    String title,
    String type,
    String smallPoster,
    String largePoster,
    String synopsis,
    String pricePurchase,
    String priceToRent,
    String views,
    String releaseDate,
    ArrayList<String> genre,
    String rating,
    Boolean featured) {

}
