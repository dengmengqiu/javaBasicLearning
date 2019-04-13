package proxy;

public class testStaticProxy {
	public static void main(String[] args) {
		NikeClothFactory nike = new NikeClothFactory();
		ProxyFactory proxy = new ProxyFactory(nike);
		proxy.productCloth();	
	}
}

interface ClothFactory{
	void productCloth();
}

class NikeClothFactory implements ClothFactory{
	public void productCloth() {
		System.out.println("Nike工厂生产一批衣服");
	}
}

//代理类
class ProxyFactory implements ClothFactory{
	ClothFactory cf;
	
	//创建代理类是 传入一个被代理类对象
	public ProxyFactory(ClothFactory cf) {
		super();
		this.cf = cf;
	}

	public void productCloth() {
		System.out.println("代理类开始执行");
		cf.productCloth();
	}
}
