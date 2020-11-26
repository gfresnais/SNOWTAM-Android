package com.ensim.snowtam.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ensim.snowtam.R;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    /**
     * Sends the content of the text fields for a search
     */
    public void onClickSearch(View v) {
        EditText airfield1 = (EditText) findViewById(R.id.editAirfield1);
        EditText airfield2 = (EditText) findViewById(R.id.editAirfield2);
        EditText airfield3 = (EditText) findViewById(R.id.editAirfield3);
        EditText airfield4 = (EditText) findViewById(R.id.editAirfield4);

        Intent intent = new Intent(this, ResultsActivity.class);

        intent.putExtra("airfield1", airfield1.getText().toString());
        intent.putExtra("airfield2", airfield2.getText().toString());
        intent.putExtra("airfield3", airfield3.getText().toString());
        intent.putExtra("airfield4", airfield4.getText().toString());

        startActivity(intent);
    }
}