package com.marin;

class item{
	private String itemName;
	private int itemQuantity;
	
	public item(String n, int q){
		itemName = cleanItem(n);
		itemQuantity = q;
	}

	public item(String i, String q){
		itemName = cleanItem(i);
		itemQuantity = cleanQuantity(q);
	}

	private String cleanItem(String i){
		if (i.length()==0){return "Invalid input";}
		char c = i.charAt(0);
		c = Character.toUpperCase(c);
		i = c + i.substring(1);
		for	(int x = 1; x<i.length();x++){
			c = i.charAt(x-1);
			if (c == ' '||c=='-'||c=='\''){
				c = i.charAt(x);
				c = Character.toUpperCase(c);
				i = i.substring(0, x) + c+  i.substring(x+1);
			}
		}
		return i;
	}

	private int cleanQuantity(String q){
		for (int x =0; x<q.length();x++){
            char c = q.charAt(x);
            if (c<48 || c>57){
                return 0;
            }
        }
        return  (Integer.parseInt(q));

	}

	public String getIname(){return itemName;}
	public int getIquantity(){return itemQuantity;}
	
	public String toString(){
		return (itemName +" " + itemQuantity);
	}
}