package cdu.linmu.knight.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xxx_
 */

@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),
    NOTFOUND(404, "NOTFOUND"),
    FAILED(500, "FAILED");

    private int code;
    private String msg;

}
