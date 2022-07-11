/**
 * FileName: SnowFlakeGenerator
 * Date:     2022/6/9 17:05
 * Author: YuanXQ
 * Description:
 */
package com.woniu.knowledgepayment.commons.util;

import java.time.Instant;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/9 17:05
 * @since 1.0.0
 */
public class SnowFlakeGenerator {

    // ==============================Fields===========================================

    // 所占位数、位移、掩码/极大值
    private static final long sequenceBits = 12L;
    private static final long sequenceShift = 0L;
    private static final long sequenceMax = ~(-1L << sequenceBits);

    private static final long workerIdBits = 5L;
    private static final long workerIdShift = sequenceBits;
    private static final long workerIdMax = ~(-1L << workerIdBits);

    private static final long dataCenterIdBits = 5L;
    private static final long dataCenterIdShift = sequenceBits + workerIdBits;
    private static final long dataCenterIdMax = ~(-1L << dataCenterIdBits);

    private static final long timestampBits = 41L;
    private static final long timestampShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private static final long timestampMax = ~(-1L << timestampBits);

    /**
     * 开始时间截 (2015-01-01)
     */
    // private static final long twepoch = 1577836800000L;
    private static final long twepoch = Instant.parse("2020-01-01T00:00:00Z").toEpochMilli();
    /*
     * Instant instant = Instant.parse("2020-01-01T00:00:00Z");
     * System.out.println(instant.getEpochSecond());
     * System.out.println(instant.toEpochMilli());
     */


    private long sequence = 0L;
    private long workerId;
    private long dataCenterId;

    /**
     * 上次生成 ID 的时间截
     */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================

    public SnowFlakeGenerator() {
        this(0, 0);
    }

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心 ID (0~31)
     */
    public SnowFlakeGenerator(long workerId, long dataCenterId) {
        if (workerId > workerIdMax || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", workerIdMax));
        }

        if (dataCenterId > dataCenterIdMax || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId can't be greater than %d or less than 0", dataCenterIdMax));
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    // ============================== Methods ==========================================

    /**
     * 获得下一个 ID (该方法是线程安全的，synchronized)
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次 ID 生成的时间戳，说明系统时钟回退过，这个时候应当抛出异常。
        // 出现这种原因是因为系统的时间被回拨，或出现闰秒现象。
        // 你也可以不抛出异常，而是调用 tilNextMillis 进行等待
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & sequenceMax;
            // 毫秒内序列溢出，即，同一毫秒的序列数已经达到最大
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 将当前生成的时间戳记录为『上次时间戳』。『下次』生成时间戳时要用到。
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成 64 位的 ID
        return ((timestamp - twepoch) << timestampShift) // 时间戳部分
                | (dataCenterId << dataCenterIdShift) // 数据中心部分
                | (workerId << workerIdShift) // 机器标识部分
                | sequence; // 序列号部分
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param timestamp 当前时间错
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long timestamp, long lastTimestamp) {
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
}