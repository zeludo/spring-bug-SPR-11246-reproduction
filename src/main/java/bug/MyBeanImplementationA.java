package bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBeanImplementationA extends MyBean {

	@Autowired
	private FieldOnlyUsedOnImplementationA onlyOnImplementationA;

}
