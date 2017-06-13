package PMS.PlayerMatchSysteem.webservices;
// Created on 6-6-2017.

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Management;
import PMS.PlayerMatchSysteem.model.Player;
import PMS.PlayerMatchSysteem.model.Stat;
import PMS.PlayerMatchSysteem.persistence.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.sql.SQLException;

@PermitAll
@Path("/sms")
public class SmsResource {

    @GET
    @Path("/allplayers")
    @Produces(MediaType.APPLICATION_JSON)
    public String allPlayer() {
        PlayerDAO pd = new PlayerDAO();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Player p : pd.getAllPlayers()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("spelerid", p.getID())
                    .add("spelernaam", p.getName());
            jab.add(job);
        }
        return jab.build().toString();
    }

    @GET
    @Path("/allgames")
    @Produces(MediaType.APPLICATION_JSON)
    public String allGames() {
        GameDAO gd = new GameDAO();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Game g : gd.getAllGamesWithNames()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("matchid", g.getGame_id())
                    .add("speler1", g.getPlayer1name())
                    .add("speler2", g.getPlayer2name());
            jab.add(job);
        }
        return jab.build().toString();
    }

    @GET
    @Path("/allrules")
    @Produces(MediaType.APPLICATION_JSON)
    public String allRules() {
        ManagementDAO md = new ManagementDAO();
        JsonObjectBuilder job = Json.createObjectBuilder();
        Management rules = md.getAllRules();
        job.add("rule1", rules.getRule1())
                .add("rule2", rules.getRule2())
                .add("rule3", rules.getRule3())
                .add("rule4", rules.getRule4())
                .add("rule5", rules.getRule5());
        return job.build().toString();
    }

    @GET
    @Path("/allstats")
    @Produces(MediaType.APPLICATION_JSON)
    public String allStats() {
        StatDAO sd = new StatDAO();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Stat stat : sd.getAllStats()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("rank", stat.getRank())
                    .add("spelernaam", stat.getName())
                    .add("punten", stat.getPunten())
                    .add("doelsaldo", stat.getDoelsaldo())
                    .add("tegenpunten", stat.getTegenpunten())
                    .add("matchesGespeeld", stat.getGames());
            jab.add(job);
        }
        return jab.build().toString();
    }

    @POST
    @RolesAllowed("manager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changerules")
    public Response changeRules(String json){
        ManagementDAO md = new ManagementDAO();
        boolean success = false;
        JsonObject object = stringToJson(json);
        double r1 = Double.parseDouble(object.getString("r1"));
        double r2 = Double.parseDouble(object.getString("r2"));
        double r3 = Double.parseDouble(object.getString("r3"));
        double r4 = Double.parseDouble(object.getString("r4"));
        double r5 = Double.parseDouble(object.getString("r5"));

        success = md.updateRules(r1,r2,r3,r4,r5);

        if (success) {
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @POST
    @RolesAllowed("manager")
    @Path("/newplayer/{name}")
    public Response createNewPlayer(@PathParam("name") String name) {
        PlayerDAO pd = new PlayerDAO();

        boolean success = false;
        success = pd.createPlayer(name);

        if (success) {
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @DELETE
    @RolesAllowed("manager")
    @Path("/deleteplayer/{playerid}")
    public Response deletePlayer(@PathParam("playerid") int playerid) {
        PlayerDAO pd = new PlayerDAO();
        boolean success = false;
        success = pd.deletePlayer(playerid);

        if (success) {
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @POST
    @RolesAllowed("manager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/submitscore")
    public Response submitScore(String json) {
        StatDAO sd = new StatDAO();
        boolean success = false;
        JsonObject object = stringToJson(json);
        int gid = Integer.parseInt(object.getString("gid"));
        double p1s = Double.parseDouble(object.getString("p1s"));
        double p2s = Double.parseDouble(object.getString("p2s"));
        double p1p = Double.parseDouble(object.getString("p1p"));
        double p2p = Double.parseDouble(object.getString("p2p"));

        success = sd.setStat(p1s, p2s, gid, p1p, p2p);

        if (success) {
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @POST
    @RolesAllowed("manager")
    @Path("/restart")
    public Response restart() throws SQLException {
        TournamentDAO td = new TournamentDAO();
        GameDAO gd = new GameDAO();
        boolean success = false;

        if (!td.getAllTournaments().isEmpty()) {
            int tid = td.getNewestTournament();
            td.deleteTournament(tid);
        } else {
            System.out.println("no tournaments to delete skipping delete...");
        }
        success = gd.createAllGames();
        if (success) {
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @POST
    @RolesAllowed("manager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changepass")
    public Response ChangePass(String json) {
        ManagementDAO md = new ManagementDAO();
        boolean success = false;
        JsonObject object = stringToJson(json);
        String pass = object.getString("pass");
        String pass1 = object.getString("pass1");
        success = md.changeMPass(pass, pass1);
        if (success) {
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    private JsonObject stringToJson(String jsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        return object;
    }
}
