package com.ensim.snowtam.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ensim.snowtam.R;

public class HomePageActivity extends AppCompatActivity {

    private EditText airfield1;
    private EditText airfield2;
    private EditText airfield3;
    private EditText airfield4;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize all the fields
        airfield1 = (EditText) findViewById(R.id.editAirfield1);
        airfield2 = (EditText) findViewById(R.id.editAirfield2);
        airfield3 = (EditText) findViewById(R.id.editAirfield3);
        airfield4 = (EditText) findViewById(R.id.editAirfield4);

        // Initialize the intent for ResultsActivity
        intent = new Intent(this, ResultsActivity.class);
    }

    /**
     * Sends the content of the text fields for a search
     * @param v
     */
    public void onClickSearch(View v) {
        // If the fields are empty, show an error
        if( airfield1.getText().toString().isEmpty() && airfield2.getText().toString().isEmpty() &&
                airfield3.getText().toString().isEmpty() && airfield4.getText().toString().isEmpty()) {
            Log.e("Home_AllEmpty", "All fields are empty");
            Toast.makeText(this, getString(R.string.error_empty_fields), Toast.LENGTH_LONG).show();
        } else {
            // Else, go to the search results
            int fields = checkfields();

            // If there are data, go to the search results page
            if( (intent.getExtras() != null) && (fields == intent.getExtras().size()) ) {
                Log.i("Home_AllOk", "Start the ResultsActivity");
                startActivity(intent);
            }
        }
    }

    /**
     * Checks if the fields are filled and contain 4 letters
     * @return
     */
    private int checkfields() {
        int fields = 0;

        if(!airfield1.getText().toString().isEmpty()) {
            fields++;
            if( airfield1.getText().length() < 4 ) {
                Log.e("Home_air1", "Airfield1 is less than 4 letters");
                Toast.makeText(this, getString(R.string.error_length_fields), Toast.LENGTH_LONG).show();
            } else {
                Log.i("Home_air1", "Airfield1 if ok");
                intent.putExtra("airfield1", airfield1.getText().toString());
            }
        }

        if(!airfield2.getText().toString().isEmpty()) {
            fields++;
            if( airfield2.getText().length() < 4 ) {
                Log.e("Home_air2", "Airfield2 is less than 4 letters");
                Toast.makeText(this, getString(R.string.error_length_fields), Toast.LENGTH_LONG).show();
            } else {
                Log.i("Home_air2", "Airfield2 if ok");
                intent.putExtra("airfield2", airfield2.getText().toString());
            }
        }

        if(!airfield3.getText().toString().isEmpty()) {
            fields++;
            if( airfield3.getText().length() < 4 ) {
                Log.e("Home_air3", "Airfield3 is less than 4 letters");
                Toast.makeText(this, getString(R.string.error_length_fields), Toast.LENGTH_LONG).show();
            } else {
                Log.i("Home_air3", "Airfield3 if ok");
                intent.putExtra("airfield3", airfield3.getText().toString());
            }
        }

        if(!airfield4.getText().toString().isEmpty()) {
            fields++;
            if( airfield4.getText().length() < 4 ) {
                Log.e("Home_air4", "Airfield4 is less than 4 letters");
                Toast.makeText(this, getString(R.string.error_length_fields), Toast.LENGTH_LONG).show();
            } else {
                Log.i("Home_air4", "Airfield4 if ok");
                intent.putExtra("airfield4", airfield4.getText().toString());
            }
        }
        return fields;
    }
}