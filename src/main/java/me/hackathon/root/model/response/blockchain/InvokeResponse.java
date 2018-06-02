package me.hackathon.root.model.response.blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvokeResponse {
    private String response_code;
    private InvokeSubResponse response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InvokeSubResponse {
        private String message;
        private Integer code;
        private String jsonrpc = "2.0";
    }

}
