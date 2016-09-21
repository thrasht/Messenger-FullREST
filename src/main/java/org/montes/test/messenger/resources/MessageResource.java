package org.montes.test.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.montes.test.messenger.exception.DataNotFoundException;
import org.montes.test.messenger.model.Message;
import org.montes.test.messenger.resources.beans.FilterBean;
import org.montes.test.messenger.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService ms = new MessageService();
	
	@GET
	public List<Message> getMessages (@BeanParam FilterBean filterBean) {
		if (filterBean.getYear() > 0)
			return ms.getAllMessagesForYear(filterBean.getYear() );
		
		if (filterBean.getStart()  >= 0 && filterBean.getSize()  > 0)
			return ms.getAllMessagesPaginated(filterBean.getStart() , filterBean.getSize() );
		
		System.out.println("Puto");
		return ms.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessageById (@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = ms.getMessage(messageId);
		
		if (message == null)
			throw new DataNotFoundException("Message with id: " + messageId + " not found");
		
				
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");
				
		return message;
	}
	
	@POST
	public Response addMessage (Message message, @Context UriInfo uriInfo) {
		Message newMessage = ms.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri).entity(newMessage).build(); 
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessageById (@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		
		return ms.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessageById (@PathParam("messageId") long messageId) {
		ms.deleteMessage(messageId);
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource () {
		return new CommentResource ();
	}
	
	/*
	 * https://www.youtube.com/watch?v=dtO5NQ8K5Wo&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn&index=30
	 */
	
	public String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
		
		return uri;
	}
	
	public String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResources.class)
				.path(message.getAuthor())
				.build()
				.toString();
		
		return uri;
	}
	
	public String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
		
		return uri;
	}
	
	
	
	
	

}
