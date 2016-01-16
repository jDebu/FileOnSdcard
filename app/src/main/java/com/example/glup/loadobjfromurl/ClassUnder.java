package com.example.glup.loadobjfromurl;

import android.content.Context;

/**
 * Created by Glup on 15/01/16.
 */
public class ClassUnder {
    private String helloWorldString;

    public ClassUnder(Context mMockContext) {
        helloWorldString=mMockContext.getString(R.string.hello_word);
    }

    public String getHelloWorldString() {
        return helloWorldString;
    }
}
