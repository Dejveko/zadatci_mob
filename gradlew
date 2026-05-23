#!/bin/sh

# Gradle startup script for POSIX-compliant shells.
# Generated to make this project openable in Android Studio.

APP_HOME=$(cd "${0%/*}" && pwd -P)

CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

JAVA_HOME=${JAVA_HOME:-""}
if [ -n "$JAVA_HOME" ]; then
  JAVA_EXEC="$JAVA_HOME/bin/java"
else
  JAVA_EXEC=java
fi

exec "$JAVA_EXEC" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
