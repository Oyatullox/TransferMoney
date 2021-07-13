package uz.pdp.task_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import uz.pdp.task_1.entity.Card;
import uz.pdp.task_1.entity.Income;
import uz.pdp.task_1.entity.Outcome;
import uz.pdp.task_1.payload.ApiResponse;
import uz.pdp.task_1.repository.CardRepository;
import uz.pdp.task_1.repository.IncomeRepository;
import uz.pdp.task_1.repository.OutcomeRepository;
import uz.pdp.task_1.securty.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    public ApiResponse addCard(Card card) {
        boolean b = cardRepository.existsByNumber(card.getNumber());
        if (b)
            return new ApiResponse("This card number already exists!", false);
        Card newCard = new Card(
                card.getUsername(),
                card.getNumber(),
                card.getBalance(),
                card.getExpiredDate(),
                card.isActive()
        );
        cardRepository.save(newCard);
        return new ApiResponse("Card added", true);
    }

    public List<Income> getIncomeHistory(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        return incomeRepository.findAllByFromCardId_Username(username);
    }

    public List<Outcome> outcomeHistory(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        authorization=authorization.substring(7);
        String usernameFromToken = jwtProvider.getUsernameFromToken(authorization);
        return outcomeRepository.findAllByFromCardId_Username(usernameFromToken);
    }
}
