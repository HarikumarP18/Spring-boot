package com.example.news.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.model.News;
import com.example.news.service.NewsService;

@RestController
@RequestMapping("/api")
public class NewsController{
    @Autowired
    private NewsService es;

    @PostMapping("/account")
    public ResponseEntity<News> createAccount(@RequestBody News e){
        News created=es.createAccount(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/accounts")
    public List<News> getAllAccounts(){
        return es.getAllAccounts();
    }

    @GetMapping("/account/{accountId}")
    public Optional<News> getEmployeeById(@PathVariable int accountId){
        Optional<News> e=es.getAccountById(accountId);
        return e;
    }
    @PutMapping("/account/{accountId}")
    public ResponseEntity<News> putMethod(@PathVariable("accountId") int accountId,@RequestBody News e)
    {
        if(es.updateDetails(accountId,e) == true)
        {
            return new ResponseEntity<>(e,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<Boolean> delete(@PathVariable("accountId") int accountId)
    {
        if(es.deleteAccount(accountId) == true)
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

}
