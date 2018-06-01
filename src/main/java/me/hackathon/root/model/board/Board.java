package me.hackathon.root.model.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Integer id;
    private Integer userId;
    private String title;
    private Long vltDurationTime;
    private Integer vltTime;
    private Integer vltStartTime;
    private Integer vltEndTime;
    private Long recruitTime;
    private Integer personCount;
    private VLTCategoryType vltCategory;
    private String vltType;
    private String organization;
    private Integer coin;
    private String address;
    private String content;
    private BoardStatus status;
}
