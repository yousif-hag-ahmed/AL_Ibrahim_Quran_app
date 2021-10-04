package com.example.al_ibrahim_group_quran_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class QuranRead extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_read);

        Intent intent = getIntent();
        int hizbNum = intent.getIntExtra("hizbNum",0);
        PDFView pdfView = findViewById(R.id.pdfView);
        PageHizbPro pageHizbPro = new PageHizbPro();

        pageHizbPro.setHizbNum(hizbNum);
        int[] array = pageHizbPro.getMyArray();
        Log.i("the array", String.valueOf(array.length));

        pdfView.fromAsset("reducedarabic-quran.pdf")
                .pages(array) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(11)
                .scrollHandle(null)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)

                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .autoSpacing(true) // add dynamic spacing to fit each page on its own on the screen

                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(true) // snap pages to screen boundaries
                .pageFling(true) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();
    }

}
