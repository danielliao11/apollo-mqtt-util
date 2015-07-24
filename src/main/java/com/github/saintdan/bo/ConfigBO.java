package com.github.saintdan.bo;

import com.github.saintdan.enums.ApiType;
import org.fusesource.mqtt.client.QoS;

import java.io.Serializable;

/**
 * Config BO.
 *
 * @author Liao Yifan
 * @date 5/28/15
 * @since JDK1.8
 */
public class ConfigBO implements Serializable {

    private static final long serialVersionUID = 5338545675886226340L;
    private String host; // Host
    private int port; // Port
    private String username; // Username
    private String password; // Password
    private ApiType apiType; // api type
    private QoS qoSType; // qos type

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApiType getApiType() {
        return apiType;
    }

    public void setApiType(ApiType apiType) {
        this.apiType = apiType;
    }

    public QoS getQoSType() {
        return qoSType;
    }

    public void setQoSType(QoS qoSType) {
        this.qoSType = qoSType;
    }
}
