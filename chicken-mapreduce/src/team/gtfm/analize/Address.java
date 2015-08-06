package team.gtfm.analize;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Address {
	
	private List<String> addrs = new ArrayList<>();
	private String fullAddress;
	
	public Address(String fullAddress){
		this.fullAddress = fullAddress;
		if(fullAddress == null || fullAddress.equals(""))
			return;
		
		String needAddress = evalAddress(fullAddress);
		
		StringTokenizer tokenizer = new StringTokenizer(needAddress);
		
		while(tokenizer.hasMoreTokens()){
			addrs.add(tokenizer.nextToken());
		}

	}
	
	protected String evalAddress(String inAddr){
		StringTokenizer tokenizer = new StringTokenizer(inAddr);
		StringBuilder builder = new StringBuilder();
		
		int i=0;
		while(i < 3 && tokenizer.hasMoreTokens()){
			builder.append(tokenizer.nextToken() + " ");
			i++;
		}
		
		return builder.toString();
	}

	public String getAddress(int index){
		try{
			return this.addrs.get(index);
		}catch(Exception e){
			throw new RuntimeException("Error Address. " + index);
		}
	}
	
	public int length(){
		return addrs.size();
	}
	
	public String getFullAddress() {
		return fullAddress;
	}
	
}
