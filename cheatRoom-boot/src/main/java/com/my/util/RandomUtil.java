package com.my.util;

import java.util.UUID;

public class RandomUtil {
    public static String createOrderId() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {
            hashCodeV = - hashCodeV;
        }
        return String.format("%015d", hashCodeV);
    }
}
