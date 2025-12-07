package example.CRUDNOTES.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import example.CRUDNOTES.exception.InvalidJwtAuthenticationException;
import example.CRUDNOTES.security.UserPrincipalService;
import example.CRUDNOTES.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String BEARER_PREFIX = "Bearer ";
    private final JwtService jwtService;
    private final UserPrincipalService userPrincipalService;
    private static final List<String> PUBLIC_PATHS = List.of("/login", "/register");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // 👉 Este método define em que rotas o filtro NÃO deve correr.
        // Ex.: /auth/login, /auth/register, /docs, etc.
        // Se o URI da request estiver em PUBLIC_PATHS, o filtro "salta".
        return PUBLIC_PATHS.contains(request.getRequestURI());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // 1. Se não houver header ou não começar por Bearer → segue anónimo
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(BEARER_PREFIX.length()).trim();
   try {
            // 2. Extrair o email do token
            String email = jwtService.extractEmail(token);

            // 3. Se não houver email → token inválido, segue sem autenticar
            if (email == null || email.isBlank()) {
                filterChain.doFilter(request, response);
                return;
            }

            SecurityContext context = SecurityContextHolder.getContext();

            // 4. Se já houver Authentication, não re-autenticas
            if (context.getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 5. Carregar o utilizador da BD via UserDetailsService
            UserDetails userDetails = userPrincipalService.loadUserByUsername(email);

            // 6. Validar o token contra o utilizador real
            if (!jwtService.isTokenValid(token, userDetails)) {
                filterChain.doFilter(request, response);
                return;
            }

            // 7. Criar Authentication com authorities
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
            // 8. Guardar no SecurityContext
            context.setAuthentication(authentication);

            // 9. Continuar a cadeia
            filterChain.doFilter(request, response);

        } catch (InvalidJwtAuthenticationException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }
    }

    }

