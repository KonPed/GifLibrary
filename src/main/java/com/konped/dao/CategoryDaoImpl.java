package com.konped.dao;

import com.konped.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = cb.createQuery(Category.class);
        List<Category> categories = session.createQuery(query.select(query.from(Category.class))).getResultList();
        session.close();
        return categories;
    }

    @Override
    public Category findCategoryByID(Long id) {
        /* Open a session */
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    @Override
    public void save(Category category) {
        /* Open a session */
        Session session = sessionFactory.openSession();
        /* Begin a transaction */
        session.beginTransaction();
        /* Save the category */
        session.save(category);
        /* Commit the transaction */
        session.getTransaction().commit();
        /* Close the session */
        session.close();
    }

    @Override
    public void delete(Category category) {

    }
}
