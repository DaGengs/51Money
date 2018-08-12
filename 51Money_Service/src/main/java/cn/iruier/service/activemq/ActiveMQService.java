package cn.iruier.service.activemq;

import java.io.Serializable;

/**
 * @Author: iruier
 * @Date: 2018/8/4 19:57
 */
public interface ActiveMQService {
    void sendMsg(String msg);

    void sendObject(Serializable serializable);
}
