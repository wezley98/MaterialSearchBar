package com.mancj.materialsearchbar.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mancj.materialsearchbar.R;

/**
 * Created by mancj on 27.01.17.
 */

public class DefaultSuggestionsAdapter extends SuggestionsAdapter<String, DefaultSuggestionsAdapter.SuggestionHolder> {
    private SuggestionsAdapter.OnItemViewClickListener listener;

    public DefaultSuggestionsAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    public void setListener(SuggestionsAdapter.OnItemViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getSingleViewHeight() {
        return 50;
    }

    @NonNull
    @Override
    public DefaultSuggestionsAdapter.SuggestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_last_request, parent, false);
        return new DefaultSuggestionsAdapter.SuggestionHolder(view);
    }

    @Override
    public void onBindSuggestionHolder(String suggestion, SuggestionHolder holder, int position) {
        holder.text.setText(getSuggestions().get(position));
    }

    class SuggestionHolder extends RecyclerView.ViewHolder{
        private final TextView text;
        private final ImageView iv_delete;
        SuggestionHolder(final View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            iv_delete = itemView.findViewById(R.id.iv_delete);
            itemView.setOnClickListener(v -> {
                v.setTag(getSuggestions().get(getAdapterPosition()));
                listener.OnItemClickListener(getAdapterPosition(),v);
            });
            iv_delete.setOnClickListener(v -> {
                v.setTag(getSuggestions().get(getAdapterPosition()));
                listener.OnItemDeleteListener(getAdapterPosition(),v);
            });
        }
    }

    public interface OnItemViewClickListener{
        void OnItemClickListener(int position,View v);
        void OnItemDeleteListener(int position,View v);
    }
}
