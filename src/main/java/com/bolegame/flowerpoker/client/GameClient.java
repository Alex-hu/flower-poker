package com.bolegame.flowerpoker.client;

import com.bolegame.flowerpoker.server.ServerHandler;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * @Description: flower-poker.
 * @author: Hu Zhongyuan
 * @version: 13-11-6
 */
public class GameClient {
    public GameClient() {
    }

    public static void main(String args[]) {
        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
        connector.setConnectTimeout(1);
        connector.setHandler(new ServerHandler());
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", 1234));
        cf.awaitUninterruptibly();
        cf.getSession().write("hello,成功!!fdsafds");
        cf.getSession().close();
        cf.getSession().getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }
}
