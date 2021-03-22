package com.example.jumpappa;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Pc extends Sprite {
    private int jump;
    public Pc(Context context){
        super(context,R.drawable.pc,0,0,64,64);
        vx=0;//vx,vyはキャラクターの移動速度
        vy=0;
        visible=1;//キャラクターの生死
        frmcnt=0;
        jump=2;
    }

    //キャラクターの描画に関するプログラム。
    @Override
    protected Rect getSrc(){
        int x=0;
        int y=0;
        if(visible==1) {//生きていて
            if (jump == 0) {//ジャンプしてない時
                if (vx == 0) {//移動してない時
                    x = 64 * ((frmcnt / 30) % 2);//(frmcnt / 30)%2は0と1が交互に30回づつ並ぶ。*64
                    y = 0;
                } else {//移動している時
                    x = 64 * ((frmcnt / 16) % 4);
                    if (vx > 0) y = 128;
                    else y = 192;
                }
            } else {//ジャンプしている時
                if (vy < 2.5 && vy > -2.0) {
                    x = 192;
                    y = 0;
                } else if (vy < 0) {
                    x = 128;
                    y = 0;
                } else {
                    x = 0;
                    y = 64;
                }
            }
        }
        else if(visible==8){
            x=64;
            y=64;
        }
        else if(visible==9){
            x=192;
            y=64;
        }
        return new Rect(x,y,x+w,y+h);
    }
    //

    @Override
    public void update(){
        if(visible==1){
            ay=0.1;
            if(jump==1){
                if(vy<=0.1&&vy>=-0.1){
                    if(vx==0)vy=-4.5;
                    else vy=-4.0;
                }
                jump=2;
            }
            px+=vx;
            vy+=ay;
            py+=vy;

            if(py>=500){
                py=500;
                vy=0;
                ay=0;
                jump=0;
            }
            frmcnt++;

        }
        this.invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(visible==9){
                px=0;
                py=0;
                visible=1;
                frmcnt=0;
                jump=2;
            }
            else if(jump==0){
                int x=getX(event.getX());
                if(x<px){
                    vx=-2;
                }else if (px+w<x){
                    vx=2;
                }else {
                    int y=getY(event.getY());
                    if (py<=y&&y<=py+h){
                        jump=1;
                    }else{
                        vx=0;
                    }
                }
            }
        }
        return true;
    }

    public Rect getFootArea(){
        return new Rect((int)px+w/4,(int)py+h/4*3,(int)px+w/4*3,(int)py+h);
    }

    public Rect getDamageArea(){
        return new Rect((int)px,(int)py+h/4,(int)px+w,(int)py+h/4*3);
    }

    public void setJump(){
        if(vx==0){
            vy=-4.5;
        }
        else{
            vy=-4.0;
        }
    }

    public void setDamage(){
        visible=9;
        vx=0;
        vy=0;
    }

    public void getOnGimmick(double grd){
        if (vy>0.1){
            vx=0;
        }
        if(jump!=1 && vy>0){
            jump=0;
            py=grd-h+4;
            vy=0;
        }
    }

    public void getItem(){
        visible=8;
        vy=0;
    }
}
