package org.spring.contacts.repository;

import org.spring.contacts.model.ContactDao;
import org.spring.contacts.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactDao, Integer> {

    List<ContactDao> findByUser(UserDao user);
}
