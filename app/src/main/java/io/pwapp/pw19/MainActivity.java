package io.pwapp.pw19;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText serviceInputMain = (EditText) findViewById(R.id.serviceInput);
        final EditText passwordInputMain = (EditText) findViewById(R.id.passwordInput);
        final TextView pwOutput = (TextView) findViewById(R.id.pwOutput);

        TextView pwappLinkMain =(TextView)findViewById(R.id.pwappLink);
        pwappLinkMain.setClickable(true);
        pwappLinkMain.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.pwapp.io'> www.pwapp.io </a>";
        pwappLinkMain.setText(Html.fromHtml(text));

        Button ButtonCopyMain = (Button) findViewById(R.id.buttonCopy);

        // service input pw output
        serviceInputMain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("afterTextChanged" + Math.random());
                System.out.println("serviceInputMain = " + serviceInputMain.getText() + " passwordInputMain = " + passwordInputMain.getText());
                System.out.println("comabined = " + serviceInputMain.getText() + passwordInputMain.getText());
                TextView pwOutput = (TextView) findViewById(R.id.pwOutput);

                //actual code
                System.out.println(serviceInputMain.getText() + "||" + "||");
                try {
                    System.out.println(AeSimpleSHA1.SHA1(serviceInputMain.getText() + "||" + passwordInputMain.getText() + "||"));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                // make the service lowercase
                String srvLower = String.valueOf(serviceInputMain.getText()).toLowerCase();
                System.out.println("srvLower = " + srvLower);

                String pwHash = "";

                try {
                    pwHash = AeSimpleSHA1.SHA1(srvLower + "||" + passwordInputMain.getText() + "||");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                System.out.println("pwHash = " + pwHash);

                // capitalise every other char
                String pwOutputInternal = "";
                String r = pwHash;
                for (int i = 0; i < r.length(); i++) {
                    char c = r.charAt(i);
                    if (i % 2 == 0) {
                        pwOutputInternal += r.substring(i, i + 1).toUpperCase();
                    } else {
                        pwOutputInternal += r.substring(i, i +1);
                    }
                }

                System.out.println("triggerd by passwordInput and is " + pwOutputInternal);
                pwOutput.setText(pwOutputInternal);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // password input pw update
        passwordInputMain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("afterTextChanged" + Math.random());
                System.out.println("serviceInputMain = " + serviceInputMain.getText() + " passwordInputMain = " + passwordInputMain.getText());
                System.out.println("comabined = " + serviceInputMain.getText() + passwordInputMain.getText());
                TextView pwOutput = (TextView) findViewById(R.id.pwOutput);

                //actual code
                System.out.println(serviceInputMain.getText() + "||" + "||");
                try {
                    System.out.println(AeSimpleSHA1.SHA1(serviceInputMain.getText() + "||" + passwordInputMain.getText() + "||"));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                // make the service lowercase
                String srvLower = String.valueOf(serviceInputMain.getText()).toLowerCase();
                System.out.println("srvLower = " + srvLower);

                String pwHash = "";

                try {
                    pwHash = AeSimpleSHA1.SHA1(srvLower + "||" + passwordInputMain.getText() + "||");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                System.out.println("pwHash = " + pwHash);

                // capitalise every other char
                String pwOutputInternal = "";
                String r = pwHash;
                for (int i = 0; i < r.length(); i++) {
                    char c = r.charAt(i);
                    if (i % 2 == 0) {
                        pwOutputInternal += r.substring(i, i + 1).toUpperCase();
                    } else {
                        pwOutputInternal += r.substring(i, i + 1);
                    }
                }

                System.out.println("triggerd by passwordInput and is " + pwOutputInternal);
                pwOutput.setText(pwOutputInternal);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // copy password
        ButtonCopyMain.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("pw", pwOutput.getText());
                        clipboard.setPrimaryClip(clip);
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Copied to clipboard :)",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );

    }
}
