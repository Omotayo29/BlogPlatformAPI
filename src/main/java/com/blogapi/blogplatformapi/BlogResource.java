package com.blogapi.blogplatformapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("blog")
public class BlogResource {
    BlogRepo blogRepo = new BlogRepo();

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Blog> getBlog(){
        return blogRepo.getBlogs();
    }

    @GET
    @Path("post")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Blog> getFilteredBlogs(@QueryParam("term") String term) {
        if (term == null || term.trim().isEmpty()) {
            return blogRepo.getBlogs();
        }
        return blogRepo.getBlogsByTerm(term.trim());
    }

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Blog getBlog(@PathParam("id") int id){
        return blogRepo.getBlog(id);
    }

    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Blog createBlog(Blog a){
        blogRepo.create(a);
        return a;
    }

    @PUT
    @Path("put/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Blog updateBlog(@PathParam("id") int id, Blog changes){
        blogRepo.update(id, changes);
        return changes;
    }

    @DELETE
    @Path("delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Blog deleteBlog(@PathParam("id") int id){
        Blog blog = blogRepo.getBlog(id);
        blogRepo.delete(id);
        return blog;
    }
}
