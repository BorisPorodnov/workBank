package net.porodnov.bank.controller;

import net.porodnov.bank.dto.CashOrderDto;
import net.porodnov.bank.exception.SecretWordException;
import net.porodnov.bank.service.CashOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class CashOrderController {
    private final CashOrderService cashOrderService;

    public CashOrderController(CashOrderService cashOrderService) {
        this.cashOrderService = cashOrderService;
    }

    @PostMapping("/create")
    ResponseEntity<String> createOrder(@RequestBody CashOrderDto dtoRequest) {
        try {
            cashOrderService.createOrderCash(dtoRequest);
        } catch (SecretWordException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}