package com.aksoftwaresolution.navigationview;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;

public class BottomNavigationView extends View {


    private Paint backgroundPaint, circlePaint;
    private int backgroundColor, circleColor, iconColor, selectedIconColor;

    private int itemCount = 3;
    private int selectedIndex = 0;
    private float circleX, circleY;

    private Drawable[] icons;
    private Menu menu;

    // bubble radius
    private float arcRadius = 50f;
    private float animatedArcRadius = arcRadius;

    public BottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MeowCloneNavigation);
        backgroundColor = ta.getColor(R.styleable.MeowCloneNavigation_mcn_backgroundColor, Color.parseColor("#FFC107"));
        circleColor = ta.getColor(R.styleable.MeowCloneNavigation_mcn_circleColor, Color.parseColor("#009688"));
        iconColor = ta.getColor(R.styleable.MeowCloneNavigation_mcn_iconColor, Color.GRAY);
        selectedIconColor = ta.getColor(R.styleable.MeowCloneNavigation_mcn_selectedIconColor, Color.WHITE);

        int menuResId = ta.getResourceId(R.styleable.MeowCloneNavigation_menu, -1);
        if (menuResId != -1) {
            loadMenu(context, menuResId);
        }

        ta.recycle();

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(backgroundColor);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(circleColor);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @SuppressLint("RestrictedApi")
    private void loadMenu(Context context, int menuResId) {
        menu = new MenuBuilder(context);
        MenuInflater inflater = new SupportMenuInflater(context);
        inflater.inflate(menuResId, menu);

        itemCount = menu.size();
        icons = new Drawable[itemCount];

        for (int i = 0; i < itemCount; i++) {
            MenuItem item = menu.getItem(i);
            icons[i] = item.getIcon();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float itemWidth = width / itemCount;

        float barHeight = 100;
        float barTop = height - barHeight;

        // Circle center position
        circleY = barTop;
        if (circleX == 0) {
            circleX = itemWidth / 2;
        }

        // ------ Bottom Bar with Carve ------
        Path barPath = new Path();
        barPath.moveTo(0, barTop);

        for (int i = 0; i < itemCount; i++) {
            float centerX = itemWidth * i + (itemWidth / 2);

            if (i == selectedIndex) {
                float carveRadius = animatedArcRadius + 30;
                barPath.lineTo(centerX - carveRadius, barTop);
                barPath.quadTo(centerX, barTop - carveRadius, centerX + carveRadius, barTop);
            }
            barPath.lineTo(itemWidth * (i + 1), barTop);
        }

        barPath.lineTo(width, height);
        barPath.lineTo(0, height);
        barPath.close();

        canvas.drawPath(barPath, backgroundPaint);

        // ------ Bubble Circle ------
        canvas.drawCircle(circleX, barTop, animatedArcRadius, circlePaint);

        // ------ Icons ------
        for (int i = 0; i < itemCount; i++) {
            float centerX = itemWidth * i + (itemWidth / 2);
            float centerY = barTop + (barHeight / 2f);

            Drawable icon = icons[i];
            if (icon != null) {
                int size = 40;
                int leftIcon = (int) (centerX - size / 2);
                int topIcon = (i == selectedIndex)
                        ? (int) (barTop - size / 2)
                        : (int) (centerY - size / 2);
                int rightIcon = (int) (centerX + size / 2);
                int bottomIcon = topIcon + size;

                icon.setBounds(leftIcon, topIcon, rightIcon, bottomIcon);
                icon.setTint(i == selectedIndex ? selectedIconColor : iconColor);
                icon.draw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float width = getWidth();
            float itemWidth = width / itemCount;
            int index = (int) (event.getX() / itemWidth);
            selectItem(index);
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void selectItem(int index) {
        selectedIndex = index;
        float width = getWidth();
        float itemWidth = width / itemCount;
        float targetX = itemWidth * index + (itemWidth / 2);

        // Move animation
        ValueAnimator moveAnim = ValueAnimator.ofFloat(circleX, targetX);
        moveAnim.setDuration(400);
        moveAnim.addUpdateListener(animation -> {
            circleX = (float) animation.getAnimatedValue();
            invalidate();
        });
        moveAnim.start();

        // Size animation (pop effect)
        ValueAnimator sizeAnim = ValueAnimator.ofFloat(40f, arcRadius);
        sizeAnim.setDuration(300);
        sizeAnim.addUpdateListener(animation -> {
            animatedArcRadius = (float) animation.getAnimatedValue();
            invalidate();
        });
        sizeAnim.start();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    // Optional: Dynamic icons set করার জন্য
    public void setIcons(Drawable[] newIcons) {
        if (newIcons != null && newIcons.length > 0) {
            icons = newIcons;
            itemCount = newIcons.length;
            invalidate();
        }
    }

    // Optional: Getter for menu
    public Menu getMenu() {
        return menu;
    }
}
