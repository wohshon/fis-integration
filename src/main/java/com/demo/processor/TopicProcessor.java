package com.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicProcessor implements Processor {

	Logger log=LoggerFactory.getLogger(getClass());
	@Override
	public void process(Exchange exchg) throws Exception {
		//get topic name
		String channelName=(String)exchg.getIn().getHeader("IncomingChannel");
		//For every incoming topic, 
		//create an equivalent of <TOPICNAME>/DATA as MQTT endpoint
		//create a JDG entry of <TOPICNAME>.DATA
		//saved them to header
		String outgoingTopiclName=channelName.toUpperCase()+"/DATA";
		outgoingTopiclName=outgoingTopiclName.replace('.', '/');
		String JDGTopiclName=channelName.toUpperCase()+".DATA";
		exchg.getIn().setHeader("OutgoingChannel", channelName);
		//<to id="amq_route_to_topic" uri="mqtt://dummy?host={{outgoing.amq.url.mqtt}}&amp;userName={{outgoing.amq.username}}&amp;password={{outgoing.amq.password}}&amp;publishTopicName=${header.OutgoingChannel}"/>		
		String outgoingAmqUrlMqtt = exchg.getContext().resolvePropertyPlaceholders("{{outgoing.amq.url.mqtt}}");
		String outgoingAmqUsername = exchg.getContext().resolvePropertyPlaceholders("{{outgoing.amq.username}}");
		String outgoingAmqPassword = exchg.getContext().resolvePropertyPlaceholders("{{outgoing.amq.password}}");
		String uri="mqtt://endpoint?host="+outgoingAmqUrlMqtt+"&userName="+outgoingAmqUsername+"&password="+outgoingAmqPassword+"&publishTopicName="+outgoingTopiclName;
		log.info("Connection String "+uri);
		exchg.getIn().setHeader("OutgoingAMQURI", uri);
		exchg.getIn().setHeader("JDGTopic", JDGTopiclName);
	}

}
