package org.finastra.hackathon.yourule.loader;

public class WhereThenData 
{
	String sWhere;
	String sThen;
	
	public WhereThenData(String sWhere, String sThen) 
	{
		super();
		this.sWhere = sWhere;
		this.sThen = sThen;
	}

	public String getsWhere() {
		return sWhere;
	}

	public void setsWhere(String sWhere) {
		this.sWhere = sWhere;
	}

	public String getsThen() {
		return sThen;
	}

	public void setsThen(String sThen) {
		this.sThen = sThen;
	}

}
