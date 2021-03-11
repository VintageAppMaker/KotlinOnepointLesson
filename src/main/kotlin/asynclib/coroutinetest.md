### Async 관련 coroutine, Flow
> coroutine 핵심만 정리하기 

[전체소스](coroutinetest.kt)

- 코루틴 ( coroutine )
  
  비동기 방식의 협력형 멀티태스킹. Thread보다 쉽고 가볍고 안정적임.  
    
  - 적용방법 
    -  ( 1 ) 비동기 code가 적용되는 범위설정 
        - CoroutineScope() 함수를 이용하여 어디에서 실행될 것인지 결정
            - Dispatchers.Main (Android의 UI Thread용)
            - Dispatchers.IO (File IO, Network IO)
            - Dispatchers.Default (background)
        ~~~kotlin
        val scope = CoroutineScope(Dispatchers.IO)
        ~~~
    -  ( 2 ) 비동기 code 실행 - launch{ ... }
        - withContext로 Main, IO, Default 전환 
        ~~~kotlin
        scope.launch {
            withContext(Dispatchers.Default){
                // 백그라운드 작업
                delay(2000)
                println ("Hi, background")
            }
        }
        ~~~
       - 코루틴 내의 비동기 함수 : async{} 결과값을 리턴하고 await()로 대기한다. 
       - 코루틴 내의 대기함수 : delay(milliseconds)   
       - 타임아웃으로 대기하고 기다림 : withTimeoutOrNull(milliseconds) 
        ~~~kotlin
        scope.launch {
            // CoroutineContext 를 변경하여 백그라운드로 전환하여 작업을 처리합니다
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
        ~~~
    - 참고: runBlocking ()은 Thread를 점유하므로 사용을 권장하지 않음. 
    
        ~~~kotlin
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
      
        // 메모리 누수를 위해 cancel
        scope.cancel()
        println("end")
        ~~~   
        

    