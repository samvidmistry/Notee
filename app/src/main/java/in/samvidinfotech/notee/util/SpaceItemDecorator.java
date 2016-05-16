package in.samvidinfotech.notee.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by samvidmistry on 16/5/16.
 */
public class SpaceItemDecorator extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecorator(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left += space;
        outRect.right += space;
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top += space;
        }
        outRect.bottom += space;
    }
}
