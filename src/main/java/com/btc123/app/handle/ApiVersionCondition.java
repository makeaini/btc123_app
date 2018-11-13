package com.btc123.app.handle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class ApiVersionCondition implements
		RequestCondition<ApiVersionCondition> {
	
	private static final Logger LOG= org.slf4j.LoggerFactory.getLogger(ApiVersionCondition.class);

	private final static Pattern VERSION_PREFIX_PATTERN = Pattern
			.compile("(v[0-9].[0-9])|(v[0-9])");


	private double apiVersion;


	public ApiVersionCondition(double apiVersion) {
		this.apiVersion = apiVersion;
		
	}

	public ApiVersionCondition combine(ApiVersionCondition other) {
		return new ApiVersionCondition(other.getApiVersion());
	}

	public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
		Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
		Boolean flag = m.find();
		if (flag) {
			String group = m.group();
			String v = group.substring(1, group.length());
			Double version = Double.parseDouble(v);
			if (version == 0) {
				if(version <= this.apiVersion){
					return this;
				}
			}
			if (version >= this.apiVersion) {
				return this;
			}
		}
		return null;
	}

	public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
		LOG.debug("other:{}",other.getApiVersion());
		LOG.debug("api version:{}",this.apiVersion);
		if(other.getApiVersion() <= this.apiVersion  ){
			return -1;
		}
		return 0;
	}
	
	public double getApiVersion() {
		return apiVersion;
	}

}