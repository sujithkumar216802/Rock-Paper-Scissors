package com.sujithkumar.rockpaperscissors;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class gameover extends Fragment {
    private viewmodel rep;
    private NavController nav;
    private TextView score1, score2, won;
    private Button menu;
    private ImageButton reload;
    private MediaPlayer click;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gameover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rep = new ViewModelProvider(requireActivity()).get(viewmodel.class);
        nav = Navigation.findNavController(view);
        score1 = view.findViewById(R.id.score2);
        score2 = view.findViewById(R.id.score1);
        menu = view.findViewById(R.id.menu);
        click = MediaPlayer.create(requireContext(), R.raw.click);
        reload = view.findViewById(R.id.reload);
        won = view.findViewById(R.id.won);
        score1.setText(rep.getName1() + ": \n" + rep.getScore1());
        score2.setText(rep.getName2() + ": \n" + rep.getScore2());
        if (rep.isComputer())
            score2.setText("Computer: \n" + rep.getScore2());
        if (rep.getScore1() > rep.getScore2()) {
            won.setText(rep.getName1() + " WON!");
        } else if (rep.getScore1() < rep.getScore2()) {
            won.setText(rep.getName2() + " WON!");
            if (rep.isComputer())
                won.setText("Computer Won!");
        } else {
            won.setText("TIE!");
        }


        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                nav.navigate(R.id.action_gameover_to_input);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                nav.navigate(R.id.action_gameover_to_options2);
            }
        });

    }

}
