package com.hapicker.common.constant;

/**
 * 状态
 *
 * @author yuyufeng
 * @date 2018/12/7
 */
public enum BusWarningStatusEnum {
    WARNING(0, "监听中"), WARNING_SUCCESS(1, "监听成功"), WARNING_FAIL(2, "监听失败"), WARNING_ABANDON(3, "放弃监听");
    private Integer key;
    private String value;

    BusWarningStatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        if (key == null) {
            return "";
        }
        for (BusWarningStatusEnum item : BusWarningStatusEnum.values()) {
            if (item.getKey().equals(key)) {
                return item.value;
            }
        }
        return "unknow";
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
