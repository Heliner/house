package com.mooc.house.userservice1.common;


import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class NewRuleConfig {
	
	@Autowired
	private IClientConfig ribbonClientConfig;
	
	@Bean
	public IPing ribbonPing(IClientConfig config){
		return new PingUrl(false,"/health");
	}
	
	@Bean
	public IRule ribbonRule(IClientConfig config){
		return new AvailabilityFilteringRule();
	}

}
