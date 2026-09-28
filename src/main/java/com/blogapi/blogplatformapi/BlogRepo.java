package com.blogapi.blogplatformapi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BlogRepo {
    Blog blog;
    SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Blog.class)
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();


    public void create(Blog a) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(a);
        transaction.commit();
        session.close();
    }
    public void update(int id, Blog a) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Blog tempblog = session.find(Blog.class,id);
        tempblog.setCategory(a.getCategory());
        tempblog.setContent(a.getContent());
        tempblog.setTitle(a.getTitle());
        tempblog.setTags(a.getTags());
        session.persist(tempblog);
        transaction.commit();
        session.close();
    }

    public List<Blog> getBlogs() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Blog ORDER BY id", Blog.class)
                    .getResultList();
        }
    }

    public List<Blog> getBlogsByTerm(String term) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Blog b " +
                            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%')) " +
                            "OR LOWER(b.content) LIKE LOWER(CONCAT('%', :term, '%')) " +
                            "OR LOWER(b.category) LIKE LOWER(CONCAT('%', :term, '%')) " +
                            "ORDER BY b.id", Blog.class)
                    .setParameter("term", term)
                    .getResultList();
        }
    }

    public Blog getBlog(int id) {
        Session session = sessionFactory.openSession();
        blog = session.find(Blog.class,id);
        session.close();
        return blog;
    }


    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        blog = session.find(Blog.class,id);
        session.remove(blog);
        transaction.commit();
        session.close();
    }
}
