package uz.pdp.task_1.payload;

import lombok.Data;

@Data
public class TransferDto {
    private Integer fromCardId;
    private Integer toCardId;
    private Double amount;
}
