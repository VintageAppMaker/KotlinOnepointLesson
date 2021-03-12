package asynclib

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun successCount(lst : List<Int>){
    fun add (d : Int) = d + 1;
    fun sub (d : Int) = d - 1;

    var count = 0
    GlobalScope.launch {
        flow{
            lst.forEach {
                var fn = { n : Int -> 0 }
                if( it < 70 ){
                    fn = ::sub
                } else {
                    fn = ::add
                }
                emit ( fn )
            }
        }.collect {
            count = it(count)
            println ("fast count => $count")
        }
    }

}

fun successCountWithDelay(lst : List<Int>){
    fun add (d : Int) = d + 1;
    fun sub (d : Int) = d - 1;

    var count = 0
    GlobalScope.launch {
        flow{
            lst.forEach {
                var fn = { n : Int -> 0 }
                if( it < 70 ){
                    fn = ::sub
                } else {
                    fn = ::add
                }
                delay(500)
                emit ( Pair(fn, it ) )
            }
        }.collect {
            count = it.first(count)
            println ("delay count => $count , value is ${it.second} ")
        }
    }

}

fun successCountWithFilter(lst : List<Int>){
    fun add (d : Int) = d + 1;
    fun sub (d : Int) = d - 1;

    var count = 0
    GlobalScope.launch {
        flow{
            lst.filter { it !=100 }
                .forEach {
                var fn = { n : Int -> 0 }
                if( it < 70 ){
                    fn = ::sub
                } else {
                    fn = ::add
                }
                emit ( fn )
            }
        }.collect {
            count = it(count)
            println ("filter(100) count => $count")
        }
    }

}

fun main() = runBlocking<Unit> {
    successCountWithDelay(listOf(90, 80, 100, 100, 60, 100, 90))

    // 위의 함수보다 아래 함수가 먼저 실행된다.
    successCount(listOf(1, 90, 29, 77, 80, 100, 100, 60))

    // 100만 제외하고 계산
    successCountWithFilter(listOf(100, 90, 29, 77, 80, 100, 100, 100))

    delay(1000 * 10)
    println("runBlocking end")
}