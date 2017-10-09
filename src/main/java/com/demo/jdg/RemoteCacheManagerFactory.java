package com.demo.jdg;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class RemoteCacheManagerFactory {

	private RemoteCacheManager cacheManager;
   private String[] hosts; 

   public RemoteCacheManagerFactory(String hosts) { 
      if( hosts == null ) 
         throw new IllegalArgumentException("Hosts is null"); 
      this.hosts = hosts.split(";"); 
   } 	
   
   public RemoteCacheManager getRemoteCacheManager() { 
	      if(cacheManager != null) 
	         return cacheManager; 

	      // Create the RemoteCacheManager 
	      ConfigurationBuilder configurationBuilder = new ConfigurationBuilder(); 

	      for (int i = 0; i < hosts.length; i++) { 
	         String host = hosts[i]; 
	         String[] hostConfig = host.split(":"); 
	         configurationBuilder 
	            .addServer() 
	               .host(hostConfig[0]) 
	               .port(Integer.parseInt(hostConfig[1])); 
	      } 

	      cacheManager = new RemoteCacheManager(configurationBuilder.build()); 
	      return cacheManager; 
	   }    
}
