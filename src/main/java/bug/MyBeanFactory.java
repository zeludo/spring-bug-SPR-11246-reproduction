package bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactory {

	@Autowired
	private MyBeanImplementationA myBeanImplementationA;
	@Autowired
	private MyBeanImplementationB myBeanImplementationB;

	int i = 0;

	public MyBean myBeanFactoryMethod() {
		// Simulates a request context factory
		return i++ % 2 == 0 ? myBeanImplementationA : myBeanImplementationB;
	}

}
