java_library(
    name = "posts-app-lib",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "//posts:posts_java_proto",
        "//com.ydemenkov.common:common",
        "//com.ydemenkov.common:health_proto",
        "//third_party:com_google_code_gson_gson",
        "//third_party:com_google_guava_guava",
        "//third_party:com_google_inject_guice",
        "//third_party:com_sparkjava_spark_core",
        "//third_party:com_zaxxer_HikariCP",
        "//third_party:javax_inject_javax_inject",
        "//third_party:org_apache_derby_derby",
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

load("//:junit5.bzl", "java_junit5_tests")

# Use java_test after bazel officially support junit5
java_junit5_tests(
    name = "posts-app-java-tests",
    srcs = glob(["src/test/java/**/*.java"]),
    deps = [
        ":posts-app-lib",
        "//com.ydemenkov.common:common",
        "//com.ydemenkov.common:health_proto",
        "//third_party:com_google_truth_truth",
        "//third_party:com_sparkjava_spark_core",
    ],
    runtime_deps = [
    ],
)