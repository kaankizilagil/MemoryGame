package com.example.kaankizilagil.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    int LastCard=0;
    int SCORE = 0;
    int ERROR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        final String s  = i.getStringExtra("Message");
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText(s);
        GridLayout gl = (GridLayout) findViewById(R.id.Cards);
        Card Cards[] = new Card[16];

        for(int j =1;j<=16;j++) {
            Cards[j-1]=new Card(this, j);
            Cards[j-1].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    final Card k = (Card)v;
                    k.turn();

                    if(LastCard>0) {

                        final Card k2 = (Card)findViewById(LastCard);

                        if(k2.onPlanID==k.onPlanID&&k2.getId()!=k.getId()) {
                            //MACTHED
                            k2.cevrilebilir = false;
                            k.cevrilebilir = false;
                            SCORE++;
                            TextView tv = (TextView)findViewById(R.id.textView3);
                            tv.setText("SCOR:"+SCORE);

                            if(SCORE==8) {
                                Intent i =new Intent(MainActivity2.this,MainActivity3.class);
                                i.putExtra("POINT",ERROR);
                                i.putExtra("NAME",s);
                                startActivity(i);

                                //END GAME
                            }
                        }

                        else { // NO MACTH, TURN BACK CARDS

                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    k.turn();
                                    k2.turn();
                                }
                            },500);
                            ERROR++;

                            TextView tv = (TextView)findViewById(R.id.textView4);
                            tv.setText("ERRORS:"+ERROR);
                            LastCard=0;
                        }
                    }

                    else {
                        LastCard = k.getId();
                    }
                }
            });
        }

        // MIXED
        for(int j=0 ; j<16 ; j++) {

            int rg = (int)(Math.random()*16);
            Card k = Cards[rg];
            Cards[rg]=Cards[j];
            Cards[j]=k;
        }

        for(int j =0 ; j<16 ; j++) {

            gl.addView(Cards[j]);
        }
    }
}
