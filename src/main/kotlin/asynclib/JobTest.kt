package asynclib

import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) = runBlocking {

    // job을 넘겨서 취소
    val job = Job()
    JobTest1(job)

    delay(1000 * 5)
    job.cancel()
    println("job.cancel()")

    // job을 넘겨받아 취소
    val job2 = JobTest2()

    delay(1000 * 5)
    job2.cancel()
    println("job2.cancel()")

    // 실행결과 보기위해 대기
    delay(1000 * 100)
}


private suspend fun JobTest2() : Job{
    val job2 = CoroutineScope(Dispatchers.Default).launch {
        val totalSeconds = TimeUnit.MINUTES.toSeconds(2)
        val tickSeconds = 1
        for (second in totalSeconds downTo tickSeconds) {
            val time = String.format(
                "%02d분%02d초",
                TimeUnit.SECONDS.toMinutes(second),
                second - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(second))
            )
            println(time)
            delay(1000)
        }
        println("끝")
    }

    return job2
}

// job을 넘겨서 취소
private suspend fun JobTest1(job: CompletableJob) {
    CoroutineScope(Dispatchers.Default + job).launch {
        val totalSeconds = TimeUnit.MINUTES.toSeconds(2)
        val tickSeconds = 1
        for (second in totalSeconds downTo tickSeconds) {
            val time = String.format(
                "%02d분%02d초",
                TimeUnit.SECONDS.toMinutes(second),
                second - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(second))
            )
            println(time)
            delay(1000)
        }
        println("끝")
    }
}