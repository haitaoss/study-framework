package com.atguigu.demo


def obj = new Demo01BasicNotice(bookname: "斗罗大陆", description: "尚硅谷是个学习的好地方");
println(obj.getBookname())


def result = obj.sale 100
println(result)

println obj.sale(1000)
