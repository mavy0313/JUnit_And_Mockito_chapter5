import java.util.Collection;
import java.util.HashSet;

public class RaceResultsService {

    private Collection<Client> clients = new HashSet<>();

    public void setLogger(RaceLogger logger) {
        this.logger = logger;
    }

    private RaceLogger logger;

    public void addSubscriber(Client client) {
        clients.add(client);
    }

    public void send(Message message) {
        for (Client client : clients) {
            if (client.getCategories().contains(message.getCategory())) {
                client.receive(message);
                logger.log(message.getDate(), message.getContent());
            }
        }
    }

    public void removeSubscriber(Client client) {
        if (!clients.contains(client)) {
            throw new IllegalArgumentException("Client is not found in subscribed clients");
        }
        clients.remove(client);
    }
}
