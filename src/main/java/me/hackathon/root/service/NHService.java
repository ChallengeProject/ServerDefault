package me.hackathon.root.service;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import lombok.extern.slf4j.Slf4j;
import me.hackathon.root.model.nh.ApiNHType;
import me.hackathon.root.model.nh.CheckResponse;
import me.hackathon.root.model.nh.Header;
import me.hackathon.root.model.nh.NhRequest;

@Slf4j
@Service
public class NHService {
    @Autowired
    private RestTemplate restTemplate;
    private static final String URL = "http://10.10.3.53:8084/NH-KISA-OTA/ota/process.jsp";

    private static final int DEFAULT_AMOUNT = 10;
    ObjectMapper objectMapper = new ObjectMapper();

    //입금
    public boolean receivedTransferFinAccount() throws IOException {
        NhRequest nhRequest = createNhRequest(ApiNHType.ReceivedTransferFinAccount, DEFAULT_AMOUNT);
        String jsonInString = nhRequestToJson(nhRequest);

        ResponseEntity<String> responseEntity = getResponseEntity(jsonInString);
        String resultCode = getResultCode(responseEntity.getBody());

        if (resultCode.equals("00000")) {
            CheckResponse receivedTransferFinAccountResponse = objectMapper.readValue(responseEntity.getBody(),
                                                                                      CheckResponse.class);
            if (checkOnReceivedTransfer(receivedTransferFinAccountResponse.getHeader())) {
                return true;
            }
        }

        return false;
    }

    //입금확인
    public boolean checkOnReceivedTransfer(Header prevHeader) {
        NhRequest nhRequest = createNhRequest(ApiNHType.CheckOnReceivedTransfer, DEFAULT_AMOUNT);
        nhRequest.setBncd("");
        nhRequest.setAcno("");
        nhRequest.setOrtrYmd(prevHeader.getTsymd());
        nhRequest.setOrtrIsTuno(prevHeader.getIsTuno());

        String jsonInString = nhRequestToJson(nhRequest);
        ResponseEntity<String> responseEntity = getResponseEntity(jsonInString);

        String resultCode = getResultCode(responseEntity.getBody());

        if (resultCode.equals("00000")) {
            return true;
        }

        log.debug("ERROR CODE : {}", resultCode);

        return false;
    }

    //출금
    public boolean drawingTransfer() throws IOException {
        NhRequest nhRequest = createNhRequest(ApiNHType.DrawingTransfer, DEFAULT_AMOUNT);
        nhRequest.setDractOtlt("우승은 우리것");
        nhRequest.setMractOtlt("1등 가즈아");
        String jsonInString = nhRequestToJson(nhRequest);
        ResponseEntity<String> responseEntity = getResponseEntity(jsonInString);

        String resultCode = getResultCode(responseEntity.getBody());

        if (resultCode.equals("00000")) {
            CheckResponse checkResponse = objectMapper.readValue(responseEntity.getBody(), CheckResponse.class);
            if (checkOnDrawingTransfer(checkResponse.getHeader())) {
                return true;
            }
        }

        log.debug("ERROR CODE : {}", resultCode);

        return false;

    }

    //출금확인
    public boolean checkOnDrawingTransfer(Header prevHeader) {
        NhRequest nhRequest = createNhRequest(ApiNHType.CheckOnDrawingTransfer, DEFAULT_AMOUNT);
        nhRequest.setOrtrYmd(prevHeader.getTsymd());
        nhRequest.setOrtrIsTuno(prevHeader.getIsTuno());
        String jsonInString = nhRequestToJson(nhRequest);
        ResponseEntity<String> responseEntity = getResponseEntity(jsonInString);

        String resultCode = getResultCode(responseEntity.getBody());

        if (resultCode.equals("00000")) {
            return true;
        }

        log.debug("ERROR CODE : {}", resultCode);

        return false;
    }

    //잔액조회
    public boolean inquireBalance() {
        String jsonInString = nhRequestToJson(createNhRequest(ApiNHType.InquireBalance, DEFAULT_AMOUNT));
        ResponseEntity<String> responseEntity = getResponseEntity(jsonInString);

        String resultCode = getResultCode(responseEntity.getBody());

        if (resultCode.equals("00000")) {
            return true;
        }

        log.debug("ERROR CODE : {}", resultCode);

        return false;
    }

    //거래내역조회
    public boolean inquireTransactionHistory() {
        Header header = createHeader(ApiNHType.InquireTransactionHistory);
        NhRequest nhRequest = new NhRequest();
        nhRequest.setHeader(header);
        nhRequest.setTram(Integer.toString(DEFAULT_AMOUNT)); //거래금액
        nhRequest.setIneymd(header.getTsymd());
        nhRequest.setInsymd(header.getTsymd());
        nhRequest.setTrnsDsnc("A");
        nhRequest.setLnsq("ASC");
        nhRequest.setPageNo("1");
        nhRequest.setDmcnt("100");

        String jsonInString = nhRequestToJson(nhRequest);
        ResponseEntity<String> responseEntity = getResponseEntity(jsonInString);

        String resultCode = getResultCode(responseEntity.getBody());

        if (resultCode.equals("00000")) {
            return true;
        }

        log.debug("ERROR CODE : {}", resultCode);

        return false;
    }

    //예금주 조회
    public boolean inquireDepositorFinAccount() {
        NhRequest nhRequest = createNhRequest(ApiNHType.InquireDepositorFinAccount, DEFAULT_AMOUNT);

        String jsonInString = nhRequestToJson(nhRequest);
        ResponseEntity<String> responseEntity = getResponseEntity(jsonInString);

        String resultCode = getResultCode(responseEntity.getBody());

        if (resultCode.equals("00000")) {
            return true;
        }

        log.debug("ERROR CODE : {}", resultCode);

        return false;
    }

    public Header createHeader(ApiNHType apiNHType) {
        Header header = new Header();
        header.setApiNm(apiNHType.name());
        header.setApiSvcCd(apiNHType.getApiSvcCd());

        return header;
    }

    public NhRequest createNhRequest(ApiNHType apiNHType, int amount) {
        Header header = createHeader(apiNHType);
        NhRequest nhRequest = new NhRequest();
        nhRequest.setHeader(header);
        nhRequest.setTram(Integer.toString(amount)); //거래금액

        return nhRequest;
    }

    public String nhRequestToJson(NhRequest nhRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        String jsonInString = null;
        try {
            jsonInString = objectMapper.writeValueAsString(nhRequest);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }

        log.debug("json : {}", jsonInString);

        return jsonInString;
    }

    public ResponseEntity<String> getResponseEntity(String jsonInString) {
        HttpEntity<String> entity = createHttpEntity();

        UriComponentsBuilder builder = createUriComponentsBuilder(jsonInString);

        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                                                                      entity, String.class);

        return responseEntity;
    }

    public UriComponentsBuilder createUriComponentsBuilder(String jsonInString) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                                                           .queryParam("p", "send")
                                                           .queryParam("fintechApsno", "001")
                                                           .queryParam("JSONData", jsonInString);

        return builder;
    }

    public HttpEntity<String> createHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(
                new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8")));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return entity;
    }

    public String getResultCode(String responseBody) {
        log.debug("\n{}", responseBody);
        int startIndex = responseBody.indexOf("\"Rpcd\"");
        String resultCode = responseBody.substring(startIndex + 8, startIndex + 13);
        log.debug("{}", resultCode);
        return resultCode;
    }
}
