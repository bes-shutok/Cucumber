package fruit;
        import cucumber.api.java.en.*;

        import gherkin.deps.com.google.gson.Gson;

        import java.io.PrintWriter;
        import java.util.List;

public class FruitSteps {
    @Given("^the system knows about the following fruit:$")
    public void theSystemKnowsAboutTheFollowingFruit(List<Fruit> fruitlist) throws Throwable {
        Gson gson = new Gson();
        PrintWriter writer = new PrintWriter("fruit.json", "UTF-8");
        writer.println(gson.toJson(fruitlist));
        writer.close();
    }
}
