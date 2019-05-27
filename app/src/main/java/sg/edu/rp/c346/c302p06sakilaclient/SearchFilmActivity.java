package sg.edu.rp.c346.c302p06sakilaclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SearchFilmActivity extends AppCompatActivity {

    SearchView editsearch;
    private TextView tvTitle, tvReleaseYear, tvRating, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_film);

        editsearch = (SearchView) findViewById(R.id.search);
        tvTitle = findViewById(R.id.textViewTitle);
        tvReleaseYear = findViewById(R.id.textViewReleaseYear);
        tvRating = findViewById(R.id.textViewRating);
        tvDescription = findViewById(R.id.textViewDescription);

        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                RequestParams params = new RequestParams();
                params.add("id", query);

                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://10.0.2.2/C302_sakila/getFilmById.php", params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {
                            tvTitle.setText(response.getString("title"));
                            tvDescription.setText(response.getString("description"));
                            tvReleaseYear.setText(response.getString("release_year"));
                            tvRating.setText(response.getString("rating"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}
