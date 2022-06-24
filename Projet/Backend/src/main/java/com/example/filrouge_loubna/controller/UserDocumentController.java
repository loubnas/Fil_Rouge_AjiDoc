package com.example.filrouge_loubna.controller;


import com.example.filrouge_loubna.dto.model.UserDocumentDto;
import com.example.filrouge_loubna.services.UserDocuments.IUserDocumentService;
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
@RequestMapping("/userDoc/")

@Api(tags = "UserDoc", value = "UserDoc Controller")

public class UserDocumentController {

    @Autowired
    IUserDocumentService userDocService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = " UserDocument ajouter", notes ="Cette methode permet d'ajouter les documents d'un utilisateur  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User document trouvé dans BD"),
            @ApiResponse(code = 404, message = "User document n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserDocumentDto> save(@RequestBody UserDocumentDto userDocDto ){
        UserDocumentDto userDoc = userDocService.AddUserDoc(userDocDto);
        return new ResponseEntity<UserDocumentDto>(userDoc, HttpStatus.CREATED);
    }

    //  Get All docs:  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Liste des documents des utilisateurs", notes ="Cette methode permet d'afficher la liste des documents des utilisateurs  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Document d'un utilisateur trouvé dans BD"),
            @ApiResponse(code = 404, message = "Docuemnt d'un utilisateur n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<List<UserDocumentDto>> GetAll() {

        List<UserDocumentDto> userDocDto = userDocService.getAllDocs();

        return ResponseEntity.ok(userDocDto);
    }

    //  By Id :   --------------------------------------------------------

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Afficher un document  par id ", notes ="Cette methode permet d'afficher un document  id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Document est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Document  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<UserDocumentDto> getById(@PathVariable long id) {
        UserDocumentDto userDoc = userDocService.getUserDocById(id);
        return ResponseEntity.ok(userDoc);
    }


    // delete doc :---------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = " Document suprimer", notes ="Cette methode permet de supprimer un document")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Document trouvé dans BD"),
            @ApiResponse(code = 404, message = "Document n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<String> deleteUserDoc(@PathVariable long id) {
        boolean deleted = userDocService.deleteUserDoc(id);
        return new ResponseEntity<String>("{\"UserDoc\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }

    // update user Doc :  -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = " Document updater", notes ="Cette methode permet de modifier un document par son id  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Document  modifié  dans BD"),
            @ApiResponse(code = 404, message = "Document n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })


    public ResponseEntity<UserDocumentDto> UpdateUserDoc(@RequestBody UserDocumentDto userDocDto) {
        UserDocumentDto uc = userDocService.updateUserDoc(userDocDto);
        return ResponseEntity.ok(uc);
    }







}
