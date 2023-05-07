package com.atguigu.demo


def testMap(Map<String, String> map, item2) {
    map.entrySet().forEach(item -> {
        println("key is ${item.key}, value is ${item.value}")
    })
}

// 参数是 key:value 的形式，groovy 在编译的时候会自动识别成map
testMap(key: 'value1', key2: 'value2', 2)