package com.gmail.studios.co.fiish.juggle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;

enum Type {RED, GREEN, BLUE}

public class Ball {
    public Type mType;

    public Vector2 mPos, mVelocity;
    public Texture mRed, mGreen, mBlue;
    public Sprite mSprite;
    public Viewport mViewport;

    public float mRadius;
    public final float mDrag = 1.0f;
    public final float mGravity = -2000.0f;

    public boolean equals(Ball ball) {
        if (ball == null) {
            return false;
        }
        if (ball.getClass() != this.getClass()) {
            return false;
        }
        if (!(this.mType == ball.mType)) {
            return false;
        }
        if (!(this.mRadius == ball.mRadius)) {
            return false;
        }
        return true;
    }

    public Ball(Type type, Viewport viewport) {
        this.mType = type;
        this.mViewport = viewport;
    }

    public void init() {
        mVelocity = new Vector2(0, 0);

        mRed = new Texture(Gdx.files.internal("redball.png"));
        mGreen = new Texture(Gdx.files.internal("greenball.png"));
        mBlue = new Texture(Gdx.files.internal("blueball.png"));

        switch (mType) {
            case RED: mSprite = new Sprite(mRed);
                mRadius = mRed.getHeight() / 2;
                mPos = new Vector2(mViewport.getScreenWidth() / 2, mViewport.getScreenHeight() / 2);
            break;
            case GREEN: mSprite = new Sprite(mGreen);
                mRadius = mGreen.getHeight() / 2;
                mPos = new Vector2(mViewport.getScreenWidth() / 4, mViewport.getScreenHeight() / 4);
            break;
            case BLUE: mSprite = new Sprite(mBlue);
                mRadius = mBlue.getHeight() / 2;
                mPos = new Vector2(mViewport.getScreenWidth() / 4 * 3, mViewport.getScreenHeight() / 4);
            break;
        }

        mSprite.setPosition(mPos.x - mSprite.getWidth() / 2, mPos.y - mSprite.getHeight() / 2);
    }

    public void draw(SpriteBatch batch) {
        mSprite.draw(batch);
    }

    public void update(float delta) {
        mVelocity.x -= delta * mDrag * mVelocity.x;
        if (mPos.y - mRadius > 0) {
            mVelocity.y = mVelocity.y + mGravity * delta;
        }

        if (mVelocity.y > 0 && mVelocity.len() > 2000) {
            mVelocity = mVelocity.clamp(0, 2000);
        } else {
            mVelocity = mVelocity.clamp(0, 6000);
        }

        //Gdx.app.log("Velocity", "" + mVelocity.len());

        mPos.x += mVelocity.x * delta;
        mPos.y += mVelocity.y * delta;

        mSprite.setPosition(mPos.x - mSprite.getWidth() / 2, mPos.y - mSprite.getHeight() / 2);
    }

    public void dispose() {
        mRed.dispose();
        mGreen.dispose();
        mBlue.dispose();
    }

}
