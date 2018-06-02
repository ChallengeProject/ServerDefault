package me.hackathon.root.model.nh;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NhRequest {
    private Header header;
    private String FinAcno = "00820111516781526561516565594";
    private String Tram; //거래 금액
    private String OrtrYmd;// 원거래 일자
    private String OrtrIsTuno; // 원거래 기관거래고유번호
    private String Insymd;
    private String Ineymd;
    private String TrnsDsnc;
    private String Lnsq;
    private String PageNo;
    private String Dmcnt;
    private String Bncd;
    private String Acno;
    private String DractOtlt;
    private String MractOtlt;

    public NhRequest() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        this.OrtrYmd  = dateFormat.format(date);
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        OrtrIsTuno = fullFormat.format(date) + "001";
    }
}
