package com.example.filrouge_loubna.controller;

import com.example.filrouge_loubna.dto.model.UserExtraInfoDto;
import com.example.filrouge_loubna.entities.UserExtraInfo;
import com.example.filrouge_loubna.services.UserExtraInfo.IUserExtraInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/userExtraInfo/")

@Api(tags = "UserExtraInfo", value = "UserExtra info Controller")

public class UserExtraInfoController {

    @Autowired
    IUserExtraInfoService userinfoService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un extra info ", notes ="Cette methode permet d'ajouter un un extra info ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Extra info  ajouter a la DB"),
            @ApiResponse(code = 404, message = "Extra info n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserExtraInfoDto> save(@RequestBody UserExtraInfoDto userinfoDto) {

            UserExtraInfoDto userExtra = userinfoService.AddUserExtraInfo(userinfoDto);
            return new ResponseEntity<UserExtraInfoDto>(userExtra, HttpStatus.CREATED);

    }


    //  Get All info users :  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des Extra infos", notes ="Cette methode permet d'afficher une liste des Extra info ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste Extra info  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste Extra info n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<List<UserExtraInfoDto>> GetAll() {

        List<UserExtraInfoDto> userinfoDto = userinfoService.getAllUsersExtraInfo();

        return ResponseEntity.ok(userinfoDto);
    }
    @GetMapping("/specialites")
    @ApiOperation(value = "Afficher la liste des Extra infos", notes ="Cette methode permet d'afficher une liste des Extra info ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste Extra info  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste Extra info n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<List<String>> GetSpecialites() {

        List<String> specs = userinfoService.getSpecialites();
        return ResponseEntity.ok(specs);
    }


    //  By Id :   --------------------------------------------------------

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Afficher un Extra info  par id ", notes ="Cette methode permet d'afficher un Extra info par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Extra info est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Extra info  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserExtraInfoDto> getById(@PathVariable long id) {
        UserExtraInfoDto userinfo = userinfoService.getUserInfoById(id);
        return ResponseEntity.ok(userinfo);
    }


    // delete user info :---------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Extra info  suprimer", notes ="Cette methode permet de supprimer un Extra info  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Extra info trouvé dans BD"),
            @ApiResponse(code = 404, message = "Extra info n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<String> deleteUserInfo(@PathVariable long id) {
        boolean deleted = userinfoService.deleteUsersExtraInfo(id);
        return new ResponseEntity<String>("{\"UserExtraInfo\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }


    // update user info :  -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = "Extra info  updater", notes ="Cette methode permet de modifier un Extra info par son id   ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Extra info modifié  dans BD"),
            @ApiResponse(code = 404, message = "Extra info n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })


    public ResponseEntity<UserExtraInfoDto> UpdateUserInfo(@RequestBody UserExtraInfoDto userinfoDto) {
        UserExtraInfoDto uc = userinfoService.updateUsersExtraInfo(userinfoDto);
        return ResponseEntity.ok(uc);
    }

}