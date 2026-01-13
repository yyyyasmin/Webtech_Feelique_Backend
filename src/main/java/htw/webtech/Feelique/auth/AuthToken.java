package htw.webtech.Feelique.auth;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuthToken {

    @Id
    private String token; // UUID string

    @ManyToOne(optional = false)
    private User user;

    private LocalDateTime expiresAt;

    public AuthToken() {}

    public AuthToken(String token, User user, LocalDateTime expiresAt) {
        this.token = token;
        this.user = user;
        this.expiresAt = expiresAt;
    }

    public String getToken() { return token; }
    public User getUser() { return user; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
}