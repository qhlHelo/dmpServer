package com.chinagreentown.dmp.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @desc hbase属性配置
 */
@ConfigurationProperties(prefix = "spring.data.hbase")
public class HbaseProperties {

    private String quorum;
    private String port;

    public String getQuorum() {
        return quorum;
    }

    public void setQuorum(String quorum) {
        this.quorum = quorum;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
