## Lab 0
### Tianshu Wang 

<br>

### Circuit Description
It is a very simple circuit that uses a while loop to add the "prev" to "curr" and store the sum to "curr", while the new "prev" gets the old value of "curr".\
I implemented comparison logic so that the valid signal can output in the first cycle.

The level of difficulty is just right! 

#### Thank you, TA Shiyu!!!

<br>

### Running TestBench
`./run-problem.sh Fib`

<br>


Benchmark output is below, all tests passed
```
[info] welcome to sbt 1.8.0 (Oracle Corporation Java 18.0.2.1)
[info] loading project definition from /Users/tianshu/Desktop/Local/Codes/590/chisel-tutorial/project
[info] loading settings for project root from build.sbt ...
[info] set current project to Chisel Tutorial (in build file:/Users/tianshu/Desktop/Local/Codes/590/chisel-tutorial/)
[info] compiling 1 Scala source to /Users/tianshu/Desktop/Local/Codes/590/chisel-tutorial/target/scala-2.13/test-classes ...
[info] FibTests:
[info] Fib
[info] - should Perform Fibonacci 1
[info] - should Perform Fibonacci 5
[info] - should Perform Fibonacci 0
[info] - should Perform Fibonacci 71
[info] - should Perform Fibonacci 70
[info] Run completed in 3 seconds, 325 milliseconds.
[info] Total number of tests run: 5
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 5, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 8 s, completed Jan 25, 2023, 10:27:28 PM
```



Copied from Chisel Tutorials (Release branch)



