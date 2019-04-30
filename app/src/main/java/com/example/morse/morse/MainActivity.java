package com.example.morse.morse;

import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import github.nisrulz.lantern.Lantern;


public class MainActivity extends AppCompatActivity {

    private EditText input;
    private Button convert, stop, flash;
    private TextView out;
    Map <Character, String> map = new HashMap<Character, String>();
    private Camera cam;
    public Lantern lantern;
    public final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        map.put('A', "._");
        map.put('B', "_...");
        map.put('C', "_._.");
        map.put('D', "_..");
        map.put('E', ".");
        map.put('F', ".._.");
        map.put('G', "__.");
        map.put('H', "....");
        map.put('I', "..");
        map.put('J', ".___");
        map.put('K', "_._.");
        map.put('L', "._..");
        map.put('M', "__");
        map.put('N', "_.");
        map.put('O', "___");
        map.put('P', ".__.");
        map.put('Q', "__._");
        map.put('R', "._.");
        map.put('S', "...");
        map.put('T', "_");
        map.put('U', ".._");
        map.put('V', "..._");
        map.put('W', ".__");
        map.put('X', "_.._");
        map.put('Y', "_.__");
        map.put('Z', "__..");

        map.put('a', "._");
        map.put('b', "_...");
        map.put('c', "_._.");
        map.put('d', "_..");
        map.put('e', ".");
        map.put('f', ".._.");
        map.put('g', "__.");
        map.put('h', "....");
        map.put('i', "..");
        map.put('j', ".___");
        map.put('k', "_._.");
        map.put('l', "._..");
        map.put('m', "__");
        map.put('n', "_.");
        map.put('o', "___");
        map.put('p', ".__.");
        map.put('q', "__._");
        map.put('r', "._.");
        map.put('s', "...");
        map.put('t', "_");
        map.put('u', ".._");
        map.put('v', "..._");
        map.put('w', ".__");
        map.put('x', "_.._");
        map.put('y', "_.__");
        map.put('z', "__..");

        map.put('1', ".____");
        map.put('2', "..___");
        map.put('3', "...__");
        map.put('4', "...._");
        map.put('5', ".....");
        map.put('6', "_....");
        map.put('7', "__...");
        map.put('8', "___..");
        map.put('9', "____.");
        map.put('0', "_____");

        input = (EditText) findViewById(R.id.input);
        out = (TextView)findViewById(R.id.out);
        convert = (Button)findViewById(R.id.convert);
        lantern = new Lantern(this).checkAndRequestSystemPermission().observeLifecycle(this);
        if(!lantern.initTorch()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
        }



        flash = (Button)findViewById(R.id.flash);
        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String outMorse = out.getText().toString();
                for(int i = 0; i < outMorse.length(); i++) {
                    if(outMorse.charAt(i) == '.') {
                        lantern.alwaysOnDisplay(true).fullBrightDisplay(true).enableTorchMode(true).pulse(false);
                        Log.e("flash", ".");
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lantern.alwaysOnDisplay(false).fullBrightDisplay(false).enableTorchMode(false).pulse(false);
                    }else if(outMorse.charAt(i)=='_'){
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e("flash", "_");
                        lantern.alwaysOnDisplay(false).fullBrightDisplay(false).enableTorchMode(false).pulse(false);
                    }else{
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lantern.alwaysOnDisplay(false).fullBrightDisplay(false).enableTorchMode(false).pulse(false);
                    }
                }
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String in = input.getText().toString();
                String outMorse = "";
                for(int i  =0; i < in.length(); i++){
                    if(in.charAt(i) == ' ') outMorse+=" ";
                    else outMorse += map.get(in.charAt(i));
                }

                out.setText(outMorse);

            }
        });

    }

    @Override
    protected void onDestroy() {
        lantern.cleanup();
        super.onDestroy();
    }
}
