package atrahasis.testClasses;

import atrahasis.data.annotations.Repository;
import atrahasis.data.http.HttpRepository;
import atrahasis.data.http.annotations.Request;
import atrahasis.data.http.request.components.HttpResponse;

@Repository
public interface RepositoryTestClass1 extends HttpRepository{

	@Request(url="https://jsonplaceholder.typicode.com/posts")
	public HttpResponse getResponse();
	
}
