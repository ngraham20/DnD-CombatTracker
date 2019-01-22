package com.example.dndcombattracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// learned much of this from https://www.youtube.com/watch?v=Vyqz_-sJGFk

public class DnDAdapter extends RecyclerView.Adapter<DnDAdapter.DnDViewHolder> {

    private static final String TAG = "DnDAdapter";

    private ArrayList<Character> mCharacters = new ArrayList<>();
    private Context mContext;

    public DnDAdapter(Context context, ArrayList<Character> mCharacters) {
        this.mCharacters = mCharacters;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DnDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item, parent, false);

        DnDViewHolder holder = new DnDViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DnDViewHolder dnDViewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        dnDViewHolder.character_name.setText(mCharacters.get(position).getCharacterName());
        dnDViewHolder.character_ac.setText(Integer.toString(mCharacters.get(position).getArmorClass()));
        dnDViewHolder.character_init.setText(Integer.toString(mCharacters.get(position).getCurrentInitiative()));

        dnDViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mCharacters.get(position).getCharacterName());

                Toast.makeText(mContext, mCharacters.get(position).getCharacterName(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public class DnDViewHolder extends RecyclerView.ViewHolder
    {

        TextView character_ac;
        TextView character_name;
        TextView character_init;
        ConstraintLayout parentLayout;

        public DnDViewHolder(@NonNull View itemView) {
            super(itemView);
            character_ac = itemView.findViewById(R.id.character_ac);
            character_name = itemView.findViewById(R.id.character_name);
            character_init = itemView.findViewById(R.id.character_init);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
