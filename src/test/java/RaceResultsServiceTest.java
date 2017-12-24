import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class RaceResultsServiceTest {

    private RaceResultsService raceResults;
    private Message message;
    private Client clientA;
    private Client clientB;
    private RaceLogger logger;

    private static final String MESSAGE_CONTENT = "Content of message";
    private static final String MESSAGE_DATE = "23.12.2017";

    @Before
    public void setUp() {
        logger = mock(RaceLogger.class);

        raceResults = new RaceResultsService();
        raceResults.setLogger(logger);

        message = mock(Message.class);
        clientA = mock(Client.class, "clientA");
        clientB = mock(Client.class, "clientB");
    }

    @Test
    public void notSubscribedClientShouldNotReceiveMessage() {
        raceResults.send(message);

        verify(clientA, never()).receive(message);
        verify(clientB, never()).receive(message);
    }

    @Test
    public void subscribedClientShouldReceiveMessage() {
        when(clientA.getCategories()).thenReturn(Collections.singletonList(Category.F1_RACES));
        when(message.getCategory()).thenReturn(Category.F1_RACES);

        raceResults.addSubscriber(clientA);
        raceResults.send(message);

        verify(clientA).receive(message);
    }

    @Test
    public void allSubscribedClientsShouldReceiveMessages() {
        when(clientA.getCategories()).thenReturn(Collections.singletonList(Category.F1_RACES));
        when(clientB.getCategories()).thenReturn(Collections.singletonList(Category.F1_RACES));

        when(message.getCategory()).thenReturn(Category.F1_RACES);

        raceResults.addSubscriber(clientA);
        raceResults.addSubscriber(clientB);

        raceResults.send(message);

        verify(clientA).receive(message);
        verify(clientB).receive(message);
    }

    @Test
    public void shouldSendOnlyOneMessageToMultiSubscriber() {
        when(clientA.getCategories()).thenReturn(Collections.singletonList(Category.F1_RACES));
        when(message.getCategory()).thenReturn(Category.F1_RACES);

        raceResults.addSubscriber(clientA);
        raceResults.addSubscriber(clientA);

        raceResults.send(message);

        verify(clientA).receive(message);
    }

    @Test
    public void unsubscribedClientShouldNotReceiveMessages() {
        raceResults.addSubscriber(clientA);
        raceResults.removeSubscriber(clientA);

        raceResults.send(message);

        verify(clientA, never()).receive(message);
    }

    @Test
    public void notSubscribedToCategoryClientShouldNotReceiveMessageOfTheSameCategory() {
        when(message.getCategory()).thenReturn(Category.F1_RACES);

        raceResults.addSubscriber(clientA);

        raceResults.send(message);

        verify(clientA, never()).receive(message);
    }

    @Test
    public void subscribedToCategoryClientShouldReceiveMessageOfTheSameCategory() {
        when(clientA.getCategories()).thenReturn(Collections.singletonList(Category.F1_RACES));
        when(message.getCategory()).thenReturn(Category.F1_RACES);

        raceResults.addSubscriber(clientA);

        raceResults.send(message);

        verify(clientA).receive(message);
    }

    @Test
    public void subscribedToManyCategoriesClientShouldReceiveMessagesOfTheSameCategories() {
        when(clientA.getCategories()).thenReturn(Arrays.asList(Category.F1_RACES, Category.HORSE_RACES));
        when(message.getCategory()).thenReturn(Category.F1_RACES);

        Message message2 = mock(Message.class);
        when(message2.getCategory()).thenReturn(Category.HORSE_RACES);

        raceResults.addSubscriber(clientA);

        raceResults.send(message);
        raceResults.send(message2);

        verify(clientA).receive(message);
        verify(clientA).receive(message2);
    }

    @Test
    public void messageContentAndDateShouldBeLogged() {
        when(clientA.getCategories()).thenReturn(Collections.singletonList(Category.F1_RACES));

        when(message.getCategory()).thenReturn(Category.F1_RACES);
        when(message.getContent()).thenReturn(MESSAGE_CONTENT);
        when(message.getDate()).thenReturn(MESSAGE_DATE);

        raceResults.addSubscriber(clientA);
        raceResults.send(message);

        verify(logger).log(MESSAGE_DATE, MESSAGE_CONTENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notSubscribedClientTriesToUnsubscribe() {
        raceResults.removeSubscriber(clientA);

    }

//    @Test
//    public void notSubscribedClientTriesToUnsubscribe() {
//        //raceResults.removeSubscriber(clientA);
//        catchException(raceResults).removeSubscriber(clientA);
//
//        assertTrue(caughtException() instanceof IllegalArgumentException);
//        assertEquals("Client is not found in subscribed clients", caughtException().getMessage());
//    }
}
