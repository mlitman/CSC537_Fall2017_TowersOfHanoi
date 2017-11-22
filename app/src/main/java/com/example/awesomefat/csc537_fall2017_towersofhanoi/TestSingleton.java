package com.example.awesomefat.csc537_fall2017_towersofhanoi;

/**
 * Created by awesomefat on 11/21/17.
 */

class TestSingleton
{
    private static final TestSingleton ourInstance = new TestSingleton();

    static TestSingleton getInstance() {
        return ourInstance;
    }

    private TestSingleton() {
    }

    public MainActivity ma;
}
