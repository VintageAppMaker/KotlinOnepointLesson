package functional

/**
 * Created by park on 2017-06-01.
 */

fun main(args : Array<String>){
    var fn = simpleClosure(10)
    println( fn(10) )
    println( fn(10) )
    println( fn(10) )
    // 새로운 메모리 영역
    println("----------");
    var fn2 = simpleClosure(100)
    println( fn2(30) )
    println( fn2(70) )
    println( fn2(70) )
}

// 자바스크립트에서 볼 수 있는 간단한 클로져
// 객체지향처럼 함수 내의 변수들을 계속 유지할 수 있다.
fun simpleClosure(num : Int ) : (Int) -> Int {
    var initNumber: Int = num

    return fun (num2 : Int) : Int{
        initNumber = initNumber + num2
        return initNumber
    };
}