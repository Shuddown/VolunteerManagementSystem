/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package volunteermanagementsystem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Location;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }


    public static void main(String[] args) throws JsonProcessingException{
        App a = new App();
        System.out.println(a.getGreeting());
        Location l = Location.getLocation("your");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        jsonString = objectMapper.writeValueAsString(l);
        System.out.println("Serialized JSON: "+ jsonString);
        System.out.println("Deserialized JSON: "+ objectMapper.readValue(jsonString, Location.class));
       
    }
}
