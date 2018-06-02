package me.hackathon.root.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.hackathon.root.model.supoort.ExceptionCode;
import me.hackathon.root.model.supoort.ResultContainer;
import me.hackathon.root.service.NHService;
import me.hackathon.root.support.Constant;

@RestController
@RequestMapping(Constant.API_URI + "/NH")
public class NHControllerTest {
    @Autowired
    private NHService nhService;

    @GetMapping("/receivedTransferFinAccount")
    public ResultContainer<Void> receivedTransferFinAccount() throws IOException {
        ResultContainer<Void> resultContainer = new ResultContainer<>();
        if (!nhService.receivedTransferFinAccount()) {
            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        }

        return resultContainer;
    }

//    @GetMapping("/checkOnReceivedTransfer")
//    public ResultContainer<Void> checkOnReceivedTransfer() {
//        ResultContainer<Void> resultContainer = new ResultContainer<>();
//        if (!nhService.checkOnReceivedTransfer()) {
//            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
//        }
//
//        return resultContainer;
//    }

    @GetMapping("/drawingTransfer")
    public ResultContainer<Void> drawingTransfer() throws IOException {
        ResultContainer<Void> resultContainer = new ResultContainer<>();
        if (!nhService.drawingTransfer()) {
            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        }

        return resultContainer;
    }

//    @GetMapping("/checkOnDrawingTransfer")
//    public ResultContainer<Void> checkOnDrawingTransfer() {
//        ResultContainer<Void> resultContainer = new ResultContainer<>();
//        if (!nhService.checkOnDrawingTransfer()) {
//            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
//        }
//
//        return resultContainer;
//    }

    @GetMapping("/inquireBalance")
    public ResultContainer<Void> inquireBalance() {
        ResultContainer<Void> resultContainer = new ResultContainer<>();
        if (!nhService.inquireBalance()) {
            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        }

        return resultContainer;
    }

    @GetMapping("/inquireTransactionHistory")
    public ResultContainer<Void> inquireTransactionHistory() {
        ResultContainer<Void> resultContainer = new ResultContainer<>();
        if (!nhService.inquireTransactionHistory()) {
            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        }

        return resultContainer;
    }

    @GetMapping("/inquireDepositorFinAccount")
    public ResultContainer<Void> inquireDepositorFinAccount() {
        ResultContainer<Void> resultContainer = new ResultContainer<>();
        if (!nhService.inquireDepositorFinAccount()) {
            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        }

        return resultContainer;
    }
}
