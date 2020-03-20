package atrahasis.data.http.proxy;

import atrahasis.data.base.DynamicProxyFactory;

public class HttpProxyFactory extends DynamicProxyFactory{

    public HttpProxyFactory(Class<?> clazz) {
		super(clazz, new HttpRepositoryProxy());
	}
    
}
