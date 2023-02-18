package com.example.demo.repository;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);

    public boolean existsByUsername(String username);
}
