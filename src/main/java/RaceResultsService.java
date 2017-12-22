import java.util.Collection;
import java.util.HashSet;

public class RaceResultsService {

    private Collection<Client> clients = new HashSet<>();

    public void addSubscriber(Client client) {
        clients.add(client);
    }

    public void send(Message message) {
        for (Client client : clients) {
            if (client.getCategories().contains(message.getCategory())) {
                client.receive(message);
            }
        }
    }

    public void removeSubscriber(Client clientA) {
        clients.remove(clientA);
    }
}
