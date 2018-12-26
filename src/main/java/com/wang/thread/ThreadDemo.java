package com.wang.thread;

/**
 * @Auther: wangdingding5
 * @Date: 2018/12/26 14:13
 * @Description:
 */
public class ThreadDemo implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag = " + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
