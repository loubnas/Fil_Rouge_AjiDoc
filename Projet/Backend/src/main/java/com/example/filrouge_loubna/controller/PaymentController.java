package com.example.filrouge_loubna.controller;


import com.example.filrouge_loubna.dto.model.PaymentDto;
import com.example.filrouge_loubna.services.Payment.IPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/")

@Api(tags = "Payment", value = "Payment Controller")

public class PaymentController {

    @Autowired
    IPaymentService paymentService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un payement", notes ="Cette methode permet d'ajouter un payment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payement ajouter a la DB"),
            @ApiResponse(code = 404, message = "Payement n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<PaymentDto> save(@RequestBody PaymentDto paymentDto ){
        PaymentDto payment = paymentService.AddPayment(paymentDto);
        return new ResponseEntity<PaymentDto>(payment, HttpStatus.CREATED);
    }



    //  Get All payments :  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des payements ", notes ="Cette methode permet d'afficher une liste des payements")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste payement trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste payement  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<List<PaymentDto>> GetAll(@RequestParam(value ="page" ,defaultValue = "0") int page ,
                                                @RequestParam(value ="limit",defaultValue = "5")int limit) {

        List<PaymentDto> paymentDto = paymentService.getAllPayments(page, limit);
        return ResponseEntity.ok(paymentDto);
    }



    //  By Id :   --------------------------------------------------------

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Afficher un payement par id ", notes ="Cette methode permet d'afficher un payement  par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payement  est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Payement n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<PaymentDto> getById(@PathVariable long id)  {
        PaymentDto payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }


    // delete payment :---------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Payement suprimer", notes ="Cette methode permet de supprimer un payement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payement trouvé dans BD"),
            @ApiResponse(code = 404, message = "Payement n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deletePayment(@PathVariable long id) {
        boolean deleted = paymentService.deletePayment(id);
        return new ResponseEntity<String>("{\"Payment\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }


    // update payment -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = "Payement updater", notes ="Cette methode permet de modifier un payement par son id   ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payement modifié  dans BD"),
            @ApiResponse(code = 404, message = "Payement n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<PaymentDto> UpdatePayment(@RequestBody PaymentDto paymentDto){
        PaymentDto uc = paymentService.updatePayment(paymentDto);
        return ResponseEntity.ok(uc);
    }


}
