package io.manycore.threadsecurity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

// emulator -cores 4 -sdcard sdcard.img &

public class MainActivity extends AppCompatActivity {
    public Semaphore sem = new Semaphore(0);
    public AtomicInteger nbrJobs = new AtomicInteger();
    public AtomicInteger nbrRun = new AtomicInteger();
    public AtomicInteger nbrOnWorkerQueue = new AtomicInteger();

    private final int NBR_ROWS = 20;
    private final int NBR_COLS = 16;
    private final int NBR_JOBS = 30000;

    ImageView[][] imageViewArray = new ImageView[NBR_COLS][NBR_ROWS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewArray[0][0] = (ImageView) findViewById(R.id.image_0_0);
        imageViewArray[1][0] = (ImageView) findViewById(R.id.image_1_0);
        imageViewArray[2][0] = (ImageView) findViewById(R.id.image_2_0);
        imageViewArray[3][0] = (ImageView) findViewById(R.id.image_3_0);
        imageViewArray[4][0] = (ImageView) findViewById(R.id.image_4_0);
        imageViewArray[5][0] = (ImageView) findViewById(R.id.image_5_0);
        imageViewArray[6][0] = (ImageView) findViewById(R.id.image_6_0);
        imageViewArray[7][0] = (ImageView) findViewById(R.id.image_7_0);
        imageViewArray[8][0] = (ImageView) findViewById(R.id.image_8_0);
        imageViewArray[9][0] = (ImageView) findViewById(R.id.image_9_0);
        imageViewArray[10][0] = (ImageView) findViewById(R.id.image_10_0);
        imageViewArray[11][0] = (ImageView) findViewById(R.id.image_11_0);
        imageViewArray[12][0] = (ImageView) findViewById(R.id.image_12_0);
        imageViewArray[13][0] = (ImageView) findViewById(R.id.image_13_0);
        imageViewArray[14][0] = (ImageView) findViewById(R.id.image_14_0);
        imageViewArray[15][0] = (ImageView) findViewById(R.id.image_15_0);
        imageViewArray[0][1] = (ImageView) findViewById(R.id.image_0_1);
        imageViewArray[1][1] = (ImageView) findViewById(R.id.image_1_1);
        imageViewArray[2][1] = (ImageView) findViewById(R.id.image_2_1);
        imageViewArray[3][1] = (ImageView) findViewById(R.id.image_3_1);
        imageViewArray[4][1] = (ImageView) findViewById(R.id.image_4_1);
        imageViewArray[5][1] = (ImageView) findViewById(R.id.image_5_1);
        imageViewArray[6][1] = (ImageView) findViewById(R.id.image_6_1);
        imageViewArray[7][1] = (ImageView) findViewById(R.id.image_7_1);
        imageViewArray[8][1] = (ImageView) findViewById(R.id.image_8_1);
        imageViewArray[9][1] = (ImageView) findViewById(R.id.image_9_1);
        imageViewArray[10][1] = (ImageView) findViewById(R.id.image_10_1);
        imageViewArray[11][1] = (ImageView) findViewById(R.id.image_11_1);
        imageViewArray[12][1] = (ImageView) findViewById(R.id.image_12_1);
        imageViewArray[13][1] = (ImageView) findViewById(R.id.image_13_1);
        imageViewArray[14][1] = (ImageView) findViewById(R.id.image_14_1);
        imageViewArray[15][1] = (ImageView) findViewById(R.id.image_15_1);
        imageViewArray[0][2] = (ImageView) findViewById(R.id.image_0_2);
        imageViewArray[1][2] = (ImageView) findViewById(R.id.image_1_2);
        imageViewArray[2][2] = (ImageView) findViewById(R.id.image_2_2);
        imageViewArray[3][2] = (ImageView) findViewById(R.id.image_3_2);
        imageViewArray[4][2] = (ImageView) findViewById(R.id.image_4_2);
        imageViewArray[5][2] = (ImageView) findViewById(R.id.image_5_2);
        imageViewArray[6][2] = (ImageView) findViewById(R.id.image_6_2);
        imageViewArray[7][2] = (ImageView) findViewById(R.id.image_7_2);
        imageViewArray[8][2] = (ImageView) findViewById(R.id.image_8_2);
        imageViewArray[9][2] = (ImageView) findViewById(R.id.image_9_2);
        imageViewArray[10][2] = (ImageView) findViewById(R.id.image_10_2);
        imageViewArray[11][2] = (ImageView) findViewById(R.id.image_11_2);
        imageViewArray[12][2] = (ImageView) findViewById(R.id.image_12_2);
        imageViewArray[13][2] = (ImageView) findViewById(R.id.image_13_2);
        imageViewArray[14][2] = (ImageView) findViewById(R.id.image_14_2);
        imageViewArray[15][2] = (ImageView) findViewById(R.id.image_15_2);
        imageViewArray[0][3] = (ImageView) findViewById(R.id.image_0_3);
        imageViewArray[1][3] = (ImageView) findViewById(R.id.image_1_3);
        imageViewArray[2][3] = (ImageView) findViewById(R.id.image_2_3);
        imageViewArray[3][3] = (ImageView) findViewById(R.id.image_3_3);
        imageViewArray[4][3] = (ImageView) findViewById(R.id.image_4_3);
        imageViewArray[5][3] = (ImageView) findViewById(R.id.image_5_3);
        imageViewArray[6][3] = (ImageView) findViewById(R.id.image_6_3);
        imageViewArray[7][3] = (ImageView) findViewById(R.id.image_7_3);
        imageViewArray[8][3] = (ImageView) findViewById(R.id.image_8_3);
        imageViewArray[9][3] = (ImageView) findViewById(R.id.image_9_3);
        imageViewArray[10][3] = (ImageView) findViewById(R.id.image_10_3);
        imageViewArray[11][3] = (ImageView) findViewById(R.id.image_11_3);
        imageViewArray[12][3] = (ImageView) findViewById(R.id.image_12_3);
        imageViewArray[13][3] = (ImageView) findViewById(R.id.image_13_3);
        imageViewArray[14][3] = (ImageView) findViewById(R.id.image_14_3);
        imageViewArray[15][3] = (ImageView) findViewById(R.id.image_15_3);
        imageViewArray[0][4] = (ImageView) findViewById(R.id.image_0_4);
        imageViewArray[1][4] = (ImageView) findViewById(R.id.image_1_4);
        imageViewArray[2][4] = (ImageView) findViewById(R.id.image_2_4);
        imageViewArray[3][4] = (ImageView) findViewById(R.id.image_3_4);
        imageViewArray[4][4] = (ImageView) findViewById(R.id.image_4_4);
        imageViewArray[5][4] = (ImageView) findViewById(R.id.image_5_4);
        imageViewArray[6][4] = (ImageView) findViewById(R.id.image_6_4);
        imageViewArray[7][4] = (ImageView) findViewById(R.id.image_7_4);
        imageViewArray[8][4] = (ImageView) findViewById(R.id.image_8_4);
        imageViewArray[9][4] = (ImageView) findViewById(R.id.image_9_4);
        imageViewArray[10][4] = (ImageView) findViewById(R.id.image_10_4);
        imageViewArray[11][4] = (ImageView) findViewById(R.id.image_11_4);
        imageViewArray[12][4] = (ImageView) findViewById(R.id.image_12_4);
        imageViewArray[13][4] = (ImageView) findViewById(R.id.image_13_4);
        imageViewArray[14][4] = (ImageView) findViewById(R.id.image_14_4);
        imageViewArray[15][4] = (ImageView) findViewById(R.id.image_15_4);
        imageViewArray[0][5] = (ImageView) findViewById(R.id.image_0_5);
        imageViewArray[1][5] = (ImageView) findViewById(R.id.image_1_5);
        imageViewArray[2][5] = (ImageView) findViewById(R.id.image_2_5);
        imageViewArray[3][5] = (ImageView) findViewById(R.id.image_3_5);
        imageViewArray[4][5] = (ImageView) findViewById(R.id.image_4_5);
        imageViewArray[5][5] = (ImageView) findViewById(R.id.image_5_5);
        imageViewArray[6][5] = (ImageView) findViewById(R.id.image_6_5);
        imageViewArray[7][5] = (ImageView) findViewById(R.id.image_7_5);
        imageViewArray[8][5] = (ImageView) findViewById(R.id.image_8_5);
        imageViewArray[9][5] = (ImageView) findViewById(R.id.image_9_5);
        imageViewArray[10][5] = (ImageView) findViewById(R.id.image_10_5);
        imageViewArray[11][5] = (ImageView) findViewById(R.id.image_11_5);
        imageViewArray[12][5] = (ImageView) findViewById(R.id.image_12_5);
        imageViewArray[13][5] = (ImageView) findViewById(R.id.image_13_5);
        imageViewArray[14][5] = (ImageView) findViewById(R.id.image_14_5);
        imageViewArray[15][5] = (ImageView) findViewById(R.id.image_15_5);
        imageViewArray[0][6] = (ImageView) findViewById(R.id.image_0_6);
        imageViewArray[1][6] = (ImageView) findViewById(R.id.image_1_6);
        imageViewArray[2][6] = (ImageView) findViewById(R.id.image_2_6);
        imageViewArray[3][6] = (ImageView) findViewById(R.id.image_3_6);
        imageViewArray[4][6] = (ImageView) findViewById(R.id.image_4_6);
        imageViewArray[5][6] = (ImageView) findViewById(R.id.image_5_6);
        imageViewArray[6][6] = (ImageView) findViewById(R.id.image_6_6);
        imageViewArray[7][6] = (ImageView) findViewById(R.id.image_7_6);
        imageViewArray[8][6] = (ImageView) findViewById(R.id.image_8_6);
        imageViewArray[9][6] = (ImageView) findViewById(R.id.image_9_6);
        imageViewArray[10][6] = (ImageView) findViewById(R.id.image_10_6);
        imageViewArray[11][6] = (ImageView) findViewById(R.id.image_11_6);
        imageViewArray[12][6] = (ImageView) findViewById(R.id.image_12_6);
        imageViewArray[13][6] = (ImageView) findViewById(R.id.image_13_6);
        imageViewArray[14][6] = (ImageView) findViewById(R.id.image_14_6);
        imageViewArray[15][6] = (ImageView) findViewById(R.id.image_15_6);
        imageViewArray[0][7] = (ImageView) findViewById(R.id.image_0_7);
        imageViewArray[1][7] = (ImageView) findViewById(R.id.image_1_7);
        imageViewArray[2][7] = (ImageView) findViewById(R.id.image_2_7);
        imageViewArray[3][7] = (ImageView) findViewById(R.id.image_3_7);
        imageViewArray[4][7] = (ImageView) findViewById(R.id.image_4_7);
        imageViewArray[5][7] = (ImageView) findViewById(R.id.image_5_7);
        imageViewArray[6][7] = (ImageView) findViewById(R.id.image_6_7);
        imageViewArray[7][7] = (ImageView) findViewById(R.id.image_7_7);
        imageViewArray[8][7] = (ImageView) findViewById(R.id.image_8_7);
        imageViewArray[9][7] = (ImageView) findViewById(R.id.image_9_7);
        imageViewArray[10][7] = (ImageView) findViewById(R.id.image_10_7);
        imageViewArray[11][7] = (ImageView) findViewById(R.id.image_11_7);
        imageViewArray[12][7] = (ImageView) findViewById(R.id.image_12_7);
        imageViewArray[13][7] = (ImageView) findViewById(R.id.image_13_7);
        imageViewArray[14][7] = (ImageView) findViewById(R.id.image_14_7);
        imageViewArray[15][7] = (ImageView) findViewById(R.id.image_15_7);
        imageViewArray[0][8] = (ImageView) findViewById(R.id.image_0_8);
        imageViewArray[1][8] = (ImageView) findViewById(R.id.image_1_8);
        imageViewArray[2][8] = (ImageView) findViewById(R.id.image_2_8);
        imageViewArray[3][8] = (ImageView) findViewById(R.id.image_3_8);
        imageViewArray[4][8] = (ImageView) findViewById(R.id.image_4_8);
        imageViewArray[5][8] = (ImageView) findViewById(R.id.image_5_8);
        imageViewArray[6][8] = (ImageView) findViewById(R.id.image_6_8);
        imageViewArray[7][8] = (ImageView) findViewById(R.id.image_7_8);
        imageViewArray[8][8] = (ImageView) findViewById(R.id.image_8_8);
        imageViewArray[9][8] = (ImageView) findViewById(R.id.image_9_8);
        imageViewArray[10][8] = (ImageView) findViewById(R.id.image_10_8);
        imageViewArray[11][8] = (ImageView) findViewById(R.id.image_11_8);
        imageViewArray[12][8] = (ImageView) findViewById(R.id.image_12_8);
        imageViewArray[13][8] = (ImageView) findViewById(R.id.image_13_8);
        imageViewArray[14][8] = (ImageView) findViewById(R.id.image_14_8);
        imageViewArray[15][8] = (ImageView) findViewById(R.id.image_15_8);
        imageViewArray[0][9] = (ImageView) findViewById(R.id.image_0_9);
        imageViewArray[1][9] = (ImageView) findViewById(R.id.image_1_9);
        imageViewArray[2][9] = (ImageView) findViewById(R.id.image_2_9);
        imageViewArray[3][9] = (ImageView) findViewById(R.id.image_3_9);
        imageViewArray[4][9] = (ImageView) findViewById(R.id.image_4_9);
        imageViewArray[5][9] = (ImageView) findViewById(R.id.image_5_9);
        imageViewArray[6][9] = (ImageView) findViewById(R.id.image_6_9);
        imageViewArray[7][9] = (ImageView) findViewById(R.id.image_7_9);
        imageViewArray[8][9] = (ImageView) findViewById(R.id.image_8_9);
        imageViewArray[9][9] = (ImageView) findViewById(R.id.image_9_9);
        imageViewArray[10][9] = (ImageView) findViewById(R.id.image_10_9);
        imageViewArray[11][9] = (ImageView) findViewById(R.id.image_11_9);
        imageViewArray[12][9] = (ImageView) findViewById(R.id.image_12_9);
        imageViewArray[13][9] = (ImageView) findViewById(R.id.image_13_9);
        imageViewArray[14][9] = (ImageView) findViewById(R.id.image_14_9);
        imageViewArray[15][9] = (ImageView) findViewById(R.id.image_15_9);
        imageViewArray[0][10] = (ImageView) findViewById(R.id.image_0_10);
        imageViewArray[1][10] = (ImageView) findViewById(R.id.image_1_10);
        imageViewArray[2][10] = (ImageView) findViewById(R.id.image_2_10);
        imageViewArray[3][10] = (ImageView) findViewById(R.id.image_3_10);
        imageViewArray[4][10] = (ImageView) findViewById(R.id.image_4_10);
        imageViewArray[5][10] = (ImageView) findViewById(R.id.image_5_10);
        imageViewArray[6][10] = (ImageView) findViewById(R.id.image_6_10);
        imageViewArray[7][10] = (ImageView) findViewById(R.id.image_7_10);
        imageViewArray[8][10] = (ImageView) findViewById(R.id.image_8_10);
        imageViewArray[9][10] = (ImageView) findViewById(R.id.image_9_10);
        imageViewArray[10][10] = (ImageView) findViewById(R.id.image_10_10);
        imageViewArray[11][10] = (ImageView) findViewById(R.id.image_11_10);
        imageViewArray[12][10] = (ImageView) findViewById(R.id.image_12_10);
        imageViewArray[13][10] = (ImageView) findViewById(R.id.image_13_10);
        imageViewArray[14][10] = (ImageView) findViewById(R.id.image_14_10);
        imageViewArray[15][10] = (ImageView) findViewById(R.id.image_15_10);
        imageViewArray[0][11] = (ImageView) findViewById(R.id.image_0_11);
        imageViewArray[1][11] = (ImageView) findViewById(R.id.image_1_11);
        imageViewArray[2][11] = (ImageView) findViewById(R.id.image_2_11);
        imageViewArray[3][11] = (ImageView) findViewById(R.id.image_3_11);
        imageViewArray[4][11] = (ImageView) findViewById(R.id.image_4_11);
        imageViewArray[5][11] = (ImageView) findViewById(R.id.image_5_11);
        imageViewArray[6][11] = (ImageView) findViewById(R.id.image_6_11);
        imageViewArray[7][11] = (ImageView) findViewById(R.id.image_7_11);
        imageViewArray[8][11] = (ImageView) findViewById(R.id.image_8_11);
        imageViewArray[9][11] = (ImageView) findViewById(R.id.image_9_11);
        imageViewArray[10][11] = (ImageView) findViewById(R.id.image_10_11);
        imageViewArray[11][11] = (ImageView) findViewById(R.id.image_11_11);
        imageViewArray[12][11] = (ImageView) findViewById(R.id.image_12_11);
        imageViewArray[13][11] = (ImageView) findViewById(R.id.image_13_11);
        imageViewArray[14][11] = (ImageView) findViewById(R.id.image_14_11);
        imageViewArray[15][11] = (ImageView) findViewById(R.id.image_15_11);
        imageViewArray[0][12] = (ImageView) findViewById(R.id.image_0_12);
        imageViewArray[1][12] = (ImageView) findViewById(R.id.image_1_12);
        imageViewArray[2][12] = (ImageView) findViewById(R.id.image_2_12);
        imageViewArray[3][12] = (ImageView) findViewById(R.id.image_3_12);
        imageViewArray[4][12] = (ImageView) findViewById(R.id.image_4_12);
        imageViewArray[5][12] = (ImageView) findViewById(R.id.image_5_12);
        imageViewArray[6][12] = (ImageView) findViewById(R.id.image_6_12);
        imageViewArray[7][12] = (ImageView) findViewById(R.id.image_7_12);
        imageViewArray[8][12] = (ImageView) findViewById(R.id.image_8_12);
        imageViewArray[9][12] = (ImageView) findViewById(R.id.image_9_12);
        imageViewArray[10][12] = (ImageView) findViewById(R.id.image_10_12);
        imageViewArray[11][12] = (ImageView) findViewById(R.id.image_11_12);
        imageViewArray[12][12] = (ImageView) findViewById(R.id.image_12_12);
        imageViewArray[13][12] = (ImageView) findViewById(R.id.image_13_12);
        imageViewArray[14][12] = (ImageView) findViewById(R.id.image_14_12);
        imageViewArray[15][12] = (ImageView) findViewById(R.id.image_15_12);
        imageViewArray[0][13] = (ImageView) findViewById(R.id.image_0_13);
        imageViewArray[1][13] = (ImageView) findViewById(R.id.image_1_13);
        imageViewArray[2][13] = (ImageView) findViewById(R.id.image_2_13);
        imageViewArray[3][13] = (ImageView) findViewById(R.id.image_3_13);
        imageViewArray[4][13] = (ImageView) findViewById(R.id.image_4_13);
        imageViewArray[5][13] = (ImageView) findViewById(R.id.image_5_13);
        imageViewArray[6][13] = (ImageView) findViewById(R.id.image_6_13);
        imageViewArray[7][13] = (ImageView) findViewById(R.id.image_7_13);
        imageViewArray[8][13] = (ImageView) findViewById(R.id.image_8_13);
        imageViewArray[9][13] = (ImageView) findViewById(R.id.image_9_13);
        imageViewArray[10][13] = (ImageView) findViewById(R.id.image_10_13);
        imageViewArray[11][13] = (ImageView) findViewById(R.id.image_11_13);
        imageViewArray[12][13] = (ImageView) findViewById(R.id.image_12_13);
        imageViewArray[13][13] = (ImageView) findViewById(R.id.image_13_13);
        imageViewArray[14][13] = (ImageView) findViewById(R.id.image_14_13);
        imageViewArray[15][13] = (ImageView) findViewById(R.id.image_15_13);
        imageViewArray[0][14] = (ImageView) findViewById(R.id.image_0_14);
        imageViewArray[1][14] = (ImageView) findViewById(R.id.image_1_14);
        imageViewArray[2][14] = (ImageView) findViewById(R.id.image_2_14);
        imageViewArray[3][14] = (ImageView) findViewById(R.id.image_3_14);
        imageViewArray[4][14] = (ImageView) findViewById(R.id.image_4_14);
        imageViewArray[5][14] = (ImageView) findViewById(R.id.image_5_14);
        imageViewArray[6][14] = (ImageView) findViewById(R.id.image_6_14);
        imageViewArray[7][14] = (ImageView) findViewById(R.id.image_7_14);
        imageViewArray[8][14] = (ImageView) findViewById(R.id.image_8_14);
        imageViewArray[9][14] = (ImageView) findViewById(R.id.image_9_14);
        imageViewArray[10][14] = (ImageView) findViewById(R.id.image_10_14);
        imageViewArray[11][14] = (ImageView) findViewById(R.id.image_11_14);
        imageViewArray[12][14] = (ImageView) findViewById(R.id.image_12_14);
        imageViewArray[13][14] = (ImageView) findViewById(R.id.image_13_14);
        imageViewArray[14][14] = (ImageView) findViewById(R.id.image_14_14);
        imageViewArray[15][14] = (ImageView) findViewById(R.id.image_15_14);
        imageViewArray[0][15] = (ImageView) findViewById(R.id.image_0_15);
        imageViewArray[1][15] = (ImageView) findViewById(R.id.image_1_15);
        imageViewArray[2][15] = (ImageView) findViewById(R.id.image_2_15);
        imageViewArray[3][15] = (ImageView) findViewById(R.id.image_3_15);
        imageViewArray[4][15] = (ImageView) findViewById(R.id.image_4_15);
        imageViewArray[5][15] = (ImageView) findViewById(R.id.image_5_15);
        imageViewArray[6][15] = (ImageView) findViewById(R.id.image_6_15);
        imageViewArray[7][15] = (ImageView) findViewById(R.id.image_7_15);
        imageViewArray[8][15] = (ImageView) findViewById(R.id.image_8_15);
        imageViewArray[9][15] = (ImageView) findViewById(R.id.image_9_15);
        imageViewArray[10][15] = (ImageView) findViewById(R.id.image_10_15);
        imageViewArray[11][15] = (ImageView) findViewById(R.id.image_11_15);
        imageViewArray[12][15] = (ImageView) findViewById(R.id.image_12_15);
        imageViewArray[13][15] = (ImageView) findViewById(R.id.image_13_15);
        imageViewArray[14][15] = (ImageView) findViewById(R.id.image_14_15);
        imageViewArray[15][15] = (ImageView) findViewById(R.id.image_15_15);
        imageViewArray[0][16] = (ImageView) findViewById(R.id.image_0_16);
        imageViewArray[1][16] = (ImageView) findViewById(R.id.image_1_16);
        imageViewArray[2][16] = (ImageView) findViewById(R.id.image_2_16);
        imageViewArray[3][16] = (ImageView) findViewById(R.id.image_3_16);
        imageViewArray[4][16] = (ImageView) findViewById(R.id.image_4_16);
        imageViewArray[5][16] = (ImageView) findViewById(R.id.image_5_16);
        imageViewArray[6][16] = (ImageView) findViewById(R.id.image_6_16);
        imageViewArray[7][16] = (ImageView) findViewById(R.id.image_7_16);
        imageViewArray[8][16] = (ImageView) findViewById(R.id.image_8_16);
        imageViewArray[9][16] = (ImageView) findViewById(R.id.image_9_16);
        imageViewArray[10][16] = (ImageView) findViewById(R.id.image_10_16);
        imageViewArray[11][16] = (ImageView) findViewById(R.id.image_11_16);
        imageViewArray[12][16] = (ImageView) findViewById(R.id.image_12_16);
        imageViewArray[13][16] = (ImageView) findViewById(R.id.image_13_16);
        imageViewArray[14][16] = (ImageView) findViewById(R.id.image_14_16);
        imageViewArray[15][16] = (ImageView) findViewById(R.id.image_15_16);
        imageViewArray[0][17] = (ImageView) findViewById(R.id.image_0_17);
        imageViewArray[1][17] = (ImageView) findViewById(R.id.image_1_17);
        imageViewArray[2][17] = (ImageView) findViewById(R.id.image_2_17);
        imageViewArray[3][17] = (ImageView) findViewById(R.id.image_3_17);
        imageViewArray[4][17] = (ImageView) findViewById(R.id.image_4_17);
        imageViewArray[5][17] = (ImageView) findViewById(R.id.image_5_17);
        imageViewArray[6][17] = (ImageView) findViewById(R.id.image_6_17);
        imageViewArray[7][17] = (ImageView) findViewById(R.id.image_7_17);
        imageViewArray[8][17] = (ImageView) findViewById(R.id.image_8_17);
        imageViewArray[9][17] = (ImageView) findViewById(R.id.image_9_17);
        imageViewArray[10][17] = (ImageView) findViewById(R.id.image_10_17);
        imageViewArray[11][17] = (ImageView) findViewById(R.id.image_11_17);
        imageViewArray[12][17] = (ImageView) findViewById(R.id.image_12_17);
        imageViewArray[13][17] = (ImageView) findViewById(R.id.image_13_17);
        imageViewArray[14][17] = (ImageView) findViewById(R.id.image_14_17);
        imageViewArray[15][17] = (ImageView) findViewById(R.id.image_15_17);
        imageViewArray[0][18] = (ImageView) findViewById(R.id.image_0_18);
        imageViewArray[1][18] = (ImageView) findViewById(R.id.image_1_18);
        imageViewArray[2][18] = (ImageView) findViewById(R.id.image_2_18);
        imageViewArray[3][18] = (ImageView) findViewById(R.id.image_3_18);
        imageViewArray[4][18] = (ImageView) findViewById(R.id.image_4_18);
        imageViewArray[5][18] = (ImageView) findViewById(R.id.image_5_18);
        imageViewArray[6][18] = (ImageView) findViewById(R.id.image_6_18);
        imageViewArray[7][18] = (ImageView) findViewById(R.id.image_7_18);
        imageViewArray[8][18] = (ImageView) findViewById(R.id.image_8_18);
        imageViewArray[9][18] = (ImageView) findViewById(R.id.image_9_18);
        imageViewArray[10][18] = (ImageView) findViewById(R.id.image_10_18);
        imageViewArray[11][18] = (ImageView) findViewById(R.id.image_11_18);
        imageViewArray[12][18] = (ImageView) findViewById(R.id.image_12_18);
        imageViewArray[13][18] = (ImageView) findViewById(R.id.image_13_18);
        imageViewArray[14][18] = (ImageView) findViewById(R.id.image_14_18);
        imageViewArray[15][18] = (ImageView) findViewById(R.id.image_15_18);
        imageViewArray[0][19] = (ImageView) findViewById(R.id.image_0_19);
        imageViewArray[1][19] = (ImageView) findViewById(R.id.image_1_19);
        imageViewArray[2][19] = (ImageView) findViewById(R.id.image_2_19);
        imageViewArray[3][19] = (ImageView) findViewById(R.id.image_3_19);
        imageViewArray[4][19] = (ImageView) findViewById(R.id.image_4_19);
        imageViewArray[5][19] = (ImageView) findViewById(R.id.image_5_19);
        imageViewArray[6][19] = (ImageView) findViewById(R.id.image_6_19);
        imageViewArray[7][19] = (ImageView) findViewById(R.id.image_7_19);
        imageViewArray[8][19] = (ImageView) findViewById(R.id.image_8_19);
        imageViewArray[9][19] = (ImageView) findViewById(R.id.image_9_19);
        imageViewArray[10][19] = (ImageView) findViewById(R.id.image_10_19);
        imageViewArray[11][19] = (ImageView) findViewById(R.id.image_11_19);
        imageViewArray[12][19] = (ImageView) findViewById(R.id.image_12_19);
        imageViewArray[13][19] = (ImageView) findViewById(R.id.image_13_19);
        imageViewArray[14][19] = (ImageView) findViewById(R.id.image_14_19);
        imageViewArray[15][19] = (ImageView) findViewById(R.id.image_15_19);


        // # threads in emulator?
        // # threads in ExecutorService?

        if (1 == 1) {
            setTitle("Android with Multi Threaded GUI");
            experimentsMT();
        } else {
            setTitle("Google Android");
            experimentsGUI();
        }
    }

    private void experimentsMT() {
        Log.i("Manycore::log", "TimedWorkWorkerThreadActivity ------------------------------------------");

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Log.i("Manycore::log", "multiThreadTest started root run()");

                long msStartPlus5000 = System.currentTimeMillis() + 5000;

                for (int i = 0; i < NBR_JOBS * 30; i++) {
                    if (System.currentTimeMillis() > msStartPlus5000) {
                        break;
                    }

                    while (5000 < nbrOnWorkerQueue.get()) {
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (System.currentTimeMillis() > msStartPlus5000) {
                        break;
                    }

                    MyApplication.executorService.submit(new Runnable() {

                        public int seq = nbrJobs.incrementAndGet();

                        @Override
                        public void run() {

                            try {
                                int t = nbrRun.incrementAndGet();
                                updateGrid(t);
                                nbrOnWorkerQueue.decrementAndGet();

                                //Log.i("menglog", "act updated textview " + randomInt + " #submit:" + seq + " #run:" + t);
                            } catch (Exception ex) {
                                Log.e("menglog", ex.toString());
                            }

                        }
                    });

                    nbrOnWorkerQueue.incrementAndGet();
                }

                int totalRun = nbrRun.get();

                Log.i("Manycore::log", "multiThreadTest out of the loop, totalRun:" + totalRun);
                Future<?> fut = MyApplication.executorService.submit(new Runnable() {
                    public void run() {
                        Log.i("Manycore::log", "multiThreadTest release");
                        sem.release();
                    }
                });

                try {
                    Log.i("Manycore::log", "multiThreadTest waitfor sem");
                    sem.acquire();
                } catch (InterruptedException e) {
                    Log.w("Manycore::log", "Interrupted sem");
                }
                long totalTimeMs = System.currentTimeMillis() - msStartPlus5000 - 5000L;

                Log.i("Manycore::log", "multiThreadTest time ms: " + totalTimeMs);

                ((Button) findViewById(R.id.button)).setText("All updates done: " + totalRun + " updates");
            }
        });
        thread.start(); // thread.run() blocks until done
        // ... and if I .run() main thread is looked up so no crash.

        Log.i("Manycore::log", "Thread started. isaAlive=" + thread.isAlive() + " nbrJobs:" + nbrJobs.get() + " nbrRun:" + nbrRun.get());
    }

    private void experimentsGUI() {
        Log.i("Manycore::log", "mainThreadTest ------------------------------------------");

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Log.i("Manycore::log", "mainThreadTest started root run()");

                long msStartPlus5000 = System.currentTimeMillis() + 5000;

                for (int i = 0; i < NBR_JOBS * 30; i++) {
                    if (System.currentTimeMillis() > msStartPlus5000) {
                        break;
                    }

                    while (5000 < nbrOnWorkerQueue.get()) {
                        try {
                            Thread.sleep(3);
                            Log.i("Manycore::debug", "ran zzz");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (System.currentTimeMillis() > msStartPlus5000) {
                        break;
                    }

                    MyApplication.executorService.submit(new Runnable() {
                        public int seq = nbrJobs.incrementAndGet();

                        @Override
                        public void run() {

                            try {
                                int t = nbrRun.incrementAndGet();

                                MainActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        updateGrid(t);
                                        nbrOnWorkerQueue.decrementAndGet();
                                    }
                                });

                            } catch (Exception ex) {
                                Log.e("menglog", ex.toString());
                            }
                        }
                    });

                    nbrOnWorkerQueue.incrementAndGet();
                }
                int totalRun = nbrRun.get();

                Log.i("Manycore::log", "mainThreadTest out of the loop, totalRun:" + totalRun);

                Future<?> fut = MyApplication.executorService.submit(new Runnable() {
                    public void run() {
                        Log.i("Manycore::log", "mainThreadTest release from ExecutorService");
                        sem.release();
                    }
                });

                try {
                    Log.i("Manycore::log", "mainThreadTest waitfor sem from ExecutorService");
                    sem.acquire();
                } catch (InterruptedException e) {
                    Log.w("Manycore::log", "Interrupted sem");
                }

                Log.i("Manycore::log", "mainThreadTest now we know all UI thread jobs have been created");

                Log.i("Manycore::log", "mainThreadTest out of the loop, now on to sem nbrJobs:" + nbrJobs.get() + " #run so far:" + nbrRun.get());
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.i("Manycore::log", "mainThreadTest release from main thread");
                        sem.release();
                    }
                });

                try {
                    Log.i("Manycore::log", "mainThreadTest waitfor sem from UI thread");
                    sem.acquire();
                } catch (InterruptedException e) {
                    Log.w("Manycore::log", "Interrupted sem");
                }
                long totalTimeMs = System.currentTimeMillis() - msStartPlus5000  - 5000;

                Log.i("Manycore::log", "mainThreadTest time ms: " + totalTimeMs);

                ((Button) findViewById(R.id.button)).setText("All updates done: " + totalRun + " updates");
            }
        });
        thread.start(); // thread.run() blocks until done
        // ... and if I .run() main thread is looked up so no crash.

        Log.i("Manycore::log", "Thread started. isaAlive=" + thread.isAlive() + " nbrJobs:" + nbrJobs.get() + " nbrRun:" + nbrRun.get());
    }


    void updateGrid(int iteration) {
        int randomInt = ThreadLocalRandom.current().nextInt(0, 40);
        try {
            Thread.sleep(0, 13);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int y=0; y<NBR_ROWS; y++) {
            for (int x=0; x<NBR_COLS; x++) {
                imageViewArray[x][y].getLayoutParams().width = 50 + (int) (20 * Math.sin((0.0 + iteration * 0.05 + 70 * y + 70 * x) / 90.));
                imageViewArray[x][y].getLayoutParams().height = 50 + (int) (20 * Math.sin((0.0 + iteration * 0.05 + 40 * y + 40 * x) / 90.));


                imageViewArray[x][y].requestLayout();
            }
        }
    }



    public void testStuffImage(ImageView iv) {
        int randomInt = ThreadLocalRandom.current().nextInt(0, 100);


//        WebView wv = (WebView) findViewById(R.id.webView);
//        wv.getLayoutParams().width = 800 + randomInt;
//        wv.requestLayout();


        //Log.i("Manycore::log", "w" + iv.getWidth() + " ri:" + randomInt);
        iv.getLayoutParams().width = 800 + randomInt;
        iv.getLayoutParams().height = 300 + randomInt;
        iv.requestLayout();
        try {
            Thread.sleep(0, 1 + randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void clickButtonCB(View view) {
        experimentsGUI();

    }
}
