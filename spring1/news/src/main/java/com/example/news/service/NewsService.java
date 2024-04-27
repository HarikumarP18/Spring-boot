package com.example.news.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.news.model.*;
import com.example.news.repository.*;


@Service
public class NewsService{
    @Autowired
    private NewsRepo er;
    public News createAccount(News p){
        return er.save(p);
    }

    public List<News> getAllAccounts(){
        return er.findAll();
    }
    public Optional<News> getAccountById(Integer id){
        return er.findById(id);
    }
    public boolean updateDetails(int id,News e)
    {
        if(this.getAccountById(id)==null)
        {
            return false;
        }
        try{
            er.save(e);
        }
        catch(Exception a)
        {
            return false;
        }
        return true;
    }
public boolean deleteAccount(int id)
    {
        if(this.getAccountById(id) == null)
        {
            return false;
        }
        er.deleteById(id);
        return true;
    }


}