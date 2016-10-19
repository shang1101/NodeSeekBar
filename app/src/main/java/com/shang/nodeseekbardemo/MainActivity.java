package com.shang.nodeseekbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.shang.nodeseekbar.NodeSeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NodeSeekBar nodeSeekBar = (NodeSeekBar)findViewById(R.id.nodeseekbar);
        nodeSeekBar.setNodeRadius(20);
        nodeSeekBar.setNodeCount(5);
        nodeSeekBar.setOnChoiceListener(new NodeSeekBar.OnSeekChoiceListener() {
            @Override
            public void onSeekChoosen(int index) {
                Toast.makeText(getApplicationContext(),"You are now choosing position ："+index,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
