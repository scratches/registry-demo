package com.example.demo;

import org.springframework.beans.factory.aot.BeanRegistrationExcludeFilter;
import org.springframework.beans.factory.support.RegisteredBean;

public class ExcludeFilter implements BeanRegistrationExcludeFilter {

	static final String IGNORE_ME = "ignore.me";

	@Override
	public boolean isExcludedFromAotProcessing(RegisteredBean registeredBean) {
		if (registeredBean.getMergedBeanDefinition().hasAttribute(IGNORE_ME)) {
			return true;
		}
		return false;
	}

}
