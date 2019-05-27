package sg.edu.rp.c346.c302p06sakilaclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewFilmsActivity extends AppCompatActivity {
    TextView tv;
    private ListView lvFilm;
    ArrayList<Film> alFilm = new ArrayList<Film>();
    ArrayAdapter<Film> aaFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_films);
        tv = findViewById(R.id.textView4);

        Intent intent = getIntent();
        Category c = (Category) intent.getSerializableExtra("Category");
        tv.setText(c.getName());

        lvFilm = findViewById(R.id.lvFilm);
        alFilm = new ArrayList<Film>();
        aaFilm = new FilmAdapter(this, R.layout.row,alFilm);
        lvFilm.setAdapter(aaFilm);

        // set parameter "id"
        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(c.getId()));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/C302_sakila/getFilmsByCategoryId.php", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                try {
                    for(int i=0; i<response.length(); i++) {
                        JSONObject film = (JSONObject)response.get(i);
                        Film f = new Film(film.getInt("film_id"),
                                film.getString("title"),
                                film.getString("release_year"),
                                film.getString("rating"));
                        alFilm.add(f);
                    }
                }catch(JSONException e) {
                }
                aaFilm.notifyDataSetChanged();
            }
        });
    }
}
