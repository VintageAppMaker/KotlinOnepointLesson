import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.jsoup.Jsoup

// build.gradle.kts의 dependancy에
// implementation ("org.jsoup:jsoup:1.13.1")
// 추가

// Android에서는 runBlocking(점유율)과 GlobalScope(전역적)는 사용하지 않는 것이 좋다.
fun main() = runBlocking{
    // 1, 2에서 동시 작업을 통해 imgList와 종료정보를 공유함
    var imgList   = mutableListOf<String>()
    var bComplete : Boolean = false;
    var onComplete :() -> Unit  = { bComplete = true}

    // 1. 네트웍을 통해 정보가져오기(imgList 저장)
    getImageInfoFromNetwork("http://vintageappmaker.com", imgList, onComplete)

    // 2. UI 작업(imgList 보여주기)
    launch {
        (0..100).forEach {
            println (">> \uD83C\uDFA8 [UI Working].. #$it")
            println (">> \uD83C\uDFA8 [UI Working] imgList count =>  ${imgList.count()}")
            delay(500)

            if(bComplete) cancel()
        }
    }

    // 3. 결과물 출력위한 대기
    delay(1000 * 10)
}

fun getImageInfoFromNetwork(url : String, imgList : MutableList<String>, onComplete : () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        flow{
            var doc = Jsoup.connect(url).get()
            var lst = doc.getElementsByTag("img")
            lst.forEach {
                var sUrl = it.attr("src")
                emit(sUrl)
                delay(400)
            }

            // 모두 처리했음
            emit(true)
        }.collect {
            if (it is String ) {
                println("[network] image download complete => $it")
                imgList.add(it)
            } else {
                // 종료 callback 호출
                onComplete()
            }
        }
    }
}