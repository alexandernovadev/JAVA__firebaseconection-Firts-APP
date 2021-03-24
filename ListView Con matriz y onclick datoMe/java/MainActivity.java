package com.example.personal.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView tv1;
    private ListView lv1;

    private String nombres[] =
            {"Carlos","Samuel","Camilo","Pedro","Sarita"};
    private String edades[]=
            {"15","21","35","52","25"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv_1);
        lv1 = (ListView)findViewById(R.id.lv_items);

        ArrayAdapter <String> adapter =
                new ArrayAdapter<String>(this, R.layout.listview_personalizado, nombres);

        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv1.setText("La edad de " + lv1.getItemAtPosition(i) + " es " + edades[i] + " años" );
            }
        });
    }
}
