#!/bin/bash

# ----------------------------- Configuration ----------------------------- #

# Base directory of your Java source files
SRC_DIR="src/main/java"

# Output directory for compiled classes
OUT_DIR="out"

# Path to JavaFX SDK
# Update this path to match your system's JavaFX SDK location
if [[ "$OSTYPE" == "msys" || "$OSTYPE" == "win32" ]]; then
    # Windows path (ensure to escape backslashes or use forward slashes)
    JAVAFX_SDK="C:/Program Files/Java/javafx-sdk-22.0.2/lib"
else
    # Unix/Linux path
    JAVAFX_SDK="/usr/lib/javafx-sdk-22.0.2/lib"
fi

# Path to JDBC JAR
JDBC_JAR="lib/sqlite-jdbc-3.36.0.3.jar"

# JavaFX modules required
JAVAFX_MODULES="javafx.controls,javafx.fxml"

# Main class to run
MAIN_CLASS="com.application.Engine"

# ----------------------------- Script Start ------------------------------ #

# Create output directory if it doesn't exist
mkdir -p "$OUT_DIR"

# Find all Java source files
find "$SRC_DIR" -name "*.java" > sources.txt

# Compile Java source files
echo "Compiling Java source files..."
javac \
    --module-path "$JAVAFX_SDK" \
    --add-modules "$JAVAFX_MODULES" \
    -cp "$JDBC_JAR" \
    -d "$OUT_DIR" \
    @sources.txt

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful."

    # Run the application
    echo "Running application..."
    java \
        --module-path "$JAVAFX_SDK" \
        --add-modules "$JAVAFX_MODULES" \
        -cp "$OUT_DIR:$JDBC_JAR" \
        "$MAIN_CLASS"
else
    echo "Compilation failed."
    exit 1
fi

# Clean up
rm sources.txt

