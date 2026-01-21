package com.enezerel.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.Display);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private double sayi1;
    private double sayi2;
    private char islem;
    public double sonuc;
    String metin ;

    public void tus(View view){
        Button btn = (Button) view;
        String basilanTus = btn.getText().toString();
        metin = display.getText().toString();
        metin = metin.replace("," ,".");

        if (basilanTus.matches("[0-9]")){
            System.out.println(basilanTus + "sayisina basildi.");
            display.setText(display.getText().toString() + basilanTus);
        }
        else if (basilanTus.matches("[,]")){
            System.out.println(basilanTus + "virgüle basildi.");
            display.setText(display.getText().toString() + basilanTus);
        }
        else if (basilanTus.matches("[C]")){
            System.out.println("temizle tuşuna basildi.");
            display.setText("");
            metin = "";
            sayi1 = 0;
            sayi2 = 0;
            sonuc = 0;
            islem = ' ';
        }
        else if (basilanTus.matches("[+/*%\\-]")) // - burada aralık olarak belirtmemek için \\ kullanılır
        {

            //eğer ilk operatör tuşuna basılırsa
            System.out.println("ilk operatör tuşuna basildi.");
            if(display.getText().toString().isEmpty()){
                return;
            }

            System.out.println(basilanTus + "işlemi yapılacaktır.");
            //eğer virgül varsa düzelt
            sayi1 = Double.parseDouble(metin);

            //eğer basilan tuş +,-,*,/ ise
            //sayi1 = Double.parseDouble(display.getText().toString()); virgül olmadan önce bu kodu kullanıyordum

            System.out.println("ilk sayi değerin : "+ sayi1);
            islem = basilanTus.charAt(0);                       //stringin 0. karakterini alır zaten +-*/ tek karakter olduğu için
            display.setText("");
            metin = "";                         // displayi sıfırladı

        }
        else if (basilanTus.matches("=")) {
            System.out.println("esitir.");
            // sayi2 = Double.parseDouble(display.getText().toString());     virgül olmadan önce bu kodu kullanıyordum
            sayi2 = Double.parseDouble(metin);
            System.out.println("sayi 2 değeri: " + sayi2);
            switch (islem){
                case '+':
                    sonuc = sayi1 + sayi2;
                    break;
                case '-':
                    sonuc = sayi1 - sayi2;
                    break;
                case '*':
                    sonuc = sayi1 * sayi2;
                    break;
                case '/':
                    sonuc = sayi1 / sayi2;
                    break;
                case '%':
                    sonuc = sayi1 % sayi2;
                    break;
                default:
                    sonuc = 0;
                    break;
            }
            System.out.println("sonuc = " + sonuc);
            display.setText(String.valueOf(sonuc));
        }
    }
}
