package me.hackathon.root.model.response.blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryAmountResponse {
    private String response_code;
    private QueryAmountSubResponse response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QueryAmountSubResponse {
        private Integer code;
        private String message;
        private Result result;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Result{
            private Integer data;
        }
    }

}
