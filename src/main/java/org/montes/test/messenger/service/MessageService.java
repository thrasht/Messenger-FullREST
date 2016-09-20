package org.montes.test.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.montes.test.messenger.database.DatabaseClass;
import org.montes.test.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService () {
		messages.put(1L, new Message(1, "Hello World!", "Montes"));
		messages.put(2L, new Message(2, "Hello Ed!", "Montes"));
	}
	
	public List<Message> getAllMessages () {		
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear (int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated (int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		
		if (start + size > list.size()) {
			return new ArrayList<Message>();
		}
		
		return list.subList(start, start + size);
	}
	
	
	public Message getMessage (long id) {
		return messages.get(id);
	}
	
	public Message addMessage (Message m) {
		m.setId(messages.size() + 1);
		messages.put(m.getId(), m);
		
		return m;
	}
	
	public Message updateMessage (Message m) {
		if (m.getId() <= 0) {
			return null;
		}
		
		messages.put(m.getId(), m);
		return m;
	}
	
	public Message deleteMessage (long id) {
		return messages.remove(id);
	}
	
	
	
	
}
