java_library(
    name = "common",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "@com_google_protobuf//:protobuf_java",
        ":health_java_proto",
        "//third_party:com_google_code_gson_gson",
        "//third_party:com_google_guava_guava",
        "//third_party:com_google_inject_guice",
        "//third_party:com_google_protobuf_protobuf_java_util",
        "//third_party:com_sparkjava_spark_core",
        "//third_party:javax_inject_javax_inject",
    ],
    visibility = ["//visibility:public"]
)

proto_library(
    name = "health_proto",
    srcs = glob(["src/main/proto/health.proto"]),
    visibility = ["//visibility:public"]
)

java_proto_library(
    name = "health_java_proto",
    deps = [":health_proto"],
    visibility = ["//visibility:public"]
)

# Tests

load("//:junit5.bzl", "java_junit5_tests")

# Use java_test after bazel officially support junit5
java_junit5_tests(
    name = "spark-app-java-tests",
    srcs = glob(["src/test/java/com/ydemenkov/common/**/*.java"]),
    deps = [
        ":common",
        ":health_proto",
        "//third_party:com_sparkjava_spark_core",
        "//third_party:com_google_truth_truth",
    ],
    runtime_deps = [
    ],
)

