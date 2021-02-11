package com.cmh.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.os.Process;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAbout;
    private Button btnPlay;

    private Context mContext;
    //private Activity mActivity;

    private RelativeLayout mRelativeLayout;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        // Get the application context
        mContext = getApplicationContext();

        // Get the activity
        //mActivity = MainActivity.this;

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_custom_layout);


        /**
         * Play Button action
         */
        btnPlay = (Button) findViewById(R.id.btnPlay);

        // Set a click listener for the text view
        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ChoiceBoardActivity1.class);
                MainActivity.this.startActivity(myIntent);
            }
        });


        /**
         * About Button action
         */
        LinearLayout layout = (LinearLayout) findViewById(R.id.ly_main_page);
        btnAbout = (Button) findViewById(R.id.btnAbout);

        // Set a click listener for the text view
        btnAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.custom_popup_about_layout,null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        customView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                );

                // Set an elevation value for popup window
                // Call requires API level 21
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(layout, Gravity.CENTER,0,0);
            }
        });

        /**
         * Exit Button action
         */
        final Button button = (Button) findViewById(R.id.btnExit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmstion")
                        .setMessage("Voulez-vous vraiement quitter?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //MainActivity.this.finish();
                                MainActivity.this.finishAffinity();
                                //System.exit(0);
                                Process.killProcess(Process.myPid());
                            }})

                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}