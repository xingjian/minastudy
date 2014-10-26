/** @文件名: DemoServerHandler.java @创建人：邢健  @创建日期： 2013-11-22 上午9:14:31 */

package com.promise.mina.demo;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @类名: DemoServerHandler.java
 * @包名: com.promise.mina.demo
 * @描述: TODO
 * @作者: xingjian xingjian@yeah.net
 * @日期:2013-11-22 上午9:14:31
 * @版本: V1.0
 */
public class DemoServerHandler extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(DemoServerHandler.class);

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("服务端与客户端创建连接...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("服务端与客户端连接打开...");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		logger.info("服务端接收到的数据为：" + msg);
		if ("bye".equals(msg)) { // 服务端断开连接的条件
			session.close();
		}
		Date date = new Date();
		session.write(date);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		session.close(); //发送成功后主动断开与客户端的连接
		logger.info("服务端发送信息成功...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.info("服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("服务端发送异常...", cause);
	}
}
