import java.util.List;

public interface Client {
    void receive(Message message);
    List<Category> getCategories();
}
