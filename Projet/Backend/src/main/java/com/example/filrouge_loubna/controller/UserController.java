package com.example.filrouge_loubna.controller;

import com.example.filrouge_loubna.dto.model.UserDto;
import com.example.filrouge_loubna.dto.model.UserExtraInfoDto;
import com.example.filrouge_loubna.services.UserService.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/")

@Api(tags = "User", value = "User Controller")

public class UserController {

    @Autowired
    IUserService userService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un utilisateur  ", notes ="Cette methode permet d'ajouter un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur ajouter a la DB"),
            @ApiResponse(code = 404, message = "Utilisateur n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto ){
        UserDto user = userService.AddUser(userDto);
        return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
    }



    //  Get All users :  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des utilisateurs ", notes ="Cette methode permet d'afficher une liste des utilisateurs ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste utilisateurs trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste utilisateurs n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

            public ResponseEntity<Page<UserDto>> GetAll(@RequestParam(value ="page" ,defaultValue = "0") int page ,
                                                          @RequestParam(value ="limit",defaultValue = "5")int limit) {

        Page<UserDto> userDto = userService.getAllUsers(page, limit);
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/villes")
    @ApiOperation(value = "Afficher la liste des Extra infos", notes ="Cette methode permet d'afficher une liste des Extra info ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste Extra info  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste Extra info n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<List<String>> GetVilles() {

        List<String> v = userService.getVilles();


        return ResponseEntity.ok(v);
    }


    //  By Id :   --------------------------------------------------------

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Afficher un utilisateur par id ", notes ="Cette methode permet d'afficher un utilisateur par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "L'utilisateur n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserDto> getById(@PathVariable long id)  {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    // delete user :---------------------------------------------------------------
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = " Utilisateur suprimer", notes ="Cette methode permet de supprimer un utilisateur  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur trouvé dans BD"),
            @ApiResponse(code = 404, message = "Utilisateur n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        boolean deleted = userService.deleteUser(id);
        return new ResponseEntity<String>("{\"User\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }


    // update user -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = " Utilisateur updater", notes ="Cette methode permet de modifier un utilisateur  par son id   ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur modifié  dans BD"),
            @ApiResponse(code = 404, message = "Utilisateur n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserDto> UpdateUser(@RequestBody UserDto userDto){
        UserDto uc = userService.updateUser(userDto);
        return ResponseEntity.ok(uc);
    }


}
