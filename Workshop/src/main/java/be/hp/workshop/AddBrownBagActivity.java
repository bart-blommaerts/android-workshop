package be.hp.workshop;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.hp.workshop.data.model.BrownBag;
import be.hp.workshop.data.service.BrownBagService;

public class AddBrownBagActivity extends Activity {

    private AddBrownBagTask mAddTask = null;

    private String mTitle;
    private String mContent;

    private EditText mTitleView;
    private EditText mContentView;

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
    }

    public class AddBrownBagTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                BrownBag brownBag = new BrownBag(null, mTitle, mContent, null);
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
}
