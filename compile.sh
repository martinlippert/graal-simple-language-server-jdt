#!/usr/bin/env bash

rm simple-ls-jdt

export CP=org.test.language-server-jdt/target/org.test.language-server-jdt-0.0.1-SNAPSHOT.jar

printf "\n\nCompile Native Image...\n"
native-image \
  --no-server \
  -H:Name=simple-ls-jdt \
  -H:+PrintClassInitialization \
  -H:+ReportExceptionStackTraces \
  -H:DynamicProxyConfigurationFiles=generated-config/proxy-config.json \
  -H:ReflectionConfigurationFiles=generated-config/reflect-config.json \
  --no-fallback \
  --allow-incomplete-classpath \
  --report-unsupported-elements-at-runtime \
  --initialize-at-build-time=\
org.eclipse.jdt \
  --initialize-at-run-time=\
org.eclipse.jdt.internal.core.ClasspathEntry \
  -cp $CP org.test.ls.ServerSocketLauncher

printf "\n\nCompiled app (clr)\n"
time ./simple-ls-jdt
