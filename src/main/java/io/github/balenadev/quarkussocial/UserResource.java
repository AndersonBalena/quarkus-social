package io.github.balenadev.quarkussocial;

import io.github.balenadev.quarkussocial.domain.model.User;
import io.github.balenadev.quarkussocial.dto.CreateUserRequestDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    @Transactional
    public Response createUser(CreateUserRequestDTO requestDTO) {
        User user = new User();
        user.setAge(requestDTO.getAge());
        user.setName(requestDTO.getName());
        user.persist();
        return Response.ok(user).build();
    }

    @GET
    public Response getAllUsers() {
        PanacheQuery<User> query = User.findAll();
        return Response.ok(query.list()).build();
    }

}
