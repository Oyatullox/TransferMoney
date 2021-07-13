package uz.pdp.task_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_1.entity.Outcome;

import java.util.List;

public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {

    List<Outcome> findAllByFromCardId_Username(String fromCard_username);
}
