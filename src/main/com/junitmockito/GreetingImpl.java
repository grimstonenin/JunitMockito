package com.junitmockito;

public class GreetingImpl implements Greeting{

   @Override
    public String greeting(String name) {
       if(name == null || name.length() == 0){
           throw new IllegalArgumentException("Empty name");
       }

        return "Hello " + name;
    }
}
