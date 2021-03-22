package com.example.jumpappa;

import android.content.Context;
import android.graphics.Rect;

public class Npc extends Sprite {
    public Npc(Context context, int px) {
        super(context, R.drawable.npc, px, 500, 64, 64);
        vx = -1;
        visible = 1;
        frmcnt = 0;
    }

    @Override
    protected Rect getSrc() {
        int x = 128;
        if (visible == 1) {
            x = 64 * ((frmcnt / 30) % 2);
        }
        return new Rect(x, 0, x + w, h);
    }

    public void update(Pc pc) {
        px += vx;

        if (hitTest(pc.getDamageArea())) {
            pc.setDamage();
        } else if (hitTest(pc.getFootArea())) {
            pc.setJump();
            visible = -60;
            vx = 0;
        }
        if (visible < 0) {
            visible++;
            if (visible == 0) {
                px += -60;
                py = 800;
                vx = -1;
            }
        }
        if (px < -w) {
            px = 1280;
            py = 500;
            visible = 1;
        }
        frmcnt++;
        this.invalidate();
    }

    private boolean hitTest(Rect obj) {//Rect=四角形。rect同士が重なったら当たり判定
        boolean sts = false;
        Rect me = new Rect((int) px, (int) py, (int) px + w, (int) py + h);
        //(px+W)-px=wで横幅を示す。
        if (me.left <= obj.right && obj.left <= me.right) {
            if (me.top <= obj.bottom && obj.top <= me.bottom) {
                sts = true;
            }
        }
        return sts;
    }
}
