package basic

/**
 * Created by snake on 17. 5. 22.
 */
fun main(args : Array<String>){
    doubleLoop();
    println("=================>")
    ExitDoubleLoop();
    println( lambdaReturn() )
}

// @레이블 지정 후,  break@레이블로 나감
// 대부분의 언어에서 지원하는 goto문과 유사함.
// 거의 사용하지 않는 문법이지만, 함수형 프로그래밍 지원때문에
// 더 강조된 듯 함.
fun ExitDoubleLoop() {
    HereToExit@ for(i in 0 .. 100){
        for (j in  0.. 10){
            if (j == 5) break@HereToExit;
            println("i->$i, j->$j")
        }
        println("j loop end");
    }
    println("i loop end");
}

fun doubleLoop() {
    for(i in 0 .. 100){
        for (j in  0.. 10){
            if (j == 5) break
            println("i->$i, j->$j")
        }
        println("j loop end")
    }
    println("i loop end")
}

// 이름없는 함수(람다식)의 리턴
var lambdaReturn = Exit@{
    if(true){
        return@Exit 3
    }
    1000
}