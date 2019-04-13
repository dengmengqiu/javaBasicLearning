package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class testDynamicProxy {
	public static void main(String[] args) {
		//被代理类的对象
		RealSubject real = new RealSubject();
		//创建一个实现了InvocationHandler接口的类对象
		MyInvocationHandler handler = new MyInvocationHandler();
		//调用blind()方法，动态的返回一个同样实现了real所在类实现的接口subject代理类对象
		Object obj = handler.blind(real);
		Subject sub = (Subject)obj;//此时sub就是代理类的对象
		
//		Object reVal = 
		sub.action();//转到对InvocationHandler接口的实现类的invoke()方法的调用
	}
}

interface Subject{
	void action();
}

class RealSubject implements Subject{
	public void action() {
		System.out.println("我是被代理类，记得要执行我油~~");
	}
}

class MyInvocationHandler implements InvocationHandler{
	Object obj; //实现了接口的被代理类的对象的声明
	
	//给被代理的对象实例化
	//返回一个代理类的对象
	public Object blind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(), 
				this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object returnVal = method.invoke(obj, args);
		return returnVal;
	}
	
}