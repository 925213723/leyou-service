package com.leyou.service.utils;

import java.util.HashMap;
import java.util.Iterator;
public class AZ {
    /**
     * 利用HashMap
     * @param args
     */
    public static void main(String[] args) {
        String key = getKey(703);
        System.out.println("哈哈"+key);
    }

    public static String getKey(int index){
        String colCode = "";
        char key='A';
        int loop = index / 26;
        if(loop>0){
            colCode += getKey(loop-1);
        }
        key = (char) (key+index%26);
        colCode += key;
        return colCode;
    }
}