package c.aprendiendoando.pasarventanas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ventanados extends AppCompatActivity {

    Intent miintent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_ventanados);

    }

    public void btnant2(View view) {
        miintent = new Intent(this, MainActivity.class);
        startActivity(miintent);

    }

    public void btnSig2(View view) {
        miintent = new Intent(this, ventanatres.class);
        startActivity(miintent);

    }

}
