package com.example.filrouge_loubna.controller;

import com.example.filrouge_loubna.dto.model.ReviewDto;
import com.example.filrouge_loubna.services.Reviews.IReviewService;
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
@RequestMapping("/review/")

@Api(tags = "Review", value = "Review Controller")

public class ReviewController {

    @Autowired
    IReviewService reviewService;

    //  Save :   ---------------------------------------------------------

    @PostMapping("/save")
    @ApiOperation(value = "Ajouter un commentaire ", notes ="Cette methode permet d'ajouter un commentaire")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commentaire ajouter a la DB"),
            @ApiResponse(code = 404, message = "Commentaire n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })


    public ResponseEntity<ReviewDto> save(@RequestBody ReviewDto reviewDto ){
        ReviewDto rev = reviewService.AddReview(reviewDto);
        return new ResponseEntity<ReviewDto>(rev, HttpStatus.CREATED);
    }


    //  Get All reviews :  -----------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des commentaires ", notes ="Cette methode permet d'afficher une liste des commentaires")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des commentaires trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des commentaires n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<List<ReviewDto>> GetAll(@RequestParam(value ="page" ,defaultValue = "0") int page ,
                                                  @RequestParam(value ="limit",defaultValue = "5")int limit) {

        List<ReviewDto> reviewDto = reviewService.getAllReviews(page, limit);
        return ResponseEntity.ok(reviewDto);
    }

    //  By Id :   --------------------------------------------------------

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Afficher un commentaire par id ", notes ="Cette methode permet d'afficher un commentaire par son id ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le commentaire est  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Le commentaire  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<ReviewDto> getById(@PathVariable long id)  {
        ReviewDto review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    // delete review :---------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Commentaire suprimer", notes ="Cette methode permet de supprimer un commentaire")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commentaire trouvé dans BD"),
            @ApiResponse(code = 404, message = "Commentaire n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<String> deleteReview(@PathVariable long id) {
        boolean deleted = reviewService.deleteReview(id);
        return new ResponseEntity<String>("{\"Review\":\"" + id + "\",\"deleted\":\"" + deleted + "\"}", HttpStatus.OK);

    }


    // update review -------------------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = "Commentaire updater", notes ="Cette methode permet de modifier un commentaire par son id   ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commentaire modifié  dans BD"),
            @ApiResponse(code = 404, message = "Commentaire n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<ReviewDto> UpdateReview(@RequestBody ReviewDto reviewDto){
        ReviewDto rev = reviewService.updateReview(reviewDto);
        return ResponseEntity.ok(rev);
    }









}

