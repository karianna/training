package com.jclarity.advjava.ex5;

public class NotBrokenMain {

	public static void main(String[] args) {
		NotBrokenMain nbm = new NotBrokenMain();
		nbm.run();
	}

	private void run() {
		Broken b = new Broken();
		b.setOne(1);
		System.out.println(b.getOne());
		System.out.println(b.incAndRet());
		b.spinAndPrint(4);
	}
	
}
