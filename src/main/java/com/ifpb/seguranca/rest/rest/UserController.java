package com.ifpb.seguranca.rest.rest;

import com.ifpb.seguranca.rest.jwt.JwtTokenUtil;
import com.ifpb.seguranca.rest.jwt.UserLoged;
import com.ifpb.seguranca.rest.model.domain.User;
import com.ifpb.seguranca.rest.service.UserService;
import com.ifpb.seguranca.rest.service.UserServiceIF;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Mailson Dennis
 * @email mailssondennis@gmail.com
 *
 * Classe que comporta todas as rotas principais da aplicação.
 * Para o acesso de todas as rodas aqui definidas (com exceção de /auth) é necessário
 * o token de acesso gerado através da autenticação.
 */
@Stateless
@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserServiceIF userService;

    @Context
    private UriInfo uriInfo;

    private Logger LOG = Logger.getLogger(UserController.class.getName());

    /**
     * Método de autenticação do usuário. Responsável por chamar a criação do token quando
     * autentica um usuário.
     * @see JwtTokenUtil
     * @param jsonObject -> Objeto JSON com os dados de email e senha do usuário a se autenticar
     * @return -> Um Response com o token do usuário caso a autenticação seja feita. Caso contrário
     * ele lançará um status de Unauthorized para o usuário.
     */
    @POST
    @Path("auth")
    public Response auth(JsonObject jsonObject){
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        if(email != null && password != null){
            LOG.info("Autenticando com os dados\nEmail: "+email+"\nSenha: "+password);
            User user = userService.auth(email,password);
            if (user != null){
                UserLoged loged = new UserLoged();
                loged.setEmail(user.getEmail());
                loged.setToken(JwtTokenUtil.encode(user.getEmail()));
                return Response.ok().entity(loged).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    public Response findAll(){
        List<User> users = userService.findAll();

        GenericEntity<List<User>> genericUsers = new GenericEntity<List<User>>(users){};

        return Response.ok().entity(genericUsers).build();
    }

    @GET
    @Path("{email}")
    public Response find(@PathParam("email") String email){
        User user = userService.find(email);
        if(user != null){
            return Response.ok().entity(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response save(JsonObject object){
        User user = new User(
            object.getString("email"),
            object.getString("password"),
            object.getString("name")
        );

        userService.save(user);

        URI uri = uriInfo.getAbsolutePathBuilder().path(object.getString("email")).build();

        return Response.created(uri).build();
    }

    @DELETE
    @Path("{email}")
    public Response remove(@PathParam("email") String email){
        userService.remove(email);
        return Response.noContent().build();
    }


}
