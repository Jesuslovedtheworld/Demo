package com.baidu.fileb.test;

import android.content.Intent;

public class MyThreadLocal {
    private static final ThreadLocal<Object>  threadLocal = new ThreadLocal<Object>(){
      /*
      * threadLocal没有被当前线程赋值时或当前线程刚调用remoce方法后调用get方法  返回此方法值
      * */
        @Override
        protected Object initialValue() {
            System.out.println("调用get方法时，当前线程共享变量没有设置 调用initialValue获取默认值!");
            return null;
        }
    };

    public static void main(String[] args) {
            new Thread(new MyInteger("IntegerTask1")).start();
            new Thread(new MyStringTask("StringTask1")).start();
            new Thread(new MyInteger("IntegerTask2")).start();
            new Thread(new MyStringTask("StringTask3")).start();
    }
    public static class MyInteger implements Runnable{
        private String name;

        public MyInteger(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                //ThreadLocal.get()方法获取线程变量
                if (null == MyThreadLocal.threadLocal.get()){
                    //ThreadLocal.set方法设置线程变量
                    MyThreadLocal.threadLocal.set(0);
                    System.out.println("线程"+name+"0");
                }else {
                    int num = (Integer) MyThreadLocal.threadLocal.get();
                    MyThreadLocal.threadLocal.set(num+1);
                    System.out.println("线程"+name+":"+MyThreadLocal.threadLocal.get());
                    if (i == 3){
                        MyThreadLocal.threadLocal.remove();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class MyStringTask implements Runnable{
        private String name;

        public MyStringTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if (null == MyThreadLocal.threadLocal.get()){
                    MyThreadLocal.threadLocal.set("a");
                    System.out.println("线程"+name+":a");
                }else {
                    String s = (String) MyThreadLocal.threadLocal.get();
                    MyThreadLocal.threadLocal.set(s+"a");
                    System.out.println("线程"+name+":"+MyThreadLocal.threadLocal.get());
                }
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
