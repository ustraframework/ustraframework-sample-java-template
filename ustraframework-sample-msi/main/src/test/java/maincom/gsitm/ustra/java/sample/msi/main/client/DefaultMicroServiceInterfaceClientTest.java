package maincom.gsitm.ustra.java.sample.msi.main.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.gsitm.ustra.java.sample.msi.main.client.DefaultMicroServiceInterfaceClient;
import com.gsitm.ustra.java.sample.msi.main.interfaces.GetTestParam;
import com.gsitm.ustra.java.sample.msi.main.interfaces.SampleMSI;
import lombok.Getter;
import lombok.Setter;

@Component
public class DefaultMicroServiceInterfaceClientTest {

	@Test
	public void test() throws NoSuchMethodException, SecurityException {
		try {
			final GetTestParam getTestParam = new GetTestParam();
			getTestParam.setPageNo(1);

			final Method method = SampleMSI.class.getMethod("getTest", new Class<?>[] {GetTestParam.class});
			final Object[] args = new Object[] {getTestParam};

			final Map<String, String> baseUriMap = new HashMap<>();
			baseUriMap.put("api1", "http://localhost:10011/api/fo/od/proxy");
			final DefaultMicroServiceInterfaceClient client = new DefaultMicroServiceInterfaceClient(baseUriMap);
			final List<String> result = (List<String>) client.request(method, args);
			List<String> asList = Arrays.asList("test1", "test2");
			assertEquals(asList, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


