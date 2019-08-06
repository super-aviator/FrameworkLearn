package com.springboot.learn.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Tree map test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TreeMapTest {

    /**
     * Test.
     */
    @Test
    public void test() {
        TreeMap<Long, String> map = new TreeMap<>();
        map.put(10L, "熊乾坤");
        map.put(10L, "杨小毛");
        log.info(map.toString());
        log.info(map.remove(10L));
        log.info(map.toString());
    }

    /**
     * Map 按值排序时，值相同时，会被合并，为什么？如何去处理？
     */
    @Test
    public void testSort() {
        HashMap<String, Long> hash = new HashMap<>();
        hash.put("熊乾坤", 156L);
        hash.put("杨小毛", 8L);
        hash.put("a", 4L);
        hash.put("b", 10L);
        hash.put("c", 23L);
        hash.put("曹睿", 10L);
        hash.put("打发", 8L);
        hash.put("sdf", 10L);
        hash.put("asdf", 30L);
        hash.put("扬天", 1L);
        log.info(hash.toString());

        TreeMap<String, Long> treeMap = new TreeMap<>((entries1, entries2) -> {
            if (entries2.equals("熊乾坤")) {
                return -1;
            } else if (entries1.equals("熊乾坤")) {
                return 1;
            } else {
                int i = hash.get(entries2).compareTo(hash.get(entries1));
                return i == 0 ? 1 : i;
            }
        });

        treeMap.putAll(hash);

        LinkedHashMap<String, Long> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.putAll(treeMap);

        log.info(linkedHashMap.toString());

        linkedHashMap.remove("熊乾坤");
        log.info(linkedHashMap.toString());

        log.info(hash.toString());

//        //正确代码
//        Set<Map.Entry<String,Long>> entries=hash.entrySet();
//
//        SortedSet<Map.Entry<String,Long>> sortedSet=new TreeSet<>((entries1,entries2)-> {
//            if(entries2.getKey().equals("熊乾坤")) {
//                return -1;
//            }else if(entries1.getKey().equals("熊乾坤")){
//                return 1;
//            }else{
//                int i=entries2.getValue().compareTo(entries1.getValue());
//                return i==0?1:i;
//            }
//        });
//        sortedSet.addAll(entries);
//
//        log.info(sortedSet.toString());
//
//
//
//        log.info(sortedSet.toString());

    }

    /**
     * Test fast fail.
     */
    @Test
    public void testFastFail() {
        Map<String, Long> hash = new ConcurrentHashMap<>();
        hash.put("熊乾坤", 10L);
        hash.put("杨小毛", 8L);
        hash.put("曹睿", 15L);
        hash.put("扬天", 1L);

        hash.entrySet().forEach(t -> {
            if (t.getKey().equals("曹睿") || t.getKey().equals("扬天")) {
                hash.put("其他", hash.get("其他") == null ? t.getValue() : hash.get("其他") + t.getValue());
                hash.remove(t.getKey());
            }
        });

        log.info(hash.toString());
    }

    /**
     * Alert test.
     */
    @Test
    public void alertTest() {
        Map<String, Long> hash = new ConcurrentHashMap<>();
        hash.put("熊乾坤", 10L);
        hash.put("杨小毛", 8L);
        hash.put("曹睿", 15L);
        hash.put("扬天", 1L);

        TreeMap<String, Long> tree = new TreeMap<>();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        tree.putAll(hash);
        tree.put("杨小毛", 200L);
        tree.remove("扬天");
        log.info(hash.toString());
        log.info(tree.toString());
    }

    /**
     * For each test.
     */
    @Test
    public void forEachTest() {
        List list = new ArrayList();
        list.addAll(Arrays.asList("熊乾坤", "杨小毛", "曹睿"));
        list.forEach(t -> {
            if (t.equals("杨小毛")) {
                return;
            }
            log.info(t.toString());
        });
    }

    /**
     * Test 1.
     */
    @Test
    public void test1() {
//        log.info(String.format("%d-%d年",100,3));
//        log.info(System.getProperty("file.separator"));
//        Calendar calendar1=Calendar.getInstance();
//        Calendar calendar2=Calendar.getInstance();
//
//        calendar1.set(2019,11,31);
//        calendar2.set(2020,0,3);
//        log.info(String.valueOf(calendar2.get(Calendar.DAY_OF_MONTH)-calendar1.get(Calendar.DAY_OF_MONTH)));

        log.info(Arrays.toString("\"\"".split(",")));
    }

}
