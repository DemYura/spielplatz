# spielplatz
A playground repository

To run the application or tests you should fist install [bazel](https://bazel.build) and point JAVA_HOME variable to jdk11.

Tests:
```
bazel test --define=ABSOLUTE_JAVABASE=$JAVA_HOME\
           --javabase=@bazel_tools//tools/jdk:absolute_javabase\
           --host_javabase=@bazel_tools//tools/jdk:absolute_javabase\
           --host_java_toolchain=@bazel_tools//tools/jdk:toolchain_vanilla\
           --java_toolchain=@bazel_tools//tools/jdk:toolchain_vanilla\
           com.ydemenkov.posts.app/... 
 ```

Run:

```
bazel run --define=ABSOLUTE_JAVABASE=$JAVA_HOME\
          --javabase=@bazel_tools//tools/jdk:absolute_javabase\
          --host_javabase=@bazel_tools//tools/jdk:absolute_javabase\
          --host_java_toolchain=@bazel_tools//tools/jdk:toolchain_vanilla\
          --java_toolchain=@bazel_tools//tools/jdk:toolchain_vanilla\
          com.ydemenkov.posts.app:PostsApp
```
