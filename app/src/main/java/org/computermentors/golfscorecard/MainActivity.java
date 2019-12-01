package org.computermentors.golfscorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {

    public static final String PREFS_FILE = "org.computermentors.golfscorecard.preferences";
    private static final String KEY_STROKECOUNT = "key_strokecount";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Hole[] holes = new Hole[18];
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Initializing holes
        int strokes = 0;
        for (int i = 0; i < holes.length; i++){
            strokes = sharedPreferences.getInt(KEY_STROKECOUNT + i, 0);
            holes[i] = new Hole("Hole " + (i + 1) + " :", strokes);
        }

        listAdapter = new ListAdapter(this, holes);
        setListAdapter(listAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int i = 0; i < holes.length; i++){
            editor.putInt(KEY_STROKECOUNT + i, holes[i].getStrokeCount());
        }
        editor.apply();
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
        if (id == R.id.action_clear_strokes) {
            editor.clear();
            editor.apply();

            for(Hole hole : holes){
                hole.setStrokeCount(0);
            }
            listAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
