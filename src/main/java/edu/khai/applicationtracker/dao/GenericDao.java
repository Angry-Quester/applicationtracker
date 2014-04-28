package edu.khai.applicationtracker.dao;

import java.util.List;

public interface GenericDao<E, K> {
    void add(E entity);
    void update(E entity);
    void remove(E entity);
    void removeById(K key);
    E find(K key);
    List<E> list();
}