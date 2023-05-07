package com.atguigu.demo

def say(name, address) {
    println("name is ${name}, address is ${address}")
}

// 不支持这种写法
say(address = '海南省', name = "海涛")

println Object