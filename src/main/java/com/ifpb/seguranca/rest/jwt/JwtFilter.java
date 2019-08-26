package com.ifpb.seguranca.rest.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/api/*")
public class JwtFilter implements Filter {

    private static Logger LOG = Logger.getLogger(JwtFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("filtrando");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getRequestURI().contains("/auth")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);

        if(token == null || token.trim().isEmpty()){
            response.sendError(401);
            return;
        }

        try{
            Claims claim = JwtTokenUtil.decode(token);
            LOG.info("Usuario logado: "+claim.getSubject());
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (SignatureException e){
            response.sendError(401);
        }

    }

    @Override
    public void destroy() {

    }
}
