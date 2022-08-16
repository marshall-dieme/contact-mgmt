package org.spring.contacts.repository;

import org.spring.contacts.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Integer> {

    UserDao findByUsernameAndPassword(String username, String password);

    UserDao findByUsername(String username);
  /*
    @Query("SELECT u FROM UserDao u WHERE u.username=?1 AND u.password=?2")
    UserDao retrieveByUsernameAndPassword(String username, String password);*/

}
