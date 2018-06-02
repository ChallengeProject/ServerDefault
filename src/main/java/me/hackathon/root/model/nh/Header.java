package me.hackathon.root.model.nh;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Header {
    private String ApiNm;
    private String Tsymd; //전송일자
    private String Trtm; //전송 시각
    private String Iscd = "000022"; // 기관코드 (고정
    private String FintechApsno = "001"; // 핀테크앱일련번호(고정
    private String ApiSvcCd = "01D_001_00"; // API서비스 코드(고정
    private String IsTuno;
    private String Rsms;
    private String Rpcd;

    public Header() {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        this.Trtm  = timeFormat.format(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Tsymd = dateFormat.format(date);
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        IsTuno = fullFormat.format(date) + "001";

    }
    public void getDateFormat() {

        Date date = new Date();
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        // 기관거래고유번호, 일자, 시간 setting
        IsTuno = fullFormat.format(date) + "001";
        Tsymd = dateFormat.format(date);
        Trtm  = timeFormat.format(timeFormat);
    }

    public void setTrtm() {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hhmmss");
        this.Trtm  = timeFormat.format(timeFormat);
    }

    public void setTsymd(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Tsymd = dateFormat.format(date);
    }
}
