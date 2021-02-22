package com.demo.dao;

import com.demo.model.CardDomain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CardDao {
    //绑卡接口 需先查询是否有这个用户，再查看是否有绑卡记录；若有，则修改原记录的func_bmp;若没有，则insert
     int bindCard(CardDomain cardDomain);
     //查询是否有这个用户;若用户存在，查询是否有绑卡记录；若存在绑卡记录，修改func_bmp=0;若不存在，报错
     boolean unbindCard(@Param("userId") int userId,@Param("cardId") String cardId);
     //修改卡服务
     boolean updateFunc(@Param("userId") int userId,@Param("cardId") String cardId,@Param("func")int func);

     //查询卡列表
     List<CardDomain> queryCard(int userId);
     //根据用户id和卡号查询卡信息
     CardDomain queryCardByUserId(@Param("userId")int userId,@Param("cardId") String cardId);
     //查看用户在卡表是否有记录
     int findCardByUserId(@Param("userId")int userId,@Param("cardId") String cardId);
     //查询对应的用户与卡号在卡表中是否有记录
     int findRecordByUserIdAndCardId(@Param("userId")int userId,@Param("cardId") String cardId);
}
