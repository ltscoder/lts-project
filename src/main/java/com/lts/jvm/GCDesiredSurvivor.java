package com.lts.jvm;

/**
 * @author luotianshun
 * @date 2021/3/6
 * @menu 代码参考【Minor GC 中 MaxTenuringThreshold 和 TargetSurvivorRatio 参数说明】 by yangjun2
 */
public class GCDesiredSurvivor {

    public static void main(String[] args) throws Exception {

        //测试1     -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintTenuringDistribution
        GCMemoryObject object1 = new GCMemoryObject(2);
        GCMemoryObject object2 = new GCMemoryObject(8);
        GCMemoryObject object3 = new GCMemoryObject(8);
        GCMemoryObject object4 = new GCMemoryObject(8);
        object2 = null;
        object3 = null;
        GCMemoryObject object5 = new GCMemoryObject(8);
        Thread.sleep(4000);
        object2 = new GCMemoryObject(8);
        object3 = new GCMemoryObject(8);
        object2 = null;
        object3 = null;
        object5 = null;
        GCMemoryObject object6 = new GCMemoryObject(8);
        Thread.sleep(5000);

        GCMemoryObject object7 = new GCMemoryObject(8);
        GCMemoryObject object8 = new GCMemoryObject(8);
        GCMemoryObject object9 = new GCMemoryObject(8);
        GCMemoryObject object10 = new GCMemoryObject(8);
        //object6 = null;
        Thread.sleep(5000);
        System.out.println("ok");


       //测试2---------------------------------------------------------------------------------------------
//        GCMemoryObject1 object2 = new GCMemoryObject1(0.1f);
//        GCMemoryObject1 object1 = new GCMemoryObject1(2f);
//        Thread.sleep(4000);
//
//        GCMemoryObject1 object4 = new GCMemoryObject1(0.1f);
//        GCMemoryObject1 object3 = new GCMemoryObject1(2f);
//
//        Thread.sleep(4000);
//        GCMemoryObject1 object6 = new GCMemoryObject1(0.3f);
//        GCMemoryObject1 object5 = new GCMemoryObject1(2f);
//
//
//        Thread.sleep(4000);
//        GCMemoryObject1 object8 = new GCMemoryObject1(0.3f);
//        GCMemoryObject1 object7 = new GCMemoryObject1(2f);
//
//
//        Thread.sleep(4000);
//        GCMemoryObject1 object10 = new GCMemoryObject1(0.3f);
//        GCMemoryObject1 object9 = new GCMemoryObject1(2f);
//        System.out.println("ok");
    }

    public static class GCMemoryObject {

        private byte[] bytes = null;

        public GCMemoryObject(int multi) {

            bytes = new byte[1024 * 256 * multi];


        }
    }

    public static class GCMemoryObject1 {

        private byte[] bytes = null;

        public GCMemoryObject1(float multi) {

            bytes = new byte[(int) (1024 * 1024 * multi)];


        }
    }
}

//new threshold变为2(作用于下次gc)。