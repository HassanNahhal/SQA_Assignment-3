package com.example.hassannahhal.starwars;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {


    private TextView idTextView, trooperIdTextView, plantNameTextView;
    private Spinner plantsSpinner;
    private ListView plantsListView;
    private SQLController dbcon;
    private Button sendTrooperButton;
    private SimpleCursorAdapter adapter;


    private String spinnerChoice;
    private static final String[] plants = {"", "Alderaan", "Bespin", "Coruscant", "D'Qar", "Dagobah", "Endor", "Geonosis",
            "Hosnian Prime", "Hoth", "Jakku", "Kamino", "Kashyyyk", "Lothal", "Mustafar", "Naboo", "Sullust", "Takodana",
            "Tatooine", "Utapau", "Yavin", "Yavin 4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbcon = new SQLController(this);


        plantsSpinner = (Spinner) findViewById(R.id.plantsSpinner);
        plantsListView = (ListView) findViewById(R.id.plantsListView);
        refresh();

        plantsSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, plants);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plantsSpinner.setAdapter(aa);


        sendTrooperButton = (Button) findViewById(R.id.button);
        sendTrooperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbcon.open();

                Troopers trooper = new Troopers();
                trooper.setId(trooper.getId());
                trooper.setPlantName(spinnerChoice);
                dbcon.insertData(trooper.getPlantName());
                Log.d("dbcon.getLastId():  ", " " + dbcon.getLastId());
                Log.d("Trooper planet", trooper.getPlantName());
                refresh();
                dbcon.close();

            }
        });

    }

    public void refresh() {
        dbcon.open();

        final Cursor cursor = dbcon.readall();
        String[] from = new String[]{DBHelper.TROOPER_ID,
                DBHelper.TROOPER_STAR};


        int[] to = new int[]{R.id.trooperIdTextView, R.id.plantNameTextView};
        adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.trooper, cursor, from, to)

        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                cursor.moveToPosition(position);
                final View row = super.getView(position, convertView, parent);
                return row;

            }
        };

        adapter.notifyDataSetChanged();
        plantsListView.setAdapter(adapter);
        dbcon.close();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerChoice = plants[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        spinnerChoice = "";
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
