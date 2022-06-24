package com.example.filrouge_loubna.controller;


import com.example.filrouge_loubna.dto.model.MedicalOfficeDto;
import com.example.filrouge_loubna.entities.MedicalOffice;
import com.example.filrouge_loubna.services.MedicalOffice.IMedicalOfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalOffice/")

@Api(tags = "MedicalOffice", value = "MedicalOffice Controller")

public class MedicalOfficeController {

    @Autowired
    IMedicalOfficeService medicalOfficeService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un medical office ", notes ="Cette methode permet d'ajouter un medical office ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medical office ajouter a la DB"),
            @ApiResponse(code = 404, message = "Medical office n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<MedicalOfficeDto> save(@RequestBody MedicalOfficeDto medicalOfficeDto ){
        MedicalOfficeDto medicalOffice = medicalOfficeService.AddMedicalOffice(medicalOfficeDto);
        return new ResponseEntity<MedicalOfficeDto>(medicalOffice, HttpStatus.CREATED);
    }



    //  Get All medicalOffices :  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste Medical Office ", notes ="Cette methode permet d'afficher une liste des Medicals offie ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste Medical office trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste Medical office n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<Page<MedicalOfficeDto>> GetAll(@RequestParam(value ="page" ,defaultValue = "0") int page ,
                                                         @RequestParam(value ="limit",defaultValue = "5")int limit) {

        Page<MedicalOfficeDto> medicalOfficeDto = medicalOfficeService.getAllMedicalOffices(page, limit);
        return ResponseEntity.ok(medicalOfficeDto);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Afficher la liste Medical Office ", notes ="Cette methode permet d'afficher une liste des Medicals offie ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste Medical office trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste Medical office n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
       public ResponseEntity<Page<MedicalOfficeDto>> SearchAll(@RequestParam(value = "keyword" , defaultValue = "") String keyword,@RequestParam(value ="page" ,defaultValue = "0") int page ,
                                                         @RequestParam(value ="limit",defaultValue = "5")int limit) {

        Page<MedicalOfficeDto> medicalOffices = medicalOfficeService.searchMedicalOffices(keyword,page, limit);
        return ResponseEntity.ok(medicalOffices);
    }



    //  By Id :   --------------------------------------------------------

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Afficher un Medical office par id ", notes ="Cette methode permet d'afficher un Medical office  par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medical office est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Medical office  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<MedicalOfficeDto> getById(@PathVariable long id)  {
        MedicalOfficeDto medicalOffice = medicalOfficeService.getMedicalOfficeById(id);
        return ResponseEntity.ok(medicalOffice);
    }


    // delete medicalOffice :---------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Afficher un Medical office par id ", notes ="Cette methode permet d'afficher un Medical office par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medical office est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Medical office  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deleteMedicalOffice(@PathVariable long id) {
        boolean deleted = medicalOfficeService.deleteMedicalOffice(id);
        return new ResponseEntity<String>("{\"MedicalOffice\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }


    // update medicalOffice -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = "Medical offcie updater", notes ="Cette methode permet de modifier un Medical offcie par son id   ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medical office   modifié  dans BD"),
            @ApiResponse(code = 404, message = "Medical offcie n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<MedicalOfficeDto> UpdateMedicalOffice(@RequestBody MedicalOfficeDto medicalOfficeDto){
        MedicalOfficeDto uc = medicalOfficeService.updateMedicalOffice(medicalOfficeDto);
        return ResponseEntity.ok(uc);
    }


}
