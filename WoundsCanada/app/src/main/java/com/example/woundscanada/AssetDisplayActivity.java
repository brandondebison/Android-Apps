package com.example.woundscanada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;

public class AssetDisplayActivity extends AppCompatActivity implements OnRenderListener {

    String targetPdf;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_display);
        PDFView pdfView = findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.progressBar);

        targetPdf = getIntent().getStringExtra("pdfFile");

        progressBar.setVisibility(View.VISIBLE);

        //open the pdf
        pdfView.fromAsset(targetPdf)
                .enableSwipe(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .enableAntialiasing(true)
                .spacing(0)
                .onRender(this)
                .load();

    }


    //onInitiallyRendered is called when the pdf form is first rendered, this is used to disable the progress bar
    @Override
    public void onInitiallyRendered(int nbPages) {
        //Log.d("test", "RENDER COMPLETE");
        progressBar.setVisibility(View.INVISIBLE);
    }
}