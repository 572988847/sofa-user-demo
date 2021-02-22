package com.demo.service;

import com.demo.model.CardDomain;

import java.util.List;

public interface CardService {
    String bindCard(CardDomain cardDomain);
    String unbindCard(int userId, String cardId);
    List<CardDomain> queryCard(int userId);
    CardDomain queryCardByUserId(int userId,String cardId);
    int findRecordByUserIdAndCardId(int userId,String cardId);
    boolean updateFunc(int userId,String cardId,int func);
}
