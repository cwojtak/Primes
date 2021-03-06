package com.connorwojtak.main;

import java.util.*;

class Primes {
	
	private static long startTime;
	private static int ent;
	
	public static void main(String[] args) throws InterruptedException {
		try {
			System.out.println("Enter how many prime numbers would you like to compute: ");
			Scanner s = new Scanner(System.in);
			ent = s.nextInt();
			s.close();
			startTime = System.nanoTime();
			MultiThreadPrimeList z = threaded();
			Thread.sleep(3000);
			startTime = System.nanoTime();
			unthreaded(z);
		} catch(InputMismatchException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static MultiThreadPrimeList threaded() {
		if(ent < 10) System.exit(1);
		MultiThreadPrimeList pl = new MultiThreadPrimeList(ent);
		Runnable r1 = new PrimesThread(pl, 31);
		Runnable r2 = new PrimesThread(pl, 37);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		while(pl.getChecks() != 2);
		try {
			Thread.sleep(2);
			pl.setGo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return pl;
	}
	
	public static void unthreaded(MultiThreadPrimeList a) {
		int[] ans = primes(ent);
		Primes.finish();
		if(a != null) {
			boolean matches = true;
			long[] list = a.getPrimesList();
			for(int i = 0; i < ans.length; i++) {
				boolean good = false;
				for(int j = 0; j < list.length; j++) {
					if(ans[i] == list[j]) {
						list[j] = -1;
						ans[i] = -1;
						good = true;
						break;
					}
				}
				if(good == false) {
					matches = false;
					break;
				}
			}
			System.out.println(matches);
			/*for(int z = 0; z < list.length; z++) {
				if(list[z] == -1) continue;
				System.out.println(list[z]);
			}
			System.out.println();
			for(int z = 0; z < list.length; z++) {
				if(ans[z] == -1) continue;
				System.out.println(ans[z]);
			}*/
		}
	}
	
	public static void finish() {
		long finalTime = System.nanoTime();
		System.out.println("Time to calculate " + ent + " trials: " + (double)(finalTime - startTime) / 1000000000 + " seconds.");	
		//System.exit(0);
	}
	
	public static int[] primes(Integer a){
		int[] ret = new int[a];
		if(a < 1) return null;
		ret[0] = 2;
		System.out.println(2 + "\t\t" + "\t\t===============\t\t" + (a-1));
		int c = 3;
		int b = 1;
		while(b < a){
			boolean prime = true;
			double pow = Math.sqrt(c);
			for(int i = 0; i < b; i++){
				if((double)ret[i] > pow) break;
				if(c%ret[i] == 0){
					prime = false;
					break;
				}
			}
			if(prime){ 
				ret[b] = c; 
				System.out.println(c + "\t\t===============\t\t" + (a-b));
				b++;
			}
			c += 2;
		}
		return ret;
	  }
}