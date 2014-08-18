package com.kekwanu.jniexecutionspeedtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends Activity {
    private final String TAG = MainActivity.class.getCanonicalName();
    private TextView tipPctTextView;
    private TextView tipAmountTextView;
    private TextView totalAmountTextView;
    private Button calcTipAmountButton;
    private EditText billAmountTextView;
    private TextView jniElapsedTextView;
    private TextView javaElapsedTextView;
    private Context context;

    static {
        System.loadLibrary("JNIExecutionSpeedTest"); // "JNIExecutionSpeedTest.dll" in Windows, "libJNIExecutionSpeedTest.so" in Unixes
    }

    // A native method that returns a Java String to be displayed on the
    // TextView
    public native double elapsedTimeFromJNI(double billAmount);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        billAmountTextView  = (EditText)this.findViewById(R.id.billAmtEditText);
        calcTipAmountButton = (Button)  this.findViewById(R.id.calcTipButton);
        tipPctTextView      = (TextView)this.findViewById(R.id.tipPctTextView);
        tipAmountTextView   = (TextView)this.findViewById(R.id.tipAmtTextView);
        totalAmountTextView = (TextView)this.findViewById(R.id.totalAmtTextView);
        jniElapsedTextView  = (TextView)this.findViewById(R.id.jniElapsedTextView);
        javaElapsedTextView = (TextView)this.findViewById(R.id.javaElapsedTextView);


        calcTipAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (billAmountTextView.getText().toString().equals("")){
                    Toast.makeText(context, "You did not enter a bill amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                calculateTip();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void calculateTip(){
        Log.i(TAG, "calculateTip");

        double billAmount = Double.parseDouble( billAmountTextView.getText().toString() );

        long startTime = System.currentTimeMillis();

        double tip   = billAmount * 0.17;
        double total = tip + billAmount;

        DecimalFormat df   = new DecimalFormat("#.##");
        String tipAmount   = "$" + df.format(tip);
        String totalAmount = "$" + df.format(total);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        DecimalFormat df2   = new DecimalFormat("#.######");

        javaElapsedTextView.setText(df2.format(elapsedTime)+"ms");
        tipAmountTextView.setText(tipAmount);
        totalAmountTextView.setText(totalAmount);

        double rawJNITime = elapsedTimeFromJNI(billAmount);
        Log.i(TAG, "calculateTip - rawJNITime: "+rawJNITime);

        String formattedJNITime = df2.format(rawJNITime) + "ms";
        jniElapsedTextView.setText(formattedJNITime);
    }
}
