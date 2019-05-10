package app.axross.ciccc.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    public WordListAdapter(Context context, LinkedList<String> words) {
        inflater = LayoutInflater.from(context);
        this.words = words;
    }

    private LayoutInflater inflater;

    private final LinkedList<String> words;

    @Override
    public int getItemCount() {
        return words.size();
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.wordlist_item, parent, false);

        return new WordViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String mCurrent = words.get(position);

        holder.wordItemView.setText(mCurrent);
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);

            wordItemView = itemView.findViewById(R.id.word);
            this.adapter = adapter;

            itemView.setOnClickListener(this);
        }

        public final TextView wordItemView;

        final WordListAdapter adapter;

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String element = words.get(position);
            words.set(position, "Clicked! " + element);
            adapter.notifyDataSetChanged();
        }
    }
}
