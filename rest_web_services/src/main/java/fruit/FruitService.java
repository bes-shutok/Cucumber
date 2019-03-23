package fruit;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class FruitService {
    @GET
    @Path("/fruits")
    @Produces(MediaType.APPLICATION_JSON)
    public Fruit[] getAllFruits() {
        List<Fruit> fruits = getFruitsFromFile();
        return fruits.toArray(new Fruit[]{});
    }
    private List<Fruit> getFruitsFromFile() {
        String fruitJson = readJsonFruitFile();
        return buildListFromJson(fruitJson);
    }
    private String readJsonFruitFile() {
        try {
            java.nio.file.Path path = FileSystems.getDefault().getPath("fruit.json");
            return new String(Files.readAllBytes(path));
        }
        catch (IOException ioe) {
            return "[]";
        }
    }
    private List<Fruit> buildListFromJson(String fruitJson) {
        final TypeToken<List<Fruit>> token = new TypeToken<List<Fruit>>() {};
        final Type type = token.getType();
        final Gson gson = new Gson();
        return gson.fromJson(fruitJson, type);
    }
}
