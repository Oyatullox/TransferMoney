package uz.pdp.task_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_1.entity.Card;
import uz.pdp.task_1.entity.Income;
import uz.pdp.task_1.entity.Outcome;
import uz.pdp.task_1.payload.ApiResponse;
import uz.pdp.task_1.service.CardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/addCard")
    public ResponseEntity<?> addCard(@RequestBody Card card){
        ApiResponse apiResponse= cardService.addCard(card);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @GetMapping("/incomeHistory")
    public HttpEntity<?> getIncomeHistory(HttpServletRequest request){
        List<Income> incomes =cardService.getIncomeHistory(request);
        return ResponseEntity.ok(incomes);
    }
    @GetMapping("/outcomeHistory")
    public HttpEntity<?> outcomeHistory(HttpServletRequest request){
        List<Outcome> outcomes=cardService.outcomeHistory(request);
        return ResponseEntity.ok(outcomes);
    }
}
