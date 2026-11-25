import java.util.ArrayList;

public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public int createPost(String title, String content, String author) {
        return postRepository.save(title, content, author);
    }

    public ArrayList<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Post findPost(int id) {
        return postRepository.findById(id);
    }

    public void updatePost(Post post, String title, String content, String author) {
        postRepository.update(post, title, content, author);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
