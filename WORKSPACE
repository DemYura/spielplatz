# General maven dependencies
load(":generate_workspace.bzl", "generated_maven_jars")
generated_maven_jars()


# JUnit 5 dependencies and test rules
load(":junit5.bzl", "junit_jupiter_java_repositories", "junit_platform_java_repositories")
JUNIT_JUPITER_VERSION = "5.4.0"
JUNIT_PLATFORM_VERSION = "1.4.0"
junit_jupiter_java_repositories(
    version = JUNIT_JUPITER_VERSION,
)
junit_platform_java_repositories(
    version = JUNIT_PLATFORM_VERSION,
)


# Protobuf dependencies
load(":protobuf.bzl", "load_protobuf_archives")
load_protobuf_archives()

load("@bazel_skylib//lib:versions.bzl", "versions")
versions.check(minimum_bazel_version = "0.5.4")

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")
protobuf_deps()