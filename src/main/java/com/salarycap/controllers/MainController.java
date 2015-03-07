package com.salarycap.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(value="/", method=RequestMethod.GET)
    public final String wrapperGet(){
        return "wrapper";
    }
    
    @RequestMapping(value="/Home", method=RequestMethod.GET)
    public final String homeGet(){
        return "home";
    }
    
    @RequestMapping(value="/Calculations", method=RequestMethod.GET)
    public final String calculationsGet(){
        return "calculations";
    }    
    
    @RequestMapping(value="/Team", method=RequestMethod.GET)
    public final String teamGet(){
        return "team";
    }    
    
    @RequestMapping(value="/Transaction", method=RequestMethod.GET)
    public final String transactionGet(){
        return "transaction";
    }
    
    @RequestMapping(value="/Transactions", method=RequestMethod.GET)
    public final String transactionsGet(){
        return "transactions";
    }    
    
    @RequestMapping(value="/Player", method=RequestMethod.GET)
    public final String playerGet(){
        return "player";
    }
    
    @RequestMapping(value="/Extension", method=RequestMethod.GET)
    public final String extensionGet(){
        return "extension";
    }
    
    @RequestMapping(value="/Restructure", method=RequestMethod.GET)
    public final String restructureGet(){
        return "restructure";
    }
    
    @RequestMapping(value="/Trade", method=RequestMethod.GET)
    public final String tradePlayerGet(){
        return "trade";
    }        
    
    @RequestMapping(value="/CreatePlayer", method=RequestMethod.GET)
    public final String createPlayerGet(){
        return "createPlayer";
    }
    
    @RequestMapping(value="/Error", method=RequestMethod.GET)
    public final ModelAndView RequestErrorHandler(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("redirect:/");
    }        
}
