package it.polito.tdp.borders.model;

public class Border {
	
	Country c1;
	Country c2;
	
	public Border(Country c1, Country c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public String toString() {
		return "Border: " + c1.toString() + " " + c2.toString();
	}

	public Country getC1() {
		return c1;
	}

	public void setC1(Country c1) {
		this.c1 = c1;
	}

	public Country getC2() {
		return c2;
	}

	public void setC2(Country c2) {
		this.c2 = c2;
	}
/*
	@Override
	public boolean equals(Object obj) {
		
		Border altro = (Border)obj;
		
		if(c1.equals(altro.getC1()) && c2.equals(altro.getC2()))
			return true;
		
		if(c1.equals(altro.getC2()) && c2.equals(altro.getC1()))
			return true;
		
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c1 == null) ? 0 : c1.hashCode());
		result = prime * result + ((c2 == null) ? 0 : c2.hashCode());
		return result;
	}
*/

}
