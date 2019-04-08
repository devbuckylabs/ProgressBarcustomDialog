package com.buckylabs.progressbarcustomdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Context context;
    ProgressBar progressBar;
    TextView textView;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;



        View v = getLayoutInflater().inflate(R.layout.custom_dialog,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Progress");
        builder.setMessage("Downloading...");
        builder.setView(v);
        dialog=builder.create();

        progressBar =v.findViewById(R.id.progress_bar);
        textView = v.findViewById(R.id.tv);

        dialog.show();
        //textView.setText("Woah");

        bg t = new bg(this);
        t.execute();

    }

    class bg extends AsyncTask<Void, Integer, Void> {
        Context context;


        public bg(Context context) {

            this.context = context;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setMax(100);
            textView.setText("Woah");
            Toast.makeText(context, "Preee", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (int h = 0; h < 100; h++) {

               // progressBar.incrementProgressBy(h);
                publishProgress(h);

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.incrementProgressBy(values[0]);
            textView.setText(""+values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context, "Post", Toast.LENGTH_SHORT).show();
            Log.e("Post","post");
            textView.setText("HOLO");
           // dialog.dismiss();

        }
    }
}