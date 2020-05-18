package com.sujithkumar.rockpaperscissors;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Random;

public class GamePlay extends Fragment implements View.OnClickListener {
    private Button next;
    private ConstraintLayout left, right;
    private Random rand = new Random();
    private MediaPlayer click;
    private Animation shrink, expand;
    private TextView player1score, player2score, rounds, current;
    private ImageView player1, player2;
    private ImageButton rock, paper, scissors;
    private viewmodel rep;
    private NavController nav;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gameplay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        click = MediaPlayer.create(requireContext(), R.raw.click);
        shrink = AnimationUtils.loadAnimation(requireContext(), R.anim.button);
        expand = AnimationUtils.loadAnimation(requireContext(), R.anim.buttonexpand);
        rep = new ViewModelProvider(requireActivity()).get(viewmodel.class);
        nav = Navigation.findNavController(view);
        left = view.findViewById(R.id.left);
        right = view.findViewById(R.id.right);
        player1score = view.findViewById(R.id.player1score);
        player2score = view.findViewById(R.id.player2score);
        rounds = view.findViewById(R.id.rounds);
        current = view.findViewById(R.id.currentplayer);
        player1 = view.findViewById(R.id.player1option);
        player2 = view.findViewById(R.id.player2option);
        rock = view.findViewById(R.id.rock);
        paper = view.findViewById(R.id.paper);
        scissors = view.findViewById(R.id.scissors);
        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);
        rock.setOnClickListener(this);
        paper.setOnClickListener(this);
        scissors.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        draw();
    }

    @Override
    public void onClick(View v) {
        click.start();
        if (rep.getCurrentuser() == 2 && !rep.isComputer() || rep.getCurrentuser() == 1)
            switch (v.getId()) {
                case R.id.next:
                    if(rep.isGameover())
                        nav.navigate(R.id.action_gamePlay_to_gameover);
                    else{

                        rep.setCurrentroundover(false);
                        rock.setVisibility(View.VISIBLE);
                        paper.setVisibility(View.VISIBLE);
                        scissors.setVisibility(View.VISIBLE);
                        current.setVisibility(View.VISIBLE);
                        player1.setImageResource(R.drawable.questionmark);
                        player2.setImageResource(R.drawable.questionmark);
                        rep.setCorrect(0);
                        left.setBackgroundColor(Color.TRANSPARENT);
                        right.setBackgroundColor(Color.TRANSPARENT);
                        draw();
                    }
                    break;
                case R.id.rock:
                    if (rep.getCurrentuser() == 1) {
                        rep.setPlayer1option(1);
                        rep.setCurrentuser(2);
                    } else {
                        rep.setPlayer2option(1);
                        rep.setCurrentroundover(true);
                        calculate();
                    }
                    break;
                case R.id.paper:

                    if (rep.getCurrentuser() == 1) {
                        rep.setPlayer1option(2);
                        rep.setCurrentuser(2);
                    } else {
                        rep.setPlayer2option(2);
                        rep.setCurrentroundover(true);
                        calculate();
                    }
                    break;
                case R.id.scissors:
                    if (rep.getCurrentuser() == 1) {
                        rep.setPlayer1option(3);
                        rep.setCurrentuser(2);
                    } else {
                        rep.setPlayer2option(3);
                        rep.setCurrentroundover(true);
                        calculate();
                    }
                    break;
            }
        if (v.getId() != R.id.next && rep.isComputer() && rep.getCurrentuser() == 2)
            rando();
        draw();

        if (v.getId() != R.id.next) {
            rock.startAnimation(shrink);
            paper.startAnimation(shrink);
            scissors.startAnimation(shrink);
            //current.startAnimation(shrink);

            if (rep.isComputer() || (!rep.isComputer() && rep.getCurrentuser() == 1))
                next.startAnimation(expand);
            else if (!rep.isComputer() && rep.getCurrentuser() == 2) {
                rock.startAnimation(expand);
                paper.startAnimation(expand);
                scissors.startAnimation(expand);
                //current.startAnimation(expand);
            }

        } else {
            next.setAnimation(shrink);
            rock.startAnimation(expand);
            paper.startAnimation(expand);
            scissors.startAnimation(expand);
            //current.startAnimation(expand);
            next.setVisibility(View.GONE);
        }


    }

    void rando() {
        rep.setPlayer2option(rand.nextInt(3) + 1);
        rep.setCurrentroundover(true);
        calculate();
    }


    void draw() {

        player1score.setText(rep.getName1() + "\n" + rep.getScore1());
        player2score.setText(rep.getName2() + "\n" + rep.getScore2());
        if (rep.isComputer())
            player2score.setText("Computer\n" + rep.getScore2());
        rounds.setText(rep.getCurrentround() + "/" + rep.getRound());
        if (rep.getCurrentuser() == 1) {
            current.setText(rep.getName1() + "'s Turn :");
        } else {
            current.setText(rep.getName2() + "'s Turn :");
        }
        if (rep.isCurrentroundover()) {
            player1.startAnimation(shrink);
            player2.startAnimation(shrink);
            switch (rep.getPlayer1option()) {
                case 1:
                    player1.setImageResource(R.drawable.rock);
                    break;
                case 2:
                    player1.setImageResource(R.drawable.paper);
                    break;
                case 3:
                    player1.setImageResource(R.drawable.scissors);
                    break;
            }
            switch (rep.getPlayer2option()) {
                case 1:
                    player2.setImageResource(R.drawable.rock);
                    break;
                case 2:
                    player2.setImageResource(R.drawable.paper);
                    break;
                case 3:
                    player2.setImageResource(R.drawable.scissors);
                    break;
            }
            player1.startAnimation(expand);
            player2.startAnimation(expand);
            switch (rep.getCorrect()) {
                case 0:
                    left.setBackgroundColor(Color.TRANSPARENT);
                    right.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case 1:
                    left.setBackgroundColor(Color.argb(80, 0, 255, 0));
                    right.setBackgroundColor(Color.argb(80, 255, 0, 0));
                    break;
                case 2:
                    right.setBackgroundColor(Color.argb(80, 0, 255, 0));
                    left.setBackgroundColor(Color.argb(80, 255, 0, 0));
                    break;
            }
            next.setVisibility(View.VISIBLE);
            if(rep.isGameover())
                next.setText(R.string.results);
            rock.setVisibility(View.GONE);
            paper.setVisibility(View.GONE);
            scissors.setVisibility(View.GONE);
            current.setVisibility(View.GONE);
        }


    }


    void calculate() {
        rep.setCurrentuser(1);
        if (rep.getPlayer2option() == rep.getPlayer1option())
            rep.setCorrect(0);
        else
            switch (rep.getPlayer1option()) {
                case 1:
                    if (rep.getPlayer2option() == 3) {
                        rep.setScore1(rep.getScore1() + 1);
                        rep.setCorrect(1);
                    } else if (rep.getPlayer2option() == 2) {
                        rep.setScore2(rep.getScore2() + 1);
                        rep.setCorrect(2);
                    }

                    break;
                case 2:
                    if (rep.getPlayer2option() == 1) {
                        rep.setScore1(rep.getScore1() + 1);
                        rep.setCorrect(1);
                    } else if (rep.getPlayer2option() == 3) {
                        rep.setScore2(rep.getScore2() + 1);
                        rep.setCorrect(2);
                    }

                    break;
                case 3:
                    if (rep.getPlayer2option() == 2) {
                        rep.setScore1(rep.getScore1() + 1);
                        rep.setCorrect(1);
                    } else if (rep.getPlayer2option() == 1) {
                        rep.setScore2(rep.getScore2() + 1);
                        rep.setCorrect(2);
                    }
                    break;
            }


        if (rep.getCurrentround() != rep.getRound())
            rep.setCurrentround(rep.getCurrentround() + 1);
        else {
            rep.setGameover(true);
           // nav.navigate(R.id.action_gamePlay_to_gameover);
        }
        draw();
    }


}
