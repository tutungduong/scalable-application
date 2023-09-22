package com.javaguides.scalable.repository;

import com.javaguides.scalable.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Long>, PagingAndSortingRepository<User,Long> {
    User findByEmail(String email);

}
