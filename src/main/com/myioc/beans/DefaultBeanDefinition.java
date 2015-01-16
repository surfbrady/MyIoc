package main.com.myioc.beans;

/*
 * save bean's attribute information
 * 
 */
public class DefaultBeanDefinition implements BeanDefinition{
	private String beanClass = null;
	
	private String beanName = null;

	private String scope = null;
	
	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@Override
	public boolean isLazyInit(){
		return true;
	}
	
}
