package ch.bzz.delish.controllers;

import ch.bzz.delish.models.Login;
import ch.bzz.delish.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("{id}")
    public Login findById(@PathVariable Integer id) {
        try {
            return loginRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User could not be found");
        }
    }

}
