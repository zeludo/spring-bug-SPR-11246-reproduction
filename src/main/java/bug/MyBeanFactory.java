package bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactory {

	@Autowired
	MyBeanImplementationA myBeanImplementationA;
	@Autowired
	MyBeanImplementationB myBeanImplementationB;

	int i = 0;

	public MyBean myBeanFactoryMethod() {
		return i++ % 2 == 0 ? myBeanImplementationA : myBeanImplementationB;
	}

}
