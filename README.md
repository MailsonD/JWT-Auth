# JWT-Auth
Sistema de autenticação simples utilizando REST e JWT com java para a disciplina de segurança de dados.

## Passo a passo

   A autenticação ela funciona em partes. Existe um filtro na aplicação que só deixa os usuários acessarem as rotas principais da aplicação caso estejam autenticados. 
   A única rota aberta na aplicação é a de login, que recebe as credenciais de autenticação do usuário.
   Após realizar o login, ele receberá um token que o dará acesso as demais rotas.
   
## Criação do Token

   Para a crição do token é necessário uma chave privada, um subject (que identifique o usuário autenticado) e um algoritmo de assinatura.
   
## Exemplo de algortimo
    private static SignatureAlgorithm SIG_ALGORITHM = SignatureAlgorithm.HS256;
    
## Exemplo de construção do token
    return Jwts.builder()
                .setSubject(subject)
                .signWith(SIG_ALGORITHM, key)
                .compact();
# Processo de checagem do Token
   Após a realização do login, o cliente terá posse do token, e para cada nova requisição ele deverá enviar o token como parte do cabeçalho da requisição para que seja feita a checagem de autenticação do usuário.
   
   
