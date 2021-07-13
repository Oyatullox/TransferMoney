package uz.pdp.task_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Card fromCard;

    @ManyToOne(optional = false)
    private Card toCard;

    @Column(nullable = false)
    private Double amount;

    @CreationTimestamp
    private Timestamp timestamp;

    public Income(Card fromCard,Card toCard,Double amount) {
        this.fromCard = fromCard;
        this.toCard = toCard;
        this.amount = amount;
    }
}
