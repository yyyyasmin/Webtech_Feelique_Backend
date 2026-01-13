package htw.webtech.Feelique.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TokenAuthFilter implements Filter {

    private final AuthTokenRepository tokenRepo;

    public TokenAuthFilter(AuthTokenRepository tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            var tokenOpt = tokenRepo.findById(token);
            if (tokenOpt.isPresent()) {
                var authToken = tokenOpt.get();

                if (authToken.getExpiresAt() == null || authToken.getExpiresAt().isAfter(LocalDateTime.now())) {
                    String username = authToken.getUser().getUsername();

                    var auth = new UsernamePasswordAuthenticationToken(username, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        chain.doFilter(request, response);
    }
}