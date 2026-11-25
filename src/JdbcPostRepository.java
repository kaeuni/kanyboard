import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepository implements PostRepository {

    @Override
    public int save(String title, String content, String author) {
        String sql = "INSERT INTO posts (title, content, author) values (?, ?, ?)";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, author);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows != 1) {
                throw new IllegalStateException("게시글 등록에 실패했습니다.");
            }
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new IllegalStateException("게시글 ID를 가져오지 못했습니다.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("게시글 등록 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public Post findById(int id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("게시글 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public List<Post> findAll() {
        String sql = "SELECT * FROM posts";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            try (ResultSet rs = pstmt.executeQuery()) {
                List<Post> posts = new ArrayList<>();
                while (rs.next()) {
                    posts.add(new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author")
                    ));
                }
                return posts;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("게시글 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void update(Post post, String title, String content, String author) {
        String sql = "UPDATE posts SET title = ?, content = ?, author = ? WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setString(3, author);
            pstmt.setInt(4, post.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows != 1) {
                throw new IllegalStateException("게시글 수정에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("게시글 수정 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void delete(Post post) {
        String sql = "DELETE FROM posts WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, post.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows != 1) {
                throw new IllegalStateException("게시글 삭제에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("게시글 삭제 중 오류가 발생했습니다.", e);
        }
    }
}
