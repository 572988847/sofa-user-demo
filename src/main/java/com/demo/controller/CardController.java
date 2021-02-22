package com.demo.controller;


import com.demo.model.CardDomain;
import com.demo.service.CardService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/card")

public class CardController {
    @Autowired
    private UserService userService;
   // @SofaReference
    @Autowired
    private CardService cardService;
    @ResponseBody
    @PostMapping("/bindcard")
    public String bindcard(CardDomain cardDomain){

        return  cardService.bindCard(cardDomain);
    }
    @ResponseBody
    @GetMapping("/unbindCard")
    public String unbindCard(int userId,String cardId ){

        return  cardService.unbindCard(userId,cardId);
    }
    @ResponseBody
    @GetMapping("/queryCardList")
    public String queryCard(int userId){
        String str ="";
      if(userService.queryUserById(userId)>=1)
      {
          List<CardDomain> cardDomainList = cardService.queryCard(userId);
          for(CardDomain card : cardDomainList)
          {
              str += card.toString()+"\n";
          }
          return str;
      }
      else
      {
          return "查询卡列表失败";
      }
    }
    @ResponseBody
    @GetMapping("/queryCardByUserId")
    public String queryCardByUserId(int userId,String cardId ){
        if(userService.queryUserById(userId)>=1)
        {
            if(cardService.findRecordByUserIdAndCardId(userId,cardId)>=1)
            {
                CardDomain cardDomain = cardService.queryCardByUserId(userId,cardId);
                return  cardDomain.toString();
            }
            else {
                return "用户名下无该卡";
            }

        }
        else
        {
            return "用户不存在";
        }


    }



}
