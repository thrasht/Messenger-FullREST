package org.montes.test.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.montes.test.messenger.database.DatabaseClass;
import org.montes.test.messenger.model.Comment;
import org.montes.test.messenger.model.ErrorMessage;
import org.montes.test.messenger.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		
		for (Comment c : comments.values()) {
			System.out.println(c.getMessage());
		}
		
		return new ArrayList<Comment>(comments.values());
	}
	
	
	/* Forma de hacerlo con la mapping exception
	public Comment getComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		
		return comments.get(commentId);
	}
	*/
	
	public Comment getComment(long messageId, long commentId) {
		ErrorMessage error = new ErrorMessage("no se ", 404, "http://tuputamadre.com");
		Response response = Response.status(Status.NOT_FOUND).entity(error).build();
		
		Message message = messages.get(messageId);
		
		if(message == null) {
			throw new WebApplicationException(response);
		}
		
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		
		if(comment == null) {
			throw new NotFoundException(response);
		}
		
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		
		for (Comment c : comments.values()) {
			System.out.println(c.getId());
		}
		
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		
		if (comment.getId() <= 0)
			return null;
		
		comments.put(comment.getId(), comment);
		
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
				
		return comments.remove(commentId);
	}
	
	
	
	
	
	
	
	
	
	

}
