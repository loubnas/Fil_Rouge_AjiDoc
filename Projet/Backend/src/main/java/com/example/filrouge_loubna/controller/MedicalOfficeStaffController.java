package com.example.filrouge_loubna.controller;


import com.example.filrouge_loubna.dto.model.MedicalOfficeStaffDto;
import com.example.filrouge_loubna.services.MedicalOfficeStaff.IMedicalOfficeStaffService;
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
@RequestMapping("/medicalOfficeStaff/")

@Api(tags = "MedicalOfficeStaff", value = "MedicalOfficeStaff Controller")

public class MedicalOfficeStaffController {

    @Autowired
    IMedicalOfficeStaffService medicalOfficeStaffService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un medical office staff ", notes ="Cette methode permet d'ajouter un medical office staff ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medical office staff  ajouter a la DB"),
            @ApiResponse(code = 404, message = "Medical office staff  n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<MedicalOfficeStaffDto> save(@RequestBody MedicalOfficeStaffDto medicalOfficeStaffDto ){
        MedicalOfficeStaffDto medicalOfficeStaff = medicalOfficeStaffService.AddMedicalOfficeStaff(medicalOfficeStaffDto);
        return new ResponseEntity<MedicalOfficeStaffDto>(medicalOfficeStaff, HttpStatus.CREATED);
    }



    //  Get All medicalOfficeStaffs :  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste Medical Office staff ", notes ="Cette methode permet d'afficher une liste des Medicals offie staff")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste Medical office staff trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste Medical office staff n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<List<MedicalOfficeStaffDto>> GetAll(@RequestParam(value ="page" ,defaultValue = "0") int page ,
                                                @RequestParam(value ="limit",defaultValue = "5")int limit) {

        List<MedicalOfficeStaffDto> medicalOfficeStaffDto = medicalOfficeStaffService.getAllMedicalOfficeStaffs(page, limit);
        return ResponseEntity.ok(medicalOfficeStaffDto);
    }



    //  By Id :   --------------------------------------------------------

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Afficher un Medical office staff par id ", notes ="Cette methode permet d'afficher un Medical office staff par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medical office staff est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Medical office staff  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<MedicalOfficeStaffDto> getById(@PathVariable long id)  {
        MedicalOfficeStaffDto medicalOfficeStaff = medicalOfficeStaffService.getMedicalOfficeStaffById(id);
        return ResponseEntity.ok(medicalOfficeStaff);
    }


    // delete medicalOfficeStaff :---------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Medical office staff suprimer", notes ="Cette methode permet de supprimer un Medical office staff")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medical office staff trouvé dans BD"),
            @ApiResponse(code = 404, message = "Medical office staff n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deleteMedicalOfficeStaff(@PathVariable long id) {
        boolean deleted = medicalOfficeStaffService.deleteMedicalOfficeStaff(id);
        return new ResponseEntity<String>("{\"MedicalOfficeStaff\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }


    // update medicalOfficeStaff -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = "Medical offcie staff updater", notes ="Cette methode permet de modifier un Medical offcie staff par son id   ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medical office staff  modifié  dans BD"),
            @ApiResponse(code = 404, message = "Medical offcie staff  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<MedicalOfficeStaffDto> UpdateMedicalOfficeStaff(@RequestBody MedicalOfficeStaffDto medicalOfficeStaffDto){
        MedicalOfficeStaffDto uc = medicalOfficeStaffService.updateMedicalOfficeStaff(medicalOfficeStaffDto);
        return ResponseEntity.ok(uc);
    }


}

