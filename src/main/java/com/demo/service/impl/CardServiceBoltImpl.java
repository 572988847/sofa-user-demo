package com.demo.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.demo.service.CardService;


public class CardServiceBoltImpl {

    @SofaReference(interfaceType = CardService.class,binding = @SofaReferenceBinding( bindingType = "bolt"))
    private CardServiceBoltImpl cardServiceBolt;



}
