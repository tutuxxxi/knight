package cdu.linmu.knight.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xxx_
 */
@Data
@NoArgsConstructor
@ToString
@ApiModel("请求返回内容")
public class ResponseData {

    private int code;
    private String msg;
    private Object data;

    public ResponseData(ResponseCode code, Object data){
        this(code);
        this.data = data;
    }

    public ResponseData(ResponseCode code){
        this(code.getCode(), code.getMsg());
    }

    public ResponseData(int code, String msg, Object data){
        this(code, msg);
        this.data = data;
    }

    public ResponseData(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
