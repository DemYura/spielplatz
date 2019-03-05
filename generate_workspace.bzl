# The following dependencies were calculated from:
#
# generate_workspace --maven_project=/Users/demyura/sources/spielplatz/spielplatz/test --repositories=http://central.maven.org/maven2


def generated_maven_jars():
  # com.google.guava:guava:bundle:25.1-android
  native.maven_jar(
      name = "com_google_code_findbugs_jsr305",
      artifact = "com.google.code.findbugs:jsr305:3.0.2",
      repository = "http://central.maven.org/maven2/",
      sha1 = "25ea2e8b0c338a877313bd4672d3fe056ea78f0d",
  )


  # org.eclipse.jetty.websocket:websocket-client:jar:9.4.12.v20180830
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_client",
      artifact = "org.eclipse.jetty:jetty-client:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "1d329d68f31dce13135243c06013aaf6f708f7e7",
  )


  # org.eclipse.jetty.websocket:websocket-client:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty.websocket:websocket-server:jar:9.4.12.v20180830
  native.maven_jar(
      name = "org_eclipse_jetty_websocket_websocket_common",
      artifact = "org.eclipse.jetty.websocket:websocket-common:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "33997cdafbabb3ffd6947a5c33057f967e10535b",
  )


  # org.eclipse.jetty:jetty-security:jar:9.4.12.v20180830 got requested version
  # com.sparkjava:spark-core:bundle:2.8.0
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_server",
      artifact = "org.eclipse.jetty:jetty-server:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "b0f25df0d32a445fd07d5f16fff1411c16b888fa",
  )


  # org.eclipse.jetty.websocket:websocket-client:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-http:jar:9.4.12.v20180830
  # org.eclipse.jetty.websocket:websocket-common:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-server:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-client:jar:9.4.12.v20180830 got requested version
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_io",
      artifact = "org.eclipse.jetty:jetty-io:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "e93f5adaa35a9a6a85ba130f589c5305c6ecc9e3",
  )


  # com.google.truth:truth:jar:0.42
  native.maven_jar(
      name = "org_checkerframework_checker_qual",
      artifact = "org.checkerframework:checker-qual:2.5.3",
      repository = "http://central.maven.org/maven2/",
      sha1 = "4fe154d21bd734fe8c94ada37cdc41a9a6d61776",
  )


  # pom.xml got requested version
  # com.ydemenkov:test:jar:1.0
  native.maven_jar(
      name = "com_sparkjava_spark_core",
      artifact = "com.sparkjava:spark-core:2.8.0",
      repository = "http://central.maven.org/maven2/",
      sha1 = "784ff9ba2ff8b45ef44b4cbe7a8b3e34a839a69b",
  )


  # pom.xml got requested version
  # com.ydemenkov:test:jar:1.0
  native.maven_jar(
      name = "com_google_truth_truth",
      artifact = "com.google.truth:truth:0.42",
      repository = "http://central.maven.org/maven2/",
      sha1 = "b5768f644b114e6cf5c3962c2ebcb072f788dcbb",
  )


  # com.google.truth:truth:jar:0.42 wanted version 2.3.1
  # com.google.guava:guava:bundle:25.1-android
  native.maven_jar(
      name = "com_google_errorprone_error_prone_annotations",
      artifact = "com.google.errorprone:error_prone_annotations:2.1.3",
      repository = "http://central.maven.org/maven2/",
      sha1 = "39b109f2cd352b2d71b52a3b5a1a9850e1dc304b",
  )


  # com.sparkjava:spark-core:bundle:2.8.0 got requested version
  # org.eclipse.jetty.websocket:websocket-server:jar:9.4.12.v20180830
  native.maven_jar(
      name = "org_eclipse_jetty_websocket_websocket_servlet",
      artifact = "org.eclipse.jetty.websocket:websocket-servlet:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "8d212616b6ea21b96152ff202c2f53fdca8b8b53",
  )


  # com.google.truth:truth:jar:0.42
  native.maven_jar(
      name = "com_googlecode_java_diff_utils_diffutils",
      artifact = "com.googlecode.java-diff-utils:diffutils:1.3.0",
      repository = "http://central.maven.org/maven2/",
      sha1 = "7e060dd5b19431e6d198e91ff670644372f60fbd",
  )


  # com.google.truth:truth:jar:0.42
  native.maven_jar(
      name = "junit_junit",
      artifact = "junit:junit:4.12",
      repository = "http://central.maven.org/maven2/",
      sha1 = "2973d150c0dc1fefe998f834810d68f278ea58ec",
  )


  # org.eclipse.jetty.websocket:websocket-servlet:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty.websocket:websocket-common:jar:9.4.12.v20180830
  native.maven_jar(
      name = "org_eclipse_jetty_websocket_websocket_api",
      artifact = "org.eclipse.jetty.websocket:websocket-api:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "97d6376f70ae6c01112325c5254e566af118bc75",
  )


  # com.google.guava:guava:bundle:25.1-android
  # com.google.truth:truth:jar:0.42 wanted version 2.5.3
  native.maven_jar(
      name = "org_checkerframework_checker_compat_qual",
      artifact = "org.checkerframework:checker-compat-qual:2.0.0",
      repository = "http://central.maven.org/maven2/",
      sha1 = "fc89b03860d11d6213d0154a62bcd1c2f69b9efa",
  )


  # com.google.guava:guava:bundle:25.1-android
  native.maven_jar(
      name = "org_codehaus_mojo_animal_sniffer_annotations",
      artifact = "org.codehaus.mojo:animal-sniffer-annotations:1.14",
      repository = "http://central.maven.org/maven2/",
      sha1 = "775b7e22fb10026eed3f86e8dc556dfafe35f2d5",
  )


  # com.sparkjava:spark-core:bundle:2.8.0
  # org.slf4j:slf4j-simple:jar:1.7.25 got requested version
  native.maven_jar(
      name = "org_slf4j_slf4j_api",
      artifact = "org.slf4j:slf4j-api:1.7.25",
      repository = "http://central.maven.org/maven2/",
      sha1 = "da76ca59f6a57ee3102f8f9bd9cee742973efa8a",
  )


  # org.eclipse.jetty.websocket:websocket-client:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-http:jar:9.4.12.v20180830
  # org.eclipse.jetty:jetty-xml:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-io:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty.websocket:websocket-common:jar:9.4.12.v20180830 got requested version
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_util",
      artifact = "org.eclipse.jetty:jetty-util:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "cb4ccec9bd1fe4b10a04a0fb25d7053c1050188a",
  )


  # junit:junit:jar:4.12
  native.maven_jar(
      name = "org_hamcrest_hamcrest_core",
      artifact = "org.hamcrest:hamcrest-core:1.3",
      repository = "http://central.maven.org/maven2/",
      sha1 = "42a25dc3219429f0e5d060061f71acb49bf010a0",
  )


  # pom.xml got requested version
  # com.ydemenkov:test:jar:1.0
  native.maven_jar(
      name = "com_google_code_gson_gson",
      artifact = "com.google.code.gson:gson:2.3.1",
      repository = "http://central.maven.org/maven2/",
      sha1 = "ecb6e1f8e4b0e84c4b886c2f14a1500caf309757",
  )


  # com.sparkjava:spark-core:bundle:2.8.0
  native.maven_jar(
      name = "org_eclipse_jetty_websocket_websocket_server",
      artifact = "org.eclipse.jetty.websocket:websocket-server:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "fadf609aec6026cb25f25b6bc0b979821f849fd7",
  )


  # com.google.truth:truth:jar:0.42
  native.maven_jar(
      name = "com_google_auto_value_auto_value_annotations",
      artifact = "com.google.auto.value:auto-value-annotations:1.6.2",
      repository = "http://central.maven.org/maven2/",
      sha1 = "ed193d86e0af90cc2342aedbe73c5d86b03fa09b",
  )


  # org.eclipse.jetty.websocket:websocket-server:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-webapp:jar:9.4.12.v20180830
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_servlet",
      artifact = "org.eclipse.jetty:jetty-servlet:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "4c1149328eda9fa39a274262042420f66d9ffd5f",
  )


  # org.eclipse.jetty.websocket:websocket-server:jar:9.4.12.v20180830
  native.maven_jar(
      name = "org_eclipse_jetty_websocket_websocket_client",
      artifact = "org.eclipse.jetty.websocket:websocket-client:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "75880b6a90a6eda83fdbfc20a42f23eade4b975d",
  )


  # org.eclipse.jetty.websocket:websocket-server:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-server:jar:9.4.12.v20180830
  # org.eclipse.jetty:jetty-client:jar:9.4.12.v20180830 got requested version
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_http",
      artifact = "org.eclipse.jetty:jetty-http:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "1341796dde4e16df69bca83f3e87688ba2e7d703",
  )


  # org.eclipse.jetty:jetty-servlet:jar:9.4.12.v20180830
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_security",
      artifact = "org.eclipse.jetty:jetty-security:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "299e0602a9c0b753ba232cc1c1dda72ddd9addcf",
  )


  # org.eclipse.jetty.websocket:websocket-servlet:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-server:jar:9.4.12.v20180830
  native.maven_jar(
      name = "javax_servlet_javax_servlet_api",
      artifact = "javax.servlet:javax.servlet-api:3.1.0",
      repository = "http://central.maven.org/maven2/",
      sha1 = "3cd63d075497751784b2fa84be59432f4905bf7c",
  )


  # org.eclipse.jetty.websocket:websocket-client:jar:9.4.12.v20180830 got requested version
  # org.eclipse.jetty:jetty-webapp:jar:9.4.12.v20180830
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_xml",
      artifact = "org.eclipse.jetty:jetty-xml:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "e9f1874e9b5edd498f2fe7cd0904405da07cc300",
  )


  # com.google.truth:truth:jar:0.42
  native.maven_jar(
      name = "com_google_guava_guava",
      artifact = "com.google.guava:guava:25.1-android",
      repository = "http://central.maven.org/maven2/",
      sha1 = "bdaab946ca5ad20253502d873ba0c3313d141036",
  )


  # pom.xml got requested version
  # com.ydemenkov:test:jar:1.0
  native.maven_jar(
      name = "org_slf4j_slf4j_simple",
      artifact = "org.slf4j:slf4j-simple:1.7.25",
      repository = "http://central.maven.org/maven2/",
      sha1 = "8dacf9514f0c707cbbcdd6fd699e8940d42fb54e",
  )


  # com.sparkjava:spark-core:bundle:2.8.0
  native.maven_jar(
      name = "org_eclipse_jetty_jetty_webapp",
      artifact = "org.eclipse.jetty:jetty-webapp:9.4.12.v20180830",
      repository = "http://central.maven.org/maven2/",
      sha1 = "a3e119df2da04fcf5aa290c8c35c5b310ce2dcd1",
  )


  # com.google.guava:guava:bundle:25.1-android
  native.maven_jar(
      name = "com_google_j2objc_j2objc_annotations",
      artifact = "com.google.j2objc:j2objc-annotations:1.1",
      repository = "http://central.maven.org/maven2/",
      sha1 = "ed28ded51a8b1c6b112568def5f4b455e6809019",
  )




def generated_java_libraries():
  native.java_library(
      name = "com_google_code_findbugs_jsr305",
      visibility = ["//visibility:public"],
      exports = ["@com_google_code_findbugs_jsr305//jar"],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_client",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_client//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_http",
          ":org_eclipse_jetty_jetty_io",
      ],
  )


  native.java_library(
      name = "org_eclipse_jetty_websocket_websocket_common",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_websocket_websocket_common//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_io",
          ":org_eclipse_jetty_jetty_util",
          ":org_eclipse_jetty_websocket_websocket_api",
      ],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_server",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_server//jar"],
      runtime_deps = [
          ":javax_servlet_javax_servlet_api",
          ":org_eclipse_jetty_jetty_http",
          ":org_eclipse_jetty_jetty_io",
          ":org_eclipse_jetty_jetty_util",
      ],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_io",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_io//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_util",
      ],
  )


  native.java_library(
      name = "org_checkerframework_checker_qual",
      visibility = ["//visibility:public"],
      exports = ["@org_checkerframework_checker_qual//jar"],
  )


  native.java_library(
      name = "com_sparkjava_spark_core",
      visibility = ["//visibility:public"],
      exports = ["@com_sparkjava_spark_core//jar"],
      runtime_deps = [
          ":javax_servlet_javax_servlet_api",
          ":org_eclipse_jetty_jetty_client",
          ":org_eclipse_jetty_jetty_http",
          ":org_eclipse_jetty_jetty_io",
          ":org_eclipse_jetty_jetty_security",
          ":org_eclipse_jetty_jetty_server",
          ":org_eclipse_jetty_jetty_servlet",
          ":org_eclipse_jetty_jetty_util",
          ":org_eclipse_jetty_jetty_webapp",
          ":org_eclipse_jetty_jetty_xml",
          ":org_eclipse_jetty_websocket_websocket_api",
          ":org_eclipse_jetty_websocket_websocket_client",
          ":org_eclipse_jetty_websocket_websocket_common",
          ":org_eclipse_jetty_websocket_websocket_server",
          ":org_eclipse_jetty_websocket_websocket_servlet",
          ":org_slf4j_slf4j_api",
      ],
  )


  native.java_library(
      name = "com_google_truth_truth",
      visibility = ["//visibility:public"],
      exports = ["@com_google_truth_truth//jar"],
      runtime_deps = [
          ":com_google_auto_value_auto_value_annotations",
          ":com_google_code_findbugs_jsr305",
          ":com_google_errorprone_error_prone_annotations",
          ":com_google_guava_guava",
          ":com_google_j2objc_j2objc_annotations",
          ":com_googlecode_java_diff_utils_diffutils",
          ":junit_junit",
          ":org_checkerframework_checker_compat_qual",
          ":org_checkerframework_checker_qual",
          ":org_codehaus_mojo_animal_sniffer_annotations",
          ":org_hamcrest_hamcrest_core",
      ],
  )


  native.java_library(
      name = "com_google_errorprone_error_prone_annotations",
      visibility = ["//visibility:public"],
      exports = ["@com_google_errorprone_error_prone_annotations//jar"],
  )


  native.java_library(
      name = "org_eclipse_jetty_websocket_websocket_servlet",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_websocket_websocket_servlet//jar"],
      runtime_deps = [
          ":javax_servlet_javax_servlet_api",
          ":org_eclipse_jetty_websocket_websocket_api",
      ],
  )


  native.java_library(
      name = "com_googlecode_java_diff_utils_diffutils",
      visibility = ["//visibility:public"],
      exports = ["@com_googlecode_java_diff_utils_diffutils//jar"],
  )


  native.java_library(
      name = "junit_junit",
      visibility = ["//visibility:public"],
      exports = ["@junit_junit//jar"],
      runtime_deps = [
          ":org_hamcrest_hamcrest_core",
      ],
  )


  native.java_library(
      name = "org_eclipse_jetty_websocket_websocket_api",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_websocket_websocket_api//jar"],
  )


  native.java_library(
      name = "org_checkerframework_checker_compat_qual",
      visibility = ["//visibility:public"],
      exports = ["@org_checkerframework_checker_compat_qual//jar"],
  )


  native.java_library(
      name = "org_codehaus_mojo_animal_sniffer_annotations",
      visibility = ["//visibility:public"],
      exports = ["@org_codehaus_mojo_animal_sniffer_annotations//jar"],
  )


  native.java_library(
      name = "org_slf4j_slf4j_api",
      visibility = ["//visibility:public"],
      exports = ["@org_slf4j_slf4j_api//jar"],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_util",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_util//jar"],
  )


  native.java_library(
      name = "org_hamcrest_hamcrest_core",
      visibility = ["//visibility:public"],
      exports = ["@org_hamcrest_hamcrest_core//jar"],
  )


  native.java_library(
      name = "com_google_code_gson_gson",
      visibility = ["//visibility:public"],
      exports = ["@com_google_code_gson_gson//jar"],
  )


  native.java_library(
      name = "org_eclipse_jetty_websocket_websocket_server",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_websocket_websocket_server//jar"],
      runtime_deps = [
          ":javax_servlet_javax_servlet_api",
          ":org_eclipse_jetty_jetty_client",
          ":org_eclipse_jetty_jetty_http",
          ":org_eclipse_jetty_jetty_io",
          ":org_eclipse_jetty_jetty_servlet",
          ":org_eclipse_jetty_jetty_util",
          ":org_eclipse_jetty_jetty_xml",
          ":org_eclipse_jetty_websocket_websocket_api",
          ":org_eclipse_jetty_websocket_websocket_client",
          ":org_eclipse_jetty_websocket_websocket_common",
          ":org_eclipse_jetty_websocket_websocket_servlet",
      ],
  )


  native.java_library(
      name = "com_google_auto_value_auto_value_annotations",
      visibility = ["//visibility:public"],
      exports = ["@com_google_auto_value_auto_value_annotations//jar"],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_servlet",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_servlet//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_security",
          ":org_eclipse_jetty_jetty_server",
      ],
  )


  native.java_library(
      name = "org_eclipse_jetty_websocket_websocket_client",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_websocket_websocket_client//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_client",
          ":org_eclipse_jetty_jetty_http",
          ":org_eclipse_jetty_jetty_io",
          ":org_eclipse_jetty_jetty_util",
          ":org_eclipse_jetty_jetty_xml",
          ":org_eclipse_jetty_websocket_websocket_common",
      ],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_http",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_http//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_io",
          ":org_eclipse_jetty_jetty_util",
      ],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_security",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_security//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_server",
      ],
  )


  native.java_library(
      name = "javax_servlet_javax_servlet_api",
      visibility = ["//visibility:public"],
      exports = ["@javax_servlet_javax_servlet_api//jar"],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_xml",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_xml//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_util",
      ],
  )


  native.java_library(
      name = "com_google_guava_guava",
      visibility = ["//visibility:public"],
      exports = ["@com_google_guava_guava//jar"],
      runtime_deps = [
          ":com_google_code_findbugs_jsr305",
          ":com_google_errorprone_error_prone_annotations",
          ":com_google_j2objc_j2objc_annotations",
          ":org_checkerframework_checker_compat_qual",
          ":org_codehaus_mojo_animal_sniffer_annotations",
      ],
  )


  native.java_library(
      name = "org_slf4j_slf4j_simple",
      visibility = ["//visibility:public"],
      exports = ["@org_slf4j_slf4j_simple//jar"],
      runtime_deps = [
          ":org_slf4j_slf4j_api",
      ],
  )


  native.java_library(
      name = "org_eclipse_jetty_jetty_webapp",
      visibility = ["//visibility:public"],
      exports = ["@org_eclipse_jetty_jetty_webapp//jar"],
      runtime_deps = [
          ":org_eclipse_jetty_jetty_security",
          ":org_eclipse_jetty_jetty_server",
          ":org_eclipse_jetty_jetty_servlet",
          ":org_eclipse_jetty_jetty_util",
          ":org_eclipse_jetty_jetty_xml",
      ],
  )


  native.java_library(
      name = "com_google_j2objc_j2objc_annotations",
      visibility = ["//visibility:public"],
      exports = ["@com_google_j2objc_j2objc_annotations//jar"],
  )


