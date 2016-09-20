package org.montes.test.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.montes.test.messenger.model.Comment;
import org.montes.test.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService cs = new CommentService();
	
	@GET
	public List<Comment> getAllCommentsForMessage (@PathParam("messageId") long messageId) {
		return cs.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getCommentForMessageById (@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return cs.getComment(messageId, commentId);
	}
	
	@POST
	public Comment addCommentForMessage(@PathParam("messageId") long messageId, Comment comment) {
		return cs.addComment(messageId, comment);
	}
	
	@PUT
	public Comment updateCommentForMessage(@PathParam("messageId") long messageId, 
										   @PathParam("commentId") long commentId, 
										   Comment comment) {
		comment.setId(commentId);
		
		return cs.updateComment(messageId, comment);
	}
	
	@DELETE
	public Comment removeCommentForMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return cs.removeComment(messageId, commentId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
