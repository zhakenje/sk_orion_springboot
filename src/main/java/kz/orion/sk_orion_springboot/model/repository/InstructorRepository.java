package kz.orion.sk_orion_springboot.model.repository;

import kz.orion.sk_orion_springboot.model.entity.Instructor;
import kz.orion.sk_orion_springboot.model.entity.enums.InstructorRole;
import kz.orion.sk_orion_springboot.model.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, String> {

    List<Instructor> findAllByRoleAndInstructorRole(Role role, InstructorRole instructorRole);

    List<Instructor> findAllByFirstNameAndRole(String text, Role role);
}
