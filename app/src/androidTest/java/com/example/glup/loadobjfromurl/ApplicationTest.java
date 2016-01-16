package com.example.glup.loadobjfromurl;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    @Test
    public void testFoo(){
        String url="http://orig02.deviantart.net/9d0c/f/2013/044/a/2/luffy_faces_by_constanza_chan14-d5utbf9.png";
        String p=url.substring(url.lastIndexOf("/")+1,url.length());
        assertEquals(p,"l");
        String obj="http://glup.com.pe/webgl/objs/M_A_Lenceria_Brasier_3_Standar.obj";
        String p3=obj.substring(obj.lastIndexOf("/")+1,obj.length());
        assertEquals(p3,"M");
    }

    public void testConvertFahrenheitToCelsius() {

    }
}