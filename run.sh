#!/bin/bash

# Base directory of your Java packages
BASE_DIR="src/main/java"

# Set the target file (Engine.java)
TARGET_FILE="Engine.java"

# Find the directory containing the specified file (Engine.java)
TARGET_DIR=$(find $BASE_DIR -type f -name "$TARGET_FILE" | xargs -I {} dirname {} | uniq)

# Check if the file was found
if [ -z "$TARGET_DIR" ]; then
  echo "File not found: $TARGET_FILE"
  exit 1
fi

JDBC_JAR="lib/sqlite-jdbc-3.36.0.3.jar"

# Compile the Java file
javac -cp "$JDBC_JAR" -d bin "$TARGET_DIR/$TARGET_FILE"

# Check if compilation was successful
if [ $? -eq 0 ]; then
  echo "Compilation successful, running Engine..."
  
  # Change to the base src directory to match the package structure
  cd "$BASE_DIR"

  # Run the compiled Java class (replace 'com.application' with the actual package name)
  java -cp "../bin:$JDBC_JAR" com.application.Engine
else
  echo "Compilation failed."
  exit 1
fi
