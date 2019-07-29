package com.example.contentprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    int LOADER_ID = 007;
    Bundle instructions;
    ListView listView;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoaderManager loaderManager = getSupportLoaderManager();

        loaderManager.initLoader(LOADER_ID,instructions,this);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.smslistview);

        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{"body"},
                new int[]{android.R.id.text1},
                0 );


        listView.setAdapter(adapter);


    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle instructions) {
        if(id == LOADER_ID){
            Uri uriSms = Uri.parse("content://sms/inbox");

            return new CursorLoader(this,uriSms,null,null,null,null);
        }
        return null;
    }



    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor dataCursor) {
        adapter.swapCursor(dataCursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }


}
