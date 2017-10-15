/**
 * 
 */
package com.demo.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * @author wohshon
 *
 */
public class JDGProcessor implements Processor {

	Logger log=LoggerFactory.getLogger(this.getClass());
	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchg) throws Exception {
		log.info("Inside processor *************for Topic "+exchg.getIn().getHeader("incomingChannel"));
		log.info("Inside processor *************for Topic "+exchg.getIn().getHeader("CamelInfinispanKey"));
		//get array if any
		String[] array=null;
		String values=null;
		log.info("json array exist? -"+exchg.getIn().getHeader("CamelInfinispanOperationResult")+"-");
		String incomingData=new String((byte[])exchg.getIn().getHeader("IncomingData"));
		log.info("incoming "+incomingData);
		if (exchg.getIn().getHeader("CamelInfinispanOperationResult")==null) {
			//new entry
			values="["+incomingData+"]";
			//array=new String[]{incomingData};		
//			exchg.getIn().setHeader("CamelInfinispanKey", exchg.getIn().getHeader("IncomingChannel"));
			exchg.getIn().setHeader("CamelInfinispanKey", exchg.getIn().getHeader("OutgoingChannel"));
			exchg.getIn().setHeader("CamelInfinispanValue", values);
		}else {		
			values=(String)exchg.getIn().getHeader("CamelInfinispanOperationResult");
			log.info("got value "+values);
			JsonParser parser=new JsonParser();
			JsonArray o=parser.parse(values).getAsJsonArray();
			o.add(parser.parse(incomingData).getAsJsonObject());
			log.info("writing ... "+o);
			exchg.getIn().setHeader("CamelInfinispanValue", o.toString());
		}
		
	}

}
