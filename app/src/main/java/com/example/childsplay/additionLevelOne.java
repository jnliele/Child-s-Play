package com.example.childsplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.childsplay.R.layout.activity_addition_level_one;

public class additionLevelOne extends AppCompatActivity {
    Button unlockLevelTwo;
    Button btn_start, btn_answer0, btn_answer1,btn_answer2, btn_answer3,btn_nextLevel, btn_backToLevels, TwoButton;
    TextView tv_score, tv_questions, tv_timer, tv_bottomMessage;
    ProgressBar prog_timer;
    long levelOneTotalPlayCount=0, temp=0;
    double currentHighPercent, passingGrade =.70;
    double result;
    long levelOneTotalCorrect;
    int highScore=0;
    long numCorrectAnswers =0;
    ConstraintLayout rootLayout;
    AnimationDrawable animationDrawable;
    long levelOneTotalquestions;
    String Id, levelOneTotalCorrectStr;
    User user;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    additonGameCode g = new additonGameCode();
    int secondsRemaining =30;

    //counts down the seconds and displays on the game screen
    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining) + "sec");
            prog_timer.setProgress(30-secondsRemaining);


        }

        @Override
        public void onFinish() {// code that runs after the level is finished

            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            tv_bottomMessage.setText("Time is Up! " + g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));



            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {//this will delay the visibility of the buttons
                @Override
                public void run() {
                    btn_start.setText("Replay");
                    btn_start.setVisibility(View.VISIBLE);
                    btn_backToLevels.setVisibility(View.VISIBLE);

                    //connect to the users information that is currently logged in
                    String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    rootNode= FirebaseDatabase.getInstance();
                    reference =  rootNode.getReference("Users");

                    Id = FirebaseAuth.getInstance().getCurrentUser().getUid();



                    // find the account user is signed in and update correct information for admin profile
                    reference.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds : snapshot.getChildren()) {
                                if (ds.child("email").getValue().equals(userEmail)) {

                                    //gets the users individual information from firebase
                                    levelOneTotalCorrect = ds.child("additionLevelOneCorrect").getValue(Long.class);
                                    levelOneTotalPlayCount = ds.child("additionLevelOneTotalPlay").getValue(Long.class);
                                    levelOneTotalquestions = ds.child("additionLevelOneTotal").getValue(Long.class);
                                    currentHighPercent = ds.child("additionLevelOneScore").getValue(Double.class);
                                    result =((double)(g.getNumberCorrect())/(double)(g.getTotalQuestions()-1));


                                    if(result> currentHighPercent && g.getTotalQuestions()> 5) {//updates users high score
                                        reference.child(Id).child("additionLevelOneScore").setValue(result);
                                        currentHighPercent =result;
                                    }

                                    if(currentHighPercent>= passingGrade)
                                        btn_nextLevel.setVisibility(View.VISIBLE);

                                    //update firebase with users information
                                    levelOneTotalPlayCount = levelOneTotalPlayCount +temp;
                                    levelOneTotalquestions = levelOneTotalquestions + (g.getTotalQuestions() - 1);
                                    levelOneTotalCorrect = levelOneTotalCorrect + numCorrectAnswers;
                                    reference.child(Id).child("additionLevelOneCorrect").setValue(levelOneTotalCorrect);
                                    reference.child(Id).child("additionLevelOneTotalPlay").setValue(levelOneTotalPlayCount);
                                    reference.child(Id).child("additionLevelOneTotal").setValue(levelOneTotalquestions);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });


                    // goes back to the choosing levels page
                    btn_backToLevels.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(additionLevelOne.this, additionGame.class));
                        }
                    });

                    //continues onto the next level
                    btn_nextLevel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(additionLevelOne.this, additionLevelTwo.class));
                        }
                    });
                }

            }, 4000);//the visibility of the buttons is delayed by 4 seconds


        }
    };





    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {// function that connects the xml
        super.onCreate(savedInstanceState);
        setContentView(activity_addition_level_one);

        //animates the background of the game
        rootLayout = (ConstraintLayout)(findViewById(R.id.addLevelOneView));
        animationDrawable = (AnimationDrawable)(rootLayout.getBackground());
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        //connects to all the buttons
        btn_backToLevels =findViewById(R.id.btn_backToLevels);
        btn_nextLevel= findViewById(R.id.btn_nextLevel);
        btn_start = findViewById(R.id.btn_start);
        btn_answer0 = findViewById(R.id.btn_answer0);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);
        TwoButton = (Button) findViewById(R.id.levelTwo);
        tv_score = findViewById(R.id.tv_score);
        tv_bottomMessage=findViewById(R.id.tv_bottomMessage);
        tv_questions = findViewById(R.id.tv_questions);
        tv_timer = findViewById(R.id.tv_timer);
        prog_timer = findViewById(R.id.prog_timer);

        //the preset on screen visual for the game
        tv_timer.setText("0Sec");
        tv_questions.setText("");
        tv_bottomMessage.setText("0/0");
        tv_score.setText("0pts");
        prog_timer.setProgress(0);
        btn_start.setText("Start Game\n get five right");

        //disables the buttons before start in case of mis-click
        btn_answer0.setEnabled(false);
        btn_answer1.setEnabled(false);
        btn_answer2.setEnabled(false);
        btn_answer3.setEnabled(false);

        //action once the start button is clicked
        View.OnClickListener startButtonClickListener= new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                Button start_button= (Button)v;
                start_button.setVisibility(View.INVISIBLE);
                btn_backToLevels.setVisibility(View.INVISIBLE);
                btn_nextLevel.setVisibility(View.INVISIBLE);
                temp++;
                tv_score.setText("0pts");
                secondsRemaining = 30;
                g.additonGameCode1();
                nextTurn();
                timer.start();
            }
        };

        //action once the user clicks an answer
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

        //sets action listener for every button once clicked by the user
        btn_start.setOnClickListener(startButtonClickListener);
        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);

    }
    //moves onto the next question
        private void nextTurn()
        {
            g.makeNewQuestion();
           int [] answer = g.getCurrentQuestion().getAnswerArray();

            //sets 3 random numbers to the buttons along with the correct onebtn_answer0.setText(Integer.toString(answer[0]));
            btn_answer0.setText(Integer.toString(answer[0]));
            btn_answer1.setText(Integer.toString(answer[1]));
            btn_answer2.setText(Integer.toString(answer[2]));
            btn_answer3.setText(Integer.toString(answer[3]));

           btn_answer0.setEnabled(true);
           btn_answer1.setEnabled(true);
           btn_answer2.setEnabled(true);
           btn_answer3.setEnabled(true);
           tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());
           tv_bottomMessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));//sets the correct score and total score
           numCorrectAnswers = g.getNumberCorrect();

        }
}