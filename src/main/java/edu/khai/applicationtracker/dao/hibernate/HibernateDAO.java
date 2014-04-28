package edu.khai.applicationtracker.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.khai.applicationtracker.dao.GenericDao;

public class HibernateDAO<E, K extends Serializable> implements GenericDao<E, K> {

    private SessionFactory sessionFactory;

    protected Class<? extends E> daoType;

    @SuppressWarnings("unchecked")
    public HibernateDAO() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void removeById(K key) {
        E entity = (E) currentSession().get(daoType, key);

        currentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E find(K key) {
        return (E) currentSession().get(daoType, key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> list() {
        return currentSession().createCriteria(daoType).list();
    }
}