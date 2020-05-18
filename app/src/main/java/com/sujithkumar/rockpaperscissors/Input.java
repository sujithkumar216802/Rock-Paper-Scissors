package com.sujithkumar.rockpaperscissors;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Input extends Fragment {
    private EditText name1, name2, rounds;
    private TextView second;
    private viewmodel rep;
    private NavController nav;
    private Button go;
    private MediaPlayer click;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rep = new ViewModelProvider(requireActivity()).get(viewmodel.class);
        nav = Navigation.findNavController(view);
        click = MediaPlayer.create(requireContext(), R.raw.click);
        name1 = view.findViewById(R.id.name1);
        name2 = view.findViewById(R.id.name2);
        second = view.findViewById(R.id.second);
        if (rep.isComputer()){
            name2.setVisibility(View.INVISIBLE);
            second.setVisibility(View.INVISIBLE);
        }

        rounds = view.findViewById(R.id.round);
        go = view.findViewById(R.id.go);
        rep.setCurrentround(1);
        rep.setScore1(0);
        rep.setScore2(0);
        rep.setCurrentuser(1);
        rep.setCurrentroundover(false);
        rep.setGameover(false);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.start();
                rep.setName1(name1.getText());
                if (!rep.isComputer())
                    rep.setName2(name2.getText());
                try {
                    rep.setRound(Integer.parseInt((rounds.getText().toString())));
                    if (rep.getRound() > 0)
                        nav.navigate(R.id.action_input_to_gamePlay);
                    else
                        Toast.makeText(getContext(), "ENTER A NUMBER GREATER THAN 0 ", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "ENTER A VALID NUMBER", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
