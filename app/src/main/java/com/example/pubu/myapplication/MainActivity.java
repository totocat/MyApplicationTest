package com.example.pubu.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "Edward:Main";
    public LoginButton loginButton; // FB loging button
    public CallbackManager callbackManager;
    private TextView info;
    private LikeView likeView;
    private ShareButton shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate()....");
        // Edward: initialize FB SDK before executing any other operstions, esp. if you are using FB UI elements
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Edward: For FB
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

        likeView = (LikeView) findViewById(R.id.likeView);
        likeView.setLikeViewStyle(LikeView.Style.STANDARD);
        likeView.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE);
        likeView.setObjectIdAndType("http://inthecheesefactory.com/blog/understand-android-activity-launchmode/en", LikeView.ObjectType.PAGE);

      //  ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build();
        ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://www.youtube.com/watch?v=2Jm8Cbfl4yE")).build();
        shareButton = (ShareButton)  findViewById(R.id.share_button);
        shareButton.setShareContent(content);

        //ShareVideo shareVideo = new ShareVideo.Builder().setLocalUrl(Uri.parse("https://www.youtube.com/watch?v=2Jm8Cbfl4yE")).build();
        //ShareVideoContent videoContent = new ShareVideoContent.Builder().setVideo(shareVideo).build();
        //shareButton.setShareContent(videoContent);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(LOG_TAG, "FB onSuccess()");
                info.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d(LOG_TAG, object.toString());
                            }
                        });
                Bundle parameters = new Bundle();
               parameters.putString("fields", "id,name,link,birthday,gender,last_name,location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d(LOG_TAG, "FB onCancel()");
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d(LOG_TAG, "FB onError()");
                info.setText("Login attempt failed.");
            }


        });

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "onActivityResult()...");
        callbackManager.onActivityResult(requestCode, resultCode, data);

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

        // recyclerview test
        if (id == R.id.action_recyclerview) {
            Log.d(LOG_TAG, "Recyclerview Test....");
            Intent recyclerviewActivity = new Intent(getApplicationContext(), RecyclerviewActivity.class);
            startActivity(recyclerviewActivity);
            return true;
        }

        // design  test
        if (id == R.id.action_design) {
            Log.d(LOG_TAG, "Recyclerview Test....");
            Intent designActivity = new Intent(getApplicationContext(), DesignActivity.class);
            startActivity(designActivity);
            return true;
        }

        // custome view test
        if (id == R.id.action_myview) {
            Log.d(LOG_TAG, "Custom Test....");
            Intent myviewActivity = new Intent(getApplicationContext(), MyviewActivity.class);
            startActivity(myviewActivity);
            return true;
        }

        // tranitional fragment  test
        if (id == R.id.action_transitionFrament) {
            Log.d(LOG_TAG, "action_transitionFrament Test....");
            Intent transitionFragmentActivity = new Intent(getApplicationContext(), TransitionFragmentActivity.class);
            startActivity(transitionFragmentActivity);
            return true;
        }

        // tranitional fragment  test
        if (id == R.id.action_simpleFrament) {
            Log.d(LOG_TAG, "action_simpleFrament Test....");
            Intent simpleFragmentActivity = new Intent(getApplicationContext(), SimpleFragmentActivity.class);
            startActivity(simpleFragmentActivity);
            return true;
        }

        // flipper view test
        if (id == R.id.action_viewFlipper) {
            Log.d(LOG_TAG, "action_viewFlipper Test....");
            Intent viewFlipperActivity = new Intent(getApplicationContext(), ViewFlipperActivity.class);
            startActivity(viewFlipperActivity);
            return true;
        }

        // Gallery view test
        if (id == R.id.action_gallery) {
            Log.d(LOG_TAG, "action_gallery Test....");
            Intent galleryActivity = new Intent(getApplicationContext(), GalleryActivity.class);
            startActivity(galleryActivity);
            return true;
        }

        // CVSview test
        if (id == R.id.action_cvs) {
            Log.d(LOG_TAG, "action_cvs Test....");
            Intent cvsActivity = new Intent(getApplicationContext(), CvsActivity.class);
            startActivity(cvsActivity);
            return true;
        }

        // Image Slide
        if (id == R.id.action_imgSlide) {
            Log.d(LOG_TAG, "Image Slide Test....");
            Intent imgSlideActivity = new Intent(getApplicationContext(), ImgSlideActivity.class);
            startActivity(imgSlideActivity);
            return true;
        }

        // Image Slide
        if (id == R.id.action_dragDrop) {
            Log.d(LOG_TAG, "Drag and Drop Test....");
            Intent dragDropActivity = new Intent(getApplicationContext(), DragDropActivity.class);
            startActivity(dragDropActivity);
            return true;
        }

        // Image Slide
        if (id == R.id.action_expandableListView) {
            Log.d(LOG_TAG, "Expandable ListView Test....");
            Intent expandableListViewActivity = new Intent(getApplicationContext(), ExpandableListViewActivity.class);
            startActivity(expandableListViewActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

}
