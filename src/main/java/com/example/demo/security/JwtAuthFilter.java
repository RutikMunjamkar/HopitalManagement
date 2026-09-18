package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try{
            String methodType=request.getMethod();
            String token=request.getHeader("Authorization");
            if("OPTIONS".equalsIgnoreCase(methodType) || token==null){
                filterChain.doFilter(request,response);
                return;
            }
            String username=authUtil.getUserName(token.substring(7));
            if(username!=null && getContext().getAuthentication()==null){
                User user= (User) userRepository.findByUsername(username).orElseThrow();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user,token,user.getAuthorities() );
                getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request,response);
//        }
//        catch (ExpiredJwtException e){
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType("application/json");
//            response.getWriter().write("""
//                    {
//                       "status":"401",
//                       "error":"UNAUTHORIZED",
//                        "message":"%s"
//                    }
//                    """.formatted(e.getMessage()));
//        }
    }
}