package in.bmsit.sixthsem.program2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button start;
    Button stop;
    Button reset;
    int counter = 1;
    Handler myh = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.displayCount);
        start = findViewById(R.id.buttonStart);
        stop = findViewById(R.id.buttonStop);
        reset = findViewById(R.id.buttonReset);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myh.postDelayed(countTimer, 0);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myh.removeCallbacks(countTimer);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
    private final Runnable countTimer = new Runnable() {
        @Override
        public void run() {
            tv.setText("" + counter);
            myh.postDelayed(this,1000);
            counter++;
        }
    };
}