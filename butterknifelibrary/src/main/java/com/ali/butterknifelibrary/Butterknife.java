package com.ali.butterknifelibrary;

import android.app.Activity;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mumu on 2018/7/5.
 */

public class Butterknife {

    public static void bind(final Activity activity) {
        Class<Activity> clazz = (Class<Activity>) activity.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            //判断当前得Field是否被BindView注解修饰
            if (field.isAnnotationPresent(BindView.class)) {
                //拿到这个注解对象
                BindView annotation = field.getAnnotation(BindView.class);
                int id = annotation.value();
                try {
                    //给控件赋值
                    View view = activity.findViewById(id);
                    //赋值
                    field.set(activity, view);
                    //是否是过时
                    if (field.isAnnotationPresent(MyDeclare.class)) {
                        if(view instanceof TextView){
                          TextView  textView = (TextView) view;
                            textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (final Method method : declaredMethods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(OnClick.class)) {
                OnClick onClickAnnotation = method.getAnnotation(OnClick.class);
                int[] values = onClickAnnotation.value();

                for (int id : values) {
                    final View view = activity.findViewById(id);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                method.invoke(activity, view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }


    }
}
