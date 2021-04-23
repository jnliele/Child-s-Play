package com.example.teachingtoddlers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class additionLevelOne extends AppCompatActivity {

    Button btn_start, btn_answer0, btn_answer1,btn_answer2, btn_answer3;
    TextView tv_score, tv_questions, tv_timer, tv_bottomMessage;
    ProgressBar prog_timer;
    additonGameCode g = new additonGameCode();

    int secondsRemaining =30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining) + "sec");
            prog_timer.setProgress(30-secondsRemaining);

        }

        @Override
        public void onFinish() {
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            tv_bottomMessage.setText("Time is Up! " + g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));


            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                }
            }, 4000);


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_level_one);

        btn_start = findViewById(R.id.btn_start);
        btn_answer0 = findViewById(R.id.btn_answer0);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);


        tv_score = findViewById(R.id.tv_score);
        tv_bottomMessage=findViewById(R.id.tv_bottomMessage);
        tv_questions = findViewById(R.id.tv_questions);
        tv_timer = findViewById(R.id.tv_timer);
        prog_timer = findViewById(R.id.prog_timer);

        tv_timer.setText("0Sec");
        tv_questions.setText("");
        tv_bottomMessage.setText("Press Go");
        tv_score.setText("0pts");
        prog_timer.setProgress(0);


        View.OnClickListener startButtonClickListener= new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button start_button= (Button)v;
                start_button.setVisibility(View.INVISIBLE);
                tv_score.setText("0pts");
                secondsRemaining = 30;
                g = new additonGameCode();
                nextTurn();
                timer.start();
            }
        };


        View.OnClickListener answerButtonClickListener= new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Button buttonClicked= (Button)v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                g.checkAnswer(answerSelected);
                tv_score.setText(Integer.toString(g.getScore()));
                nextTurn();

            }
        };
        btn_start.setOnClickListener(startButtonClickListener);
        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);

    }

        private void nextTurn()
        {
            g.makeNewQuestion();
           int [] answer = g.getCurrentQuestion().getAnswerArray();

           btn_answer0.setText(Integer.toString(answer[0]));
           btn_answer1.setText(Integer.toString(answer[1]));
           btn_answer2.setText(Integer.toString(answer[2]));
           btn_answer3.setText(Integer.toString(answer[3]));

           btn_answer0.setEnabled(true);
           btn_answer1.setEnabled(true);
           btn_answer2.setEnabled(true);
           btn_answer3.setEnabled(true);

            tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());
            tv_bottomMessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));

        }
}