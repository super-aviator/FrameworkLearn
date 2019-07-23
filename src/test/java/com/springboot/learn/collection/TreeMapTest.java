package com.springboot.learn.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TreeMapTest {

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
}
