java_library(
    name = "posts-app-lib",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "//third_party:com_sparkjava_spark_core",
        "@com_sparkjava_spark_core//jar",
    ],
    javacopts = [
        "--module-path $(location @com_sparkjava_spark_core//jar)",
    ],
    visibility = ["//com.ydemenkov.posts.app:__subpackages__"],
    runtime_deps = [
        "//third_party:org_slf4j_slf4j_simple",
    ],
)

java_binary(
    name = "PostsApp",
    main_class = "com.ydemenkov.posts.app.PostsApp",
    runtime_deps = [":posts-app-lib"],
)


# Tests

load("//:junit5.bzl", "java_junit5_test")

# Use java_test after bazel officially support junit5
java_junit5_test(
    name = "posts-app-java-tests",
    srcs = glob(["src/test/java/**/*.java"]),
    test_class = "com.ydemenkov.posts.app.PostsAppTest",
    deps = [
        ":posts-app-lib",
        "//third_party:com_sparkjava_spark_core",
    ],
    runtime_deps = [
    ],
)