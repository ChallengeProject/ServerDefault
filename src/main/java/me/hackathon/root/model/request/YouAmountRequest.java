package me.hackathon.root.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YouAmountRequest {
    private String jsonrpc = "2.0";
    private String id = "123";
    private String method = "give_point_to_another";
    private YouAmountParams params;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class YouAmountParams {
        private String to_id;
        private String from_id;
        private String amount;
    }
}
