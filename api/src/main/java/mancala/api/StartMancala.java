package mancala.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.Player;
import mancala.domain.PlayingBowl;

@Path("/start")
public class StartMancala {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
			@Context HttpServletRequest request, 
			PlayerInput players) {
				
        String namePlayer1 = players.getNameplayer1();
		String namePlayer2 = players.getNameplayer2();
		
		Player player = new Player(namePlayer1, namePlayer2);
		PlayingBowl firstBowl = new PlayingBowl(player);
		
        HttpSession session = request.getSession(true);
		session.setAttribute("firstBowl", firstBowl);
        session.setAttribute("player1", namePlayer1);
        session.setAttribute("player2", namePlayer2);

		var output = new Mancala(firstBowl, namePlayer1, namePlayer2);
		return Response.status(200).entity(output).build();
	}
}
