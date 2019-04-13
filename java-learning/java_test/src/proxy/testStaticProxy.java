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
		System.out.println("Nike��������һ���·�");
	}
}

//������
class ProxyFactory implements ClothFactory{
	ClothFactory cf;
	
	//������������ ����һ�������������
	public ProxyFactory(ClothFactory cf) {
		super();
		this.cf = cf;
	}

	public void productCloth() {
		System.out.println("�����࿪ʼִ��");
		cf.productCloth();
	}
}
