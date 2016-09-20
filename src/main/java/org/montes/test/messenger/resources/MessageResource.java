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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.montes.test.messenger.model.Message;
import org.montes.test.messenger.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService ms = new MessageService();
	
	@GET
	public List<Message> getMessages (@QueryParam("year") int year,
									  @QueryParam("start") int start,
									  @QueryParam("size") int size ) {
		if (year > 0)
			return ms.getAllMessagesForYear(year);
		
		if (start >= 0 && size > 0)
			return ms.getAllMessagesPaginated(start, size);
		
		return ms.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessageById (@PathParam("messageId") long messageId){
		return ms.getMessage(messageId);
	}
	
	@POST
	public Message addMessage (Message message){
		return ms.addMessage(message);
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
	
	
	
	
	
	
	
	
	
	

}
