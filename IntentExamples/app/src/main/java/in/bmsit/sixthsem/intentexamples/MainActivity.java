package in.bmsit.sixthsem.intentexamples;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button vtu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vtu = findViewById(R.id.vtu);
        vtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri web = Uri.parse("https://vtu.ac.in/");
                Intent myInt = new Intent(Intent.ACTION_VIEW, web);
                startActivity(myInt);
            }
        });
    }

    public void mBmsit(View v)
    {
        Uri web = Uri.parse("https://bmsit.ac.in/");
        Intent myInt = new Intent(Intent.ACTION_VIEW, web);
        startActivity(myInt);
    }
}