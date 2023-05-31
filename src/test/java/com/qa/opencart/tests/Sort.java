package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> expectedHeaderList1 =Arrays.asList("My Account","My Orders","Zebra","Chimpanzee","My Affiliate Account","Newsletter");
		System.out.println("Expected Header List1 is :");
		System.out.println(expectedHeaderList1);
		
		Collections.sort(expectedHeaderList1);
		System.out.println("Expected Header List1 After Sorting is "+expectedHeaderList1);

	}

}
