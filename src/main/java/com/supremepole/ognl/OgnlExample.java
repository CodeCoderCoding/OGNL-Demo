package com.supremepole.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.HashMap;
import java.util.Map;

public class OgnlExample {
    public static void main(String[] args) throws OgnlException {
        // 定义两个整数变量
        int a = 10;
        int b = 20;
        // 定义一个 OGNL 表达式，比较两个变量的值
        String expression = "a > b";
        // 解析 OGNL 表达式
        Object parsedExpression = Ognl.parseExpression(expression);
        // 创建一个包含 a 和 b 变量的上下文
        OgnlContext context = new OgnlContext();
        context.put("a", a);
        context.put("b", b);
        // 使用 OGNL 表达式获取比较结果
        boolean result = (boolean) Ognl.getValue(parsedExpression, context, context);
        System.out.println("Result: " + result); // Output: Result: false

        // 等价于下面的方式
        Map<String, Object> map=new HashMap<>();
        map.put("a", 1L);
        map.put("b", 2L);
        Object expression1 = Ognl.parseExpression("a>b");
        Object result1 = Ognl.getValue(expression1, map);
        System.out.println(result1);
    }
}
