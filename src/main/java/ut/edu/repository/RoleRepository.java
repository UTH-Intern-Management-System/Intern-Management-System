package ut.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ut.edu.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
