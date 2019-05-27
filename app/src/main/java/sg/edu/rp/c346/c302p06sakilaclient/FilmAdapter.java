package sg.edu.rp.c346.c302p06sakilaclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Film> {

    private ArrayList<Film> film;
    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvRating;
    private Context context;

    public FilmAdapter(Context context, int resource, ArrayList<Film> objects) {
        super(context, resource, objects);
        film = objects;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
               .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvTitle = rowView.findViewById(R.id.tvTitle);
        tvYear = rowView.findViewById(R.id.tvYear);
        tvRating = rowView.findViewById(R.id.tvRating);

        Film currentfilm = film.get(position);

        tvTitle.setText(currentfilm.getTitle());
        tvYear.setText(currentfilm.getYear());
        tvRating.setText(currentfilm.getRating());

        return rowView;
    }
}
