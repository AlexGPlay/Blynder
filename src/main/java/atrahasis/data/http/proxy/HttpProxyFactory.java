package atrahasis.data.http.proxy;

import atrahasis.data.base.DynamicProxyFactory;

/**
 * 
 * The implementation of the DynamicProxyFactory for the HttpRepositories.
 * It just inserts the HttpRepositoryProxy and the asked interface into de parent
 * implementation.
 *
 */
public class HttpProxyFactory extends DynamicProxyFactory{

    public HttpProxyFactory(Class<?> clazz) {
		super(clazz, new HttpRepositoryProxy());
	}
    
}
