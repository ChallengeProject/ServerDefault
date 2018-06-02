package me.hackathon.root.model.nh;

public enum ApiNHType {
    InquireBalance("03Q_004_F0"),
    InquireDepositorFinAccount("02M_001_00"),
    InquireTransactionHistory("03Q_005_F0"),
    ReceivedTransferFinAccount("01M_003_F0"),
    CheckOnReceivedTransfer("02M_001_00"),
    DrawingTransfer("01D_001_00"),
    CheckOnDrawingTransfer("01D_001_00");

    private String ApiSvcCd;

    ApiNHType(String apiSvcCd) {
        ApiSvcCd = apiSvcCd;
    }

    public String getApiSvcCd() {
        return ApiSvcCd;
    }
}
