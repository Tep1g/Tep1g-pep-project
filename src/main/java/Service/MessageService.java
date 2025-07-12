package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public boolean createMessage(Message message) {
        if ((!message.message_text.isBlank()) && (message.message_text.length() < 255) && messageDAO.checkPosterExists(message)) {
            return messageDAO.insertMessage(message);
        }
        return false;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        return messageDAO.getMessageById(messageId);
    }

    public boolean deleteMessageById(int messageId) {
        return messageDAO.deleteMessageById(messageId);
    }

    public Message updateMessageById(int messageId, String messageString) {
        if (!messageString.isBlank()) {
            if (messageDAO.updateMessageById(messageId, messageString)) {
                return messageDAO.getMessageById(messageId);
        }
        }
        return null;
    }

    public List<Message> getMessagesByAccountId(int accountId) {
        return messageDAO.getMessagesByAccountId(accountId);
    }
}
