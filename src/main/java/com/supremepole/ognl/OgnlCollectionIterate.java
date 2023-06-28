package com.supremepole.ognl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ognl.Ognl;
import ognl.OgnlException;

public class OgnlCollectionIterate {
    public static void main(String[] args) throws OgnlException {
        // 随机生成一个集合
        Collection<Object> collection = new ArrayList<>();
        for (int i = 0; i < (int) (Math.random() * 10); i++) {
            if (Math.random() < 0.5) {
                collection.add((int) (Math.random() * 100));
            } else {
                collection.add("string" + (int) (Math.random() * 100));
            }
        }

        // 使用 OGNL 遍历集合中的元素
        Iterator<Object> it = collection.iterator();
        for (int i = 0; i < (int) Ognl.getValue("size()", collection); i++) {
            Object value = Ognl.getValue("next()", it);
            System.out.println(value);
        }
    }
}
