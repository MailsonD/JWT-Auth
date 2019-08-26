package com.ifpb.seguranca.rest.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Mailson Dennis
 * @email mailssondennis@gmail.com
 *
 * Esta classe é responsável por barrar todas as requisições feitas a API e testar
 * se o usuário que está tentando acessar possui ou não um token de acesso.
 * Caso o usuário tente acessar uma das rotas da API (que não seja a de login) sem possuir
 * um token ele receberá um código de Unauthorized.
 */
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

        //Testa se ele está requisitando para login
        if(request.getRequestURI().contains("/auth")){
            //Se sim ele deixa a requisição seguir
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //Caso não esteja requisitando para login ele captura o token do usuário
        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);

        //Ele testa se o token realmente existe
        if(token == null || token.trim().isEmpty()){
            //Se não existir ele lança o erro de Unauthorized
            response.sendError(401);
            return;
        }

        try{
            //Se o token existir ele tenta realizar o decode dele
            //Se o token for válido, ele passa e segue a requisição normalmente
            Claims claim = JwtTokenUtil.decode(token);
            LOG.info("Usuario logado: "+claim.getSubject());
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (SignatureException e){
            //Se o token não for válido ele lançará essa exceção ao tentar decodificá-lo
            //E lança o o código de Unauthorized
            response.sendError(401);
        }

    }

    @Override
    public void destroy() {

    }
}
