package ch.bzz.delish.controllers;


import ch.bzz.delish.models.Quantity;
import ch.bzz.delish.repositories.QuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/quantities")
public class QuantityController {

    @Autowired
    private QuantityRepository quantityRepository;


    @GetMapping(path = "{id}")
    public Quantity findById(@PathVariable Integer id) {
        try {
            return quantityRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quantity could not be found");
        }
    }
}
