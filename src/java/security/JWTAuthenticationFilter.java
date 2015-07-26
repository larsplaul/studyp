package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import entity.StudyPointUser;
import facade.StudyPointUserFacade;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.security.Principal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthenticationFilter implements ContainerRequestFilter {

  private static final List<Class<? extends Annotation>> securityAnnotations = Arrays.asList(DenyAll.class, PermitAll.class, RolesAllowed.class);

  @Context
  private ResourceInfo resourceInfo;

  @Override
  public void filter(ContainerRequestContext request) throws IOException {

    if (isSecuredResource()) {

        //String token = request.getHeaderString("x-access-token"); 
      String authorizationHeader = request.getHeaderString("Authorization");
      if(authorizationHeader == null){
        makeErrorResponse(request, "No authorization header provided");
        return;
      }
      String token = request.getHeaderString("Authorization").substring("Bearer ".length());

      try {
        String username = getUsernameFromToken(token);
        final User user = getUserByName(username);
        if(user == null){
          makeErrorResponse(request, "Request could not be authenticated");
          return;
        }

        request.setSecurityContext(new SecurityContext() {

          @Override
          public boolean isUserInRole(String role) {
            return user.isUserInRole(role);
          }

          @Override
          public boolean isSecure() {
            return false;
          }

          @Override
          public Principal getUserPrincipal() {
            return user;
          }

          @Override
          public String getAuthenticationScheme() {
            return SecurityContext.BASIC_AUTH;
          }
        });

      } catch (ParseException | JOSEException e) {
        makeErrorResponse(request, "Request could not be authenticated");
      }
    }
  }

  private void makeErrorResponse(ContainerRequestContext request,String msg) {
    JsonObject responseJson = new JsonObject();
    responseJson.addProperty("error", msg);
    request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(new Gson().toJson(responseJson)).build());
  }

  EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudyPointSystemPU");
  private User getUserByName(String name) {
    StudyPointUserFacade facade = new StudyPointUserFacade(emf);
    StudyPointUser user =facade.findStudyPointUser(name);
    if(user != null){
      return new User(user.getUserName(), user.getRolesAsStrings());  //Load from database
    }
    return null;
  }

  private boolean isSecuredResource() {

    for (Class<? extends Annotation> securityClass : securityAnnotations) {
      if (resourceInfo.getResourceMethod().isAnnotationPresent(securityClass)) {
        return true;
      }
    }

    for (Class<? extends Annotation> securityClass : securityAnnotations) {
      if (resourceInfo.getResourceClass().isAnnotationPresent(securityClass)) {
        return true;
      }
    }

    return false;
  }

  private String getUsernameFromToken(String token) throws ParseException, JOSEException {

    SignedJWT signedJWT = SignedJWT.parse(token);
    JWSVerifier verifier = new MACVerifier(Secrets.ADMIN);

    if (signedJWT.verify(verifier)) {
      return signedJWT.getJWTClaimsSet().getSubject();
    } else {
      throw new JOSEException("Firm is not verified.");
    }
  }
}
