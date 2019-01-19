import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/greetings")
public class BotResource {
    @GET
    @Path("/{name}")
    public String getGreetings(@PathParam("name") String name){
        return "Hello, " + name + "!";
    }
}