import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.jsoup.Jsoup

// build.gradle.kts의 dependancy에
// implementation ("org.jsoup:jsoup:1.13.1")
// 추가

// Android에서는 runBlocking(점유율)과 GlobalScope(전역적)는 사용하지 않는 것이 좋다.
fun main() = runBlocking{
    // 1, 2의 동시 작업을 통해 imgList를 처리
    var imgList = mutableListOf<String>()

    // 1. 네트웍을 통해 정보가져오기(imgList 저장)
    getImageInfoFromNetwork("http://vintageappmaker.com", imgList)

    // 2. UI 작업(imgList 보여주기)
    launch {
        (0..10).forEach {
            println (">> Another UI Working.. $it")
            println (">> imgList count =>  ${imgList.count()}")
            delay(1000)
        }
    }

    // 3. 결과물 출력위한 대기
    delay(1000 * 10)
}

fun getImageInfoFromNetwork(url : String, imgList : MutableList<String>) {
    CoroutineScope(Dispatchers.IO).launch {
        flow{
            var doc = Jsoup.connect(url).get()
            var lst = doc.getElementsByTag("img")
            lst.forEach {
                var sUrl = it.attr("src")
                emit(sUrl)
                delay(400)
            }
        }.collect {
            println ("image download complete => $it")
            imgList.add(it)
        }
    }
}