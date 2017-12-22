import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;


public class RaceResultsServiceTest {

    private RaceResultsService raceResults = new RaceResultsService();
    private Message message = mock(Message.class);
    private Client clientA = mock(Client.class, "clientA");
    private Client clientB = mock(Client.class, "clientB");

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


}
