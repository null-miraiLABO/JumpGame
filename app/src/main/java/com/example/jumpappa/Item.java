package com.example.jumpappa;

import android.content.Context;
import android.graphics.Rect;

public class Item extends Sprite{
    public Item(Context context,int px,int py){
        super(context,R.drawable.star,px,py,32,32);
        visible=1;
        frmcnt=0;
    }

    @Override
    protected Rect getSrc(){
        int x=0;
        if(visible==1){
            x=w*((frmcnt/10)%4);
        }
        return new Rect(x,0,x+w,h);
    }

    public void update(Pc pc){
        if(hitTest(pc.getFootArea())){
            visible=0;
            pc.getItem();
        }
    }

    private boolean hitTest(Rect obj){
        boolean sts=false;
        Rect me=new Rect((int)px,(int)py,(int)px+w,(int)py+h);
        if (me.left<=obj.right && obj.left<=me.right){
            if (me.top<=obj.bottom && obj.top<=me.bottom) {
                sts = true;
            }
        }
        return sts;
    }

}
