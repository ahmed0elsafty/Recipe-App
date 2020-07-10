package com.elsafty.Recipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.WordViewHolder> {
    private static final String TAG = "RecipeListAdapter";
    private ArrayList<Recipe> mRecipeList;
    private OnListItemClick mOnListItemClick;
    private LayoutInflater mInflater;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipesList,OnListItemClick onListItemClick) {
        mInflater = LayoutInflater.from(context);
        this.mRecipeList = recipesList;
        this.mOnListItemClick = onListItemClick;
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipe_list_item, parent, false);
        return new WordViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final WordViewHolder holder, int position) {
        Recipe currentRecipe = mRecipeList.get(position);
        holder.mRecipeTitleTextView.setText(currentRecipe.getName());
//        Log.d(TAG, "onBindViewHolder: steps.size() " + currentRecipe.getSteps().size());
        for (String step:currentRecipe.getSteps()) {
            holder.mRecipeDescTextView.append(step.replace(".",".\n"));
        }
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public interface OnListItemClick {
        void onItemCLick(int itemClicked);
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mRecipeTitleTextView;
        TextView mRecipeDescTextView;
        RecipeListAdapter adapter;

        public WordViewHolder(@NonNull View itemView, RecipeListAdapter adapter) {
            super(itemView);
            mRecipeTitleTextView = itemView.findViewById(R.id.tv_recipe_name);
            mRecipeDescTextView = itemView.findViewById(R.id.tv_recipe_desc);
            itemView.setOnClickListener(this);
            this.adapter = adapter;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnListItemClick.onItemCLick(position);
        }
    }
}
