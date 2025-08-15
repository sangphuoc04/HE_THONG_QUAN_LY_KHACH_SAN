package project.myweb.se;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

// import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestConnection {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/connection")
    public ResponseEntity<String> testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            String dbName = connection.getCatalog();
            return ResponseEntity.ok("Connection successful! Database: " + dbName);

        } catch (SQLException e) {
            return ResponseEntity.status(500).body("Connection failed: " + e.getMessage());
        }
    }
}
