package me.hackathon.root.model.response.blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountResponse {
    private String response_code;
    private String tx_hash;
    private String more_info;
}
