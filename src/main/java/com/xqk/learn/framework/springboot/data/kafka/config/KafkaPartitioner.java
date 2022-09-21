//package com.xqk.learn.framework.data.kafka.config;
//
//import org.apache.kafka.clients.producer.Partitioner;
//import org.apache.kafka.common.Cluster;
//import org.apache.kafka.common.PartitionInfo;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
///**
// * Kafka自定义分区器
// *
// * @author 熊乾坤
// */
////@Component
//@Profile("kafka")
//public class KafkaPartitioner implements Partitioner {
//    private static final String VERY_IMPORTANT_KEY = "xqk";
//    private static final Random RAND = new Random();
//
//    @Override
//    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
//        String keyStr = null;
//        List<PartitionInfo> partitionInfoList = cluster.partitionsForTopic(s);
//        int size = partitionInfoList.size();
//        if (o instanceof String) {
//            keyStr = (String) o;
//        }
//        if (VERY_IMPORTANT_KEY.equals(keyStr)) {
//            return 1;
//        }
//        return RAND.nextInt(size);
//    }
//
//    /**
//     * 分区被关闭时调用
//     */
//    @Override
//    public void close() {
//    }
//
//    @Override
//    public void configure(Map<String, ?> map) {
//
//    }
//}
