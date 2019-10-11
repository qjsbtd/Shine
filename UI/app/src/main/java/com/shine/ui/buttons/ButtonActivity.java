package com.shine.ui.buttons;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shine.ui.BaseActivity;
import com.shine.ui.R;

public class ButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    public void onLevelImgClick(View view) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null && drawable.getConstantState() instanceof DrawableContainer.DrawableContainerState) {
                int childCount = ((DrawableContainer.DrawableContainerState) drawable.getConstantState()).getChildCount();
                int level = (drawable.getLevel() + 1) % childCount;
                ((ImageView) view).setImageLevel(level);
            }
        }
    }
}
