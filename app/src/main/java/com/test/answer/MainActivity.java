package com.test.answer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] questions = new String[]{
            "疆域是由统治者给你划定的一条明确的界限，一定的空间范围，是否正确？",
            "'中国'两字最早出现在何尊中，是否正确？",
            "早期关于'中国'一词的使用，不是政治空间，而具有鲜明的文化区特征，是否正确？",
            "中国早期的文字是甲骨文，是否正确？",
            "中国古代的军乐是传递信息的，是否正确？",
    };
    private int[] answers = new int[]{1, 1, 1, 0, 1};
    // 问题、回答结果
    private TextView textQuestion, textRight;
    // 答案选择、题目操作
    private LinearLayout btnSelect, btnOpts, btnShow;
    //  正确、错误、 下一题、查看答案、答案、返回  按钮
    private Button btnSelectRight, btnSelectError, btnNext, btnShowAnswer, btnAnswer, btnCancel;
    private int current = 0;
    private boolean viewAnswer = false; // 是否查看了答案

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textQuestion = findViewById(R.id.text_question);
        textRight = findViewById(R.id.text_right);
        btnSelect = findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(this);//绑定点击
        btnOpts = findViewById(R.id.btn_opts);
        btnOpts.setOnClickListener(this);
        btnSelectRight = findViewById(R.id.btn_select_right);
        btnSelectRight.setOnClickListener(this);
        btnSelectError = findViewById(R.id.btn_select_error);
        btnSelectError.setOnClickListener(this);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        btnShowAnswer = findViewById(R.id.btn_show_answer);
        btnShowAnswer.setOnClickListener(this);
        btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(this);
        btnAnswer = findViewById(R.id.btn_answer);
        btnAnswer.setOnClickListener(this);
        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        textQuestion.setText(questions[current]);
    }

    @Override
    public void onClick(View v) {
        textRight.setText("");
        switch (v.getId()) {
            case R.id.btn_next:
                if (current >= 4) current = 0;
                else current++;
                textQuestion.setText(questions[current]);
                textRight.setText("");
                viewAnswer = false;
                break;
            case R.id.btn_select_right:
                showResult(answers[current] == 1);
                break;
            case R.id.btn_select_error:
                showResult(answers[current] == 0);
                break;
            case R.id.btn_show_answer:
                btnSelect.setVisibility(View.GONE);
                btnShowAnswer.setVisibility(View.GONE);
                btnOpts.setVisibility(View.GONE);
                btnShow.setVisibility(View.VISIBLE);
                //牛逼
                textQuestion.setText("确定要查看答案？");
                break;
            case R.id.btn_answer:
                btnSelect.setVisibility(View.GONE);
                btnShowAnswer.setVisibility(View.GONE);
                btnOpts.setVisibility(View.GONE);
                btnShow.setVisibility(View.VISIBLE);
                textQuestion.setText(answers[current] == 0 ? "错误" : "正确");
                viewAnswer = true;
                break;
            case R.id.btn_cancel:
                btnSelect.setVisibility(View.VISIBLE);
                btnShowAnswer.setVisibility(View.VISIBLE);
                btnOpts.setVisibility(View.VISIBLE);
                btnShow.setVisibility(View.GONE);
                textQuestion.setText(questions[current]);
                break;
        }
    }

    private void showResult(boolean isRight) {
        String successColor = "#39b54a";
        String errorColor = "#e54d42";
        if (viewAnswer)
            Toast.makeText(getBaseContext(), "作弊是不对的", Toast.LENGTH_SHORT).show();
        textRight.setText("回答" + (isRight ? "正确" : "错误"));
        textRight.setTextColor(Color.parseColor(isRight ? successColor : errorColor));
    }
}