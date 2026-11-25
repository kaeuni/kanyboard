import java.util.List;

public interface PostRepository {

    int save(String title, String content, String author);

    Post findById(int id);

    List<Post> findAll();

    void update(Post post, String title, String content, String author);

    void delete(Post post);
}