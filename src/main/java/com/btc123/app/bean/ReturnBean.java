package com.btc123.app.bean;


import com.btc123.app.enumeration.ErrorEnum;

public class ReturnBean extends BaseBean {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5761386302316325878L;

    /**
     * returnCode 返回码
     */
    private Integer returnCode = ErrorEnum.SUCCESS.getCode();

    /**
     * ReturnMsg 错误描述
     */
    private String returnMsg = ErrorEnum.SUCCESS.getMessage();

    /**
     * data 返回值
     */
    private Object data;


    private ReturnBean() {

    }


    public ReturnBean(Integer returnCode, Object data) {
        super();
        this.returnCode = returnCode;
        this.data = data;
    }


    public ReturnBean(Integer returnCode, String ReturnMsg, Object data) {
        super();
        this.returnCode = returnCode;
        this.returnMsg = ReturnMsg;
        this.data = data;
    }


    public Integer getReturnCode() {
        return returnCode;
    }

    public ReturnBean setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
        return this;
    }


    public ReturnBean setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return data;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public ReturnBean setReturnMsg(String ReturnMsg) {
        this.returnMsg = ReturnMsg;
        return this;
    }

    public static ReturnBean returnBeanBuild() {
        ReturnBean bean = new ReturnBean();
        return bean;
    }

    @Override
    public String toString() {
        return "ReturnBean [returnCode=" + returnCode + ", returnMsg="
                + returnMsg + ", data=" + data + "]";
    }

}
