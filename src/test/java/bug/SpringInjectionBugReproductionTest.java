package bug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ContextConfiguration("applicationContext-Test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { WebContextTestExecutionListener.class, DependencyInjectionTestExecutionListener.class })
public class SpringInjectionBugReproductionTest {

	@Autowired
	private MyBean myBean;

	@Test
	public void reproduceBug() {
		System.out.println(myBean);
	}

	@Test
	public void reproduceBug2() {
		System.out.println(myBean);
		// Fail with the following stack trace :
		
		//	org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'scopedTarget.myBean': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: bug.FieldOnlyUsedOnImplementationA bug.MyBeanImplementationA.onlyOnImplementationA; nested exception is java.lang.IllegalArgumentException: Can not set bug.FieldOnlyUsedOnImplementationA field bug.MyBeanImplementationA.onlyOnImplementationA to bug.MyBeanImplementationB
		//	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:292)
		//	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1185)
		//	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:537)
		//	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:475)
		//	at org.springframework.beans.factory.support.AbstractBeanFactory$2.getObject(AbstractBeanFactory.java:343)
		//	at org.springframework.context.support.SimpleThreadScope.get(SimpleThreadScope.java:63)
		//	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:338)
		//	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:195)
		//	at org.springframework.aop.target.SimpleBeanTargetSource.getTarget(SimpleBeanTargetSource.java:35)
		//	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.getTarget(CglibAopProxy.java:678)
		//	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:629)
		//	at bug.MyBean$$EnhancerByCGLIB$$84990f00.toString(<generated>)
		//	at java.lang.String.valueOf(String.java:2854)
		//	at java.io.PrintStream.println(PrintStream.java:821)
		//	at bug.SpringInjectionBugReproductionTest.reproduceBug2(SpringInjectionBugReproductionTest.java:26)
		//	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		//	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
		//	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		//	at java.lang.reflect.Method.invoke(Method.java:606)
		//	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
		//	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		//	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
		//	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		//	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:74)
		//	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:83)
		//	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72)
		//	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:232)
		//	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:89)
		//	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
		//	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
		//	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
		//	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
		//	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
		//	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
		//	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:71)
		//	at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
		//	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:175)
		//	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)
		//	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		//	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
		//	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
		//	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
		//	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
		//Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: bug.FieldOnlyUsedOnImplementationA bug.MyBeanImplementationA.onlyOnImplementationA; nested exception is java.lang.IllegalArgumentException: Can not set bug.FieldOnlyUsedOnImplementationA field bug.MyBeanImplementationA.onlyOnImplementationA to bug.MyBeanImplementationB
		//	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:508)
		//	at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:87)
		//	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:289)
		//	... 42 more
		//Caused by: java.lang.IllegalArgumentException: Can not set bug.FieldOnlyUsedOnImplementationA field bug.MyBeanImplementationA.onlyOnImplementationA to bug.MyBeanImplementationB
		//	at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:164)
		//	at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:168)
		//	at sun.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:55)
		//	at sun.reflect.UnsafeObjectFieldAccessorImpl.set(UnsafeObjectFieldAccessorImpl.java:75)
		//	at java.lang.reflect.Field.set(Field.java:741)
		//	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:504)
		//	... 44 more


	}

}
