package PMS.PlayerMatchSysteem.webservices;
// Created on 29-5-2017.

import PMS.PlayerMatchSysteem.persistence.ManagementDAO;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.security.Key;
import java.util.Calendar;

@Path("/authentication")
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(String json) {

        try {
            JsonObject object = stringToJson(json);
            String mPass = object.getString("mPass");
            System.out.println("trying to login!");

            // Authenticate the password against the database
            ManagementDAO md = new ManagementDAO();
            String dbmPass = md.getMPass();
            String role = "";
            if (mPass.equals(dbmPass)) {
                role = "manager";
            } else {
                throw new IllegalArgumentException("pass incorrect!");
            }
            // Issue a token for the login
            Calendar expiration = Calendar.getInstance();
            expiration.add(Calendar.MINUTE, 30);
            String token = Jwts.builder()
                    .setSubject("logintoken")
                    .claim("role", role)
                    .setExpiration(expiration.getTime())
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
            // Return the token on the response
            return Response.ok(token).build();
        } catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private JsonObject stringToJson(String jsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        return object;
    }


}
