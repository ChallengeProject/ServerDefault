package me.hackathon.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import me.hackathon.root.model.request.MyAmountRequest;
import me.hackathon.root.model.request.MyAmountRequest.MyAmountParams;
import me.hackathon.root.model.request.QueryAmountRequest;
import me.hackathon.root.model.request.QueryAmountRequest.QueryAmountParams;
import me.hackathon.root.model.request.YouAmountRequest;
import me.hackathon.root.model.request.YouAmountRequest.YouAmountParams;
import me.hackathon.root.model.response.blockchain.AmountResponse;
import me.hackathon.root.model.response.blockchain.InvokeResponse;
import me.hackathon.root.model.response.blockchain.QueryAmountResponse;
import me.hackathon.root.support.security.SecurityUtils;

@Slf4j
@Service
public class BlockChainService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://13.125.18.54:9000/api/v1";
    private static final String SUCCESS = "0";

    public InvokeResponse invoke(String tx_hash) {
        String url = URL + "/transactions/result?hash=" + tx_hash ;

        return restTemplate.getForObject(url, InvokeResponse.class);
    }

    public boolean updateMyAmount(MyAmountRequest myAmountRequest) {
        String url = URL + "/transactions";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MyAmountRequest> entity = new HttpEntity<>(myAmountRequest, httpHeaders);
        ResponseEntity<AmountResponse> myAmountResponse = restTemplate.exchange(url, HttpMethod.POST, entity, AmountResponse.class);
        log.debug(myAmountResponse.getBody().getTx_hash());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        InvokeResponse invokeResponse = invoke(myAmountResponse.getBody().getTx_hash());

        if (invokeResponse.getResponse_code().equals(SUCCESS)) {
            return invokeResponse.getResponse().getCode() != 9000; //실패
        }

        return false;
    }

    public boolean transferAmount(YouAmountRequest youAmountRequest) {
        String url = URL + "/transactions";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<YouAmountRequest> entity = new HttpEntity<>(youAmountRequest, httpHeaders);
        ResponseEntity<AmountResponse> youAmountResponse = restTemplate.exchange(url, HttpMethod.POST, entity, AmountResponse.class);
        log.debug(youAmountResponse.getBody().getTx_hash());
        InvokeResponse invokeResponse = invoke(youAmountResponse.getBody().getTx_hash());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (invokeResponse.getResponse_code().equals(SUCCESS)) {
            return invokeResponse.getResponse().getCode() != 9000; //실패
        }

        return false;
    }

    public Integer queryAmount(QueryAmountRequest queryAmountRequest) {
        String url = URL + "/query";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<QueryAmountRequest> entity = new HttpEntity<>(queryAmountRequest, httpHeaders);
        ResponseEntity<QueryAmountResponse> queryAmountResponse = restTemplate.exchange(url, HttpMethod.POST, entity, QueryAmountResponse.class);
        if (queryAmountResponse.getStatusCode() == HttpStatus.OK) {
            if (queryAmountResponse.getBody().getResponse().getResult().getData() == null) {
                throw new IllegalArgumentException("계정이 없습니다.");
            }
        }

        return queryAmountResponse.getBody().getResponse().getResult().getData();
    }

    public MyAmountRequest createMyAmountRequest(String email, Integer amount) {
        MyAmountRequest myAmountRequest = new MyAmountRequest();
        MyAmountParams myAmountParams = new MyAmountParams();
        myAmountParams.setUid(SecurityUtils.encryptMD5AndSHA256(email));
        myAmountParams.setAmount(Integer.toString(amount));
        myAmountRequest.setParams(myAmountParams);

        return myAmountRequest;
    }

    public YouAmountRequest createYouAmountRequest(String fromId, String toId, Integer amount) {
        YouAmountRequest youAmountRequest = new YouAmountRequest();
        YouAmountParams youAmountParams = new YouAmountParams();
        youAmountParams.setFrom_id(SecurityUtils.encryptMD5AndSHA256(fromId));
        youAmountParams.setTo_id(SecurityUtils.encryptMD5AndSHA256(toId));
        youAmountParams.setAmount(Integer.toString(amount));
        youAmountRequest.setParams(youAmountParams);

        return youAmountRequest;
    }

    public QueryAmountRequest createQueryAmountRequest(String uid) {
        QueryAmountRequest queryAmountRequest = new QueryAmountRequest();
        QueryAmountParams queryAmountParams = new QueryAmountParams();
        queryAmountParams.setUid(SecurityUtils.encryptMD5AndSHA256(uid));
        queryAmountRequest.setParams(queryAmountParams);

        return queryAmountRequest;
    }

    public Integer getUserCoin(String email) {
        return queryAmount(createQueryAmountRequest(email));
    }

    public boolean sendCoin(String email, Integer amount) {
        return updateMyAmount(createMyAmountRequest(email, amount));
    }

    public boolean transferCoin(String fromId, String toId, Integer amonut) {
        return transferAmount(createYouAmountRequest(fromId,toId,amonut));
    }



}
