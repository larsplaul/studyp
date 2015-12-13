
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("remotelogin")
public class RememoteLogin {
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response remoteLogin(String jsonString) throws JOSEException {
    JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
    String username =  json.get("username").getAsString(); 
    String password =  json.get("password").getAsString();
    JsonObject responseJson = new JsonObject();
    String role;
    
    if ((role=Login.authenticate(username, password,false))!=null) { 
      String token = Login.createToken(username, "lam@cphbusieness.dk",role);    
      responseJson.addProperty("username", username);
      responseJson.addProperty("token", token);  
      return Response.
              ok(new Gson().toJson(responseJson)).
              header("Access-Control-Allow-Origin", "*").
              build();
    }  
    throw new NotAuthorizedException("Ilegal username or password",Response.Status.UNAUTHORIZED);
  }
  
  @OPTIONS
  public Response handlePreflightRequest(String jsonString) {
    return Response
            .status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Origin, Accept, Accept-Encoding")
            .header("Access-Control-Allow-Methods", "POST, OPTIONS, HEAD")
            .build();
    
  }
}
