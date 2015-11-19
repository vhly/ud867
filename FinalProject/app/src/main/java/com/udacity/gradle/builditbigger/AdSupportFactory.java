package com.udacity.gradle.builditbigger;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/11/19
 * Email: vhly@163.com
 */
public class AdSupportFactory {

    private static AdSupport adSupportInstance;

    public static AdSupport getAdSupportInstance(){

        // Use for Free version
        if ("free".equals(BuildConfig.FLAVOR)){

            if(adSupportInstance == null){

                try {
                    Class<?> aClass = Class.forName("com.udacity.gradle.builditbigger.FreeAdSupport");
                    adSupportInstance = (AdSupport) aClass.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }

        return  adSupportInstance;
    }

}
