package uz.pdp.task_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_1.entity.Card;
import uz.pdp.task_1.entity.Income;
import uz.pdp.task_1.entity.Outcome;
import uz.pdp.task_1.payload.ApiResponse;
import uz.pdp.task_1.payload.TransferDto;
import uz.pdp.task_1.repository.CardRepository;
import uz.pdp.task_1.repository.IncomeRepository;
import uz.pdp.task_1.repository.OutcomeRepository;
import uz.pdp.task_1.securty.JwtProvider;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    JwtProvider jwtProvider;


    public ApiResponse transferMoney(HttpServletRequest request, TransferDto transferDto) {
        Optional<Card> optionalCard = cardRepository.findById(transferDto.getFromCardId());
        if (optionalCard.isEmpty())
            return new ApiResponse("Card not found!", false);
        Card fromCard = optionalCard.get();
        if (!fromCard.isActive())
            return new ApiResponse("Your card is not active!",false);
        String username = fromCard.getUsername();
        String authorization = request.getHeader("Authorization");
        authorization=authorization.substring(7);
        String usernameFromToken = jwtProvider.getUsernameFromToken(authorization);
        if (!username.equals(usernameFromToken))
            return new ApiResponse("You are not owner this card",false);
        Optional<Card> optional = cardRepository.findById(transferDto.getToCardId());
        if (optional.isEmpty())
            return new ApiResponse("Card not found",false);
        Card toCard = optional.get();
        if (!toCard.isActive())
            return new ApiResponse("Second card is not active!", false);
        Double commissionAmount = transferDto.getAmount()*0.01;
        if (fromCard.getBalance()<transferDto.getAmount()+commissionAmount)
            return new ApiResponse("There are not enough funds in the account!",false);
        fromCard.setBalance(fromCard.getBalance()-transferDto.getAmount()-commissionAmount);
        toCard.setBalance(toCard.getBalance()+transferDto.getAmount());
        cardRepository.save(fromCard);
        cardRepository.save(toCard);
        Income income=new Income(fromCard,toCard,transferDto.getAmount());
        incomeRepository.save(income);
        Outcome outcome=new Outcome(fromCard,toCard,transferDto.getAmount(),commissionAmount);
        outcomeRepository.save(outcome);
        return new ApiResponse("Funds transferred!",true);
    }
}
