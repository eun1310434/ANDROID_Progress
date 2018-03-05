/**
 * 05.03.2018
 * eun1310434@naver.com
 * https://blog.naver.com/eun1310434
 * 참고) Do it android programming
 */
package com.eun1310434.progress;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView dataItem;

    ProgressDialog dialog;
    SeekBar seekBar;
    Button button2;

    LinearLayout seekBarPanel;
    int brightness = 100;
    TextView seekBarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(50);


        dataItem = (TextView) findViewById(R.id.dataItem);
        dataItem.setText(progressBar.getProgress() + "%");


        //
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog("show ProgressDialog");
            }
        });


        //
        seekBarText = (TextView) findViewById(R.id.seekBarText);
        seekBarText.setText("SeekBar : " + progressBar.getProgress());
        seekBarPanel = (LinearLayout) findViewById(R.id.seekBarPanel);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seekBarPanel.getVisibility() == View.GONE){
                    seekBarPanel.setVisibility(View.VISIBLE);
                    button2.setText("Close SeekBar");
                }else{
                    seekBarPanel.setVisibility(View.GONE);
                    button2.setText("Open SeekBar");
                }
            }
        });

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(progressBar.getProgress());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setBrightness(i);
                seekBarText.setText("SeekBar : " + i);
                progressBar.setProgress(i);
                dataItem.setText(i+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setBrightness(int value) {
        if (value < 10) { // 완전히 빛을 없에면 없어짐

            value = 10;
        } else if (value > 100) {
            value = 100;
        }

        brightness = value;


        WindowManager.LayoutParams params = getWindow().getAttributes();//화면 밝기조정
        params.screenBrightness = (float) value / 100;
        getWindow().setAttributes(params);
    }


    private void showProgressDialog(String message) {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //dialog.setSecondaryProgress(50);
        dialog.setMessage(message);
        dialog.show();
    }
}
