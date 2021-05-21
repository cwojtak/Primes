package com.connorwojtak.main;

public class PrimesThread implements Runnable {
	
	private MultiThreadPrimeList list;
	private int start;
	
	public PrimesThread(Object a, Object b) {
		try {
			list = (MultiThreadPrimeList)a;
			start = (int)b;
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	@Override
	public void run() {
		try {
		  list.checkIn();
		  while(!list.go());
		  long c = start;
		  int a = list.getSize();
		  int b = 1;
		  int d = a / 2;
		  int privateb = 4;
		  while(b < a && privateb < d){
			  b = list.getIndex();
			  boolean prime = true;
			  double pow = Math.sqrt(c);
			  for(int i = 0; i < b; i++){
				  long q = list.getValue(i);
				  if((double)q > pow) break;
				  if(c%q == 0){
					  prime = false;
					  break;
				  }
			  }
			  if(prime){ 
				  //System.out.println(start + " " + b);
				  list.addValue(c);
				  privateb++;
			  }
			  c += 4;
		  }
		  if(list.getIndex() == a) {
			  Primes.finish();
		  }
		} catch(Exception e) {
		}
	}
}
