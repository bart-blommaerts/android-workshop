package be.hp.workshop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import be.hp.workshop.data.model.BrownBag;
import be.hp.workshop.data.service.BrownBagService;
import be.hp.workshop.util.CameraManipulation;

public class AddBrownBagActivity extends Activity {

    private AddBrownBagTask mAddTask = null;

    private String mTitle;
    private String mContent;

    private EditText mTitleView;
    private EditText mContentView;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private String imageUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brownbag);

        mTitleView = (EditText) findViewById(R.id.title);
        mContentView = (EditText) findViewById(R.id.content);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle = mTitleView.getText().toString();
                mContent = mContentView.getText().toString();

                mAddTask = new AddBrownBagTask();
                mAddTask.execute((Void) null);
            }
        });

        findViewById(R.id.add_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri fileUri = CameraManipulation.getOutputMediaFileUri(CameraManipulation.MEDIA_TYPE_IMAGE); // create a file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                imageUrl = fileUri.getPath();
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public class AddBrownBagTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                BrownBag brownBag = new BrownBag(null, mTitle, mContent, null, imageUrl);
                BrownBagService brownBagService = new BrownBagService();
                brownBagService.insert(AddBrownBagActivity.this.getBaseContext(), brownBag);

                finish();
            } else {
                // don't insert
            }
        }

        @Override
        protected void onCancelled() {
            // verify nothing has been inserted
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && imageUrl != null) {
                    Toast.makeText(this, "Image saved to:\n" +  imageUrl, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }
}
