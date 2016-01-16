package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import deploy.DeploymentConfiguration;
import facade.StudyPointUserFacade;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.Secrets;

@Path("login")
public class Login {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(String jsonString) throws JOSEException {
    JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
    
    String username =  json.get("username").getAsString(); 
    String password =  json.get("password").getAsString();
    boolean useFronter =  json.get("useFronter").getAsBoolean();
    JsonObject responseJson = new JsonObject();
    String role;  //Refactor to use an enum
    
    if ((role=authenticate(username, password,useFronter))!=null) { 
      String token = createToken(username, "lam@cphbusieness.dk",role);    
      responseJson.addProperty("username", username);
      responseJson.addProperty("token", token);  
      return Response.ok(new Gson().toJson(responseJson)).header("Access-Control-Allow-Origin", "*").build();
    }  
    throw new NotAuthorizedException("Ilegal username or password",Response.Status.UNAUTHORIZED);
  }
  
  
  //Todo Deep deeper into this
  @OPTIONS
  @Produces("application/json")
  @Consumes("application/json")
  public Response loginOpt(String scoresAsJson){
     return Response
            .status(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Origin, Authorization, Accept, Client-Security-Token, Accept-Encoding")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .header("Access-Control-Max-Age", "1209600")
            .build();
  }
   
  static String  authenticate(String userName, String password, boolean useFronter){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    StudyPointUserFacade facade = new StudyPointUserFacade(emf);
    return facade.authenticateUser(userName, password,useFronter);
  }

  static String createToken(String subject, String issuer, String role) throws JOSEException {

    JWSSigner signer = new MACSigner(Secrets.ADMIN.getBytes());
    
    JWTClaimsSet claimsSet = new JWTClaimsSet();
    claimsSet.setSubject(subject); 
    claimsSet.setClaim("username", subject);
    claimsSet.setCustomClaim("role", role);
    Date date = new Date();
    claimsSet.setIssueTime(date);
    claimsSet.setExpirationTime(new Date(date.getTime() + 1000*30)); //1000*60*60*7
    claimsSet.setIssuer(issuer);

    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
    signedJWT.sign(signer);

    return signedJWT.serialize(); 

  }
}