package team.gtfm.server.bean;

import java.util.List;

public class LocalChicken {
	private List<SummaryChicken> summaryChickens;
	private String address;
	private int openNum;
	private int closeNum;
	

	public List<SummaryChicken> getSummaryChickens() {
		return summaryChickens;
	}
	public void setSummaryChickens(List<SummaryChicken> summaryChickens) {
		this.summaryChickens = summaryChickens;
	}
	public int getOpenNum() {
		return openNum;
	}
	public void setOpenNum(int openNum) {
		this.openNum = openNum;
	}
	public int getCloseNum() {
		return closeNum;
	}
	public void setCloseNum(int closeNum) {
		this.closeNum = closeNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
