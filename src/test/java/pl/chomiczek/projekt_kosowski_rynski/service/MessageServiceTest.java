package pl.chomiczek.projekt_kosowski_rynski.service;

import com.detectlanguage.errors.APIError;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.chomiczek.projekt_kosowski_rynski.model.Message;
import pl.chomiczek.projekt_kosowski_rynski.model.MessageDto;
import pl.chomiczek.projekt_kosowski_rynski.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageServiceTest {

    @Mock
    MessageRepository messageRepository;

    @Mock
    SenderMail senderMail;

    @InjectMocks
    MessageService messageService;

    private List<Message> messageList;

    @Before
    public void init() throws Exception {
        messageList = new ArrayList<>();
        messageList.add(new Message("Hello", "en", "legnin@gmail.com"));
        messageList.add(new Message("Cześć", "pl", "legnin@gmail.com"));
        messageList.add(new Message("Что ты делаешь сегодня", "ru", "legnin@gmail.com"));
        messageList.add(new Message("Qual é o seu prato favorito?", "pt", "legnin@gmail.com"));
    }

    @After
    public void clear() {
        messageList.clear();
    }

    @Test
    public void should_return_cars() {
        when(messageRepository.findAll()).thenReturn(messageList);
        List<Message> emptyList = messageService.getMessages();
        Assert.assertEquals(4, emptyList.size());
    }

    @Test
    public void should_not_return_cars() {
        when(messageRepository.findAll()).thenReturn(messageList);
        List<Message> emptyList = messageService.getMessages();
        Assert.assertNotEquals(2, emptyList.size());
    }

    @Test
    public void should_return_proper_car() throws APIError {
        MessageDto messageDto = new MessageDto("Dzisiaj mamy wtorek i piszemy testy", "legnin@gmail.com");
        Message message = new Message("Dzisiaj mamy wtorek i piszemy testy", "pl", "legnin@gmail.com");
        //doAnswer(invocation -> messageList.add(message)).when(messageRepository.save(message));
        Message message2 = messageService.addMessage(messageDto);
        Assert.assertEquals(message.toString(), message2.toString());
    }

    @Test
    public void should_not_return_proper_car() throws APIError {
        MessageDto messageDto = new MessageDto("Dzisiaj mamy wtorek i piszemy testy", "legnin@gmail.com");
        Message message = new Message("Dzisiaj mamy wtorek i piszemy testy", "en", "legnin@gmail.com");
        Message message2 = messageService.addMessage(messageDto);
        Assert.assertNotEquals(message.toString(), message2.toString());
    }

}