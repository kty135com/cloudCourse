package org.ty.cloudCourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ty.cloudCourse.enums.UserStateEnum;

import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/6/10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllExecution<A> {
    //操作返回的单对象
    private A a;
    //操作返回的对象集合
    private List<A> aList;
    //操作状态编号
    private int state;
    //状态信息
    private String stateInfo;
    //操作总数
    private int count;

    //用户操作失败使用的构造
    public AllExecution(UserStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //用户单操作成功使用的构造
    public AllExecution(A a, UserStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.a = a;
    }


    //用户集合操作成功使用的构造
    public AllExecution(List<A> aList, UserStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.aList = aList;
    }

}
