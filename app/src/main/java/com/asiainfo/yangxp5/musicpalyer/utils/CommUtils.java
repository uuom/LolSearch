package com.asiainfo.yangxp5.musicpalyer.utils;


import android.content.Context;

import java.lang.reflect.Field;

/**
 * Created by uuom on 15-12-24.
 */
public class CommUtils {

    public int getStatusBarHeight(Context context){
        try
        {
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  context.getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
