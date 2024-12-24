package org.zerock.myapp.generic;

//인터페이스 : 객체(대용량 스토리지)의 규격을 정한다!
public interface IStorage<T> {
	
	//규격은 바로 인터페이스의 6가지 멤버중에, 오로지 '추상 메소드' 이다!
	public abstract void add(T item, int index);
	public abstract T get(int index);
	
	
	
}// end interface
