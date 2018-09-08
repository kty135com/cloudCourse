package org.ty.cloudCourse.enums;

import lombok.Getter;

/**
 * Created by kangtaiyang on 2018/5/13.
 */
@Getter
public enum UserStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1, "非法用户"), LOGIN_ERROR(-2, "登陆失败"), PASS(2, "通过认证"), SUCCESS(1, "操作成功"),
    INNER_ERROR(-1001, "内部系统错误"), NULL_ID(-1002, "Id为空"), NULL_MESS(-1003, "用户信息为空");

    private int state;
    private String stateInfo;

    UserStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static UserStateEnum stateOf(int state) {
        for (UserStateEnum pse : values()
                ) {
            if (pse.state == state) {
                return pse;
            }
        }
        return null;
    }

}
