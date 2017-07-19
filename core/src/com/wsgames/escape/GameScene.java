package com.wsgames.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by wietze on 7/17/2017.
 */

public class GameScene extends ScreenAdapter implements GestureDetector.GestureListener{

    Escape game;
    SpriteBatch batch;

    Sprite background;
    Sprite grid;
    Sprite player;

    ArrayList<Sprite> walls = new ArrayList<Sprite>();

    float gridX = 101;
    float gridY = 226;

    int x = 2;
    int y = 1;

    PathFind pf;

    public GameScene(Escape game){
        this.game = game;

        background = new Sprite(game.assetManager.get("bg.png", Texture.class));


        grid = new Sprite(game.assetManager.get("grid.png",Texture.class));
        grid.setPosition(game.screenWidth/2 - (grid.getWidth()/2),
                game.screenHeight/2 - (grid.getHeight()/2));

        player = new Sprite(game.assetManager.get("player.png",Texture.class));
        moveTo(player,x,y);


        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
    }
    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        game.viewport.update(width,height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor( 1, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        batch.setProjectionMatrix(game.camera.combined);

        batch.begin();
        background.draw(batch);
        grid.draw(batch);
        player.draw(batch);
        for(Sprite s:walls){
            s.draw(batch);
        }
        batch.end();



    }
    void initTest(int startX,int startY,int targetX, int targetY){
        pf = new PathFind();
        pf.setStart(new Node(startX,startY));
        pf.setTarget(new Node(targetX,targetY));
        pf.calcCost();



    }

    void findPath(){
        ArrayList<Node> test = pf.findPath();
        walls.clear();
        for (Node n:test) {
            Sprite w = new Sprite(game.assetManager.get("wall.png",Texture.class));
            moveWall(w,n.x,n.y);
            walls.add(w);
        }
    }



    public void moveTo(Sprite player,int x,int y){
       player.setPosition(gridX + (x*50),
               gridY + (y*50));
        initTest(x,y,10,10);
        findPath();
    }
    public void moveWall(Sprite wall,int x,int y){
        wall.setPosition(gridX + (x*50),
                gridY + (y*50));

        

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(velocityX>2000){
            x = x+1;
        }else if(velocityX<-2000){
            x = x-1;
        }else if(velocityY>2000){
            y = y-1;
        }else if(velocityY<-2000){
            y = y+1;
        }
        System.out.println(velocityY);
        moveTo(player,x,y+1);
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
