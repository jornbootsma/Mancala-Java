package mancala.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import mancala.api.models.Mancala;
import mancala.api.models.MoveInput;
import mancala.domain.PlayingBowl;

@Path("/play")
public class PlayMancala {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response initialize(
			@Context HttpServletRequest request, 
			MoveInput moveInput) {
        
        int pitIndex = moveInput.getPitIndex();

        HttpSession session = request.getSession();
        PlayingBowl firstBowl = (PlayingBowl) session.getAttribute("firstBowl");
        ((PlayingBowl) firstBowl.getNthNeighbour(pitIndex)).playBowl();
        session.setAttribute("firstBowl", firstBowl);

        String namePlayer1 = (String) session.getAttribute("player1");
        String namePlayer2 = (String) session.getAttribute("player2");

		var output = new Mancala(firstBowl, namePlayer1, namePlayer2);
		return Response.status(200).entity(output).build();
	}
}
