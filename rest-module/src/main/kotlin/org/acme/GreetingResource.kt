package org.acme

import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/hello")
class GreetingResource(val moduleService: ModuleService) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun hello() : Response {
        val result = mapOf("foo" to "bar")
        return Response.ok(result).build()
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun helloPost() : Response {
        val result = mapOf("foo" to "bar")
        return Response.ok(result).build()
    }
}