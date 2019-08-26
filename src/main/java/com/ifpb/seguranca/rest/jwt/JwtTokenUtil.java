package com.ifpb.seguranca.rest.jwt;

import io.jsonwebtoken.*;

/**
 * @author Mailson Dennis
 * @email mailssondennis@gmail.com
 *
 * Toda a lógica de ciração do token e validação pertence a essa classe.Ela que comporta a chave privada
 * (que é preferível não ficar no código) e faz uso dela para gerar o token.
 */
public class JwtTokenUtil {

    //Esta constante representa o campo no cabeçalho da requisição onde será encaminhado
    //o token
    public static final String TOKEN_HEADER = "Authentication";

    //chave privada utilizada no token
    private static String key = "secreta";

    //Algoritmo escolhido para a criptografia do token
    private static SignatureAlgorithm SIG_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * Função responsável por criar os tokens de autenticação. Ela utiliza de um builder do JWT
     * para construir passo a passo os dados que farão parte do token. É possível adicionar
     * outros parâmetros a mais, mas a princípio utilizados apelas o subject e a chave privada para gerar o token
     * @param subject -> atributo que irá identificar o usuário a quem o token pertence. É apartir
     *                dele que o token é criado.
     * @return -> O token gerado
     */
    public static String encode(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SIG_ALGORITHM, key)
                .compact();
    }

    /**
     * Função responsável para validar o token e decriptografá-lo. Ele faz o processo de parse do token através da chave privada,
     * retornado o corpo do token. Ao fazer esse processo de parse, se o token for um token inválido ele lançará uma exceção em
     * runtime.
     * @see SignatureException
     * @param token -> o token que se quer decriptografar
     * @return -> as credenciais guardadas dentro do token que foram informadas durante sua construção.
     */
    public static Claims decode(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}
