package com.zhw.chemistrywave.event;

/**
 * Created by axehome on 2017/12/4.
 */

public class DeleteEvent {
    public String mMsg;
    public DeleteEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
