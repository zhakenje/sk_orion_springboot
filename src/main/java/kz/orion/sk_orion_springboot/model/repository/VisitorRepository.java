package kz.orion.sk_orion_springboot.model.repository;

import kz.orion.sk_orion_springboot.model.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, String> {

    List<Visitor> findAllByFirstName(String text);
}
