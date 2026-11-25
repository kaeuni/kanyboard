import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest {

    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("DB 연결 성공!");
        } catch (SQLException e) {
            System.out.println("DB 연결 실패!");
            System.out.println(e.getMessage());
        }
    }
}