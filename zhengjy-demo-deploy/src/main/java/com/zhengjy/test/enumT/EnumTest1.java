package com.zhengjy.test.enumT;

public enum EnumTest1 {
	SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");
	
	private String abbreviation;
	
	public String getAbbreviation(){
		return abbreviation;
	}
	
	private EnumTest1(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	public static void main(String[] args) {
		EnumTest1 e = Enum.valueOf(EnumTest1.class, "SMALL");
		System.out.println(e.getAbbreviation());
		
		EnumTest1[] e2 = EnumTest1.values();
		System.out.println(e2.length);
		
		
		System.out.println(EnumTest1.LARGE.ordinal());//返回enum声明中枚举的位置
		
		System.out.println(e2.toString());
	}
	
}
