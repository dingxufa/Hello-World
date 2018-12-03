package designPattern2.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

public class Client {
	public static void main(String[] args) {


        Star realStar = new RealStar();
		StarHandler handler = new StarHandler(realStar);
		
		Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{Star.class}, handler);  //new Class[]{RealStar.class}不对
		
		proxy.sing();

	}
	
}