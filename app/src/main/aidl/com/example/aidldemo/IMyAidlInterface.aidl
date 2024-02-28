// IMyAidlInterface.aidl
package com.example.aidldemo;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     //write a function of calculate sum of two numbers
    int calculateSum(int a,int b);
}