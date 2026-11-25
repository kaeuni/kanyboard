import java.util.ArrayList;

public class PostRepository {
    private ArrayList<Post> posts = new ArrayList<>();
    private int nextId = 1;

    public int save(String title, String content, String author) {
        Post post = new Post(nextId++, title, content, author);
        posts.add(post);
        return post.getId();
    }

    public Post findById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        } return null;
    }

    public ArrayList<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public void update(Post post, String title, String content, String author) {
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
    }
    public void delete(Post post) {
        posts.remove(post);
    }
}