public class JdbcPostRepositoryTest {
    public static void main(String[] args) {
        PostRepository postRepository = new JdbcPostRepository();

        int savedId = postRepository.save(
                "JDBC 테스트",
                "MySQL에 게시글을 저장합니다.",
                "테스트 작성자"
        );

        System.out.println("등록된 게시글 ID: " + savedId);
    }
}