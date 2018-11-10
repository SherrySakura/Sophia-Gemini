package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arxanfintech.common.rest.Client;
import com.arxanfintech.sdk.tomago.Tomago;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TomagoService {

    private final String miniboxAddress = "127.0.0.1:9143";
    private final String remoteAddress = "139.198.15.132:9143";
    private final String remoteCert = "/home/asuna/project/DCPC/Sophia-Gemini/resource/cert/";
    private final String miniboxCert = "/home/asuna/project/DCPC/Sophia-Gemini/resource/localcert/";
    private final String miniboxApiKey = "UmZWPJNTp1541577803";
    private final String remoteApiKey = "A7bUwK7Hp1541494283";

    public String invoke(String number, String ori, String des) throws Exception {
        String data = "{\"payload\": {\"chaincode_id\": \"pubchain-mycc\",\"args\": [\"invoke\", \"" + ori + "\", \"" + des + "\", \"" + number + "\"]} }";
        JSONObject jsonData = JSON.parseObject(data);
        String header = "{\"Callback-Url\":\"127.0.0.1\", \"Channel-Id\":\"pubchain\"}";
        JSONObject jsonHeader = JSON.parseObject(header);

        String address = remoteAddress;
        String apiKey = remoteApiKey;
        String certPath = remoteCert;
        Client client = new Client(apiKey, certPath, "", "", "", "", address, true, "tomago");
        Tomago tomago = new Tomago(client);
        String response = tomago.Invoke(jsonHeader, jsonData);
        return response;
    }

    public String query(String id) throws Exception {
        String data = "{\"payload\": {\"chaincode_id\": \"pubchain-mycc\",\"args\": [\"query\", \"" + id + "\"]} }";
        JSONObject jsonData = JSON.parseObject(data);
        String header = "{\"Callback-Url\":\"127.0.0.1\", \"Channel-Id\":\"pubchain\"}";
        JSONObject jsonHeader = JSON.parseObject(header);

        String address = remoteAddress;
        String apiKey = remoteApiKey;
        String certPath = remoteCert;
        Client client = new Client(apiKey, certPath, "", "", "", "", address, true, "tomago");
        Tomago tomago = new Tomago(client);
        String response = tomago.Query(jsonHeader, jsonData);
        return response;
    }

    public String transaction() throws Exception {
        String id = "18a70037526e9ad474513d284941b3dcecf78f390e0b01b4764d03a56666facd";
        System.out.println(id);
        String header = "{\"Callback-Url\":\"127.0.0.1\"}";
        JSONObject jsonHeader = JSON.parseObject(header);
        String address = miniboxAddress;
        String apiKey = miniboxApiKey;
        String certPath = miniboxCert;
        Client client = new Client(apiKey, certPath, "", "", "", "", address, true, "tomago");
        Tomago tomago = new Tomago(client);
        String response = tomago.Transaction(jsonHeader, id);
        return response;
    }

    public String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    private String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
