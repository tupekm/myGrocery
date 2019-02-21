package com.marin;

class item{
	private String itemName;
	private String itemQuantity;
	
	public item(String n, String q){
		itemName = n;
		itemQuantity = q;
	}
	public String getIname(){return itemName;}
	public String getIquantity(){return itemQuantity;}
	
	public String toString(){
		return (itemName +" " + itemQuantity);
	}
}