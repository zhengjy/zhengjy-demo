package com.zhengjy.test.java8.t4;

/**
 * Created by Jiyang.Zheng on 2018/10/16 14:08.
 */
public class Validator {
    private final ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy){
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String s){
        return validationStrategy.execute(s);
    }

    public static void main(String[] args) {
        Validator validator =new Validator(new IsNumeric());
        boolean b1 = validator.validate("aaaa");

        Validator v2 = new Validator(new IsAllLowerCase());
        boolean b2 = v2.validate("bbbb");


        Validator v3 = new Validator((String s) -> s==null);

        boolean b3 = v3.validate("aaaa");

        Validator v4 = new Validator((String s) -> s.matches("\\d+"));
        boolean b4 = v4.validate("bbbb");



    }
}
