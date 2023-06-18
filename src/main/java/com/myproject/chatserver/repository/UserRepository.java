package com.myproject.chatserver.repository;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.tomcat.jni.User;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.chatserver.entity.UserEntity;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<UserEntity> getAll() {
        Session session = entityManager.unwrap(Session.class);
        List<UserEntity> result = session.createQuery("FROM UserEntity").getResultList();
        return result;
    }

    public UserEntity getByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        UserEntity result = (UserEntity) session.find(UserEntity.class, username);
        return result;
    }

    @Transactional(rollbackFor = { Exception.class, Throwable.class })
    public void insertUser(UserEntity user) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(user);
    }
}
