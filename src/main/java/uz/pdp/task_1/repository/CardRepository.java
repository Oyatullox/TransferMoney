package uz.pdp.task_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_1.entity.Card;

public interface CardRepository extends JpaRepository<Card,Integer> {

    boolean existsByNumber(String number);
}
