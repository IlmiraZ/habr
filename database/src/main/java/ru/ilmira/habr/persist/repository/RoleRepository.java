package ru.ilmira.habr.persist.repository;

import ru.ilmira.habr.persist.model.ERole;
import ru.ilmira.habr.persist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(ERole name);
}
