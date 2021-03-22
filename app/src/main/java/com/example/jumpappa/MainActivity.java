package com.example.jumpappa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

//Activityでタイトルバー消す。implementはユーザーが操作した時の処理。
public class MainActivity extends Activity implements Runnable{
    Pc pc;
    Npc[] npc;
    Gimmick[] gimmick;
    Item item;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout root=(ConstraintLayout)findViewById(R.id.root);

        npc=new Npc[3];
        for (int i=0;i<npc.length;i++){
            npc[i]=new Npc(getApplicationContext(),i*1280/npc.length);
            root.addView(npc[i]);
        }
        //雲
        gimmick=new Gimmick[5];
        gimmick[0]=new Gimmick(getApplicationContext(),400,400);
        gimmick[1]=new Gimmick(getApplicationContext(),750,300);
        gimmick[1].setVx(-2,350,814);
        gimmick[2]=new Gimmick(getApplicationContext(),850,170);
        gimmick[2].setVy(1,170,420);
        gimmick[3]=new Gimmick(getApplicationContext(),930,320);
        gimmick[3].setVy(-2,120,320);
        gimmick[4]=new Gimmick(getApplicationContext(),200,120);
        gimmick[4].setVx(2,200,1070);
        for (Gimmick obj:gimmick)root.addView(obj);

        //星
        item=new Item(getApplicationContext(),150,200);
        root.addView(item);

        pc=new Pc(getApplicationContext());//,R.drawable.pc,0,0,64,64);
        root.addView(pc);//root.addView(pc) は画面にキャラクターを表示。
        handler=new Handler();
        handler.postDelayed((Runnable)this,10);//thisはMainActivity
    }

    @Override
    //ナビゲーションバーを隠す。
    protected void onStart(){
        super.onStart();
        View rootView=getWindow().getDecorView();
        int uiOptions=View.SYSTEM_UI_FLAG_IMMERSIVE|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN;
        rootView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void run(){
        for (Gimmick obj:gimmick)obj.update(pc);
        item.update(pc);
        pc.update();
        for (Npc obj:npc)obj.update(pc);
        handler.postDelayed((Runnable)this,10);//10ミリ秒毎にrunを呼び出す。
    }
}

