package com.lts;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.function.Function;

/**
 * @author luotianshun
 * @date 2021/7/21
 * @menu javap -verbose test
 */
public abstract class JavaPTest implements Function {

    public static volatile Integer aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa = 4;

    public float aFloat = 3.23f;

    public JavaPTest(String notStatic, String notStatic0) {
        this.notStatic = notStatic;
        this.notStatic0 = notStatic0;
    }

    private String notStatic11;
    private String notStatic = "ss";
    private String notStatic0 = "mm";


    {
        notStatic = "sss";
        aFloat += 1;
    }

    static {
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa = 5;
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        char c = '汉';
        String s = "汉";
        String ss = "d";
        byte[] bytes = s.getBytes("utf-8");
        byte[] bytes1 = ss.getBytes("utf-8");
        byte[] bytes2 = new byte[4];
        bytes2[0] = bytes[0];
        bytes2[1] = bytes[1];
        bytes2[2] = bytes[2];
        bytes2[3] = bytes1[0];
        System.out.println(byteArrToBinStr(bytes));
        String converTedStr = new String(bytes2, "utf-8");
        System.out.println(binStrToByteArr(converTedStr));
        JavaPTest spiTest = new JavaPTest("1", "1"){

            @Override
            protected void getInstance2() {

            }
        };

        spiTest.invokeVirtualMethod();
        spiTest.aFloat +=1;
        Object apply = spiTest.apply(1);
        Function spiTest1 = new JavaPTest("1", "1") {
            @Override
            protected void getInstance2() {

            }
        };
        spiTest1.apply(2);
        Integer aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa = 4;
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa++;

        int m = 0;
        for (int i = 0; i < 100; i++) {
            m = m++;
        }
        System.out.println("m:" + m);
    }

    public static String tset(String[] args) throws ClassNotFoundException, SQLException {
//        int m = 0;
//        for (int i = 0; i < 100; i++) {
//            m = m++;
//            int j = 3;
//            for (int k = 0; k < 2; k++) {
//                j++;
//            }
//        }
//
//        for (int i = 0; i < 100; i++) {
//            m = m++;
//        }
        return null;
    }

    @Override
    public Object apply(Object o) {
        return null;
    }

    public String invokeVirtualMethod() {
        return null;
    }

     abstract void    getInstance2();
    protected  void    getInstance3(){};


    public static String byteArrToBinStr(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            result.append(Long.toString(b[i] & 0xff, 2) + ",");
        }
        return result.toString().substring(0, result.length() - 1);
    }
    public static byte[] binStrToByteArr(String binStr) {
        String[] temp = binStr.split(",");
        byte[] b = new byte[temp.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp[i], 2).byteValue();
        }
        return b;
    }
}
//
//    Connection connection = null;
//    AtomicInteger atomicInteger = new AtomicInteger(1);
//        atomicInteger.compareAndSet(1, 3);
//
//                try {
//                String url = "jdbc:mysql://10.197.236.152:3306/db_tbd?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
//                String user = "root";
//                String password = "123456";
//
//                Class.forName("com.mysql.jdbc.Driver");
//                connection = DriverManager.getConnection(url, user, password);
//                } catch (ClassNotFoundException e) {
//
//                } catch (Exception e) {
//                System.out.println(e);
//                } finally {
//                if (null != connection) {
//                connection.close();
//                }
//                }

