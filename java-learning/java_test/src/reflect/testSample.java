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
		
		//属性
		
		//1. getFields():只能获取到类中及其父类声明为public的属性
		Field[] f = clazz.getFields();
		for(int i = 0; i < f.length; i++) {
			System.out.println(f[i]);
		}
		System.out.println();
		//2. getDeclaredFields：能获取到类本身声明的全部属性
		Field[] f1 = clazz.getDeclaredFields();
		for(Field field : f1) {
	
			//3.获取每个属性的权限修饰符
			int i= field.getModifiers();
			String str1 = Modifier.toString(i);
			System.out.println(str1);
			
			System.out.println();
			
			//4. 获取属性的类型
			Class type = field.getType();
			System.out.println(type.getName());
			
			System.out.println();
			
			//5. 获取属性名
			System.out.println(field.getName());
			
		}
		
		System.out.println();
		
		//方法
		
		//getMethods()：获取运行时类及其父类所有声明为public的方法
		Method[] m1 = clazz.getMethods();
		for(Method m : m1) {
			System.out.println(m.getName());
		}
		
		System.out.println();
		
		//getDeclaredMethods()：获取运行时类本身所有方法
		Method[] m2 = clazz.getDeclaredMethods();
		for(Method m : m2) {
			System.out.println(m.getName());
		}
		
		System.out.println();
		
		for(Method m : m2) {
			//注解
			Annotation[] ann = m.getAnnotations();
			for(Annotation a : ann)
				System.out.println(a);
			
			//权限修饰符
			String str = Modifier.toString(m.getModifiers());
			System.out.println(str);
			
			//返回值类型
			Class returnType = m.getReturnType();
			System.out.println(returnType.getName());
			
			//方法名
			System.out.println(m.getName());
			
			//形参列表
			System.out.print("(");
			Class[] params = m.getParameterTypes();
			for(int i = 0; i < params.length; i++) {
				System.out.print(params[i].getName() + "args-" + i + " ");
			}
			System.out.print(")");
			
			System.out.println();
			
			//异常
			Class[] exps = m.getExceptionTypes();
			for(int i = 0; i < exps.length; i++) {
				System.out.println(exps[i].getName());
			}
			
			
			//构造器
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
			m4.invoke(p, "小花");
			System.out.println(p);
		}
	
	}
}
