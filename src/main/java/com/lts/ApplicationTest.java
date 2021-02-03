package com.lts;

import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luotianshun
 * @date 2020/12/23
 * @menu
 */
public class ApplicationTest<V> {
    public List<Map<String,String>> maps = new ArrayList<>();

    private String string;
//    private List<String> listString;
//    private List<List<String>> listLists;
    private List<? extends String> listWildcard;
    private V v;
    private V[] genericArr;
    public static void main(String[] args) throws IOException, NoSuchMethodException, NoSuchFieldException {
//        System.out.println(new Date().getTime());
//        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant(ZoneOffset.of("+8")).toEpochMilli());

//        ConcurrentReferenceHashMap map = new ConcurrentReferenceHashMap(16, ConcurrentReferenceHashMap.ReferenceType.WEAK);
//        map.put("key1","val");
//        Object key1 = map.get("key1");
//        System.out.println(key1);
//
//        System.out.println(map);
//
//        System.gc();
//        try {
//            Thread.currentThread().sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(map);

          //map value remove
//        Map<Integer, String> map = new HashMap<Integer, String>();
//        map.put(1, "a");
//        map.put(2, "b");
//        map.put(3, "c");
//        map.put(4, "d");
//        map.put(5, "e");
//        map.put(6, "f");
//        System.out.println(map);
//        Collection<String> col = map.values();
//        while(true == col.contains("c")) {
//            col.remove("c");
//        }
//        System.out.println(map);



        // 监听指定的端口
//        int port = 55533;
//        ServerSocket server = new ServerSocket(port);
//        // server将一直等待连接的到来
//        System.out.println("server将一直等待连接的到来");
//
//        //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
//        ExecutorService threadPool = Executors.newFixedThreadPool(100);
//
//        while (true) {
//            Socket socket = server.accept();
//
//            Runnable runnable=()->{
//                try {
//                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
//                    InputStream inputStream = socket.getInputStream();
//                    byte[] bytes = new byte[1024];
//                    int len;
//                    StringBuilder sb = new StringBuilder();
//                    while ((len = inputStream.read(bytes)) != -1) {
//                        // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//                        sb.append(new String(bytes, 0, len, "UTF-8"));
//                    }
//                    System.out.println("get message from client: " + sb);
//                    inputStream.close();
//                    socket.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            };
//            threadPool.submit(runnable);
//        }

//        Obj0122 obj0122 = new Obj0122();
//        Method getA = obj0122.getClass().getDeclaredMethod("getA");
//        Type type = obj0122.getClass();
//        Object o = ReflectionUtils.invokeMethod(getA, type);
//        System.out.println(o);

//        Field maps = ApplicationTest.class.getField("maps");
//        Type genericType = maps.getGenericType();
//        ParameterizedType parameterizedType = (ParameterizedType) genericType;
//        Type rawType = parameterizedType.getRawType();
//        System.out.println("k");

//        notMain("s");

//        Class aClass = Array.newInstance(ArrayList.class, 0).getClass();
//        ResolvableType resolvableType = ResolvableType.forClass(ArrayList.class, MyList.class);
//        ResolvableType resolvableType1 = ResolvableType.forClassWithGenerics(List.class, String.class);

//        ReflectionUtils.findField(ApplicationTest.class, "listLists");
        Field v = ReflectionUtils.findField(ApplicationTest.class, "v");
        Field string = ReflectionUtils.findField(ApplicationTest.class, "string");
//        Field listString = ReflectionUtils.findField(ApplicationTest.class, "listString");
        Field listWildcard = ReflectionUtils.findField(ApplicationTest.class, "listWildcard");
        Field genericArr = ReflectionUtils.findField(ApplicationTest.class, "genericArr");

        ResolvableType rForClass = ResolvableType.forClass(ApplicationTest.class);
//        resolvableType.getNested();
        //resolveClass(): -->resolveType().resolve()
        //resolveType()：this.type instanceof TypeVariable且this.variableResolver==null的情况
        ResolvableType typeVariable = ResolvableType.forField(v);
        ResolvableType rClass = ResolvableType.forField(string);
        //resolveType()：this.type instanceof ParameterizedType的情况
        ResolvableType paramterizedTypeWild = ResolvableType.forField(listWildcard);
        //resolveClass(): this.type instanceof GenericArrayType的情况
        ResolvableType rGenericArr = ResolvableType.forField(genericArr);

        //getGenerics()：-->resolveType().getGenerics()这种情况
        ResolvableType[] generics = typeVariable.getGenerics();

        ResolvableType[] generics4 = paramterizedTypeWild.getGenerics();
        ResolvableType wild = generics4[0];
        //resolveType(): -->return NONE这种情况
        ResolvableType[] generics5 = rGenericArr.getGenerics();

        ResolvableType componentType = rClass.getComponentType();
        ResolvableType componentType2 = typeVariable.getComponentType();
        ResolvableType componentType3 = rForClass.getComponentType();
        ResolvableType componentType4 = paramterizedTypeWild.getComponentType();
        //resolveType(): this.type instanceof WildcardType这种情况。
        ResolvableType componentType5 = wild.getComponentType();
        ResolvableType componentType6 = rGenericArr.getComponentType();


        //test for getGeneric() end

        //test for resolveType() start


        System.out.println("dfsd");
    }

    public static void notMain(String[] args) {
        System.out.println(args);
    }


}
