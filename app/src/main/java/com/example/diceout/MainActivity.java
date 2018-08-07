package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //field to hold the roll result text
    TextView  rollResult, messageResult;

    //field to hold the roll button
    Button rollButton;

    //field to hold the score and dice results
    int score;
    int [] die;

    //array list to hold all three dices
    ArrayList<Integer > dice;

    //field to hold the score text
    TextView scoreText;

    //array list to hold all three imageview
     ArrayList<ImageView> diceImageViews;

    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //create greeting
        Toast.makeText(getApplicationContext(),"Welcome to Dice Out!! :D", Toast.LENGTH_SHORT).show();

        //initial score
        score = 0;
        die = new int[3];

        //link instances to widgets in the activity view
        rollResult = (TextView) findViewById(R.id.rollResult);
        messageResult = (TextView) findViewById(R.id.messageResult);
        rollButton = (Button) findViewById(R.id.rollButton);
        scoreText = (TextView) findViewById(R.id.scoreText);

        //randow number generator
        rand = new Random();

        //create array list container for the dice values
        dice = new ArrayList<Integer>();

        ImageView die1Image = (ImageView) findViewById(R.id.die1image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3image);

         diceImageViews = new ArrayList<ImageView>();
         diceImageViews.add(die1Image);
         diceImageViews.add(die2Image);
         diceImageViews.add(die3Image);

    }

    public void rollDice(View v){

        //roll dice

        for(int i=0; i<3; i++)
            die[i] = rand.nextInt(6)+1;

        //set dice values into an array list
        dice.clear();
        for(int i=0; i<3; i++)
            dice.add(die[i]);

        for (int dieOfSet =0; dieOfSet<3; dieOfSet++){
            String imageName = "d"+dice.get(dieOfSet)+".png";

            try{
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //message with result
        String valorRandom = "Rolled a "+die[0]
                +", a "+die[1]+" and a "+die[2];

        String msg;

        if(die[0]==die[1]&&die[1]==die[2]){
            //triples!
            int scoreDelta = die[0]*100;
            msg = "You rolled a triple "+die[0]+"!\n" +
                    "You score "+scoreDelta+" points!";
            score += scoreDelta;
        }else if (die[0]==die[1]||die[1]==die[2]||die[0]==die[2 ]){
            //double
            msg = "You rolled doubles for 50 points!";
            score +=50;
        }else{
            msg = "You didn't score this roll. \n" +
                    " Please try again.";}

        //update the app to display the result message
        //Toast.makeText(getApplicationContext(), valorRandom,Toast.LENGTH_SHORT).show();
        rollResult.setText(valorRandom);
        messageResult.setText(msg);
        scoreText.setText("Score: "+score);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
