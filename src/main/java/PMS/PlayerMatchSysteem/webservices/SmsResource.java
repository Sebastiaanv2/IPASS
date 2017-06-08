package PMS.PlayerMatchSysteem.webservices;
// Created on 6-6-2017.

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.persistence.TournamentDAO;

import javax.annotation.security.PermitAll;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@PermitAll
@Path("/schema")
public class SmsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSchema() {
        TournamentDAO td = new TournamentDAO();

        System.out.println("PMS.PlayerMatchSysteem.webservices.SmsResource");
        JsonArrayBuilder jab = Json.createArrayBuilder();

//        for (Game g : td.getAllTournaments()) {
//            JsonObjectBuilder job = buildCountryObject(c);
//            jab.add(job);
//        }

        return jab.build().toString();

    }



}
