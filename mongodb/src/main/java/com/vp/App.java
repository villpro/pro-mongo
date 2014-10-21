package com.vp;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Spark.get(new Route("/hello") {
			@Override
			public Object handle(Request arg0, Response arg1) {
				// TODO Auto-generated method stub
				return "hello spark";
			}
		});
    }
}
