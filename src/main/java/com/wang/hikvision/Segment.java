package com.wang.hikvision;

/**
 * <p>段的配置描述</p>
 * @version V1.0
 */
public interface Segment {
    /**
     * 获取AgentNo，可能为空
     */
    String getAgentNo();

    /**
     * 获取实例序号
     */
    Integer getIndex();

    /**
     * 获取段标识
     */
    String getSegmentId();

    /**
     * 获取属性
     *
     * @param keySuffix 属性key，实例后缀
     */
    String getProperty(String keySuffix);

    String getInstanceId();

    /**
     * 设置属性
     *
     * @param keySuffix
     * @param value
     */
    void setProperty(String keySuffix, String value);

}