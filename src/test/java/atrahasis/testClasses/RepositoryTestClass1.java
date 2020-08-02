package atrahasis.testClasses;

import org.blynder.data.annotations.Repository;
import org.blynder.data.http.HttpRepository;
import org.blynder.data.http.annotations.Request;
import org.blynder.data.http.request.components.HttpResponse;

@Repository
public interface RepositoryTestClass1 extends HttpRepository{

	@Request(url="https://jsonplaceholder.typicode.com/posts")
	public HttpResponse getResponse();
	
}
