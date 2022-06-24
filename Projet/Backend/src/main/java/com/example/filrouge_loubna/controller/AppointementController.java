package com.example.filrouge_loubna.controller;


import com.example.filrouge_loubna.dto.model.AppointementDto;
import com.example.filrouge_loubna.services.Appointement.IAppointementService;
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
@RequestMapping("/appointement/")

@Api(tags = "Appointement", value = "Appointement Controller")

public class AppointementController {

    @Autowired
    IAppointementService appointementService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un rendez vous ", notes ="Cette methode permet d'ajouter un rendez vous  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rendez vous  ajouter a la DB"),
            @ApiResponse(code = 404, message = "Rendez vous n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<AppointementDto> save(@RequestBody AppointementDto appointementDto ){
        AppointementDto appointement = appointementService.AddAppointement(appointementDto);
        return new ResponseEntity<AppointementDto>(appointement, HttpStatus.CREATED);
    }



    //  Get All appointements :  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des rendez vous", notes ="Cette methode permet d'afficher une liste des rendez vous ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des rendez vous trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste rendez vous n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<List<AppointementDto>> GetAll(@RequestParam(value ="page" ,defaultValue = "0") int page ,
                                                @RequestParam(value ="limit",defaultValue = "5")int limit) {

        List<AppointementDto> appointementDto = appointementService.getAllAppointements(page, limit);
        return ResponseEntity.ok(appointementDto);
    }



    //  By Id :   --------------------------------------------------------

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Afficher un rendez vous par id ", notes ="Cette methode permet d'afficher un rendez vous par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rendez vous  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Rendez vous  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<AppointementDto> getById(@PathVariable long id)  {
        AppointementDto appointement = appointementService.getAppointementById(id);
        return ResponseEntity.ok(appointement);
    }

    @GetMapping(value="/user/{id}")
    @ApiOperation(value = "Afficher un rendez vous par id ", notes ="Cette methode permet d'afficher un rendez vous par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rendez vous  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Rendez vous  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<List<AppointementDto>> getByUserId(@PathVariable long id)  {
        List<AppointementDto> appointement = appointementService.getAllAppointementsByUserID(id);
        return ResponseEntity.ok(appointement);
    }


    // delete appointement :---------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Afficher un rendez vous par id ", notes ="Cette methode permet d'afficher un rendez vous par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rendez vous est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Rendez vous n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deleteAppointement(@PathVariable long id) {
        boolean deleted = appointementService.deleteAppointement(id);
        return new ResponseEntity<String>("{\"Appointement\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }


    // update appointement -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = "Rendez vous updater", notes ="Cette methode permet de modifier un Rendez vous par son id   ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rendez vous modifié  dans BD"),
            @ApiResponse(code = 404, message = "Rendez vous n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<AppointementDto> UpdateAppointement(@RequestBody AppointementDto appointementDto){
        AppointementDto uc = appointementService.updateAppointement(appointementDto);
        return ResponseEntity.ok(uc);
    }


}

