package designPattern2.proxy.dynamicProxy;


import designPattern2.proxy.dynamicProxy.Star;
/**
 * 真实角色  关注真正的业务逻辑，实现抽象角色，定义真实角色所要实现的业务逻辑
 */
public class RealStar implements Star {

	@Override
	public void bookTicket() {
		System.out.println("RealStar.bookTicket()");
	}

	@Override
	public void collectMoney() {
		System.out.println("RealStar.collectMoney()");
	}

	@Override
	public void confer() {
		System.out.println("RealStar.confer()");
	}

	@Override
	public void signContract() {
		System.out.println("RealStar.signContract()");
	}

	@Override
	public void sing() {
		System.out.println("RealStar(周杰伦本人).sing()");
	}
	
	
	
}
