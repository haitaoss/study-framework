// 定义闭包

Integer item = 0;
{ item++ }

        { -> item++ }

        { println it }

        { it -> println it }

        { name -> println name }

        { String x, int y ->
            println "hey ${x} the value is ${y}"
        }

        { reader ->
            def line = reader.readLine()
            line.trim()
        }