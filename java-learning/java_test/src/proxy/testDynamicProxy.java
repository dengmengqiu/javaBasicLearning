package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class testDynamicProxy {
	public static void main(String[] args) {
		//��������Ķ���
		RealSubject real = new RealSubject();
		//����һ��ʵ����InvocationHandler�ӿڵ������
		MyInvocationHandler handler = new MyInvocationHandler();
		//����blind()��������̬�ķ���һ��ͬ��ʵ����real������ʵ�ֵĽӿ�subject���������
		Object obj = handler.blind(real);
		Subject sub = (Subject)obj;//��ʱsub���Ǵ�����Ķ���
		
//		Object reVal = 
		sub.action();//ת����InvocationHandler�ӿڵ�ʵ�����invoke()�����ĵ���
	}
}

interface Subject{
	void action();
}

class RealSubject implements Subject{
	public void action() {
		System.out.println("���Ǳ������࣬�ǵ�Ҫִ������~~");
	}
}

class MyInvocationHandler implements InvocationHandler{
	Object obj; //ʵ���˽ӿڵı�������Ķ��������
	
	//��������Ķ���ʵ����
	//����һ��������Ķ���
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