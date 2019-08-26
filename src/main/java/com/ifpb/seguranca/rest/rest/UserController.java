package com.ifpb.seguranca.rest.rest;

import com.ifpb.seguranca.rest.model.domain.User;
import com.ifpb.seguranca.rest.service.UserService;
import com.ifpb.seguranca.rest.service.UserServiceIF;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Stateless
@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserServiceIF userService;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response findAll(){
        List<User> users = userService.findAll();

        GenericEntity<List<User>> genericUsers = new GenericEntity<List<User>>(users){};

        return Response.ok().entity(genericUsers).build();
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
}
