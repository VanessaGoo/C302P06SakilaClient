package sg.edu.rp.c346.c302p06sakilaclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private ListView lvCategories;
    ArrayList<Category> alCategories = new ArrayList<Category>();
    ArrayAdapter<Category> aaCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCategories = (ListView) findViewById(R.id.listViewCategories);
        aaCategories = new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, alCategories);
        lvCategories.setAdapter(aaCategories);

        //TODO: Task 1: Consume getCategories.php using AsyncHttpClient

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getCategories.php", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject category = (JSONObject) response.get(i);
                        Category c = new Category(category.getInt("category_id"),
                                category.getString("name"));
                        alCategories.add(c);
                    }
                } catch (JSONException e) {
                }
                aaCategories.notifyDataSetChanged();
            }
        });


        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category c = alCategories.get(i);  // Get the selected Category
                Intent intent = new Intent(getApplicationContext(), ViewFilmsActivity.class);
                intent.putExtra("Category", c);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.categorySummary) {

            //Intent intent = new Intent(getApplicationContext(), CategorySummaryActivity.class);
            //startActivity(intent);

            return true;
        } else if (id == R.id.searchFilms) {

            Intent intent = new Intent(getApplicationContext(), SearchFilmActivity.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.searchRental) {

            //Intent intent = new Intent(getApplicationContext(), SearchFilmsActivity.class);
            //startActivity(intent);

            return true;
        }

            return super.onOptionsItemSelected(item);



    }
}
