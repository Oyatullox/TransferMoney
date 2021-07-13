package uz.pdp.task_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_1.entity.Income;
import uz.pdp.task_1.entity.Outcome;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {

    List<Income> findAllByFromCardId_Username(String fromCard_username);
}
