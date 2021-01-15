package com.example.messanger;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

public class rsaActivity extends AppCompatActivity {

//=======
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Locale;
import java.util.logging.Logger;

import javax.crypto.Cipher;

public class rsaActivity extends AppCompatActivity {


    String temp;
    TextView output;
    EditText input;
    Button enc, dec, clear, send;
    String tosend = "";

>>>>>>> 84e8f4b9bd4e40e78097efe6131e730d2c3c470f
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
<<<<<<< HEAD
    }
=======


        output = (TextView) findViewById(R.id.output_text);
        input = (EditText) findViewById(R.id.input_text);
        enc = (Button) findViewById(R.id.encrypt);
        dec = (Button) findViewById(R.id.decrypt);
        clear = (Button) findViewById(R.id.clear_button);
        send = (Button) findViewById(R.id.send);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        KeyPair kp = getKeyPair();

        PublicKey publicKey = kp.getPublic();

        //default encoding ASN1.Der format
        final byte[] publicKeyBytes = publicKey.getEncoded();

        //base64 encoding
        final String publicKeyBytesBase64 = new String(Base64.getEncoder().encodeToString(publicKeyBytes));

        PrivateKey privateKey = kp.getPrivate();

        byte[] privateKeyBytes = privateKey.getEncoded();
        final String privateKeyBytesBase64 = new String(Base64.getEncoder().encodeToString(privateKeyBytes));

        enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = input.getText().toString(); ///input text

                if(temp.isEmpty())
                {
                    Toast.makeText( rsaActivity.this,"Empty Text! Please type some Text",Toast.LENGTH_SHORT).show();
                    return;
                }
                String encrypted = encryptRSAToString(temp, publicKeyBytesBase64);
                //  Log.d("NIKHIL", "encrypt key:" +encrypted);
                output.setText(encrypted);
                tosend = encrypted;
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = input.getText().toString();
                String decrypted = decryptRSAToString(temp, privateKeyBytesBase64);
                //    Log.d("NIKHIL", "encrypt key:" +decrypted);
                output.setText(decrypted);
                tosend = decrypted;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    input.setText(" ");
                    output.setText("");
                     Toast.makeText( rsaActivity.this,"Cleared Successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tosend.length() > 0) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, tosend);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                } else {
                    Toast.makeText(rsaActivity.this, "empty output", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public static KeyPair getKeyPair() {
        KeyPair kp = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            kp = kpg.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kp;
    }

    public static String encryptRSAToString(String clearText, String publicKey) {
        String encryptedBase64 = "";
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey.trim().getBytes()));
            Key key = keyFac.generatePublic(keySpec);

            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(clearText.getBytes("UTF-8"));
            encryptedBase64 = new String(Base64.getEncoder().encodeToString(encryptedBytes));
        } catch (Exception  e) {
            e.printStackTrace();
        }

        return encryptedBase64.replaceAll("(\\r|\\n)", "");//new line or carriage return replace kar and send kr
    }

    public static String decryptRSAToString(String encryptedBase64, String privateKey) {

        String decryptedString = "";
        try {
            KeyFactory keyFac = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.trim().getBytes()));
            Key key = keyFac.generatePrivate(keySpec);

            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
            // encrypt the plain text using the public key
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedString = new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptedString;
    }

    public void getspeechinput(View view) {

        Intent intent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent,1001);
        }
        else
        {
            Toast.makeText(this,"your device does not support this feature",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case 1001:
                if(resultCode==RESULT_OK&&data!=null)
                {
                    ArrayList<String> res=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    input.setText(res.get(0));
                }
                break;
        }
    }

>>>>>>> 84e8f4b9bd4e40e78097efe6131e730d2c3c470f
}