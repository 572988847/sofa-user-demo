package com.winterchen.service.user;

import com.winterchen.model.CardDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardService {
    String bindCard(CardDomain cardDomain);
    String unbindCard(int userId, String cardId);
    List<CardDomain> queryCard(int userId);
    CardDomain queryCardByUserId(int userId,String cardId);
    int findRecordByUserIdAndCardId(int userId,String cardId);
    boolean updateFunc(int userId,String cardId,int func);
}
