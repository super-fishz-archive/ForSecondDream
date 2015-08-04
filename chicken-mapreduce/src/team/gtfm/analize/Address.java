package team.gtfm.analize;

import java.util.StringTokenizer;

public class Address {
	
	private String firstAddress;
	private String secondAddress;
	private String thirdAddress;
	private String fullAddress;
	
	public Address(String fullAddress){
		this.fullAddress = fullAddress;
		
		String needAddress = evalAddress(fullAddress);
		
		StringBuilder builder = new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(needAddress);
		builder.append(tokenizer.nextToken());
		this.firstAddress = builder.toString();
		builder.append(tokenizer.nextToken());
		this.secondAddress = builder.toString();
		builder.append(tokenizer.nextToken());
		this.thirdAddress = builder.toString();
	}
	
	protected String evalAddress(String inAddr){
		StringTokenizer tokenizer = new StringTokenizer(inAddr);
		StringBuilder builder = new StringBuilder();
		
		int i=0;
		while(i < 3 && tokenizer.hasMoreTokens()){
			builder.append(tokenizer.nextToken() + " ");
		}
		
		return builder.toString();
	}

	public String getFirstAddress() {
		return firstAddress;
	}

	public String getSecondAddress() {
		return secondAddress;
	}

	public String getThirdAddress() {
		return thirdAddress;
	}

	public String getFullAddress() {
		return fullAddress;
	}
	
}
