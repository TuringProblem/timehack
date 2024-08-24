package com.application;


import data.SHA;

public class Engine {

  public static void main(String[] args) {
    String myPassword = "Hello, world";
    SHA.showData();
    System.out.println("\n\n");
    System.out.printf("Password: %s\n", myPassword);
    SHA.printPasswordWithEncryption(myPassword);
  }

}
