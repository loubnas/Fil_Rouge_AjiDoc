package com.example.filrouge_loubna.controller;


import com.example.filrouge_loubna.dto.model.AppointementDto;
import com.example.filrouge_loubna.dto.model.MessageDto;
import com.example.filrouge_loubna.services.Appointement.IAppointementService;
import com.example.filrouge_loubna.services.Message.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/")

@Api(tags = "Message", value = "Message Controller")

public class MessageController {

    @Autowired
    IMessageService messageService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un message ", notes = "  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "message  ajouter a la DB"),
            @ApiResponse(code = 404, message = "message n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<MessageDto> save(@RequestBody MessageDto messageDto) {
        MessageDto m = messageService.AddMessage(messageDto);
        return new ResponseEntity<MessageDto>(m, HttpStatus.CREATED);
    }
}

