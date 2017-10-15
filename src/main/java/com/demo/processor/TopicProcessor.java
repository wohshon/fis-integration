package com.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TopicProcessor implements Processor {

	@Override
	public void process(Exchange exchg) throws Exception {
		//get topic name
		String channelName=(String)exchg.getIn().getHeader("IncomingChannel");
		//does a static mapping of incoming topic to outgoing topic
		channelName=channelName.toUpperCase()+".DATA";
		exchg.getIn().setHeader("OutgoingChannel", channelName);
		
		
	}

}
