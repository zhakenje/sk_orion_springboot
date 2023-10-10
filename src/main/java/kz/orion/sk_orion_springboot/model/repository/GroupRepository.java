package kz.orion.sk_orion_springboot.model.repository;

import kz.orion.sk_orion_springboot.model.entity.Group;
import kz.orion.sk_orion_springboot.model.entity.enums.TypeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Long> {



}
