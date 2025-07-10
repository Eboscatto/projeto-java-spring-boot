package com.eboscatto.projetoJava.security;

import com.eboscatto.projetoJava.model.Usuario;
import com.eboscatto.projetoJava.service.JwtService;
import com.eboscatto.projetoJava.service.UsuarioService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;
@Component
public class JwtFilter extends OncePerRequestFilter {

    public JwtFilter(JwtService jwtService, UsuarioService usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }
    private final JwtService jwtService;

    private final UsuarioService usuarioService;
    @SneakyThrows
    @Override
        protected void doFilterInternal (HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader("Authorization");

            if (header != null && header.startsWith("Bearer ")) {
                try {
                    final String token = header.substring(7);
                    final String username = jwtService.extractUsername(token);
                    final String role = jwtService.extractRole(token);

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        Usuario usuario = usuarioService.buscarPorUsername(username);

                        if (jwtService.isTokenValido(token, usuario)) {
                            List<SimpleGrantedAuthority> authorities = List.of(
                                    new SimpleGrantedAuthority("ROLE_" + role)
                            );

                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(usuario, null, authorities);

                            authentication.setDetails(
                                    new WebAuthenticationDetailsSource().buildDetails(request)
                            );

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // ajuda no debug
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token inválido ou expirado.");
                    return; // IMPEDIR que chegue em filterChain.doFilter

                }
            }
        filterChain.doFilter(request, response);
    }
}