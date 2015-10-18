package com.example.administrator.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int SHOW_SUBACTIVITY = 1;
    public static final int SHOW_RESULTOK = 1;
    public static final int SHOW_RESULTCANCEL = 0;
    private Button btnMyOtherActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMyOtherActivity = (Button) findViewById(R.id.btnMyOtherActivity);
        btnMyOtherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startMyOtherActivity();
                startSubActivity();
            }
        });
    }


    // Start MyOtherACtivity
    private void startMyOtherActivity() {
        Intent intent = new Intent(MainActivity.this, MyOtherActivity.class);
        startActivity(intent);
    }


    // Start sub activity for a result
    private void startSubActivity() {
        Intent intent = new Intent(MainActivity.this, MyOtherActivity.class);
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }

    /**
     * get result from subActivity
     * @param requestCode : to launch the return subActivity
     * @param resultCode : to indicate result
     * @param data : to return packet data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SHOW_SUBACTIVITY) {
            // check requestCode
            //Log.i("info", String.valueOf(requestCode));
            switch (resultCode) {
                case SHOW_RESULTOK:
                    getData(data);
                    break;
                case SHOW_RESULTCANCEL:
                    Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
                    break;
                default: break;
            }
        }
    }

    // get data from subACtivity
    private void getData(Intent data) {
        try {
            // get data in bundle
            Bundle bundle = data.getBundleExtra("packet");
            String s = bundle.getString("hello");
            // get data from intent
            String s1 = data.getStringExtra("MyName");
            Toast.makeText(this, "Bundle data : " + s +
                    ", intent data : " + s1, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("getData",e.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
