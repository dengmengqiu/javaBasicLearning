package reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;

public class testSample {
	public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException{
		Class clazz = Person.class;
		
		//����
		
		//1. getFields():ֻ�ܻ�ȡ�����м��丸������Ϊpublic������
		Field[] f = clazz.getFields();
		for(int i = 0; i < f.length; i++) {
			System.out.println(f[i]);
		}
		System.out.println();
		//2. getDeclaredFields���ܻ�ȡ���౾��������ȫ������
		Field[] f1 = clazz.getDeclaredFields();
		for(Field field : f1) {
	
			//3.��ȡÿ�����Ե�Ȩ�����η�
			int i= field.getModifiers();
			String str1 = Modifier.toString(i);
			System.out.println(str1);
			
			System.out.println();
			
			//4. ��ȡ���Ե�����
			Class type = field.getType();
			System.out.println(type.getName());
			
			System.out.println();
			
			//5. ��ȡ������
			System.out.println(field.getName());
			
		}
		
		System.out.println();
		
		//����
		
		//getMethods()����ȡ����ʱ�༰�丸����������Ϊpublic�ķ���
		Method[] m1 = clazz.getMethods();
		for(Method m : m1) {
			System.out.println(m.getName());
		}
		
		System.out.println();
		
		//getDeclaredMethods()����ȡ����ʱ�౾�����з���
		Method[] m2 = clazz.getDeclaredMethods();
		for(Method m : m2) {
			System.out.println(m.getName());
		}
		
		System.out.println();
		
		for(Method m : m2) {
			//ע��
			Annotation[] ann = m.getAnnotations();
			for(Annotation a : ann)
				System.out.println(a);
			
			//Ȩ�����η�
			String str = Modifier.toString(m.getModifiers());
			System.out.println(str);
			
			//����ֵ����
			Class returnType = m.getReturnType();
			System.out.println(returnType.getName());
			
			//������
			System.out.println(m.getName());
			
			//�β��б�
			System.out.print("(");
			Class[] params = m.getParameterTypes();
			for(int i = 0; i < params.length; i++) {
				System.out.print(params[i].getName() + "args-" + i + " ");
			}
			System.out.print(")");
			
			System.out.println();
			
			//�쳣
			Class[] exps = m.getExceptionTypes();
			for(int i = 0; i < exps.length; i++) {
				System.out.println(exps[i].getName());
			}
			
			
			//������
			Constructor[] cons = clazz.getDeclaredConstructors();
			for(Constructor con : cons) {
				System.out.println(con);
			}
			
			Field name = clazz.getField("name");
			Person p = (Person)clazz.newInstance();
			System.out.println(p);
			name.set(p, "Jerry");
			
			
			Method m3 = clazz.getMethod("toString");
			Object returnval = m3.invoke(p);
			
			Method m4 = clazz.getMethod("setName", String.class);
			m4.invoke(p, "С��");
			System.out.println(p);
		}
	
	}
}
