package org.spring.contacts.repository;

import org.spring.contacts.model.AddressDao;
import org.spring.contacts.model.ContactDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressDao, Integer> {
}
