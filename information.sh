#!/bin/bash

# Check if a file name was provided
if [ -z "$1" ]; then
  echo "Usage: $0 <file name>"
  exit 1
fi

# Base directory of your Java packages
BASE_DIR="src/main/java"

# Find the directory containing the specified file
TARGET_DIR=$(find $BASE_DIR -type f -name "$1" | xargs -I {} dirname {} | uniq)

# Check if the file was found
if [ -z "$TARGET_DIR" ]; then
  echo "File not found: $1"
  exit 1
fi

# Navigate to the directory
cd "$TARGET_DIR"

# Optional: List the contents of the directory to confirm you are in the right place
ls -la

