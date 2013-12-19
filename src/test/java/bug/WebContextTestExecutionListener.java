package bug;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class WebContextTestExecutionListener extends AbstractTestExecutionListener {

	@Override
	public void prepareTestInstance(final TestContext testContext) throws Exception {
		if (testContext.getApplicationContext() instanceof GenericApplicationContext) {
			GenericApplicationContext context = (GenericApplicationContext) testContext.getApplicationContext();
			ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
			beanFactory.registerScope("request", new SimpleThreadScope());
			beanFactory.registerScope("session", new SimpleThreadScope());
		}
	}
}