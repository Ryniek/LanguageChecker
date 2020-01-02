package pl.chomiczek.projekt_kosowski_rynski.service;

import com.detectlanguage.errors.APIError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.chomiczek.projekt_kosowski_rynski.model.Message;
import pl.chomiczek.projekt_kosowski_rynski.model.MessageDto;
import pl.chomiczek.projekt_kosowski_rynski.repository.MessageRepository;
import com.detectlanguage.DetectLanguage;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private SenderMail senderMail;

    @Autowired
    public MessageService(MessageRepository messageRepository, SenderMail senderMail) throws APIError {
        this.messageRepository = messageRepository;
        this.senderMail = senderMail;
        messageRepository.save(new Message("Jestem Michał i lubie jeść pierogi", "pl", "legnin@gmail.com"));
        messageRepository.save(new Message("I'm from England and I like bread", "en", "legnin@gmail.com"));
        messageRepository.save(new Message("Ushqimi im i preferuar është mishi", "sq", "legnin@gmail.com"));
        messageRepository.save(new Message("Tutaj wstawiłem jakieś polskie zdanie", "pl", "legnin@gmail.com"));
        messageRepository.save(new Message("Стілець має чотири ніжки", "uk", "legnin@gmail.com"));
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public boolean addMessage(MessageDto messageDto) throws APIError {
        Message message = new Message(messageDto.getText(), detectLanguage(messageDto.getText()), messageDto.getEmail());
        messageRepository.save(message);
        senderMail.sendEmail(getMessagesByLanguage(detectLanguage(messageDto.getText()), messageDto.getEmail()), messageDto.getEmail());
        return true;
    }

    private String detectLanguage(String text) throws APIError {
        DetectLanguage.apiKey = "5106d3b45f5d3819f83f0b4e55becb81";
        String result = DetectLanguage.simpleDetect(text);
        return result;
    }

    private String getMessagesByLanguage(String language, String email) {
        List<Message> messages = messageRepository.findAllByLanguageAndAndEmail(language, email);
        StringBuilder text = new StringBuilder();
        text.append("Wszystkie Twoje dotychczasowe wiadomości w języku: ").append(language).append('\n');
        messages.forEach(message -> text.append(message.getText()).append('\n'));
        return text.toString();
    }
}
