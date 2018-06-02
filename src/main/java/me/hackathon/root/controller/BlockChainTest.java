package me.hackathon.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.hackathon.root.model.request.MyAmountRequest;
import me.hackathon.root.model.request.QueryAmountRequest;
import me.hackathon.root.model.request.YouAmountRequest;
import me.hackathon.root.model.supoort.ExceptionCode;
import me.hackathon.root.model.supoort.ResultContainer;
import me.hackathon.root.service.BlockChainService;
import me.hackathon.root.support.Constant;
import me.hackathon.root.support.security.SecurityUtils;

@RestController
@RequestMapping(Constant.API_URI + "/test")
public class BlockChainTest {
    @Autowired
    private BlockChainService blockChainService;

    @PostMapping("/updateAmount")
    public ResultContainer<Void> updateMyAmount(@RequestBody MyAmountRequest myAmountRequest) {
        ResultContainer resultContainer= new ResultContainer<>();
        myAmountRequest.getParams().setUid(SecurityUtils.encryptMD5AndSHA256(myAmountRequest.getParams().getUid()));

        if (!blockChainService.updateMyAmount(myAmountRequest)) {
            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        }
        return resultContainer;
    }

    @PostMapping("/transferAmount")
    public ResultContainer<Void> transferAmount(@RequestBody YouAmountRequest youAmountRequest) {
        ResultContainer resultContainer= new ResultContainer<>();

        if (!blockChainService.transferAmount(youAmountRequest)) {
            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        }
        return new ResultContainer<>();
    }

    @PostMapping("/queryAmount")
    public ResultContainer<Integer> queryAmount(@RequestBody QueryAmountRequest queryAmountRequest) {

        return new ResultContainer<>(blockChainService.queryAmount(queryAmountRequest));
    }


}
