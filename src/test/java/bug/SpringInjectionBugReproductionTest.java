package bug;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ContextConfiguration("applicationContext-Test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { WebContextTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class })
public class SpringInjectionBugReproductionTest {

	@Autowired
	private MyBean myBean;

	@Test
	public void test1() {
		assertNotNull(myBean);
		// Needs aspectj weaving to work
		assertTrue(myBean instanceof MyBeanImplementationA);
	}

	@Test
	public void test2() {
		assertNotNull(myBean);
		// Needs aspectj weaving to work
		assertTrue(myBean instanceof MyBeanImplementationB);
	}

}
