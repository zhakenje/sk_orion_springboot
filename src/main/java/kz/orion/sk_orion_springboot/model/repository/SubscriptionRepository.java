package kz.orion.sk_orion_springboot.model.repository;

import kz.orion.sk_orion_springboot.model.entity.Subscription;
import kz.orion.sk_orion_springboot.model.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findById(int id);

    List<Subscription> findByVisitor(Visitor vs);

}
