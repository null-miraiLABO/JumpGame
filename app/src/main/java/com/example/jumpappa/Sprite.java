package com.example.jumpappa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public class Sprite extends View {
    //protected　継承する可能性のある変数。
    protected Bitmap image;
    protected double px;
    protected double py;
    protected int w;
    protected int h;
    protected double vx;
    protected double vy;
    protected double ay;
    protected int visible;
    protected int frmcnt;
    private double sx = 1;
    private double sy = 1;
    private double g6sx = 1.6875;
    private double g6sy = 1.5;

    public Sprite(Context context, int id, int px, int py, int w, int h) {
        //位置 px,py ;大きさ w,h
        super(context);
        image = BitmapFactory.decodeResource(context.getResources(), id);
        this.px = px;
        this.py = py;
        this.w = w;
        this.h = h;
        frmcnt = 0;

        //36-40はエミュレーターが720*1280なので、実機で動かした時の誤差ための調整。
        boolean g6 = false;
        if (g6) {
            sx = g6sx;
            sy = g6sy;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //src 16枚の画像のどれを表示するのか。　dst どこに表示するのか。
        Rect src = getSrc();
        //Rect(top,bottom,left,right) 四角形(位置と面積)
        Rect dst = new Rect((int) (px * sx), (int) (py * sy), (int) ((px + w) * sx), (int) ((py + h) * sy));
        canvas.drawBitmap(image, src, dst, null);
    }

    protected Rect getSrc() {
        return new Rect(0, 0, w, h);//onDrawの呼び出し。
    }

    public void update() {
        frmcnt++;
        this.invalidate();
    }

    public int getX(double x) {
        return (int) (x / sx);
    }

    public int getY(double y) {
        return (int) (y / sy);
    }

}

