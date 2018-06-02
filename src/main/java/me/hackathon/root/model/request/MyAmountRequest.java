package me.hackathon.root.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyAmountRequest {
    private String jsonrpc = "2.0";
    private String id = "123";
    private String method = "regulate_point";
    private MyAmountParams params;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MyAmountParams {
        private String uid;
        private String amount;
    }
}
