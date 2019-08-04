package advance

/**
 * Created by snake on 17. 5. 23.
 */

fun main(args : Array<String> ){
    // 가장 심플한 람다식 표현
    var pfunc = {a: Int, b: Int -> a + b}

    // 그냥함수변수는 TestFunc : (Int, Int) -> Int 형으로 표현가능하다.
    // C/C++ 에서 함수형변수 정의하는 것과 유사하다.
    // 함수처럼 사용함
    println( pfunc(10, 30 ) )

    // 변수로 넘김
    funTest1( pfunc )
    funTest2{ n, n2 -> n + n2}
    funtTest3(
            { n, n2 -> n +n2 },
            100, 10
    )

    // 함수원형의 포인터를 대입시키는 편법
    var funParameter = {a : Int -> AddFunc(a)}
    funTest4( funParameter )

    // 위의 코딩을 점점 더 단순화
    funTest4( { a : Int -> AddFunc(a)} )
    funTest4{ a : Int -> AddFunc(a)}
    funTest4{ a  -> AddFunc(a) }

    // 함수형 인자가 맨마지막에 존재할 시
    funTest5(100){ num -> num - 10}
}

fun funTest1(func : (Int, Int) -> Int  ){
    func(10, 10).let { 결과값 ->  println("결과값은 $결과값 입니다") }
}

fun funTest2(func : (Int, Int) -> Int  ){
    println ( func(10, 10) )
}

fun funtTest3(func : (Int, Int) -> Int, a : Int, b : Int  ){
    println ( func(a, b) )
}

fun funTest4(func : (Int) -> Int ){
    println ( func(1) )
}

fun funTest5(num : Int, func : (Int) -> Int ){
    if(num > 10) println ( func(num) )
}

fun AddFunc(i : Int) : Int {return i+100}
