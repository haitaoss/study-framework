/*
function fun(n, o) {
    console.log(o);
    return {
        fun: function (m) {
            return fun(m, n);
        }
    }
}

var a = fun(0);
a.fun(1);
a.fun(2);
a.fun(3);

console.log('----------------------------------------------------------------');
var b = fun(0).fun(1).fun(2).fun(3);
console.log('----------------------------------------------------------------');
var c = fun(0).fun(1);
c.fun(2);
c.fun(3);


console.log('----------------------------------------------------------------');


for (var i = 0; i < 3; i++) {
    setTimeout(function log() {
        console.log(i)
    }, 1000)
}

console.log('----------------------------------------------------------------');

function Foo() {
    var i = 0;
    return function () {
        console.log(i++);
    }
}

var f1 = Foo();
var f2 = Foo();
f1();
f1();
f2();
*/
(async () => {
    console.log(1)
    await (async () => {
        console.log(1)
        setTimeout(() => {
            console.log(3)
        }, 3000)
        console.log(2)
    })()
    console.log(4)
})()