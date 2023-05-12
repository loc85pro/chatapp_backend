package com.myproject.chatserver.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.chatserver.entity.UserEntity;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<UserEntity> getAll() {
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<UserEntity> query = session.createQuery("FROM UserEntity", UserEntity.class);
        return query.getResultList();
    }

    public UserEntity getByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        return (UserEntity) session.find(UserEntity.class, username);
    }

    @Transactional(rollbackFor = { Exception.class, Throwable.class })
    public void insertUser(UserEntity user) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(user);
    }
}
