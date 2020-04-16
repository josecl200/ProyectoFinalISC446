package routes;
import org.rosuda.JRI.*;
import spark.Spark;

public class Main {
    public static void main(String[] args) throws Exception {
        Spark.port(42069);
        Spark.staticFileLocation("/public");
        Routes.rutas();
    }

}
