package com.bunnystudio.test.repositories;

import com.bunnystudio.test.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    @Query(value = "SELECT user FROM User user WHERE (:name is null or lower(user.name) LIKE lower(concat('%', :name,'%')))")
    public Page<User> findByFilterName(Pageable page , @Param("name")  String name);

}
