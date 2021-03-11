import kotlinx.coroutines.*

// build.gradle.kts 내에
// implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

fun main() {
    val scope = CoroutineScope(Dispatchers.IO)

    scope.launch {

        // CoroutineContext를 변경하여 백그라운드로 전환하여 작업을 처리합니다
        withContext(Dispatchers.Default){
            // 백그라운드 작업
            delay(2000)
            println ("Hi, background")
        }
    }

    scope.launch {
        // 타임아웃을 적용
        val rst = withTimeoutOrNull(10000){
            delay(5000)
            "data processing Success"
        }

        if (rst == null){
            println("time out 1 sec")
        } else {
            println(rst)
        }

    }


    // thread를 혼자점유함. Android 프로그래밍에서는 피해야 하는 코드(ANR)
    runBlocking {
        // async를 통해 동시작업을 수행함.
        var process1 = async {
            delay(1600);
            println("process1")
            100
        }

        var process2 = async {
            delay(1000);
            println("process2")
            200
        }

        println(process2.await()+ process1.await())
        delay(15000)
    }

    // 메모리 누수방지 위해 cancel
    scope.cancel()
    println("end")

}
