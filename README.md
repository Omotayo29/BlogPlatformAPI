Blogging Platform API

A simple RESTful API for a personal blogging platform supporting basic CRUD operations. 

Tech Stack
-Language/Framework: Java 
-ORM: Hibernate
-Database: PostgreSQL

Features
- Create, read, update, and delete (CRUD) blog posts.
- Filter and search posts by title, content, or category.
- Standard HTTP status codes and error handling.

API Endpoints

 Method | Endpoint | Description 
 `GET` | `/post` | Fetch all blog posts (supports `?term=` for searching) 
 `GET` | `/get/{id}` | Fetch a single blog post by ID 
 `POST` | `/post` | Create a new blog post 
 `PUT` | `/put/{id}` | Update an existing blog post 
 `DELETE` | `/delete/{id}` | Delete a blog post 
