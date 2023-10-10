package kz.orion.sk_orion_springboot.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRepository{

    @Query(nativeQuery = true, value = "SELECT visitor.firstName, visitor.lastName, subscription.typeSubscription, subscription.price, group_1.type_of_group, instructor.firstName, instructor.lastName, statistic.date\n" +
            "FROM statistic\n" +
            "INNER JOIN subscription ON statistic.subscription_id = subscription.id\n" +
            "INNER JOIN visitor ON subscription.visitor_username = visitor.username\n" +
            "INNER JOIN group_1 ON subscription.group_id = group_1.id\n" +
            "INNER JOIN instructor ON group_1.instructor_username = instructor.username\n" +
            "ORDER BY visitor.lastName ASC")
    List<Object[]> getCombinedTable();

}
