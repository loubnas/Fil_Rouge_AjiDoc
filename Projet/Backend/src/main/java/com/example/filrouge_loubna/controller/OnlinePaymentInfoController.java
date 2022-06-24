package com.example.filrouge_loubna.controller;


import com.example.filrouge_loubna.dto.model.OnlinePaymentInfoDto;
import com.example.filrouge_loubna.services.OnlinePaymentInfo.IOnlinePaymentInfoService;
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
@RequestMapping("/onlinePaymentInfo/")

@Api(tags = "OnlinePaymentInfo", value = "OnlinePaymentInfo Controller")

public class OnlinePaymentInfoController {

    @Autowired
    IOnlinePaymentInfoService onlinePaymentInfoService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un  payement en ligne ", notes ="Cette methode permet d'ajouter un mode de payment en ligne ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payement en ligne  ajouter a la DB"),
            @ApiResponse(code = 404, message = "Payement en ligne n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })


    public ResponseEntity<OnlinePaymentInfoDto> save(@RequestBody OnlinePaymentInfoDto onlinePaymentInfoDto ){
        OnlinePaymentInfoDto onlinePaymentInfo = onlinePaymentInfoService.AddOnlinePaymentInfo(onlinePaymentInfoDto);
        return new ResponseEntity<OnlinePaymentInfoDto>(onlinePaymentInfo, HttpStatus.CREATED);
    }



    //  Get All onlinePaymentInfos :  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des payements en ligne ", notes ="Cette methode permet d'afficher une liste des payements en ligne ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste payement en ligne trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste payement en ligne n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<List<OnlinePaymentInfoDto>> GetAll(@RequestParam(value ="page" ,defaultValue = "0") int page ,
                                                @RequestParam(value ="limit",defaultValue = "5")int limit) {

        List<OnlinePaymentInfoDto> onlinePaymentInfoDto = onlinePaymentInfoService.getAllOnlinePaymentInfos(page, limit);
        return ResponseEntity.ok(onlinePaymentInfoDto);
    }



    //  By Id :   --------------------------------------------------------

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Afficher un payement en ligne avec son  id ", notes ="Cette methode permet d'afficher un payement en ligne  par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payement  en ligne est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Payement en ligne  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<OnlinePaymentInfoDto> getById(@PathVariable long id)  {
        OnlinePaymentInfoDto onlinePaymentInfo = onlinePaymentInfoService.getOnlinePaymentInfoById(id);
        return ResponseEntity.ok(onlinePaymentInfo);
    }


    // delete onlinePaymentInfo :---------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Payement en ligne suprimer", notes ="Cette methode permet de supprimer un payement en ligne")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payement en ligne  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Payement en ligne n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deleteOnlinePaymentInfo(@PathVariable long id) {
        boolean deleted = onlinePaymentInfoService.deleteOnlinePaymentInfo(id);
        return new ResponseEntity<String>("{\"OnlinePaymentInfo\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }


    // update onlinePaymentInfo -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = "Payement en ligne updater", notes ="Cette methode permet de modifier un payement en ligne par son id   ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Payement en ligne  modifié  dans BD"),
            @ApiResponse(code = 404, message = "Payement en ligne  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<OnlinePaymentInfoDto> UpdateOnlinePaymentInfo(@RequestBody OnlinePaymentInfoDto onlinePaymentInfoDto){
        OnlinePaymentInfoDto uc = onlinePaymentInfoService.updateOnlinePaymentInfo(onlinePaymentInfoDto);
        return ResponseEntity.ok(uc);
    }


}
