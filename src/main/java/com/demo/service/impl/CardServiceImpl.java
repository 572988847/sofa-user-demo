package com.demo.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.demo.dao.CardDao;
import com.demo.dao.UserDao;
import com.demo.model.CardDomain;
import com.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@SofaService(interfaceType = CardService.class,uniqueId ="cardService",bindings = {@SofaServiceBinding (bindingType = "bolt")})
@Service(value = "cardService")
public class CardServiceImpl implements CardService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CardDao cardDao;
    //绑卡接口 需先查询是否有这个用户，再查看是否有绑卡记录；若有，则修改原记录的func_bmp;若没有，则insert
    @Override
    public String bindCard(CardDomain cardDomain) {
        int userid = cardDomain.getUserId();
        String cardid = cardDomain.getCardId();
        if(userDao.queryUserById(userid)>=1)
        {
            if(cardDao.findRecordByUserIdAndCardId(userid,cardid)>=1)
            {
                if(cardDao.updateFunc(userid,cardid,1)){
                    return "绑卡成功";
                }
                else{
                    return "更新卡服务不成功，系统异常";
                }
            }
            else
            {
                if(cardDao.bindCard(cardDomain)>=1)
                {
                    return "绑卡成功";
                }
                else{
                    return "插入数据库记录不成功，系统异常";
                }
            }
        }else
        {
            return "用户不存在";
        }

    }
    //查询是否有这个用户;若用户存在，查询是否有绑卡记录；若存在绑卡记录，修改func_bmp=0;若不存在，报错
    @Override
    public String unbindCard(int userId, String cardId) {
        if(userDao.queryUserById(userId)>=1)
        {
            if(cardDao.findCardByUserId(userId,cardId)>=1)
            {
               if (cardDao.unbindCard(userId,cardId))
               {
                   return "用户解绑卡成功";
               }
               else
               {
                   return "系统异常,解绑卡失败";
               }
            }
            else
            {
                return "用户名下无该卡";
            }
        }else
        {
            return "用户不存在";
        }
    }
   //查询卡列表接口
    @Override
    public List<CardDomain> queryCard(int userId) {

            return cardDao.queryCard(userId);

    }

    @Override
    public CardDomain queryCardByUserId(int userId, String cardId) {
        return cardDao.queryCardByUserId(userId,cardId);
    }

    @Override
    public int findRecordByUserIdAndCardId(int userId, String cardId) {
        return cardDao.findRecordByUserIdAndCardId(userId,cardId);
    }

    @Override
    public boolean updateFunc(int userId, String cardId, int func) {
        return cardDao.updateFunc(userId,cardId,func);
    }
}
