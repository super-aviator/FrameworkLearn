package com.xqk.learn.framework.java.eunms;

public enum AlarmTypeEnum {
    /** 车辆告警 */
    PERSON,
    /** 人员告警 */
    VEHICLE,
    /** 非法告警类型 */
    INVALID;

    public static AlarmTypeEnum valueOf(Integer code) {
        if (code == null) {
            return INVALID;
        }
        for (AlarmTypeEnum item : AlarmTypeEnum.values()) {
            if (item.ordinal() == code) {
                return item;
            }
        }
        return INVALID;
    }

    public static void main(String[] args) {
    }
}
