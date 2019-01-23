package com.example.dndcombattracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class CombatListFragment extends Fragment {

    View view;
    private RecyclerView mRecyclerView;
    private ArrayList<Combat> mCombats = new ArrayList<>();

    private static final String TAG = "CombatListFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_combat_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        CombatAdapter adapter = new CombatAdapter(getContext(), mCombats);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton fab = view.findViewById(R.id.add_combat_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "FAB Clicked!", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCombats = initDefaultCombat();
    }

    private ArrayList<Combat> initDefaultCombat()
    {
        ArrayList<Combat> combats = new ArrayList<>();
        Combat combat = new Combat("Test Combat");
        combat.addCharacter(new PC("Grahnath", 19, 135, 5));
        combat.addCharacter(new PC("Kelly", 12, 100, 6));
        combats.add(combat);
        return combats;
    }
}
