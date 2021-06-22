package in.bmsit.sixthsem.program4;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    Button cw;
    Timer mt;
    Drawable dw;
    WallpaperManager wm;
    int img = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cw = findViewById(R.id.bSetWallpaper);
        mt = new Timer();
        wm = WallpaperManager.getInstance(this);
        cw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }
        });
    }

    private void setWallpaper(){
        mt.schedule(new TimerTask() {
            @Override
            public void run() {
                if(img == 1){
                    dw = getResources().getDrawable(R.drawable.image1);
                    img = 2;
                } else if(img == 2) {
                    dw = getResources().getDrawable(R.drawable.image2);
                    img = 3;
                } else if(img == 3) {
                    dw = getResources().getDrawable(R.drawable.image3);
                    img = 4;
                } else if(img == 4) {
                    dw = getResources().getDrawable(R.drawable.image4);
                    img = 5;
                } else if(img == 5){
                    dw = getResources().getDrawable(R.drawable.image5);
                    img = 1;
                }
                Bitmap wallp = ((BitmapDrawable)dw).getBitmap();
                try {
                    wm.setBitmap(wallp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);
    }
}