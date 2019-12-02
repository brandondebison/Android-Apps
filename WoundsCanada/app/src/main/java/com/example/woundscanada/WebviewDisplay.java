package com.example.woundscanada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

public class WebviewDisplay extends AppCompatActivity implements OnRenderListener {

    ProgressBar progressBar;
    String pdfURL;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_display);

        pdfView = findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.progressBar);
        //get the url from the intent
        pdfURL = getIntent().getStringExtra("pdfURL");

        loadPdf();
    }

    @Override
    //on resume gets called
    public void onResume(){
        super.onResume();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(WebviewDisplay.this, "Destroy called!", Toast.LENGTH_SHORT).show();

    }


    //when the activity is killed OR moved to background
    @Override
    public void onStop(){
        super.onStop();
        //delete the data for the pdf, this is needed because the file loader saves the file to the app's data.
        //this is a problem because the files can be large and the file size will continue to grow after time.
        //this is not a perfect solution, it is not convenient to constantly redownload pdfs, an alternative would be to have a
        try{
            FileLoader.deleteWith(this).fromDirectory("PDFFiles", FileLoader.DIR_INTERNAL).deleteFiles(pdfURL);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //function for loading the pdf from the internet and displaying it in the pdfView
    private void loadPdf(){
        //show the progress bar so the app does not appear unresponsive
        progressBar.setVisibility(View.VISIBLE);
        //Downloads the file from the internet
        FileLoader.with(this)
                .load(pdfURL)
                .fromDirectory("PDFFiles", FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    //if the load was successful
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        File pdfFile = response.getBody();//get the file from the response
                        //display the pdf in the pdfView
                        pdfView.fromFile(pdfFile)
                                .enableSwipe(true)
                                .enableDoubletap(true)
                                .defaultPage(0)
                                .enableAnnotationRendering(false)
                                .onRender(WebviewDisplay.this)
                                .enableAntialiasing(true)
                                .spacing(0)
                                .load();
                    }

                    //if the file couldn't be loaded
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Toast.makeText(WebviewDisplay.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        //Log.d("Error", t.getMessage());
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    //this function is called when the pdfView successfully renders the pdf, which can only happen after the download
    @Override
    public void onInitiallyRendered(int nbPages) {
        progressBar.setVisibility(View.INVISIBLE);//hides the progress bar
    }

}
