package com.newsee.generator;

public class Test {

	public static void main(String[] args){
		String str = "12::3";
		String[] list = str.split("\\:");
		int index = str.indexOf(":");
		for(int i=0; i< list.length; i++){
			System.out.println(list[i]);
			
		}
		System.out.println("=====index========"+index+"==============");
		String str1 = "4,,5,,6";
		String[] list1 = str1.split(",");
		for(int i=0; i< list1.length; i++){
			System.out.println(list1[i]);
			
		}
		
	}
}
