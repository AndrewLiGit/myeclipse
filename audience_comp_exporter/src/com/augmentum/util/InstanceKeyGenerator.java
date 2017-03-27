package com.augmentum.util;

public class InstanceKeyGenerator {

    public static final String KEY = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getInstaceId() {
        String instanceId = "";
        
        for (int i = 0; i < 8; i++) {
            int pos = (int) Math.floor(Math.random() * KEY.length());
            instanceId += KEY.substring(pos, pos + 1);
        }
        
        return instanceId;
    }
    
    public static void main(String[] args) {
        System.out.println(getInstaceId());
    }
}
