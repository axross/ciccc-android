package app.axross.ciccc.materialme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {
    private ArrayList<Sport> sports;
    private Context context;

    public SportAdapter(Context context, ArrayList<Sport> sports) {
        this.sports = sports;
        this.context = context;
    }

    @Override
    public SportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SportAdapter.ViewHolder holder, int position) {
        Sport currentSport = sports.get(position);

        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView infoTextView;

        private ViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title);
            infoTextView = itemView.findViewById(R.id.subTitle);
        }

        private void bindTo(Sport currentSport){
            titleTextView.setText(currentSport.getTitle());
            infoTextView.setText(currentSport.getInfo());
        }
    }
}
