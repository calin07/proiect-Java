package com.example.web123.repository;
import com.example.web123.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value="select name from users where id=:id",nativeQuery = true)
    @Transactional
    String findByName(@Param("id") UUID id);

    User findByName(String name);

}
