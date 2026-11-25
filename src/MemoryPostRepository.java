import java.util.ArrayList;
import java.util.List;

public class MemoryPostRepository implements PostRepository {
    private final List<Post> posts = new ArrayList<>();
    private int nextId = 1;

    @Override
    public int save(String title, String content, String author) {
        Post post = new Post(nextId++, title, content, author);
        posts.add(post);
        return post.getId();
    }

    @Override
    public Post findById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        } return null;
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    @Override
    public void update(Post post, String title, String content, String author) {
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
    }

    @Override
    public void delete(Post post) {
        posts.remove(post);
    }
}