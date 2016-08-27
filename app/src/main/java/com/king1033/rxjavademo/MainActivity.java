package com.king1033.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * RxJava的基本使用
 * 步骤：
 * 1、导包：compile 'io.reactivex:rxjava:1.1.9'
 * 2、创建一个观察者对象
 * 3、创建一个被观察者对象
 * 4、建立观察者对象与被观察者对象之间的关联关系
 */
public class MainActivity extends AppCompatActivity {
    Subscriber<String> subscriber;
    Observable<String> observable;
    private String TAG = "King1033";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // method1();
        // method2();
        //method3();
        //method4();
    }

    private void method4() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("zhangsan");
            }
        }).map(new Func1<String, Object>() {
            @Override
            public Object call(String s) {
                return s + ":lisi";
            }
        }).map(new Func1<Object, String>() {
            @Override
            public String call(Object o) {
                return "----" + o;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "call: " + s);
            }
        });
    }


    private void method3() {
        Observable.just("zhangsan")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext: " + s);
                    }
                });
    }

    private void method2() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        })
                .observeOn(Schedulers.newThread())//表示观察者运行在子线程中
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.i(TAG, "onNext: " + o);
                        Log.i(TAG, "call1111: " + Thread.currentThread().getName());
                    }
                });


    }

    private void method1() {
        //观察者对象
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "subscriber ----->  onNext: " + Thread.currentThread().getName());

            }
        };

        //被观察者对象
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("ZhangSan");
                Log.i(TAG, "Observable--->onNext: " + Thread.currentThread().getName());
            }
        });
    }

    public void click(View view) {
        //关联
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())//开启一个子线程
                .subscribe(subscriber);
    }
}
