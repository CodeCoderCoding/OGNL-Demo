package com.supremepole.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OgnlSimpleDemo {

    public static void main(String[] args) throws OgnlException {
        // 准备OGNL的上下文
        // 1、准备Root，将一个User对象放入到Root map中
        User rootUser = new User("张三", 18);
        // 2、准备context，将User对象以键值对的形式存入Context map中
        Map<String, User> context = new HashMap<String, User>();
        context.put("user1", new User("jack", 20));
        context.put("user2", new User("rose", 18));
        OgnlContext ognlContext = new OgnlContext();
        ognlContext.setRoot(rootUser);
        ognlContext.setValues(context);

//        getRootContextData(ognlContext, context);
//        setRootContextData(ognlContext, context);
        addData(ognlContext);
    }

    // 获取root和上下文的数据
    public static void getRootContextData(OgnlContext ognlContext, Map<String, User> context) throws OgnlException {
        // 书写OGNL表达式，此表达式目的是取出rootUser中的name、age的值
        String name = (String) Ognl.getValue("name", ognlContext, ognlContext.getRoot());
        Integer age = (Integer) Ognl.getValue("age", ognlContext, ognlContext.getRoot());
        System.out.println(name + "," + age);

        /*
         * 书写OGNL：取出context中键为user1、user2的name、age
         * 1、"#"代表的是从context中取值；
         * 2、user1、user2是代表从context中的哪个对象来获取数据；
         * 3、.name、.age代表的是取出哪个属性值；
         */
        String name1 = (String) Ognl.getValue("#user1.name", ognlContext, ognlContext.getRoot());
        String name2 = (String) Ognl.getValue("#user2.name", ognlContext, ognlContext.getRoot());
        Integer age2 = (Integer) Ognl.getValue("#user2.age", ognlContext, ognlContext.getRoot());
        System.out.println(name1 + "," + name2 + "," + age2);
    }

    // 设置root和上下文中的数据
    public static void setRootContextData(OgnlContext ognlContext, Map<String, User> context) throws OgnlException {
        // 修改root的name属性，然后再获取他的属性
        Ognl.setValue("name", ognlContext, ognlContext.getRoot(), "李四");
        String nameOfRoot = (String) Ognl.getValue("name", ognlContext, ognlContext.getRoot());
        System.out.println(nameOfRoot);
        // 修改user1的name属性，然后再获取他的属性
        Ognl.setValue("#user1.name", ognlContext, ognlContext.getRoot(), "vincent");
        String nameOfUser1 = (String) Ognl.getValue("#user1.name", ognlContext, ognlContext.getRoot());
        System.out.println(nameOfUser1);
    }

    public static void addData(OgnlContext ognlContext) throws OgnlException{
        // 创建一个名为num的变量，值为10
        Ognl.setValue("num", ognlContext, 10);
        // 通过OGNL表达式获取num变量的值
        Integer numValue = (Integer)Ognl.getValue("#num", ognlContext, ognlContext.getRoot());
        System.out.println("numValue = " + numValue);
        // 修改num变量的值
        Ognl.setValue("num", ognlContext, 20);
        // 通过OGNL表达式获取num变量的值
        Integer numValueModified = (Integer)Ognl.getValue("#num", ognlContext, ognlContext.getRoot());
        System.out.println("numValueModified = " + numValueModified);

        // 创建一个名为list的变量，值为一个包含3个元素的List
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Ognl.setValue("list", ognlContext, list);
        // 通过OGNL表达式获取list变量的值
        List<String> listValue = (List<String>)Ognl.getValue("#list", ognlContext, ognlContext.getRoot());//第三个参数可以是ognlContext也可以是ognlContext.getRoot()
        System.out.println("listValue = " + listValue);
        // 删除list变量
        Ognl.setValue("list", ognlContext, null);
        // 通过OGNL表达式获取list变量的值
        List<String> listValueModified = (List<String>)Ognl.getValue("#list", ognlContext, ognlContext.getRoot());
        System.out.println("listValue = " + listValueModified);
    }

}
