package il.kikoliashvili.chatSiteBack.filter;

import il.kikoliashvili.chatSiteBack.entities.SiteUser;
import il.kikoliashvili.chatSiteBack.services.UserService;
import il.kikoliashvili.chatSiteBack.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserService userService;

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        try {
            // 1. Берем заголовок Authorization из запроса
            final String authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String jwt = null;

            // 2. Если есть заголовок "Bearer токен"
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7); // Отрезаем "Bearer "
                username = jwtUtil.extractUsername(jwt); // Извлекаем имя пользователя
            }

            // 3. Если пользователь найден и он еще не авторизован
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                SiteUser user = userService.getUserByUsername(username);

                // 4. Проверяем валидность токена
                if (jwtUtil.validateToken(jwt, user)) {
                    // 5. Разрешаем доступ
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            String errorMsg = "doFilterInternal -> " + e.getMessage();
            throw new AuthenticationServiceException(errorMsg, e);
        }
    }
}

