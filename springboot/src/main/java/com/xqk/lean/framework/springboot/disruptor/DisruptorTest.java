package com.xqk.lean.framework.springboot.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.xqk.lean.framework.springboot.disruptor.WorkHandler.MyWorkHandler1;
import com.xqk.lean.framework.springboot.disruptor.WorkHandler.MyWorkHandler2;
import com.xqk.lean.framework.springboot.disruptor.WorkHandler.MyWorkHandler3;
import com.xqk.lean.framework.springboot.disruptor.event.MyEvent;
import com.xqk.lean.framework.springboot.disruptor.event.MyEventFactory;
import lombok.SneakyThrows;

/**
 * DisruptorTest
 *
 * @author xiongqiankun
 * @since 2022/9/23 18:35
 */
public class DisruptorTest {
    private static final EventTranslatorOneArg<MyEvent,String> TRANSLATOR = (event, sequence, msg)->event.setMsg(msg);

    @SneakyThrows
    public static void main(String[] args) {
        var disruptor = new Disruptor<>(new MyEventFactory(), 1024, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());
        var ringBuffer = disruptor.getRingBuffer();
        //并行处理（唯一消费）
        disruptor.handleEventsWithWorkerPool(new MyWorkHandler1(), new MyWorkHandler2(), new MyWorkHandler3());

        //并行处理（重复消费）
        // disruptor.handleEventsWith(new MyEventHandler(), new MySequenceEventHandler1());

        //MyEventHandler与后面的handler串行处理、MySequenceEventHandler1、MySequenceEventHandler2并行处理
        // disruptor.handleEventsWith(new MyEventHandler())
        //          .then(new MySequenceEventHandler1(), new MySequenceEventHandler2());
        disruptor.start();
        for (int i = 0; i < 100; i++) {
            // 方式一
            ringBuffer.next();
            // var sequence = ringBuffer.next();   // 获取下一个序列号
            // try {
            //     var enent = ringBuffer.get(sequence);   // 根据序列号获取预分配的数据槽
            //     enent.setMsg(String.valueOf(i));    // 向数据槽中填充数据
            // } finally {
            //     ringBuffer.publish(sequence);   //发布事件
            // }

            // 方式二
            disruptor.publishEvent(TRANSLATOR, String.valueOf(i));
        }
        Thread.sleep(100000);
    }
}
