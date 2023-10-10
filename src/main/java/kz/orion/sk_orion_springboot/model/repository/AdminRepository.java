package kz.orion.sk_orion_springboot.model.repository;

import kz.orion.sk_orion_springboot.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
