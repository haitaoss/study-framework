// 闭包名+（）或者闭包名.call()来调用闭包。
def code = { 123 }

assert code() == 123

assert code.call() == 123