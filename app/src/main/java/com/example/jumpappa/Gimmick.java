package com.example.jumpappa;

import android.content.Context;
import android.graphics.Rect;

public class Gimmick extends Sprite{
    private Rect renge;

    public Gimmick(Context context,int px,int py){
        super(context,R.drawable.kumo,px,py,64,64);
        vy=0;
        vx=0;
        renge=new Rect(0,0,0,0);
        visible=1;
        frmcnt=0;
    }

    public void update(Pc pc) {
        px += vx;
        if (renge.right != 0) {
            if (px < renge.left) {
                px = renge.left;
                vx *= -1;
            }
            else if (px + w > renge.right) {
                px = renge.right - w;
                vx *= -1;
            }
        }
        py += vy;
        if (renge.bottom!=0) {
            if (px < renge.top) {
                px = renge.top;
                vx *= -1;
            } else if (px + h > renge.bottom) {
                py = renge.bottom - h;
                vy *= -1;
            }
        }
        if (hitTest(pc.getFootArea())){
            pc.getOnGimmick(py);
        }
        this.invalidate();
    }
    public void setVx(int vx,int min,int max){
        this.vx=vx;
        if (max>min && max<1280-w){
            if(min>0)renge.left=min;
            renge.right=max;
        }
    }

    public void setVy(int vy,int min,int max){
        this.vy=vy;
        if (max>min && max<800-h){
            if(min>0)renge.top=min;
            renge.bottom=max;
        }
    }

    public boolean hitTest(Rect obj){
        boolean sts=false;
        Rect me=new Rect((int)px+w,(int)py,(int)px+w/4*3,(int)py+h/2);
        if(me.left<=obj.right && obj.left<=me.right){
            if (me.top<=obj.bottom && obj.top<=me.bottom){
                sts=true;
            }
        }
        return sts;
    }
}
