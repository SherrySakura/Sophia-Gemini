package sophia.model;

public class Message {
    /**
     * code = 0 : operation success;
     * code = -1 : operation fail;
     */
    private int code;
    private String msg;
    private String serviceName;
    private String serviceMethod;

    public Message() {
    }

    public Message(int code, String msg, String serviceName, String serviceMethod) {
        this.code = code;
        this.msg = msg;
        this.serviceName = serviceName;
        this.serviceMethod = serviceMethod;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }
}
