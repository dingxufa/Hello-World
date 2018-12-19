package designPattern2.proxy.staticProxy;

/**
 * 代理角色，实现抽象角色，持有真实角色的引用，可以附加自己的操作
 */
public class ProxyStar implements Star {
	
	private Star star;
	
	public ProxyStar(Star star) {
		super();
		this.star = star;
	}

	@Override
	public void bookTicket() {
		System.out.println("ProxyStar.bookTicket()");
	}

	@Override
	public void collectMoney() {
		System.out.println("ProxyStar.collectMoney()");
	}

	@Override
	public void confer() {
		System.out.println("ProxyStar.confer()");
	}

	@Override
	public void signContract() {
		System.out.println("ProxyStar.signContract()");
	}

	@Override
	public void sing() {
		star.sing();
	}

}