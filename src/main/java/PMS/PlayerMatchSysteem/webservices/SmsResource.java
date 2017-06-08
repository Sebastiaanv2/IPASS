package PMS.PlayerMatchSysteem.webservices;
// Created on 6-6-2017.

import PMS.PlayerMatchSysteem.model.Player;
import PMS.PlayerMatchSysteem.persistence.DatabaseConn;
import static PMS.PlayerMatchSysteem.persistence.DatabaseConn.close;
import PMS.PlayerMatchSysteem.persistence.PlayerDAO;

import javax.annotation.security.PermitAll;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@PermitAll
@Path("/sms")
public class SmsResource {

    @PUT
    @Path("/newplayer/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewPlayer(@PathParam("name") String name) {
        PlayerDAO pd = new PlayerDAO();

        System.out.println("PMS.PlayerMatchSysteem.webservices.SmsResource");
        boolean success = false;
        success = pd.createPlayer(name);

        if(success){
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @GET
    @Path("/allplayers")
    @Produces(MediaType.APPLICATION_JSON)
    public String allPlayer() throws SQLException {
        new DatabaseConn("MYSQL").open();
        PlayerDAO pd = new PlayerDAO();
        System.out.println("PMS.PlayerMatchSysteem.webservices.SmsResource");
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Player p : pd.getAllPlayers()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("spelerid", p.getID())
                    .add("spelernaam", p.getName());
            jab.add(job);
        }
        DatabaseConn.close();
        return jab.build().toString();
    }
}
