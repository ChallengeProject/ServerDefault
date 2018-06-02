package me.hackathon.root.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryAmountRequest {
    private String jsonrpc = "2.0";
    private String id = "123"; //의미없음 아무거나 넣어도 됨
    private String method = "get_point";
    private QueryAmountParams params;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class QueryAmountParams {
        private String uid;
    }

}
