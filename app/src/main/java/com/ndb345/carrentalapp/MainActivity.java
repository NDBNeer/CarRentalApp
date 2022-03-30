package com.ndb345.carrentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner carlist;
    ImageView carimg;
    TextView dailyrate,insurance_amt,total,status,no;
    Button btnOrder;
    RadioButton rdbSmall,rdbMedium,rdbLarge;
    CheckBox chb1,chb2,chb3;
    SeekBar noofdays;
    ArrayList<Car> cList=new ArrayList<>();
    ArrayList<String>cTypes=new ArrayList<>();
    double originalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        findViewId();
        rdbSmall.setOnClickListener(this);
        rdbMedium.setOnClickListener(this);
        rdbLarge.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
        if(rdbSmall.isChecked())
        {
            insurance_amt.setText("15");
        }
        //setting spinner
        ArrayAdapter aa=new ArrayAdapter(this, R.layout.spinnerdes,cTypes);
        carlist.setAdapter(aa);
        carlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dailyrate.setText(String.valueOf(cList.get(i).getDailyrate()));
                originalPrice=cList.get(i).getDailyrate();
                carimg.setImageResource(cList.get(i).getImage());
                if(cList.get(i).getStatus() == 1)
                {
                    status.setText("Available");
                }
                else
                {
                    status.setText("Unavailable");
                }

                rdbSmall.setChecked(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //seekbar
        noofdays.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                no.setText(String.valueOf(noofdays.getProgress()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void fillData() {
        cList.add(new Car("Hyundai Venue",9.75,R.drawable.hyundaivenue,1));
        cList.add(new Car("Maruti Suzuki Baleno",6.95,R.drawable.marutisuzukibaleno,0));
        cList.add(new Car("Hyundai Creta",12.75,R.drawable.hyundaicreta,1));
        cList.add(new Car("Toyota Glanza",8.05,R.drawable.toyotaglanza,0));
        cList.add(new Car("Renault Kiger",10.75,R.drawable.renaultkiger,1));

        for(Car pz:cList)
            cTypes.add(pz.getName());
    }

    private void findViewId() {
        carlist=findViewById(R.id.carlist);
        status=findViewById(R.id.status);
        carimg=findViewById(R.id.carimg);
        dailyrate=findViewById(R.id.dailyrate);
        insurance_amt=findViewById(R.id.insurance_amt);
        total=findViewById(R.id.total);
        btnOrder=findViewById(R.id.btnOrder);
        rdbSmall=findViewById(R.id.rdbSmall);
        rdbMedium=findViewById(R.id.rdbMedium);
        rdbLarge=findViewById(R.id.rdbLarge);
        chb1=findViewById(R.id.chb1);
        chb2=findViewById(R.id.chb2);
        chb3=findViewById(R.id.chb3);
        noofdays=findViewById(R.id.noofdays);
        no=findViewById(R.id.no);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rdbSmall:
                insurance_amt.setText("15");
                break;
            case R.id.rdbMedium:
                insurance_amt.setText("7");
                break;
            case R.id.rdbLarge:
                insurance_amt.setText("10");
                break;
            case R.id.btnOrder:
                int chval = 0;
                double totl;
                if(status.getText().toString().equals("Available")) {
                    if(no.getText().toString().equals("0"))
                    {
                        Toast.makeText(this,"Select at least 1 day",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        double dalrat = Double.parseDouble(dailyrate.getText().toString());
                        double noday = Double.parseDouble(no.getText().toString());
                        double insamtt = Double.parseDouble(insurance_amt.getText().toString());
                        if (chb1.isChecked()) {
                            chval += 7;
                        }

                        if (chb2.isChecked()) {
                            chval += 5;
                        }

                        if (chb3.isChecked()) {
                            chval += 15;
                        }

                        totl = (dalrat + chval) * noday + insamtt;
                        total.setText(String.valueOf(totl));
                    }
                }
                else
                {
                    chval=0;
                    totl=0;
                    chb1.setChecked(false);
                    chb2.setChecked(false);
                    chb3.setChecked(false);
                    noofdays.setProgress(0);
                    total.setText("0");
                    no.setText("0");
                    Toast.makeText(this,"Car is currently unavailable",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}