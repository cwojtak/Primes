package com.connorwojtak.main;

import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadPrimeList {
	
	private long[] primesList;
	private int index;
	private int checks;
	private boolean go;
	private long lastNumber;
	private ReentrantLock lock;
	private long locked;
	
	public MultiThreadPrimeList(int size) {
		lock = new ReentrantLock();
		locked = 0;
		checks = 0;
		go = false;
		primesList = new long[size];
		primesList[0] = 2;
		primesList[1] = 3;
		primesList[2] = 5;
		primesList[3] = 7;
		primesList[4] = 11;
		primesList[5] = 13;
		primesList[6] = 17;
		primesList[7] = 19;
		primesList[8] = 23;
		primesList[9] = 29;
		System.out.println(2 + " =============== " + (primesList.length - 1));
		System.out.println(3 + " =============== " + (primesList.length - 2));
		System.out.println(5 + " =============== " + (primesList.length - 3));
		System.out.println(7 + " =============== " + (primesList.length - 4));
		System.out.println(11 + " =============== " + (primesList.length - 5));
		System.out.println(13 + " =============== " + (primesList.length - 6));
		System.out.println(17 + " =============== " + (primesList.length - 7));
		System.out.println(19 + " =============== " + (primesList.length - 8));
		System.out.println(23 + " =============== " + (primesList.length - 9));
		System.out.println(29 + " =============== " + (primesList.length - 10));
		index = 10;
	}
	
	public void addValue(long val) {
		while(lock.isLocked() && locked != Thread.currentThread().getId());
		lock.lock();
		locked = Thread.currentThread().getId();
		primesList[index] = val;
		index++;
		lastNumber = val;
		System.out.println(val + " =============== " + (primesList.length - index));
		lock.unlock();
	}
	
	public synchronized long getValue(int i) {
		return primesList[i];
	}
	
	public int getSize() {
		return primesList.length;
	}
	
	public synchronized int getIndex() {
		return index;
	}
	
	public synchronized long[] getPrimesList() {
		return primesList;
	}
	
	public synchronized void checkIn() {
		checks++;
	}
	
	public synchronized boolean go() {
		return go;
	}
	
	public synchronized int getChecks() {
		return checks;
	}
	
	public synchronized void setGo() {
		go = true;
	}
	
	public synchronized long getLastNumber() {
		return lastNumber;
	}
}
