package kz.orion.sk_orion_springboot.model.repository;


import kz.orion.sk_orion_springboot.model.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Query(nativeQuery = true, value = "SELECT visitor.firstName, visitor.lastName, subscription.typeSubscription, subscription.price, group_1.type_of_group, instructor.firstName, instructor.lastName, statistic.date\n" +
            "FROM statistic\n" +
            "INNER JOIN subscription ON statistic.subscription_id = subscription.id\n" +
            "INNER JOIN visitor ON subscription.visitor_username = visitor.username\n" +
            "INNER JOIN group_1 ON subscription.group_id = group_1.id\n" +
            "INNER JOIN instructor ON group_1.instructor_username = instructor.username\n" +
            "ORDER BY visitor.lastName ASC")
    List<Statistic[]> getCombinedTable();
}
